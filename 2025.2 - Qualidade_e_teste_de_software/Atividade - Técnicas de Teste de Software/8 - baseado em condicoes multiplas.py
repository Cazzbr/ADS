import unittest

def pode_passar(idade: int, possui_permissao: bool) -> bool:
    if idade >= 18 and possui_permissao:
        return True
    else:
        return False

class TestMultipleCondition(unittest.TestCase):
    def test_todas_combinacoes(self):
        test_cases = [
            (17, False, False),
            (17, True, False),
            (18, False, False),
            (18, True, True),
        ]
        for idade, permissao, esperado in test_cases:
            with self.subTest(idade=idade, permissao=permissao):
                self.assertEqual(pode_passar(idade, permissao), esperado)

if __name__ == "__main__":
    unittest.main()
