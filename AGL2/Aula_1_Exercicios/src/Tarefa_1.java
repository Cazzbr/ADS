/*
Leia 3 valores, no caso, variáveis A, B e C, que são as três notas de um
aluno. A seguir, calcule a média do aluno, sabendo que a nota A tem peso
2, a nota B tem peso 3 e a nota C tem peso 5. Considere que cada nota
pode ir de 0 até 10.0, sempre com uma casa decimal.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Tarefa_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        // Delcara as variaveis
        float nota_A, nota_B, nota_C, media;
        // Msg na tela para primeira nota
        System.out.println("Digite a primeira nota: ");
        // Recebe nota na variavel nota_A convertida para float
        nota_A = Float.parseFloat(bf.readLine());
        // Msg na tela para segunda nota
        System.out.println("Digite a segunda nota: ");
        // Recebe nota na variavel nota_B convertida para float
        nota_B = Float.parseFloat(bf.readLine());
        // Msg na tela para terceira nota
        System.out.println("Digite a terceira nota: ");
        // Recebe nota na variavel nota_C convertida para float
        nota_C = Float.parseFloat(bf.readLine());
        // Calcula media ponderada das notas
        media = (nota_A * 2  + nota_B * 3 + nota_C * 5) / 10;
        // Informa a media para o usuario
        System.out.println("A media é: " + media);

    }
}
