package AlexPimentel;

import java.util.Scanner;

public class Ex_1 { //5,0/5,0
    // ótima modularização

    public static void main(String[] args) {
        int N;

        do {
            N = lerQtdAlunos();
            int M = contarAssinaturasFalsas(N);

            System.out.println(M);
        } while (N != 0);
    }

    public static int contarAssinaturasFalsas(int N) {
        int M = 0;
        String assinaturaOriginal;
        String assinaturaAula;

        while (N != 0) {
            System.out.print("Assinatura Original: ");
            assinaturaOriginal = lerAssinatura();
            System.out.print("Assinatura na aula: ");
            assinaturaAula = lerAssinatura();

            if (!(assinaturaOriginal.equals(assinaturaAula))) {
                M++;
            }
            N--;
        }

        return M;
    }

    public static String lerAssinatura() {
        Scanner scanner = new Scanner(System.in);
        String assinatura;

        do {
            assinatura = scanner.next();
        } while (assinatura.length() > 20);

        return assinatura;
    }

    public static int lerQtdAlunos() {
        Scanner scanner = new Scanner(System.in);
        int N;

        System.out.print("Quantidade de alunos na turma: ");
        do {
            N = scanner.nextInt();
        } while (N < 1 || N > 50);

        return N;
    }
}
