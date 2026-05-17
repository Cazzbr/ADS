"""
environment_feromonio.py – Ambiente da simulação orquestrado pela ontologia.

Todos os parâmetros (capacidade de alimento, posição do tamanduá) são
lidos da ontologia via OntologyManager. Os eventos de coleta e predação
são registrados de volta no grafo RDF.
"""


class Environment:
    def __init__(self, onto_manager, food_pos=(5, 5), tamandua_pos=None):
        self.onto = onto_manager
        self.food_pos = food_pos
        self.tamandua_pos = tamandua_pos
        # Capacidade lida da ontologia (ex:capacidadeMaxima)
        self.capacity = self.onto.get_capacidade_alimento()
        self.collected = 0
        self.pheromone = {}  # Maps pos -> next_pos

    def has_food(self, pos):
        return pos == self.food_pos and self.collected < self.capacity

    def collect_food(self):
        if self.collected < self.capacity:
            self.collected += 1
            # Registra coleta na ontologia
            self.onto.incrementar_coleta()
            return True
        return False

    def is_exhausted(self):
        return self.collected >= self.capacity

    def registrar_predacao(self):
        """Registra a predação no grafo RDF (ex:contadorPredadas)."""
        self.onto.incrementar_predacao()

    def mark_pheromone(self, path):
        # Remove loops from the individual ant's path to prevent global cycles
        clean_path = []
        seen = {}
        for pos in path:
            if pos in seen:
                clean_path = clean_path[:seen[pos]]
            clean_path.append(pos)
            seen[pos] = len(clean_path) - 1

        # path is a sequence of positions from base to food
        for i in range(len(clean_path) - 1):
            if clean_path[i] not in self.pheromone:
                self.pheromone[clean_path[i]] = clean_path[i + 1]
