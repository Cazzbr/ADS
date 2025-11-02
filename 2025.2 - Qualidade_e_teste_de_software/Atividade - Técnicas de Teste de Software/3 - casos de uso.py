def cadastrar_produto(nome: str, preco: float) -> bool:
    if not nome or preco < 0:
        return False
    return True

def teste_caso_de_uso_cadastro_produto():
    assert cadastrar_produto("Camiseta", 49.90) == True, "Erro: Produto válido não foi cadastrado."

    assert cadastrar_produto("", 49.90) == False, "Erro: Produto com nome vazio deveria ser inválido."

    assert cadastrar_produto("Calça", -10) == False, "Erro: Produto com preço negativo deveria ser inválido."

    print("Todos os testes de caso de uso para cadastro de produto passaram com sucesso.")

if __name__ == "__main__":
    teste_caso_de_uso_cadastro_produto()
