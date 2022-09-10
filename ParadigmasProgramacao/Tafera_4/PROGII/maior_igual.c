#include <stdio.h>
#include <stdlib.h>

/*
Escreva um programa que peça ao usuário que digite dois valores numéricos.
Imprima o maior número seguido das palavras ‘é maior’. Se forem iguais, imprima a mensagem ‘Esses números são iguais’.
*/

void maior_igual()
{
    int numero1, numero2;

    printf("Digite um número: \n");
    scanf("%d", &numero1);
    printf("%d\n",numero1);

    printf("Digite outro número: \n");
    scanf("%d", &numero2);
    printf("%d\n",numero2);

    if(numero1 > numero2)
    {
        printf("N1 é maior.");
    }
    else
    {
        if(numero2 > numero1)
        {
            printf("N2 é maior.");
        }
        else
        {
            printf("São iguais.");
        }
    }
}
