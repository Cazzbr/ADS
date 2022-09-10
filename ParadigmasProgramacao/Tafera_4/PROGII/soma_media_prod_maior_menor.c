#include <stdio.h>
#include <stdlib.h>

/*
Escreva um programa que leia 3 inteiros e escreva a soma, média, produto, maior e menor desses números.
*/

void soma_media_prod_maior_menor()
{
    int numero1, numero2,numero3;
    int maior = -1, x;


    printf("Digite 3 números: \n");
    scanf("%d%d%d", &numero1, &numero2,&numero3);

    /*x = numero1 > numero2;

    printf("%x", x);

    if(2 > 1 > 0)
    {
        maior = numero1;
    }*/

    if(numero1 > numero2 > numero3)
    {
        maior = numero1;
    }

    if(numero2 > numero1 > numero3)
    {
        maior = numero1;
    }

    if(numero3 > numero2 > numero3)
    {
        maior = numero1;
    }

    printf("%d é o maior.\n", maior);



    // --//--

    printf("%d\n", (numero1+ numero2 + numero3) );

    printf("%d\n", (numero1 * numero2 * numero3) );

    printf("%f\n", ((float)(numero1+ numero2 + numero3) / 3) );

}
