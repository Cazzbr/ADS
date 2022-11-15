
def expression(x: int):
    local_variable = x^2
    print(f"O valor recebido é {x} e a variável local é {local_variable}")

    def expression_enclosed():
        print(f"O valor da variável local é {local_variable} - enclosed expression!")

    expression_enclosed()

if __name__ == "__main__":
    expression(20)
