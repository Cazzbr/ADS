import java.util.HashSet;
import java.util.Scanner;

public class Problema_3 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        scan.nextLine(); // Limpeza do buffer do teclado

        for (int i = 0; i < N; i++) {
            String frase = scan.nextLine(); // Lê a frase inteira

            HashSet<Character> letras = new HashSet<>();
            for (int j = 0; j < frase.length(); j++){
                char c = frase.charAt(j);
                if (Character.isLetter(c)) {
                    letras.add(c);
                }
            }
            if (letras.size() >= 26) {
                System.out.println("frase completa");
            } else if (letras.size() >= 13) {
                System.out.println("frase quase completa");
            } else {
                System.out.println("frase mal elaborada");
            }
        }
        scan.close();
    }
}