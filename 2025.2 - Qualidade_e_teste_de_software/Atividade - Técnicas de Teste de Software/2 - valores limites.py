def idade_valida(idade: int) -> bool:
    return 18 <= idade <= 65

def teste_valores_limite():
    assert idade_valida(17) == False, "Erro: 17 deveria ser inválido."
    assert idade_valida(18) == True, "Erro: 18 deveria ser válido."
    assert idade_valida(19) == True, "Erro: 19 deveria ser válido."
    
    assert idade_valida(64) == True, "Erro: 64 deveria ser válido."
    assert idade_valida(65) == True, "Erro: 65 deveria ser válido."
    assert idade_valida(66) == False, "Erro: 66 deveria ser inválido."

    print("Todos os testes de valores-limite passaram com sucesso.")

if __name__ == "__main__":
    teste_valores_limite()
