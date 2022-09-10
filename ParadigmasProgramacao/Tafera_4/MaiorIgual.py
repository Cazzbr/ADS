
class MaiorIgual:
    def __init__(self):
        self.n1 = float(input("Digite um número: "))
        self.n2 = float(input("Digite outro número: "))
        print(self.maior())

    def maior(self):
        if self.n1 == self.n2:
            return "São iguais."
        elif self.n1 > self.n2:
            return "N1 é maior."
        return "N2 é maior"

if __name__ == "__main__":
    MaiorIgual()