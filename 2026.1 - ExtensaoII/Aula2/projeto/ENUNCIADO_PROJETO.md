# Projeto — Jogo de Adivinhação 🎮

## Objetivo

Criar um jogo em Portugol Studio onde o computador "pensa" em um número secreto
entre **1 e 100** e o jogador tenta adivinhar.

---

## Requisitos obrigatórios

### Nível 1 — Básico (todos devem entregar)

1. O programa define um número secreto fixo (ex: `inteiro numeroSecreto = 42`)
2. Pede para o jogador digitar um chute
3. Responde `"Maior!"` ou `"Menor!"` conforme o chute
4. Quando acertar, exibe uma mensagem de parabéns
5. Mostra quantas tentativas foram usadas

### Nível 2 — Intermediário (bônus)

6. Usar `aleatorio(1, 100)` para gerar o número automaticamente
7. Validar: se o chute for fora do intervalo (< 1 ou > 100), avisar e não contar a tentativa
8. Ao final, classificar o desempenho:
   - Até 5 tentativas: "Incrível! Você é um gênio!"
   - Até 10 tentativas: "Muito bem!"
   - Mais de 10: "Conseguiu! Continue praticando!"

### Nível 3 — Avançado (desafio extra)

9. Perguntar se o jogador quer jogar novamente ao final
10. Manter um placar de recordes (menor número de tentativas da sessão)

---

## Estrutura mínima esperada

```portugol
programa
{
    funcao inicio()
    {
        inteiro numeroSecreto = 42   // troque por aleatorio(1,100) no nível 2
        inteiro chute = 0
        inteiro tentativas = 0

        escreva("Adivinhe o número entre 1 e 100!\n")

        enquanto (chute != numeroSecreto)
        {
            escreva("Seu chute: ")
            leia(chute)
            tentativas = tentativas + 1

            // coloque aqui as condições de maior/menor/acerto
        }
    }
}
```

---

## Dicas

| Precisa de...           | Use...                                  |
|-------------------------|-----------------------------------------|
| Guardar o número        | `inteiro numeroSecreto`                 |
| Contar tentativas       | `inteiro tentativas = 0` + `tentativas = tentativas + 1` |
| Repetir até acertar     | `enquanto (chute != numeroSecreto)`     |
| Comparar chute          | `se / senao se / senao`                 |
| Número aleatório        | `aleatorio(1, 100)`                     |
| Jogar de novo           | Loop externo com `faca ... enquanto`    |

---

## Entrega

- Arquivo `.por` com o jogo funcionando
- Demonstração ao vivo na próxima aula
- Explique em 1 minuto o trecho de código que mais te orgulha

---

> **Lembre:** errar faz parte! O importante é tentar, debugar e aprender.
