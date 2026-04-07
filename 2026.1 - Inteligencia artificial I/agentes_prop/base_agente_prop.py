"""
Classe base compartilhada entre os agentes autonomos.
Cada agente importa esta classe e cria sua propria instancia no seu arquivo.
"""
import asyncio
import random


class Agent:
    def __init__(self, name, neighbors, domain):
        self.name = name
        self.neighbors = neighbors
        self.domain = domain
        self.color = random.choice(domain)
        self.views = {}
        self.inbox = asyncio.Queue()
        self.network = {}
        print(f"{self.name} criado com cor inicial {self.color}")

    def connect(self, network):
        """Recebe o dicionario de filas de todos os agentes da rede."""
        self.network = network

    async def send_state(self, target_name):
        await asyncio.sleep(random.uniform(0.03, 0.18))
        await self.network[target_name].put(
            {
                "kind": "state",
                "sender": self.name,
                "color": self.color,
            }
        )

    async def broadcast_state(self):
        await asyncio.gather(*(self.send_state(n) for n in self.neighbors if self.name < n))

    def resolve_conflict(self):
        conflicting = [
            n for n, c in self.views.items()
            if n in self.neighbors and c == self.color
        ]
        if not conflicting:
            return False

        # Desempate local: so o agente com nome maior troca de cor.
        if self.name == min([self.name] + conflicting):
            return False

        blocked = [c for n, c in self.views.items() if n in self.neighbors]
        options = [c for c in self.domain if c not in blocked]
        if not options:
            return False

        old = self.color
        self.color = random.choice(options)
        print(f"{self.name} resolveu conflito: {old} -> {self.color}")
        return True

    async def run(self, stop_event):
        await self.broadcast_state()
        while not stop_event.is_set():
            try:
                msg = await asyncio.wait_for(self.inbox.get(), timeout=0.5)
            except asyncio.TimeoutError:
                await self.broadcast_state()
                continue

            if msg.get("kind") != "state":
                continue

            self.views[msg["sender"]] = msg["color"]
            if self.resolve_conflict():
                await self.broadcast_state()
