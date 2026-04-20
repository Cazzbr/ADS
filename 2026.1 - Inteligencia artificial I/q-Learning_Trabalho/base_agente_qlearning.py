import asyncio
import random
import time


class QLearningAgent:
    def __init__(self, name, neighbors, domain, prioridades):
        self.name = name
        self.neighbors = neighbors
        self.domain = domain
        self.prioridades = prioridades
        self.prioridade = prioridades[name]
        self.appointment = random.choice(self.domain)
        self.inbox = asyncio.Queue()
        self.network = {}
        
        # Visão local para calcular a recompensa (o "estado" do ambiente)
        self.agent_view = {vizinho: None for vizinho in self.neighbors}
        
        # 1. SETUP DO Q-LEARNING:
        # Tabela Q inicializada em 0.0 para todas as cores do domínio
        self.q_table = {appointment: 0.0 for appointment in self.domain}
        self.alpha = 0.1   # Taxa de aprendizado (Learning Rate)
        self.epsilon = 0.2 # Taxa de exploração (Epsilon-greedy)
        
        print(f"{self.name} inicia com a cor {self.appointment} (prioridade={self.prioridade}).")

    def _conflitos_com_mesmo_horario(self):
        """Retorna vizinhos conhecidos que estao com a mesma cor deste agente."""
        return [
            v for v, appointment in self.agent_view.items()
            if appointment is not None and appointment == self.appointment
        ]

    def _ha_superior_em_conflito(self, vizinhos_em_conflito):
        """True quando existe vizinho com maior prioridade (numero menor) em conflito."""
        return any(self.prioridades[v] < self.prioridade for v in vizinhos_em_conflito)

    def _horarios_bloqueados_por_superiores(self):
        """Horários usados por vizinhos de maior prioridade; devem ser evitados."""
        return {
            appointment for v, appointment in self.agent_view.items()
            if appointment is not None and self.prioridades[v] < self.prioridade
        }

    async def agir(self):
        """
        Corrotina principal. Processa mensagens, calcula recompensas,
        atualiza a Tabela Q e escolhe novos horários usando epsilon-greedy.
        """
        while True:
            # 1. Recebe a mensagem do vizinho
            mensagem = await self.inbox.get()
            nome_vizinho = mensagem['from']
            appointment_vizinho = mensagem['slot_escolhido']
            self.agent_view[nome_vizinho] = appointment_vizinho

            # 2. Determina conflitos e próxima ação
            vizinhos_em_conflito = self._conflitos_com_mesmo_horario()
            ha_superior = self._ha_superior_em_conflito(vizinhos_em_conflito)

            novo_horario = self.escolher_horario()
            if vizinhos_em_conflito and ha_superior:
                while novo_horario == self.appointment:
                    novo_horario = self.escolher_horario()

            # 3. Calcula recompensa composta
            recompensa = 0.0
            if not vizinhos_em_conflito:
                recompensa += 10.0   # slot único, sem conflito
            else:
                recompensa -= 20.0   # conflito direto

            if novo_horario != self.appointment:
                recompensa -= 2.0    # penalidade por mudança de horário
                if ha_superior:
                    recompensa += 5.0  # bônus por respeitar prioridade

            # 4. Atualiza o valor Q para o horário atual e decai epsilon
            valor_antigo = self.q_table[self.appointment]
            self.q_table[self.appointment] = (1 - self.alpha) * valor_antigo + self.alpha * recompensa
            self.epsilon = max(0.01, self.epsilon * 0.99)

            # 5. Se mudou de horário, propaga a nova escolha na rede
            if novo_horario != self.appointment:
                self.appointment = novo_horario
                print(f"[{self.name}] atualizou Q({self.appointment}) e mudou horário (Reward: {recompensa}).")
                await self.propagar_horario()

    def escolher_horario(self):
        """
        Política de seleção de ação (Epsilon-Greedy).
        """
        bloqueadas = self._horarios_bloqueados_por_superiores()
        candidatas = [c for c in self.domain if c not in bloqueadas]
        if not candidatas:
            candidatas = list(self.domain)

        # Exploração (Exploration): escolhe uma cor aleatória
        if random.random() < self.epsilon:
            return random.choice(candidatas)
        
        # Explotação (Exploitation): escolhe a cor com o maior valor Q na tabela
        melhor_cor = max(candidatas, key=lambda cor: self.q_table[cor])
        return melhor_cor

    async def propagar_horario(self):
        """ Envia a cor atual para a fila de todos os vizinhos conectados """
        for vizinho in self.neighbors:
            if vizinho in self.network:
                mensagem = {
                    'from': self.name,
                    'slot_escolhido': self.appointment,
                    'prioridade': self.prioridade,
                    'timestamp': time.time()
                }
                await self.network[vizinho].put(mensagem)