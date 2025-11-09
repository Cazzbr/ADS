import unittest


def media(notas: list) -> float:
    return sum(notas) / len(notas)


class TestesMedia(unittest.TestCase):
    def teste_media_1(self):
        self.assertEqual(media([10, 8, 6]), 8)
        self.assertEqual(media([10, 8, 6, 4, 2]), 6)
        self.assertEqual(media([10, 8.5, 6.3, 3.2]), 7)


if __name__ == "__main__":
    unittest.main()
