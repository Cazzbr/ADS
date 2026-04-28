// ============================================================
//  JOGO DE ADIVINHAÇÃO — Gabarito (não mostrar aos alunos!)
// ============================================================
programa
{
    funcao inicio()
    {
        inteiro numeroSecreto
        inteiro chute
        inteiro tentativas
        cadeia  jogarNovamente

        escreva("╔════════════════════════════╗\n")
        escreva("║   JOGO DE ADIVINHAÇÃO 🎮   ║\n")
        escreva("╚════════════════════════════╝\n")

        faca
        {
            // Gera número aleatório entre 1 e 100
            numeroSecreto = aleatorio(1, 100)
            tentativas    = 0
            chute         = 0

            escreva("\nPensei em um número de 1 a 100. Qual é?\n")

            enquanto (chute != numeroSecreto)
            {
                escreva("\nSeu chute: ")
                leia(chute)
                tentativas = tentativas + 1

                se (chute == numeroSecreto)
                {
                    escreva("\n🎉 ACERTOU! O número era ", numeroSecreto, "!\n")
                    escreva("Você usou ", tentativas, " tentativa(s).\n")

                    se (tentativas <= 5)
                    {
                        escreva("Incrível! Você é um gênio! 🧠\n")
                    }
                    senao se (tentativas <= 10)
                    {
                        escreva("Muito bem! Boa performance!\n")
                    }
                    senao
                    {
                        escreva("Conseguiu! Continue praticando!\n")
                    }
                }
                senao se (chute < 1 ou chute > 100)
                {
                    escreva("⚠ Digite um número entre 1 e 100!\n")
                    tentativas = tentativas - 1  // não conta tentativa inválida
                }
                senao se (chute < numeroSecreto)
                {
                    escreva("📈 Maior! Tente um número maior.\n")
                }
                senao
                {
                    escreva("📉 Menor! Tente um número menor.\n")
                }
            }

            escreva("\nQuer jogar novamente? (sim/nao): ")
            leia(jogarNovamente)

        } enquanto (jogarNovamente == "sim")

        escreva("\nObrigado por jogar! Até a próxima! 👋\n")
    }
}
