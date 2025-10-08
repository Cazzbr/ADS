import java.util.Scanner;

public class Desafio{ 
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int v = scan.nextInt();

        while (v != 0) {
            ListaTAD cards = new ListaTAD();
            for (int i = 1; i <=v; i++){
                cards.insereFinal(i);
            }
            ListaTAD dischargedCards = new ListaTAD();
            while (cards.tamanho() > 1) {
                dischargedCards.insereFinal(cards.acessa(0));
                cards.removeInicio();
                cards.insereFinal(cards.acessa(0));
                cards.removeInicio();
            }
            System.out.print("Discarded cards:");
            for (int i = 0; i < dischargedCards.tamanho(); i++){
                if (i == 0) {
                    System.out.print(" " + dischargedCards.acessa(i));
                } else {
                    System.out.print(", " + dischargedCards.acessa(i));
                }
            }
            System.out.println("");
            System.out.println("Remaining cards: " + cards.acessa(0));

            v = scan.nextInt();
        }
        scan.close();
    }
}