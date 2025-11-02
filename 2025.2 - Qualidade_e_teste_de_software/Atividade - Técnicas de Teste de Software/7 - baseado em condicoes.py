import unittest

def pode_ser_candidato(idade: int , cargo: str) -> bool:
    if cargo in ["Presidente", "Vice-Presidente", "Senador"]:
        return idade >= 35
    elif cargo in ["Governador", "Vice-Governador"]:
        return idade >= 30
    elif cargo in ["Deputado Federal", "Deputado Estadual", "Deputado Distrital", "Prefeito", "Vice-Prefeito", "Juiz de Paz"]:
        return idade >= 21
    elif cargo == "Vereador":
        return idade >= 18
    return False

class TesteIdadeMinimaCandidatura(unittest.TestCase):
    def test_presidente_apos_35(self):
        self.assertTrue(pode_ser_candidato(35, "Presidente"))
        self.assertFalse(pode_ser_candidato(34, "Presidente"))

    def test_governador_apos_30(self):
        self.assertTrue(pode_ser_candidato(30, "Governador"))
        self.assertFalse(pode_ser_candidato(29, "Governador"))

    def test_deputado_apos_21(self):
        self.assertTrue(pode_ser_candidato(21, "Deputado Federal"))
        self.assertFalse(pode_ser_candidato(20, "Deputado Federal"))

    def test_vereador_apos_18(self):
        self.assertTrue(pode_ser_candidato(18, "Vereador"))
        self.assertFalse(pode_ser_candidato(17, "Vereador"))

    def test_cargo_desconhecido(self):
        self.assertFalse(pode_ser_candidato(50, "Presidente de Clube"))

if __name__ == "__main__":
    unittest.main()
