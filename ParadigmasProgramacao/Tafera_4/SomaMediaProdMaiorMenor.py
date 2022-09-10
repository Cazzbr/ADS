class SomaMediaProdMaiorMenor:
    def __init__(self) -> None:
        print("Digite 3 números: ")
        self.n1 = float(input())
        self.n2 = float(input())
        self.n3 = float(input())
    
    def Maior(self):
        print(f"O Maior Valor é: {max([self.n1, self.n2, self.n3])}")

    def Menor(self):
        print(f"O Menor valor é: {min([self.n1, self.n2, self.n3])}")

    def Soma(self):
        print(f"A Soma é: {self.n1 + self.n2 + self.n3}")
    
    def Multiplicacao(self):
        print(f"A multiplicação é: {self.n1 * self.n2 * self.n3}")
    
    def Produto(self):
        print(f"O Produto é: {(self.n1 + self.n2 + self.n3)/2}")

if __name__ == "__main__":
    obj = SomaMediaProdMaiorMenor()
    obj.Maior()
    obj.Menor()
    obj.Soma()
    obj.Multiplicacao()
    obj.Produto()