package DhiuliaAntunes.Questao1;

import java.util.Scanner;

public class Main {//4,0/5,0 (faltou modularizar)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Quantidade alunos em turma:");
        int N = sc.nextInt();
        int diferencas = 0;

        String[] teste1 = new String[5];
        String[] teste2 = new String[5];

        while(N != 0){
            for (int i = 0; i <= N; i++) {
                teste1[i] = sc.nextLine();
            }

            System.out.println("Quantidade de alunos em sala de aula:");
            int M = sc.nextInt();

            for (int j = 0; j <= M; j++) {
                teste2[j] = sc.nextLine();
            }

            N--;
        }

        for (String t : teste1) {
            for (String t2 : teste2) {
                if (t != t2) {
                    diferencas += 1;
                }
            }
        }
        System.out.println("Total de diferencas: " + diferencas);

    }
}

