#include <stdio.h>
#include <stdlib.h>

#define PI 3.14

/*
Escreva um programa que leia o raio de um c�rculo e informe o di�metro, a circunfer�ncia e a �rea do c�rculo.
Desafio: Utilize o valor de PI como uma constante definida em uma macro.
*/

void diametro_circunferencia_area()
{
    float raio;


    printf("Digite o raio: \n");
    scanf("%f", &raio);

    printf("Di�metro= %f\n", (raio*2) );

    printf("Circunfer�ncia= %f\n", (2 * PI * raio) );
	
    printf("�rea= %f\n", (PI * (raio*raio) ));

}
