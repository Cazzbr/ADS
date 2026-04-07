"""
Agente A2 — arquivo proprio do agente.
Pode ser executado diretamente para inspecionar sua configuracao.
"""
from base_agente_prop import Agent

NOME = "A2"
VIZINHOS = ["A0", "A1", "A3", "A4"]
DOMINIO = [ "Branco", "Preto", "Azul", "Verde", "Amarelo"]

agente = Agent(NOME, VIZINHOS, DOMINIO)

if __name__ == "__main__":
    print(f"\nAgente: {agente.name}")
    print(f"Vizinhos: {agente.neighbors}")
    print(f"Dominio: {agente.domain}")
    print(f"Cor inicial: {agente.color}")
