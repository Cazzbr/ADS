/*
Faça um programa que leia três valores e apresente o maior dos três
valores lidos seguido da mensagem “eh o maior”. Utilize a fórmula:
MaiorAB = (a + b + abs(a - b))/2
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Tarefa_3 {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        // Delcara as variaveis
        float valor_1, valor_2, valor_3, maior;
        // Msg na tela para primeiro valor
        System.out.println("Digite o primeiro valor: ");
        // Recebe o valor na variavel valor_1 convertido para float
        valor_1 = Float.parseFloat(bf.readLine());
        // Msg na tela para segundo valor
        System.out.println("Digite o segundo valor: ");
        // Recebe o valor na variavel valor_2 convertida para float
        valor_2 = Float.parseFloat(bf.readLine());
        // Msg na tela para terceiro valor
        System.out.println("Digite o terceiro valor: ");
        // Recebe o valor na variavel valor_3 convertida para float
        valor_3 = Float.parseFloat(bf.readLine());
        // verifica o maior valor enter 1 e 2
        maior = (valor_1 + valor_2 + Math.abs(valor_1 - valor_2))/2;
        // verifica o maior valor enter maior (1 e 2) e 3
        maior = (maior + valor_3 + Math.abs(maior - valor_3))/2;
        // Informa a media para o usuario
        System.out.println(maior + " eh o maior");
    }
}
