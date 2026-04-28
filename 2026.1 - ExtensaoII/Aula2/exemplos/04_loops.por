programa
{
    funcao inicio()
    {
        // ── Loop ENQUANTO: contagem regressiva ──
        escreva("=== Contagem Regressiva ===\n")
        inteiro contador = 10
        enquanto (contador > 0)
        {
            escreva(contador, "...\n")
            contador = contador - 1
        }
        escreva("🚀 Lançar!\n\n")

        // ── Loop PARA: tabuada ──
        inteiro numero
        escreva("=== Tabuada ===\n")
        escreva("Digite um número: ")
        leia(numero)

        para (inteiro i = 1; i <= 10; i++)
        {
            escreva(numero, " x ", i, " = ", numero * i, "\n")
        }

        // ── ENQUANTO com entrada do usuário ──
        escreva("\n=== Adivinhe a palavra secreta ===\n")
        cadeia tentativa = ""
        inteiro tentativas = 0

        enquanto (tentativa != "portugol")
        {
            escreva("Qual é a palavra secreta? ")
            leia(tentativa)
            tentativas = tentativas + 1

            se (tentativa != "portugol")
            {
                escreva("Errou! Tente novamente.\n")
            }
        }
        escreva("Parabéns! Acertou em ", tentativas, " tentativa(s)!\n")
    }
}
