import math

class Circulo:
    PI = math.pi

    def __init__(self, raio: float):
        self.raio = raio

    def CalcularDiametro(self):
        return self.raio*2

    def CalcularCircunferencia(self):
        return (2 * self.PI * self.raio)

    def CalcularArea(self):
        return self.PI * self.raio*self.raio

if __name__ == "__main__":
    raio = float(input("Digite o raio:"))
    meu_circulo = Circulo(raio)
    print(f"Diâmetro= {meu_circulo.CalcularDiametro()}")
    print(f"Circunferência= {meu_circulo.CalcularCircunferencia()}")
    print(f"Área= {meu_circulo.CalcularArea()}")