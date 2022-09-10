#include <stdio.h>
#include <stdlib.h>

/*
Monte um programa em C que pergunte ao usuário e escreva na tela todos os tipos de dados vistos em aula, no formato adequado.
*/

void le_escreve_tipos_de_dados()
{
    int inteiro;
    float decimal;
    char caracter;

    printf("Digite um inteiro: \n");
    scanf("%d", &inteiro);
    printf("%d\n",inteiro);

    //Para onde o enter vai?
    //scanf("%c",&caracter);

    printf("Digite um cáracter: \n");
    scanf("%c", &caracter);
    printf("%c\n",caracter);

    printf("Digite um decimal: \n");
    scanf("%f", &decimal);
    printf("%f\n",decimal);

    //fflush(stdin);

}
