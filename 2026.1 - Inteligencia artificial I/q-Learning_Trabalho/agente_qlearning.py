from base_agente_qlearning import QLearningAgent

AGENTES = ["P0", "P1", "P2", "P3", "P4"]
PRIORIDADES = {"P0": 1, "P1": 3, "P2": 2, "P3": 0, "P4": 4}
DOMINIO = ["09:00", "10:00", "11:00", "14:00", "15:00"]

def criar_visinhos(nome):
    return [n for n in AGENTES if n != nome]


agentes = [QLearningAgent(name=nome, 
                          neighbors=criar_visinhos(nome),
                          domain=DOMINIO,
                          prioridades=PRIORIDADES)
                          for nome in AGENTES]