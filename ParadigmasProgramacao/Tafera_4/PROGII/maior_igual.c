#include <stdio.h>
#include <stdlib.h>

/*
Escreva um programa que pe�a ao usu�rio que digite dois valores num�ricos.
Imprima o maior n�mero seguido das palavras �� maior�. Se forem iguais, imprima a mensagem �Esses n�meros s�o iguais�.
*/

void maior_igual()
{
    int numero1, numero2;

    printf("Digite um n�mero: \n");
    scanf("%d", &numero1);
    printf("%d\n",numero1);

    printf("Digite outro n�mero: \n");
    scanf("%d", &numero2);
    printf("%d\n",numero2);

    if(numero1 > numero2)
    {
        printf("N1 � maior.");
    }
    else
    {
        if(numero2 > numero1)
        {
            printf("N2 � maior.");
        }
        else
        {
            printf("S�o iguais.");
        }
    }
}
