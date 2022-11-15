x = 10 

def expression():
    x = 20

    def expression_enclosed():
        nonlocal x
        x = 30

    expression_enclosed()
    print(f"O valor de x (Enclosing scope) é: {x}")

if __name__ == "__main__":
    expression()
    print(f"O valor de x (Global scope) é: {x}")