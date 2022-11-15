x = 10 
y = 20

def expression():
    x = 30
    global y, z
    y = 40
    z = 50

if __name__ == "__main__":
    expression()
    print(f"O valor de x é: {x}")
    print(f"O valor de y é: {y}")
    print(f"O valor de z é: {z}")