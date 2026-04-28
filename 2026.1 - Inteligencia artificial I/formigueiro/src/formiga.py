import random
from base_formiga import Formiga


class Ambiente:
    """
    Representa o espaço de busca (grade NxN) e o estado global do alimento.
    Mantém a lista de caminhos inválidos (feromônio negativo) compartilhada
    entre todas as formigas.
    """

    def __init__(self, grid_size=10, pos_alimento=None):
        self.grid_size = grid_size

        if pos_alimento is None:
            # Posiciona alimento aleatoriamente, garantindo distância mínima de 3
            # e máxima de 12 passos (Manhattan) para a simulação ser desafiante mas viável
            while True:
                fx = random.randint(0, grid_size - 1)
                fy = random.randint(0, grid_size - 1)
                dist = abs(fx) + abs(fy)
                if (fx, fy) != (0, 0) and 3 <= dist <= 12:
                    pos_alimento = (fx, fy)
                    break

        self.pos_alimento = pos_alimento
        self.alimento_coletado = 0
        self.capacidade_maxima = 4          # esgota após 4 coletas (Tarefa D)
        self.caminhos_invalidos = []        # feromônio negativo global (Tarefa B)

        print(f"Ambiente criado: grade {grid_size}x{grid_size}")
        print(f"[SEGREDO DO AMBIENTE] Alimento posicionado em: {self.pos_alimento} "
              f"(distância Manhattan: {abs(pos_alimento[0]) + abs(pos_alimento[1])} passos)")

    def vizinhos(self, pos):
        """Retorna as coordenadas adjacentes válidas (4-conectividade)."""
        x, y = pos
        candidatos = [(x + 1, y), (x - 1, y), (x, y + 1), (x, y - 1)]
        return [(nx, ny) for nx, ny in candidatos
                if 0 <= nx < self.grid_size and 0 <= ny < self.grid_size]

    def tem_comida(self, pos):
        """Verifica se há alimento disponível na posição dada."""
        return pos == self.pos_alimento and self.alimento_coletado < self.capacidade_maxima

    def coletar_alimento(self):
        """
        Registra uma coleta bem-sucedida. Retorna True se ainda havia recurso disponível.
        Seguro em asyncio pois é código síncrono (sem await interno).
        """
        if self.alimento_coletado < self.capacidade_maxima:
            self.alimento_coletado += 1
            return True
        return False

# Instanciação do ambiente e das formigas
NOMES_FORMIGAS = ["F1", "F2", "F3", "F4", "F5"]

ambiente = Ambiente(grid_size=10)

# A lista é passada por referência
formigas = []
for nome in NOMES_FORMIGAS:
    formigas.append(Formiga(name=nome, ambiente=ambiente, formigas_ref=formigas))
