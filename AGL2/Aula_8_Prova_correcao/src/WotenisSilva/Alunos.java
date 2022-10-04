package WotenisSilva;

import java.io.IOException;
import java.util.Scanner;

public class Alunos {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Digite o nome do Aluno 1: ");
        String al1 = in.nextLine();
        System.out.println("Digite a assinatura do Aluno 1: ");
        String as1 = in.nextLine();
        System.out.println("Digite o nome do Aluno 2: ");
        String al2 = in.nextLine();
        System.out.println("Digite a assinatura do Aluno 2: ");
        String as2 = in.nextLine();
        System.out.println("Digite o nome do Aluno 3: ");
        String al3 = in.nextLine();
        System.out.println("Digite a assinatura do Aluno 3: ");
        String as3 = in.nextLine();
        System.out.println("Digite o nome do Aluno 4: ");
        String al4 = in.nextLine();
        System.out.println("Digite a assinatura do Aluno 4: ");
        String as4 = in.nextLine();
        System.out.println(al1 + " " + al2  + " " +  " " + al3 + " " + al4);;
        System.out.println(as1 + " " + as2  + " " +  " " + as3 + " " + as4);;

    }
}