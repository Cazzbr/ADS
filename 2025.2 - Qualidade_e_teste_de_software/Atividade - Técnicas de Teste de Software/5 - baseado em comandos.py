import unittest

def calcular_quadrado(x: int) -> int:
    return x * x

def calcular_cubo(x: int) -> int:
    return x * x * x

class TestCalculo(unittest.TestCase):
    def test_quadrado_de_2(self):
        self.assertEqual(calcular_quadrado(2), 4)

    def test_quadrado_de_0(self):
        self.assertEqual(calcular_quadrado(0), 0)

    def test_quadrado_de_numero_negativo(self):
        self.assertEqual(calcular_quadrado(-3), 9)

    def test_cubo_de_2(self):
        self.assertEqual(calcular_cubo(2), 8)
    
    def test_cubo_de_0(self):
        self.assertEqual(calcular_cubo(0), 0)
    
    def test_cubo_de_numero_negativo(self):
        self.assertEqual(calcular_cubo(-3), -27)

if __name__ == '__main__':
    unittest.main()
