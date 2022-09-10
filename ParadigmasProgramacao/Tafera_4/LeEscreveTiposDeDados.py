class LeEscreveTiposDados:
    def LeEscreveInteiro():
        inteiro = int(input("Digite um inteiro: "))
        print(inteiro)

    def LeEscreveChar():
        char = input("Digite um caracter: ")
        print(char)
    
    def LeEscreveDecimal():
        decimal = float(input("Digite um decimal: "))
        print(decimal)

if __name__ == "__main__":
    LeEscreveTiposDados.LeEscreveInteiro()
    LeEscreveTiposDados.LeEscreveChar()
    LeEscreveTiposDados.LeEscreveDecimal()
