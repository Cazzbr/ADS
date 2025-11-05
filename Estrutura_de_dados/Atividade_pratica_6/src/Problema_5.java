import java.util.Scanner;
import java.util.TreeMap;

public class Problema_5 {

    public static void main(String[] args) {

        TreeMap<Character, Double> notas = new TreeMap<>();
        notas.put('W', 1.0);
        notas.put('H', 0.5);
        notas.put('Q', 0.25);
        notas.put('E', 0.125);
        notas.put('S', 0.0625);
        notas.put('T', 0.03125);
        notas.put('X', 0.015625);

        Scanner scan = new Scanner(System.in);
        String composicao = scan.nextLine();
        while(!composicao.trim().equals("*")) {

            String[] compassos = composicao.split("/");
            int correct = 0;
            for (String compasso : compassos){
                Double soma = 0.0;
                for (int i = 0; i < compasso.length(); i++){
                    soma += notas.get(compasso.charAt(i));
                }
                if (soma == 1.0) {
                    correct += 1;
                }
            }
            System.out.println(correct);
            composicao = scan.nextLine();
        }
        scan.close();
    }
    
}
