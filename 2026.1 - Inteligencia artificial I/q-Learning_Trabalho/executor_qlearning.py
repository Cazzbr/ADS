import asyncio
import matplotlib.pyplot as plt

from agente_qlearning import agentes

def validar_unicidade(agentes):
    horarios = [a.appointment for a in agentes]
    if len(horarios) == len(set(horarios)):
        print("\nSUCESSO FINAL: Todos os horários são únicos entre todos os agentes.")
    else:
        duplicados = [h for h in set(horarios) if horarios.count(h) > 1]
        print(f"\nFALHA: Horários repetidos encontrados: {duplicados}")

def contar_conflitos(agentes):
    horarios = [a.appointment for a in agentes]
    return sum(1 for h in horarios if horarios.count(h) > 1)

def gerar_graficos(metricas, agentes):
    # Gráfico 1: Convergência de Conflitos
    plt.figure()
    plt.plot(metricas['conflitos_por_iteracao'])
    plt.title('Conflitos por Iteração')
    plt.xlabel('Iteração')
    plt.ylabel('Número de Conflitos')
    plt.tight_layout()
    plt.savefig('convergencia_conflitos.png')
    plt.close()

    # Gráfico 2: Decaimento do Epsilon
    plt.figure()
    plt.plot(metricas['epsilon_medio'])
    plt.title('Exploração (Epsilon) ao Longo do Tempo')
    plt.xlabel('Iteração')
    plt.ylabel('Epsilon Médio')
    plt.tight_layout()
    plt.savefig('decaimento_epsilon.png')
    plt.close()

    # Gráfico 3: Distribuição Final de Slots
    dominio = agentes[0].domain
    nomes = [a.name for a in agentes]
    slots_finais = [a.appointment for a in agentes]
    indices = [dominio.index(s) for s in slots_finais]

    plt.figure()
    plt.bar(range(len(slots_finais)), indices, tick_label=nomes)
    plt.yticks(range(len(dominio)), dominio)
    plt.title('Alocação Final de Slots')
    plt.xlabel('Agente')
    plt.ylabel('Slot')
    plt.tight_layout()
    plt.savefig('alocacao_final.png')
    plt.close()

    print("\n--- Gráficos salvos: convergencia_conflitos.png, decaimento_epsilon.png, alocacao_final.png ---")

async def main():
    metricas = {
        'conflitos_por_iteracao': [],
        'epsilon_medio': []
    }

    print("\n--- Estabelecendo as conexões de rede ---")
    for agente in agentes:
        for vizinho in agentes:
            if vizinho.name in agente.neighbors:
                agente.network[vizinho.name] = vizinho.inbox

    print("\n--- Iniciando propagação inicial ---")
    for agente in agentes:
        await agente.propagar_horario()

    print("\n--- Agentes iniciando aprendizado (Q-Learning) ---")
    tarefas = [asyncio.create_task(agente.agir()) for agente in agentes]

    # Timeout de segurança ajustado, Q-Learning com exploração pode precisar de mais iterações
    tempo_maximo = 5.0
    tempo_inicio = asyncio.get_event_loop().time()

    while True:
        metricas['conflitos_por_iteracao'].append(contar_conflitos(agentes))
        metricas['epsilon_medio'].append(sum(a.epsilon for a in agentes) / len(agentes))

        horarios = [a.appointment for a in agentes]
        if len(horarios) == len(set(horarios)):
            print("\n--- Solução Encontrada! Sem conflitos na rede. ---")
            break

        if asyncio.get_event_loop().time() - tempo_inicio > tempo_maximo:
            print("\n--- TIMEOUT: Exploração epsilon-greedy não convergiu a tempo. ---")
            break

        await asyncio.sleep(0.05)

    for tarefa in tarefas:
        tarefa.cancel()

    print("\n--- Resultado da Orquestração ---")
    for agente in agentes:
        print(f"{agente.name} terminou com o horário: {agente.appointment} | Q-table: {agente.q_table}")

    validar_unicidade(agentes)
    gerar_graficos(metricas, agentes)

if __name__ == "__main__":
    asyncio.run(main())
