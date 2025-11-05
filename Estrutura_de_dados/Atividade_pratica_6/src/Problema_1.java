import java.util.Arrays;
import java.util.TreeSet;
import java.util.Scanner;

public class Problema_1 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        scan.nextLine(); // Limpeza do buffer do teclado

        for (int i = 0; i < N; i++) {
            String[] frase = scan.nextLine().split("\\s+");

            TreeSet<String> palavras = new TreeSet<>(Arrays.asList(frase));
            System.out.println(String.join(" ", palavras));
        }
        scan.close();
    }
}
