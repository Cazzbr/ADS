import unittest

def avaliar_credito(renda: float, historico_score: int) -> str  :
    if renda > 5000:
        if historico_score > 700:
            return "Crédito aprovado"
        else:
            return "Reprovado por score"
    else:
        if historico_score > 700:
            return "Aprovado com restrição"
        else:
            return "Crédito negado"

class TestAvaliacaoCredito(unittest.TestCase):
    def test_aprovado(self):
        self.assertEqual(avaliar_credito(6000, 750), "Crédito aprovado")
    
    def test_reprovado_por_score(self):
        self.assertEqual(avaliar_credito(6000, 650), "Reprovado por score")
    
    def test_aprovado_com_restricao(self):
        self.assertEqual(avaliar_credito(4000, 750), "Aprovado com restrição")
    
    def test_credito_negado(self):
        self.assertEqual(avaliar_credito(4000, 650), "Crédito negado")

if __name__ == "__main__":
    unittest.main()
