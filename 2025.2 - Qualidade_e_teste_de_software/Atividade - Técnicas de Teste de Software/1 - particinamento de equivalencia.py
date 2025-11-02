def pode_beber(idade: int ) -> bool:

    if idade >= 18:
        return True
    else:
        return False

def teste_particao_equivalencia():
    assert pode_beber(17) == False, "Erro: Pessoa menor de 18 não deveria poder beber."
    
    assert pode_beber(18) == True, "Erro: Pessoa com 18 anos deveria poder beber."
    
    assert pode_beber(25) == True, "Erro: Pessoa com mais de 18 anos deveria poder beber."

    print("Todos os testes passaram com sucesso.")

if __name__ == "__main__":
    teste_particao_equivalencia()