import random

# Pesos fixos da caixa (20 pesos diferentes em kg)
PESOS = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
         31, 37, 41, 43, 47, 4, 6, 8, 14, 18]

# 1. Função de Aptidão (Fitness Function)
def fitness_fn(individuo, peso_alvo, pesos=PESOS):
    """
    Cromossomo: lista de 0s e 1s indicando quais pesos estão na balança.
    Fitness máximo = peso_alvo (diferença zero entre soma e alvo).
    Quanto maior o fitness, mais perto do alvo.
    """
    soma = sum(p for p, bit in zip(pesos, individuo) if bit == 1)
    diferenca = abs(soma - peso_alvo)
    # Retorna o alvo menos a diferença; nunca negativo
    return max(0, peso_alvo - diferenca)

def soma_individuo(individuo, pesos=PESOS):
    """Retorna a soma dos pesos selecionados pelo cromossomo."""
    return sum(p for p, bit in zip(pesos, individuo) if bit == 1)

def pesos_selecionados(individuo, pesos=PESOS):
    """Retorna a lista de pesos que o cromossomo coloca na balança."""
    return [p for p, bit in zip(pesos, individuo) if bit == 1]

# 2. Seleção por Torneio (mais eficiente que roleta para este problema)
def random_selection(populacao, fitness_fn, peso_alvo, k=3):
    """
    Seleciona o melhor dentre k indivíduos escolhidos aleatoriamente.
    Isso evita recalcular fitness da população inteira a cada chamada.
    """
    candidatos = random.sample(populacao, min(k, len(populacao)))
    return max(candidatos, key=lambda ind: fitness_fn(ind, peso_alvo))

# 3. Cruzamento (Crossover de ponto único)
def reproduce(x, y):
    """Combina dois cromossomos em um ponto de corte aleatório."""
    n = len(x)
    c = random.randint(1, n - 1)
    return x[:c] + y[c:]

# 4. Mutação (flip de bit)
def mutate(child, mutation_rate=0.05):
    """Inverte cada bit com probabilidade mutation_rate."""
    return [1 - gene if random.random() < mutation_rate else gene
            for gene in child]

# 5. O Algoritmo Genético (modo texto, para uso direto)
def genetic_algorithm(peso_alvo, pesos=PESOS, tamanho_pop=100,
                      n_geracoes=1000, mutation_rate=0.05):
    n = len(pesos)
    populacao = [[random.randint(0, 1) for _ in range(n)]
                 for _ in range(tamanho_pop)]

    melhor_historico = None

    for geracao in range(n_geracoes):
        populacao.sort(key=lambda ind: fitness_fn(ind, peso_alvo), reverse=True)

        melhor_atual = populacao[0]
        if (melhor_historico is None or
                fitness_fn(melhor_atual, peso_alvo) > fitness_fn(melhor_historico, peso_alvo)):
            melhor_historico = melhor_atual.copy()

        # Solução exata encontrada?
        if fitness_fn(melhor_historico, peso_alvo) == peso_alvo:
            print(f"✅ Solução exata encontrada na geração {geracao}!")
            print(f"   Pesos: {pesos_selecionados(melhor_historico, pesos)}")
            print(f"   Soma:  {soma_individuo(melhor_historico, pesos)} kg")
            return melhor_historico

        # Nova geração com elitismo
        nova_pop = [melhor_historico.copy()]
        while len(nova_pop) < tamanho_pop:
            x = random_selection(populacao, fitness_fn, peso_alvo)
            y = random_selection(populacao, fitness_fn, peso_alvo)
            child = reproduce(x, y)
            child = mutate(child, mutation_rate)
            nova_pop.append(child)
        populacao = nova_pop

    print(f"⚠️  Melhor resultado após {n_geracoes} gerações:")
    print(f"   Pesos: {pesos_selecionados(melhor_historico, pesos)}")
    print(f"   Soma:  {soma_individuo(melhor_historico, pesos)} kg  (alvo: {peso_alvo} kg)")
    return melhor_historico
