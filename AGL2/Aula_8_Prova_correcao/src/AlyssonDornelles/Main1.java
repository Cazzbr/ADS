package AlyssonDornelles;

import java.util.Scanner;


public class Main1 { //4,0/5,0 (faltou modularizar)
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int qty, comp;
        int iguais = 0;

        do {
            System.out.println("\nDigite a quantidade de alunos de sua turma:");
            qty = s.nextInt();
            Alunos[] alunos = new Alunos[qty];


            for (int i = 0; i < qty; i++) {
                System.out.println("Digite o nome do aluno");
                String nome = s.next();
                System.out.println("Digite a assinatura:");
                String ass = s.next();
                alunos[i] = new Alunos(nome, ass);
                System.out.println(alunos[i]);
            }

            System.out.println("Digite quantos alunos compareceram a aula:");
            comp = s.nextInt();
            Alunos[] presentes = new Alunos[comp];
            for (int x = 0; x < comp; x++) {
                System.out.println("Digite o nome do aluno");
                String nomeaula = s.next();
                System.out.println("Digite a assinatura em aula:");
                String assaula = s.next();
                presentes[x] = new Alunos(nomeaula, assaula);
            }

            for (Alunos aluno : alunos) {
                for (Alunos presente : presentes) {
                    if (aluno.equals(presente)) {
                        iguais++;
                    }
                }
            }
            System.out.println(iguais);
        } while (qty != 0);

    }
}
