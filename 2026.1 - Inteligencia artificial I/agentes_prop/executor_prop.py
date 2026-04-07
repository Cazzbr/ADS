"""
Executor do sistema multiagente com agentes em arquivos proprios.

Cada agente e definido e instanciado no seu proprio arquivo Python.
Este executor apenas importa as instancias prontas, conecta as filas
entre elas e dispara as corrotinas. Nao existe logica de controle aqui.

Estrutura de arquivos:
  base_agente_prop.py   — classe base Agent compartilhada
  agente_prop_A0.py     — instancia do agente A0
  agente_prop_A1.py     — instancia do agente A1
  agente_prop_A2.py     — instancia do agente A2
  agente_prop_A3.py     — instancia do agente A3
  agente_prop_A4.py     — instancia do agente A4
  executor_prop.py      — (este arquivo) conecta e executa
"""

import asyncio

# Cada import carrega o arquivo do agente e cria sua instancia.
import agente_prop_A0
import agente_prop_A1
import agente_prop_A2
import agente_prop_A3
import agente_prop_A4


def validar_unicidade(agentes):
    cores = [a.color for a in agentes]
    if len(cores) == len(set(cores)):
        print("Todas as cores sao unicas entre todos os agentes.")
    else:
        duplicados = [c for c in set(cores) if cores.count(c) > 1]
        print(f"Cores repetidas encontradas: {duplicados}")


async def main():
    # Coleta as instancias exportadas por cada arquivo de agente.
    agentes = [
        agente_prop_A0.agente,
        agente_prop_A1.agente,
        agente_prop_A2.agente,
        agente_prop_A3.agente,
        agente_prop_A4.agente,
    ]

    # Conecta as filas de mensagens entre os agentes.
    network = {a.name: a.inbox for a in agentes}
    for a in agentes:
        a.connect(network)

    stop_event = asyncio.Event()

    # Cada agente roda como uma tarefa assincrona independente.
    tasks = [asyncio.create_task(a.run(stop_event)) for a in agentes]

    await asyncio.sleep(5)
    stop_event.set()
    await asyncio.gather(*tasks)

    print("\nEstado final:")
    for a in sorted(agentes, key=lambda x: x.name):
        print(f"- {a.name}: {a.color}")

    validar_unicidade(agentes)


if __name__ == "__main__":
    asyncio.run(main())
