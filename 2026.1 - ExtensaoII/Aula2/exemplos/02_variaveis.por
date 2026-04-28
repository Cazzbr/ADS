programa
{
    funcao inicio()
    {
        // Declarando variáveis
        inteiro idade = 20
        real    altura = 1.75
        cadeia  nome = "Ana"
        logico  estudante = verdadeiro

        // Mostrando os valores
        escreva("=== Meus dados ===\n")
        escreva("Nome:      ", nome, "\n")
        escreva("Idade:     ", idade, " anos\n")
        escreva("Altura:    ", altura, " m\n")
        escreva("Estudante: ", estudante, "\n")

        // Lendo do teclado
        escreva("\nDigite seu nome: ")
        leia(nome)
        escreva("Digite sua idade: ")
        leia(idade)

        escreva("\nOlá, ", nome, "! Você tem ", idade, " anos.\n")
    }
}
