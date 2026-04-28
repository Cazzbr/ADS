programa
{
    funcao inicio()
    {
        inteiro nota

        escreva("=== Sistema de Notas ===\n")
        escreva("Digite sua nota (0 a 10): ")
        leia(nota)

        se (nota >= 6)
        {
            escreva("✓ Aprovado!\n")
        }
        senao se (nota >= 4)
        {
            escreva("⚠ Recuperação — estude mais!\n")
        }
        senao
        {
            escreva("✗ Reprovado. Não desista!\n")
        }

        // Exemplo com maior/menor
        inteiro a
        inteiro b

        escreva("\nDigite o primeiro número: ")
        leia(a)
        escreva("Digite o segundo número: ")
        leia(b)

        se (a > b)
        {
            escreva(a, " é maior que ", b, "\n")
        }
        senao se (a == b)
        {
            escreva("Os dois números são iguais!\n")
        }
        senao
        {
            escreva(b, " é maior que ", a, "\n")
        }
    }
}
