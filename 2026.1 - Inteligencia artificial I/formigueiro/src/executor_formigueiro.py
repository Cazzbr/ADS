import asyncio
from formiga import formigas, ambiente


# Tarefa D: Orquestrador — monitora o alimento, não dita rotas
async def main():
    print("\n" + "=" * 60)
    print("   SIMULAÇÃO DO FORMIGUEIRO — Sistema Multiagente Assíncrono")
    print("=" * 60)
    print(f"Formigas ativas : {[f.name for f in formigas]}")
    print(f"Capacidade do alimento: {ambiente.capacidade_maxima} coletas")
    print(f"Limite de energia por formiga: {formigas[0].MAX_PASSOS} passos")
    print("=" * 60 + "\n")

    # Cria uma corrotina independente para cada formiga
    tarefas = [asyncio.create_task(formiga.agir()) for formiga in formigas]

    tempo_maximo = 120.0
    tempo_inicio = asyncio.get_event_loop().time()

    # Tarefa D: monitora contador global — não interfere nas decisões das formigas
    while ambiente.alimento_coletado < ambiente.capacidade_maxima:
        tempo_decorrido = asyncio.get_event_loop().time() - tempo_inicio
        if tempo_decorrido > tempo_maximo:
            print("\n--- TIMEOUT: simulação encerrada por limite de tempo. ---")
            break
        await asyncio.sleep(0.1)

    # Encerra todas as corrotinas (Tarefa D: tarefa.cancel())
    for tarefa in tarefas:
        tarefa.cancel()
    await asyncio.gather(*tarefas, return_exceptions=True)

    # Relatório final
    print("\n" + "=" * 60)
    print("   RELATÓRIO FINAL")
    print("=" * 60)
    print(f"Alimento coletado   : {ambiente.alimento_coletado}/{ambiente.capacidade_maxima}")
    print(f"Rotas inválidas     : {len(ambiente.caminhos_invalidos)} registradas")

    rota_sucesso = next(
        (f.known_food_path for f in formigas if f.known_food_path is not None), None
    )
    if rota_sucesso:
        print(f"Rota de sucesso     : {rota_sucesso}")
    else:
        print("Rota de sucesso     : nenhuma formiga encontrou o alimento.")

    print("\nPosição final de cada formiga:")
    for f in formigas:
        print(f"  {f.name}: posição={f.position} | conhece rota={ 'sim' if f.known_food_path else 'não'}")

    if ambiente.alimento_coletado >= ambiente.capacidade_maxima:
        print("\nSUCESSO: recursos totalmente coletados pelo enxame!")
    else:
        print("\nFALHA: o enxame não conseguiu coletar todos os recursos.")


if __name__ == "__main__":
    asyncio.run(main())
