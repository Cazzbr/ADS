import unittest


def eh_senha_forte(senha: str) -> bool:
    lower: bool = False
    upper: bool = False
    number: bool = False
    special: bool = False
    for a in senha:
        ascii = ord(a)
        if 65 <= ascii <= 90:
            upper = True
        elif 97 <= ascii <= 122:
            lower = True
        elif 48 <= ascii <= 57:
            number = True
        elif (
            (32 <= ascii <= 47)
            or (58 <= ascii <= 64)
            or (91 <= ascii <= 96)
            or (123 <= ascii <= 126)
        ):
            special = True
    return (
        True if len(senha) >= 14 and lower and upper and number and special else False
    )


class TestesSenhaForte(unittest.TestCase):
    def teste_senha_forte_true(self):
        self.assertEqual(eh_senha_forte("SenhaForte1234.@!ER"), True)

    def test_senha_forte_false(self):
        self.assertEqual(eh_senha_forte("hjshshgdf"), False)


if __name__ == "__main__":
    unittest.main()
