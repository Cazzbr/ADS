import asyncio
import random
import time


class Formiga:
    MAX_PASSOS = 20

    def __init__(self, name, ambiente, formigas_ref):
        self.name = name
        self.ambiente = ambiente
        self.formigas_ref = formigas_ref  # referência à lista compartilhada de formigas
        self.inbox = asyncio.Queue()

        self.position = (0, 0)
        self.route = []
        self.known_food_path = None  # preenchido ao receber mensagem de sucesso de outra formiga

        print(f"{self.name} inicializada no formigueiro (0,0).")

    # Tarefa A: Movimentação e seleção de vizinhos
    def _vizinhos_validos(self):
        """
        Retorna vizinhos preferindo os que não aparecem como próximo passo
        em caminhos inválidos conhecidos (feromônio negativo - Tarefa B).
        """
        candidatos = self.ambiente.vizinhos(self.position)

        passos_ruins = set()
        for caminho in self.ambiente.caminhos_invalidos:
            if self.position in caminho:
                idx = caminho.index(self.position)
                if idx + 1 < len(caminho):
                    passos_ruins.add(caminho[idx + 1])

        bons = [v for v in candidatos if v not in passos_ruins]
        return bons if bons else candidatos  # fallback: todos os vizinhos

    # Processamento de inbox
    async def _processar_inbox(self):
        """Drena todas as mensagens pendentes sem bloquear."""
        while not self.inbox.empty():
            try:
                msg = self.inbox.get_nowait()
                tipo = msg['tipo']
                remetente = msg['de']

                if tipo == 'sucesso' and self.known_food_path is None:
                    # Tarefa C: recebe rota de sucesso e passa a segui-la
                    self.known_food_path = msg['rota']
                    print(f"[{self.name}] recebeu rota de sucesso de {remetente}: {msg['rota']}")

                elif tipo == 'fracasso':
                    # Tarefa B: registra feromônio negativo globalmente
                    rota = msg['rota']
                    if rota not in self.ambiente.caminhos_invalidos:
                        self.ambiente.caminhos_invalidos.append(rota)
                        print(f"[{self.name}] feromônio negativo registrado (de {remetente}, "
                              f"{len(rota)} passos).")

            except asyncio.QueueEmpty:
                break

    async def _broadcast(self, msg):
        """Envia mensagem para a inbox de todas as outras formigas ativas."""
        for formiga in self.formigas_ref:
            if formiga.name != self.name:
                await formiga.inbox.put(msg)

    # Corrotina principal (Tarefa A + loop de buscas)
    async def agir(self):
        """Loop principal: realiza tentativas de forrageamento continuamente."""
        while True:
            await self._forragear()
            await asyncio.sleep(0)  # cede controle antes de nova tentativa

    async def _forragear(self):
        """Uma tentativa completa de sair do formigueiro e buscar alimento."""
        self.position = (0, 0)
        self.route = [(0, 0)]
        print(f"\n[{self.name}] saindo do formigueiro para buscar alimento...")

        for passo in range(self.MAX_PASSOS):
            await self._processar_inbox()

            # Se conhece a rota de sucesso, segue ela diretamente (Tarefa C)
            if (self.known_food_path is not None and
                    passo + 1 < len(self.known_food_path)):
                next_pos = self.known_food_path[passo + 1]
                modo = "→ rota conhecida"
            else:
                next_pos = random.choice(self._vizinhos_validos())
                modo = "~ aleatório"

            self.position = next_pos
            self.route.append(next_pos)
            print(f"[{self.name}] passo {passo + 1:02d}: {self.position} ({modo})")

            # Verificação de alimento (Tarefa C)
            if self.ambiente.tem_comida(self.position):
                print(f"\n*** [{self.name}] ENCONTROU ALIMENTO em {self.position}! ***")
                await self._retornar_sucesso()
                return

            await asyncio.sleep(0.02)

        # Tarefa B: atingiu limite sem encontrar alimento
        print(f"[{self.name}] atingiu {self.MAX_PASSOS} passos sem alimento. Retornando...")
        await self._retornar_fracasso()

    # Tarefa B: Reforço negativo (fracasso)
    async def _retornar_fracasso(self):
        """Retorna à base, registra e propaga feromônio negativo."""
        rota = list(self.route)
        await asyncio.sleep(0.05)  # simula tempo de retorno
        self.position = (0, 0)

        # Registra localmente
        self.ambiente.caminhos_invalidos.append(rota)
        print(f"[{self.name}] caminho inválido registrado globalmente "
              f"(feromônio negativo, {len(rota)} nós).")

        # Propaga a restrição (análogo ao Nogood do ABT)
        await self._broadcast({
            'tipo': 'fracasso',
            'de': self.name,
            'rota': rota,
            'timestamp': time.time()
        })

    # Tarefa C: Comunicação de sucesso (broadcast)
    async def _retornar_sucesso(self):
        """Retorna à base e transmite a rota de sucesso a todas as formigas."""
        rota = list(self.route)
        await asyncio.sleep(0.05)  # simula tempo de retorno
        self.position = (0, 0)

        if self.ambiente.coletar_alimento():
            print(f"[{self.name}] entregou alimento! "
                  f"Total coletado: {self.ambiente.alimento_coletado}/{self.ambiente.capacidade_maxima}")

            # A formiga descobridora memoriza a própria rota (não recebe broadcast de si mesma)
            self.known_food_path = rota

            # Broadcast da rota de sucesso para acelerar convergência (LRTA(n)*)
            await self._broadcast({
                'tipo': 'sucesso',
                'de': self.name,
                'rota': rota,
                'timestamp': time.time()
            })
            print(f"[{self.name}] rota de sucesso comunicada ao enxame: {rota}")
