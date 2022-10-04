package GuilhermeFarinela;

import java.util.Scanner;

public class Exercicio1 { //1,0/5,0
    public static void main(String[] args) {
        int cont;
        String sair ;
        int c = 0;

        String [] lista1 = new String[50];
        String [] lista2 = new String[50];

        Scanner scan = new Scanner(System.in);

        System.out.println("Quantos alunos tem na sala");
        int N = scan.nextInt();

        int s = 1;
        while(!(s == 0)) {

            System.out.println("Informe o primeiro nome do aluno e a sua assinatura: ");
            String nome = scan.nextLine();
            nome = lista1[c];
            cont = c++;


            System.out.println("Para sair digite 0");
            s = scan.nextInt();
        }

        }

}
