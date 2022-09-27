import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Notas {
    static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws Exception {
        
        Double notas[] = new Double[4];

        Double media = 0.0;

        for (int i = 0; i < notas.length; i++){
            notas[i] = get_input("Digite a nota " + (i+1) + ":");
            media += notas[i];
        }
        
        media = media / 4;

        System.out.println();
        System.out.println("######################################################");
        System.out.println();
        System.out.println("As notas do aluno são:");
        System.out.print("  |  ");
        for (Double nota: notas){
            System.out.print(nota);
            System.out.print("  |  ");
        }
        System.out.println();
        System.out.println();
        System.out.println("A média do aluno é: " + media);
        System.out.println("######################################################");
    }

    public static double get_input (String txt) throws Exception {
        System.out.println(txt);
        return Double.parseDouble(bf.readLine());
    }
}
