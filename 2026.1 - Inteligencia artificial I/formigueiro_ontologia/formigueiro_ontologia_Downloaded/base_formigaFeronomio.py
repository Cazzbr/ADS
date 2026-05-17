"""
base_formigaFeronomio.py – Agente FormigaFeronomio orquestrado pela
ontologia formigueiro.ttl.

Toda a movimentação e parâmetros de decisão (limite de passos, regra de
ignorar limite de energia ao seguir feromônio positivo) são consultados
na ontologia via OntologyManager. Cada mudança de posição é registrada
no grafo RDF (ex:posicionadoEm). Rotas e feromônios são persistidos
como instâncias no grafo.
"""

import asyncio
import random

from ambiente_formigueiro import AmbienteFormigueiro


class FormigaFeronomio:
    ALIMENTO = (5, 10)
    TOTAL_COLETADO = 0
    LIMITE_ALIMENTO = 4
    AMBIENTE = AmbienteFormigueiro(limite_alimento=LIMITE_ALIMENTO)
    _ALIMENTO_VINCULADO = None
    _TICKS = 0
    ROTA_VENCEDORA = None  # Primeiro caminho de sucesso — compartilhado por todas as formigas
    ONTO = None  # OntologyManager — atribuído pelo executor antes de criar as formigas

    def __init__(self, nome, vizinhos_nomes):
        self.nome = nome
        self.posicao = (0, 0)        # Formigueiro é a origem
        self.caminho = [(0, 0)]      # Histórico da rota atual
        self.inbox = asyncio.Queue() # Fila de mensagens assíncronas
        self.network = {}            # Conexões com as outras formigas
        self.vizinhos_nomes = vizinhos_nomes
        self.controle_execucao = None # Evento opcional para pausar/retomar

        # Memória do agente
        self.rotas_proibidas = set() # Feromônio negativo (Nogoods)
        self.rota_sucesso = None     # Feromônio positivo (Caminho para o alimento)
        self.alimento_trazido = 0    # Contador individual de sucesso
        self.retornando = False      # True enquanto caminha de volta ao formigueiro
        self.caminho_retorno = []    # Passos restantes do retorno (em ordem reversa)
        self.eliminada = False       # True quando a formiga encontra o tamandua

        # ── Consulta os parâmetros na ontologia ──
        self.nome_onto = self._resolver_nome_onto()
        if FormigaFeronomio.ONTO is not None:
            self.limite_passos = FormigaFeronomio.ONTO.get_limite_passos(self.nome_onto)
            self.ignora_limite_energia = FormigaFeronomio.ONTO.get_ignora_limite_energia(self.nome_onto)
            print(f"[{self.nome}] Ontologia → limitePassos={self.limite_passos}, "
                  f"ignoraLimiteEnergia={self.ignora_limite_energia}")
        else:
            self.limite_passos = 20  # fallback
            self.ignora_limite_energia = False

    # -----------------------------------------------------------------
    # Helpers para mapear nome do agente ao indivíduo da ontologia
    # -----------------------------------------------------------------

    def _resolver_nome_onto(self) -> str:
        """Converte o nome de exibição (F1, F2, …) no nome da ontologia (Formiga01, Formiga02, …)."""
        # O executor cria formigas com nomes como "F1", "F2", etc.
        num_str = self.nome.replace("F", "")
        try:
            num = int(num_str)
            return f"Formiga{num:02d}"  # "F1" → "Formiga01"
        except ValueError:
            return self.nome  # fallback: usa o nome bruto

    def _registrar_posicao(self):
        """Atualiza ex:posicionadoEm da formiga no grafo RDF."""
        if FormigaFeronomio.ONTO is not None:
            FormigaFeronomio.ONTO.atualizar_posicao_formiga(self.nome_onto, self.posicao)

    def _esta_seguindo_feromonio_positivo(self) -> bool:
        """Retorna True se a formiga está seguindo feromônio positivo (rota fixa ou gradiente)."""
        return (
            self.rota_sucesso is not None
            or (FormigaFeronomio.ROTA_VENCEDORA is not None
                and self.posicao in FormigaFeronomio.ROTA_VENCEDORA)
            or FormigaFeronomio.AMBIENTE.valor_feromonio(self.posicao) > 0
        )

    def _verificar_limite_passos(self) -> bool:
        """
        Retorna True se o limite de passos foi excedido.
        Regra da ontologia (ex:ignoraLimiteEnergia): quando True, a formiga
        ignora o limite enquanto estiver seguindo feromônio positivo.
        """
        if self.ignora_limite_energia and self._esta_seguindo_feromonio_positivo():
            return False  # Ignora o limite de passos
        return len(self.caminho) > self.limite_passos

    @classmethod
    def _sincronizar_ambiente(cls):
        precisa_reset = (
            cls._ALIMENTO_VINCULADO != cls.ALIMENTO
            or (cls.TOTAL_COLETADO == 0 and cls.AMBIENTE.total_coletado != 0)
        )
        if precisa_reset:
            cls.AMBIENTE.reset(alimento_pos=cls.ALIMENTO)
            cls._ALIMENTO_VINCULADO = cls.ALIMENTO
            cls.TOTAL_COLETADO = 0
            cls._TICKS = 0
            cls.ROTA_VENCEDORA = None

    async def agir(self):
        """Corrotina principal que dita o ciclo de vida da FormigaFeronomio."""
        while True:
            FormigaFeronomio._sincronizar_ambiente()

            if self.controle_execucao is not None:
                await self.controle_execucao.wait()

            # 0. Retorno animado: processa um passo de volta ao formigueiro por tick.
            if self.retornando:
                if self.caminho_retorno:
                    self.posicao = self.caminho_retorno.pop()
                    # Atualiza o caminho visual para a trilha encolher durante o retorno.
                    self.caminho = list(self.caminho_retorno) + [self.posicao]
                    # Registra posição na ontologia
                    self._registrar_posicao()
                else:
                    # Chegou ao formigueiro — reinicia estado de busca.
                    self.posicao = (0, 0)
                    self.caminho = [(0, 0)]
                    self.retornando = False
                    self._registrar_posicao()
                await asyncio.sleep(0.08)
                continue

            # Após coletar 1 alimento, a formiga permanece no formigueiro.
            if self.alimento_trazido >= 1:
                self.posicao = (0, 0)
                self.caminho = [(0, 0)]
                await asyncio.sleep(0.08)
                continue

            # 1. Processar mensagens recebidas (escuta passiva)
            while not self.inbox.empty():
                mensagem = await self.inbox.get()
                tipo, conteudo = mensagem

                # Tarefa C: Comunicação de Sucesso
                if tipo == "SUCESSO" and not self.rota_sucesso:
                    self.rota_sucesso = conteudo
                    print(f"[{self.nome}] Recebeu rota de sucesso! Mudando para explotação.")

                # Tarefa B: Atualização de rotas falhas (Nogoods)
                elif tipo == "FALHA":
                    self.rotas_proibidas.add(conteudo)

            # 2. Tomada de decisão:
            # Se a rota vencedora existe e a formiga está sobre ela → segue deterministicamente.
            # Senão, se há feromônio no ambiente → segue gradiente.
            # Senão → explora aleatoriamente.
            if FormigaFeronomio.ROTA_VENCEDORA and self.posicao in FormigaFeronomio.ROTA_VENCEDORA:
                await self.seguir_rota_fixa()
            elif FormigaFeronomio.AMBIENTE.feromonio:
                await self.seguir_rota_sucesso()
            else:
                await self.explorar()

            FormigaFeronomio._TICKS += 1
            if FormigaFeronomio._TICKS % 20 == 0:
                FormigaFeronomio.AMBIENTE.evaporar()

            # Cede o controle ao event loop do asyncio
            await asyncio.sleep(0.05)

    async def explorar(self):
        """Tarefa A: Busca aleatória com limite de energia (consultado na ontologia)."""
        ALIMENTO = FormigaFeronomio.AMBIENTE.alimento_pos

        if FormigaFeronomio.AMBIENTE.alimento_esgotado():
            await self.retornar_ao_formigueiro()
            return

        # Sucesso: encontrou o alimento
        if self.posicao == ALIMENTO:
            print(f"[{self.nome}] ACHOU O ALIMENTO no passo {len(self.caminho)-1}!")
            rota_vencedora = tuple(self.caminho)
            # Primeira formiga a chegar estabelece a rota vencedora global.
            if FormigaFeronomio.ROTA_VENCEDORA is None:
                FormigaFeronomio.ROTA_VENCEDORA = rota_vencedora
                FormigaFeronomio.AMBIENTE.fixar_trilha(rota_vencedora)  # Feromônio dessa trilha não evapora
                print(f"[{self.nome}] Rota vencedora estabelecida com {len(rota_vencedora)} passos.")
            if self.alimento_trazido < 1 and FormigaFeronomio.AMBIENTE.registrar_coleta():
                self.alimento_trazido = 1
                FormigaFeronomio.TOTAL_COLETADO = FormigaFeronomio.AMBIENTE.total_coletado
                FormigaFeronomio.AMBIENTE.depositar_trilha(rota_vencedora, intensidade=2.5)
                # ── Registra coleta na ontologia ──
                if FormigaFeronomio.ONTO is not None:
                    FormigaFeronomio.ONTO.incrementar_coleta()
            # ── Registra rota de sucesso na ontologia ──
            if FormigaFeronomio.ONTO is not None:
                FormigaFeronomio.ONTO.registrar_rota(self.nome_onto, list(rota_vencedora), sucesso=True)
                # Registra feromônios para cada segmento da rota
                for i in range(len(rota_vencedora) - 1):
                    FormigaFeronomio.ONTO.registrar_feromonio(
                        self.nome_onto, rota_vencedora[i], rota_vencedora[i + 1], "positivo"
                    )
            self.rota_sucesso = rota_vencedora
            await self.retornar_ao_formigueiro()
            return

        # ── Consulta à ontologia: verifica limite de passos ──
        if self._verificar_limite_passos():
            print(f"[{self.nome}] Atingiu {self.limite_passos} passos sem sucesso. Marcando falha...")
            rota_falha = tuple(self.caminho)
            FormigaFeronomio.AMBIENTE.depositar_trilha(rota_falha, intensidade=-0.8)
            # ── Registra rota de falha na ontologia ──
            if FormigaFeronomio.ONTO is not None:
                FormigaFeronomio.ONTO.registrar_rota(self.nome_onto, list(rota_falha), sucesso=False)
                # Feromônio negativo (nogood)
                FormigaFeronomio.ONTO.registrar_feromonio(
                    self.nome_onto, self.posicao, None, "nogood"
                )
            await self.retornar_ao_formigueiro()
            return

        opcoes = self._opcoes_movimento(evitar_ciclos=True)

        # Beco sem saída (todos os caminhos à frente são falhos ou já visitados)
        if not opcoes:
            rota_falha = tuple(self.caminho)
            FormigaFeronomio.AMBIENTE.depositar_trilha(rota_falha, intensidade=-0.6)
            if FormigaFeronomio.ONTO is not None:
                FormigaFeronomio.ONTO.registrar_rota(self.nome_onto, list(rota_falha), sucesso=False)
            await self.retornar_ao_formigueiro()
            return

        # Comunicação indireta: escolhas guiadas pelo gradiente de feromonio.
        if random.random() < 0.75:
            self.posicao = max(
                opcoes,
                key=lambda p: FormigaFeronomio.AMBIENTE.valor_feromonio(p) + random.uniform(0.0, 0.05),
            )
        else:
            self.posicao = random.choice(opcoes)

        self.caminho.append(self.posicao)
        # ── Registra nova posição na ontologia ──
        self._registrar_posicao()

    async def seguir_rota_fixa(self):
        """Segue a rota vencedora global deterministicamente, passo a passo."""
        await asyncio.sleep(0.1)

        if FormigaFeronomio.AMBIENTE.alimento_esgotado():
            await self.retornar_ao_formigueiro()
            return

        if self.posicao == FormigaFeronomio.AMBIENTE.alimento_pos:
            if self.alimento_trazido < 1 and FormigaFeronomio.AMBIENTE.registrar_coleta():
                self.alimento_trazido = 1
                FormigaFeronomio.TOTAL_COLETADO = FormigaFeronomio.AMBIENTE.total_coletado
                FormigaFeronomio.AMBIENTE.depositar_trilha(tuple(self.caminho), intensidade=2.0)
                print(f"[{self.nome}] Coletou seguindo a rota vencedora.")
                # ── Registra coleta na ontologia ──
                if FormigaFeronomio.ONTO is not None:
                    FormigaFeronomio.ONTO.incrementar_coleta()
                    FormigaFeronomio.ONTO.registrar_rota(
                        self.nome_onto, list(self.caminho), sucesso=True
                    )
            self.rota_sucesso = FormigaFeronomio.ROTA_VENCEDORA
            await self.retornar_ao_formigueiro()
            return

        try:
            idx = FormigaFeronomio.ROTA_VENCEDORA.index(self.posicao)
        except ValueError:
            # A formiga saiu da rota; volta a explorar livremente.
            await self.explorar()
            return

        if idx >= len(FormigaFeronomio.ROTA_VENCEDORA) - 1:
            await self.retornar_ao_formigueiro()
            return

        proximo = FormigaFeronomio.ROTA_VENCEDORA[idx + 1]
        self.posicao = proximo
        self.caminho.append(self.posicao)
        # ── Registra nova posição na ontologia ──
        self._registrar_posicao()

    async def seguir_rota_sucesso(self):
        """Segue o gradiente de feromonio ate o alimento, passo a passo."""
        await asyncio.sleep(0.1)

        if FormigaFeronomio.AMBIENTE.alimento_esgotado():
            await self.retornar_ao_formigueiro()
            return

        # Se chegou ao alimento, coleta (respeitando limites) e reforca trilha.
        if self.posicao == FormigaFeronomio.AMBIENTE.alimento_pos:
            rota_atual = tuple(self.caminho)
            # Primeira a chegar por gradiente também estabelece a rota vencedora.
            if FormigaFeronomio.ROTA_VENCEDORA is None:
                FormigaFeronomio.ROTA_VENCEDORA = rota_atual
                FormigaFeronomio.AMBIENTE.fixar_trilha(rota_atual)
                print(f"[{self.nome}] Rota vencedora (gradiente) estabelecida com {len(rota_atual)} passos.")
            if self.alimento_trazido < 1 and FormigaFeronomio.AMBIENTE.registrar_coleta():
                self.alimento_trazido = 1
                FormigaFeronomio.TOTAL_COLETADO = FormigaFeronomio.AMBIENTE.total_coletado
                FormigaFeronomio.AMBIENTE.depositar_trilha(rota_atual, intensidade=2.0)
                print(f"[{self.nome}] Coletou seguindo trilha de feromonio. (Total dela: {self.alimento_trazido})")
                # ── Registra coleta na ontologia ──
                if FormigaFeronomio.ONTO is not None:
                    FormigaFeronomio.ONTO.incrementar_coleta()
                    FormigaFeronomio.ONTO.registrar_rota(
                        self.nome_onto, list(rota_atual), sucesso=True
                    )
                    for i in range(len(rota_atual) - 1):
                        FormigaFeronomio.ONTO.registrar_feromonio(
                            self.nome_onto, rota_atual[i], rota_atual[i + 1], "positivo"
                        )
            self.rota_sucesso = FormigaFeronomio.ROTA_VENCEDORA
            await self.retornar_ao_formigueiro()
            return

        # Evita ciclos ao seguir gradiente para não ficar preso em loop.
        opcoes = self._opcoes_movimento(evitar_ciclos=True)
        if not opcoes:
            await self.retornar_ao_formigueiro()
            return

        # Evita o "vai-e-volta" imediato quando há outra opção viável.
        if len(self.caminho) >= 2:
            pos_anterior = self.caminho[-2]
            opcoes_sem_retorno = [p for p in opcoes if p != pos_anterior]
            if opcoes_sem_retorno:
                opcoes = opcoes_sem_retorno

        # Explotacao: prioriza o maior feromonio com pequeno ruido para desempate.
        self.posicao = max(
            opcoes,
            key=lambda p: FormigaFeronomio.AMBIENTE.valor_feromonio(p) + random.uniform(0.0, 0.02),
        )
        self.caminho.append(self.posicao)
        # ── Registra nova posição na ontologia ──
        self._registrar_posicao()

    def _opcoes_movimento(self, evitar_ciclos):
        x, y = self.posicao
        vizinhos = [
            (x + 1, y),
            (x - 1, y),
            (x, y + 1),
            (x, y - 1),
        ]

        opcoes = []
        for v in vizinhos:
            if not (-10 <= v[0] <= 10 and -10 <= v[1] <= 10):
                continue

            if evitar_ciclos and v in self.caminho:
                continue

            tentativa_rota = tuple(self.caminho + [v])
            if tentativa_rota not in self.rotas_proibidas:
                opcoes.append(v)

        return opcoes

    async def retornar_ao_formigueiro(self):
        """Inicia o retorno animado pela menor rota (distância Manhattan) até a origem."""
        x, y = self.posicao
        passos = []

        # Constrói o caminho mínimo do ponto atual até (0, 0).
        while x != 0 or y != 0:
            if x > 0:
                x -= 1
            elif x < 0:
                x += 1
            elif y > 0:
                y -= 1
            else:
                y += 1
            passos.append((x, y))

        # O loop de retorno usa pop(), então armazenamos em ordem inversa.
        self.caminho_retorno = list(reversed(passos))
        self.retornando = True

    async def fazer_broadcast(self, tipo, conteudo):
        """Mantido por compatibilidade com o executor antigo."""
        return
