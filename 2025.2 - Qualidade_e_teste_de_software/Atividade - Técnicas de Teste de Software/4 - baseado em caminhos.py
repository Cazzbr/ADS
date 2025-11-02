def validar_numero(numero: int) -> str:
    if numero > 0:
        return "positivo"
    elif numero < 0:
        return "negativo"
    else:
        return "zero"

def testes_caminhos():
    assert validar_numero(10) == "positivo", "Erro no caminho 1: esperado 'positivo'"

    assert validar_numero(-5) == "negativo", "Erro no caminho 2: esperado 'negativo'"

    assert validar_numero(0) == "zero", "Erro no caminho 3: esperado 'zero'"

    print("Todos os caminhos de teste passaram com sucesso.")

if __name__ == "__main__":
    testes_caminhos()
