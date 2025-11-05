import java.util.Scanner;
import java.util.TreeMap;

public class Problema_4 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        
        int N = scan.nextInt();
        
        for (int i = 0; i < N; i++) {
            int M = scan.nextInt();
            scan.nextLine(); // Limpeza do buffer do teclado

            TreeMap<String, Double> preco = new TreeMap<>();

            for (int j = 0; j < M; j++) {
                String mome_preco = scan.nextLine();
                String[] nome_preco_arr = mome_preco.split("\\s+");
                preco.put(nome_preco_arr[0], Double.parseDouble(nome_preco_arr[1]));
            }
            int P = scan.nextInt();
            scan.nextLine(); // Limpeza do buffer do teclado

            double valor = 0.0;

            for (int k = 0; k < P; k++) {
                String[] nome_qtd = scan.nextLine().split("\\s+");
                if (preco.containsKey(nome_qtd[0])) {
                    valor += preco.get(nome_qtd[0]) * Double.parseDouble(nome_qtd[1]);
                }
            }
            System.out.println("R$ " + String.format("%.2f", valor));
        }
        scan.close();
    }
    
}
