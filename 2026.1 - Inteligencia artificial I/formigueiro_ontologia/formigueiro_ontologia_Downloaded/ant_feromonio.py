"""
ant_feromonio.py – Agente Formiga orquestrado pela ontologia formigueiro.ttl.

Toda a movimentação e parâmetros de decisão (limite de passos, regra de
ignorar limite de energia ao seguir feromônio positivo) são consultados
na ontologia via OntologyManager. Cada mudança de posição é registrada
no grafo RDF (ex:posicionadoEm). Rotas e feromônios são persistidos
como instâncias no grafo.
"""

import asyncio
import random


class Ant:
    def __init__(self, name, env, all_ants_inboxes, color, onto_manager, nome_onto):
        self.name = name
        self.env = env
        self.color = color
        self.pos = (0, 0)
        self.history = [(0, 0)]
        self.all_paths = []  # Persistent history of all trips
        self.inbox = all_ants_inboxes[name]
        self.others = all_ants_inboxes
        self.onto = onto_manager
        self.nome_onto = nome_onto  # Ex: "Formiga01"

        self.failed_routes = set()
        self.success_route = None
        self.state = "EXPLORING"
        self.collected_food = False
        self.pause_event = None
        self.total_steps_taken = 0

        # ── Consulta os parâmetros na ontologia ──
        self.energy_limit = self.onto.get_limite_passos(self.nome_onto)
        self.ignora_limite_energia = self.onto.get_ignora_limite_energia(self.nome_onto)

        print(f"[{self.name}] Limite de passos: {self.energy_limit} | "
              f"Ignora limite c/ feromônio: {self.ignora_limite_energia}")

    # -----------------------------------------------------------------
    # Helpers
    # -----------------------------------------------------------------

    def _esta_seguindo_feromonio_positivo(self) -> bool:
        """Retorna True se a formiga está em modo EXPLOITING (seguindo feromônio)."""
        return self.state == "EXPLOITING"

    def _verificar_limite_passos(self) -> bool:
        """Retorna True se o limite de passos foi excedido (respeitando a regra da ontologia)."""
        if self.ignora_limite_energia and self._esta_seguindo_feromonio_positivo():
            return False  # Regra: ignora o limite enquanto segue feromônio positivo
        return len(self.history) > self.energy_limit

    def check_predator(self):
        if self.env.tamandua_pos and self.pos == self.env.tamandua_pos:
            self.state = "DEAD"
            self.env.registrar_predacao()
            # Registra na ontologia
            self.onto.incrementar_predacao()
            print(f"[{self.name}] FOI DEVORADA PELO TAMANDUÁ em {self.pos}!")
            return True
        return False

    def _registrar_posicao(self):
        """Atualiza ex:posicionadoEm da formiga no grafo RDF."""
        self.onto.atualizar_posicao_formiga(self.nome_onto, self.pos)

    # -----------------------------------------------------------------
    # Loop principal
    # -----------------------------------------------------------------

    async def agir(self):
        while True:
            if self.pause_event:
                await self.pause_event.wait()

            if self.state == "DEAD" or self.state == "FINISHED":
                await asyncio.sleep(0.1)
                continue

            # Checa se o alimento esgotou
            if self.env.is_exhausted():
                if self.pos != (0, 0):
                    await self.retornar_ao_formigueiro("EXHAUSTED")
                else:
                    self.state = "FINISHED"
                continue

            # Processa mensagens recebidas
            while not self.inbox.empty():
                try:
                    msg = self.inbox.get_nowait()
                    msg_type, content = msg
                    if msg_type == "SUCCESS" and not self.success_route:
                        self.success_route = content
                        self.state = "EXPLOITING"
                        print(f"[{self.name}] Recebeu rota de sucesso via broadcast!")
                    elif msg_type == "FAILURE":
                        self.failed_routes.add(content)
                except asyncio.QueueEmpty:
                    break

            # Decide a ação com base no estado
            if self.state == "EXPLORING":
                await self.explorar()
            elif self.state == "RETURNING_SUCCESS":
                await self.retornar_ao_formigueiro("SUCCESS")
            elif self.state == "RETURNING_FAILURE":
                await self.retornar_ao_formigueiro("FAILURE")
            elif self.state == "EXPLOITING":
                await self.seguir_rota_sucesso()

            await asyncio.sleep(0.05)

    # -----------------------------------------------------------------
    # Exploração
    # -----------------------------------------------------------------

    async def explorar(self):
        # Checa feromônio no ambiente
        if self.pos in self.env.pheromone:
            curr = self.pos
            path_to_food = [curr]
            seen_trace = {curr}
            while curr in self.env.pheromone:
                curr = self.env.pheromone[curr]
                if curr in seen_trace:
                    print(f"[{self.name}] ALERTA: Ciclo detectado na trilha de feromônio!")
                    break
                seen_trace.add(curr)
                path_to_food.append(curr)
            self.success_route = tuple(path_to_food)
            self.state = "EXPLOITING"
            print(f"[{self.name}] Encontrou feromônio em {self.pos}! Seguindo trilha.")
            return

        # Encontrou alimento
        if self.env.has_food(self.pos):
            self.state = "RETURNING_SUCCESS"
            return

        # ── Consulta à ontologia: verifica limite de passos ──
        if self._verificar_limite_passos():
            self.state = "RETURNING_FAILURE"
            return

        # Movimento aleatório
        x, y = self.pos
        possible_moves = [(x + 1, y), (x - 1, y), (x, y + 1), (x, y - 1)]
        opcoes = []
        for move in possible_moves:
            # Evita voltar imediatamente
            if move == (self.history[-2] if len(self.history) > 1 else None):
                continue

            nova_rota = tuple(self.history + [move])

            # Evita repetir exatamente uma rota que já sabemos que falhou completamente
            if nova_rota in self.failed_routes:
                continue

            opcoes.append(move)

        if not opcoes:
            self.state = "RETURNING_FAILURE"
            return

        self.pos = random.choice(opcoes)
        self.history.append(self.pos)
        self.total_steps_taken += 1
        # Registra nova posição na ontologia
        self._registrar_posicao()
        self.check_predator()

    # -----------------------------------------------------------------
    # Retorno ao formigueiro
    # -----------------------------------------------------------------

    async def retornar_ao_formigueiro(self, motivo):
        rota_final = tuple(self.history)
        self.all_paths.append(list(self.history))

        if motivo == "FAILURE":
            await self.broadcast("FAILURE", rota_final)
            self.failed_routes.add(rota_final)
            # Registra rota de falha na ontologia
            self.onto.registrar_rota(self.nome_onto, list(rota_final), sucesso=False)
        elif motivo == "SUCCESS":
            self.env.mark_pheromone(rota_final)
            self.success_route = rota_final
            if self.env.collect_food():
                self.collected_food = True
                print(f"[{self.name}] Coletou e encerrou.")
            # Registra rota de sucesso na ontologia
            rota_uri = self.onto.registrar_rota(self.nome_onto, list(rota_final), sucesso=True)
            # Registra feromônios na ontologia para cada segmento da rota
            for i in range(len(rota_final) - 1):
                self.onto.registrar_feromonio(self.nome_onto, rota_final[i], rota_final[i + 1], "positivo")
            await self.broadcast("SUCCESS", rota_final)

        # Animação do retorno (distância Manhattan até (0,0))
        x, y = self.pos
        while x != 0 or y != 0:
            if self.pause_event:
                await self.pause_event.wait()
            if x > 0:
                x -= 1
            elif x < 0:
                x += 1
            elif y > 0:
                y -= 1
            elif y < 0:
                y += 1
            self.pos = (x, y)
            self.history.append(self.pos)
            self.total_steps_taken += 1
            self._registrar_posicao()
            if self.check_predator():
                return
            await asyncio.sleep(0.02)

        self.pos = (0, 0)
        self.history = [(0, 0)]
        self._registrar_posicao()
        if not self.collected_food:
            self.state = "EXPLOITING" if self.success_route else "EXPLORING"
        else:
            self.state = "FINISHED"

    # -----------------------------------------------------------------
    # Explotação (seguir rota conhecida)
    # -----------------------------------------------------------------

    async def seguir_rota_sucesso(self):
        if self.collected_food:
            return

        path_to_follow = list(self.success_route)
        for p in path_to_follow[1:]:
            if self.pause_event:
                await self.pause_event.wait()
            self.pos = p
            self.history.append(p)
            self.total_steps_taken += 1
            self._registrar_posicao()
            if self.check_predator():
                return
            await asyncio.sleep(0.02)

        if self.env.collect_food():
            self.collected_food = True
            self.all_paths.append(list(self.history))
            # Registra rota de coleta na ontologia
            self.onto.registrar_rota(self.nome_onto, list(self.history), sucesso=True)
            print(f"[{self.name}] Coletou via rota direta e encerrou. (Total base: {self.env.collected})")
            self.pos = (0, 0)
            self.history = [(0, 0)]
            self._registrar_posicao()
            self.state = "FINISHED"
        else:
            print(f"[{self.name}] Rota direta falhou, alimento esgotado.")
            self.all_paths.append(list(self.history))
            self.pos = (0, 0)
            self.history = [(0, 0)]
            self._registrar_posicao()
            self.state = "FINISHED"

    # -----------------------------------------------------------------
    # Comunicação
    # -----------------------------------------------------------------

    async def broadcast(self, msg_type, content):
        for name, queue in self.others.items():
            if name != self.name:
                await queue.put((msg_type, content))
