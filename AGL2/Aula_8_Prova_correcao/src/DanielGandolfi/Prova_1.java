package DanielGandolfi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prova_1 {//4,0/5,0 (faltou modularizar)

    public static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        System.out.print("Digite a quantidade de alunos: ");
        int soma = 0;
        int qnt = Integer.parseInt(bf.readLine());
        if (qnt == 0){
            System.out.println("Programa finalizado");
        }
        else {
            for (int i = 0; i <= qnt; i++) {
                System.out.print("Escreva o nome do aluno: ");
                String nome = bf.readLine();
                for (int x = 0; x <= i; x++) {
                    System.out.println("Escreva a assinatura do aluno: ");
                    String assinatura = bf.readLine();
                    String nome_caractere = String.valueOf(nome.charAt(i));
                    String assinatura_caractere = String.valueOf(assinatura.charAt(x));
                    if (nome_caractere.equals(assinatura_caractere)){
                        soma = soma + 1;
                        }
                    System.out.println(soma);
                    }
                }
            }
        }
    }
