programa
{
    funcao inicio()
    {
        inteiro idade
        logico  temCarteira
        logico  temOnibus
        real    saldo

        escreva("=== Verificador de Transporte ===\n\n")

        escreva("Sua idade: ")
        leia(idade)
        escreva("Tem carteira de motorista? (verdadeiro/falso): ")
        leia(temCarteira)
        escreva("Tem passe de ônibus? (verdadeiro/falso): ")
        leia(temOnibus)
        escreva("Seu saldo (R$): ")
        leia(saldo)

        escreva("\n=== Resultado ===\n")

        // E (AND) — as duas condições precisam ser verdadeiras
        se (idade >= 18 e temCarteira)
        {
            escreva("✓ Pode dirigir carro\n")
        }
        senao
        {
            escreva("✗ Não pode dirigir carro\n")
        }

        // OU (OR) — pelo menos uma precisa ser verdadeira
        se (temOnibus ou saldo >= 5.0)
        {
            escreva("✓ Pode pegar ônibus\n")
        }
        senao
        {
            escreva("✗ Não pode pegar ônibus\n")
        }

        // NAO (NOT)
        se (nao temCarteira)
        {
            escreva("⚠ Lembre de tirar a carteira de motorista!\n")
        }

        // Combinação complexa
        se (idade >= 18 e (temCarteira ou (nao temOnibus e saldo >= 5.0)))
        {
            escreva("✓ Tem como se locomover de forma independente\n")
        }
    }
}
