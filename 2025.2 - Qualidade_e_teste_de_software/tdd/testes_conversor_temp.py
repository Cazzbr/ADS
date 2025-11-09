import unittest


def conversor(n: float) -> float:
    return n * 1.8 + 32


class TestesConversao(unittest.TestCase):
    def teste_conversao_1(self):
        self.assertEqual(conversor(23), 73.4)

    def teste_conversao_2(self):
        self.assertEqual(conversor(0), 32)


if __name__ == "__main__":
    unittest.main(argv=["first-arg-is-ignored"], exit=False)
