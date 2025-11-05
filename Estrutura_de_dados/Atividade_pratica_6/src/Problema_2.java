import java.util.HashMap;
import java.util.Scanner;

public class Problema_2 {
    public static void main(String[] args) {

        HashMap<String, String> feliz_natal = new HashMap<>();
        feliz_natal.put("brasil", "Feliz Natal!");
        feliz_natal.put("alemanha", "Frohliche Weihnachten!");
        feliz_natal.put("austria", "Frohe Weihnacht!");
        feliz_natal.put("coreia", "Chuk Sung Tan!");
        feliz_natal.put("espanha", "Feliz Navidad!");
        feliz_natal.put("grecia", "Kala Christougena!");
        feliz_natal.put("estados-unidos", "Merry Christmas!");
        feliz_natal.put("inglaterra", "Merry Christmas!");
        feliz_natal.put("australia", "Merry Christmas!");
        feliz_natal.put("portugal", "Feliz Natal!");
        feliz_natal.put("suecia", "God Jul!");
        feliz_natal.put("turquia", "Mutlu Noeller");
        feliz_natal.put("argentina", "Feliz Navidad!");
        feliz_natal.put("chile", "Feliz Navidad!");
        feliz_natal.put("mexico", "Feliz Navidad!");
        feliz_natal.put("antardida", "Merry Christmas!");
        feliz_natal.put("canada", "Merry Christmas!");
        feliz_natal.put("irlanda", "Nollaig Shona Dhuit!");
        feliz_natal.put("belgica", "Zalig Kerstfeest!");
        feliz_natal.put("italia", "Buon Natale!");
        feliz_natal.put("libia", "Buon Natale!");
        feliz_natal.put("siria", "Milad Mubarak!");
        feliz_natal.put("marrocos", "Milad Mubarak!");
        feliz_natal.put("japao", "Merii Kurisumasu!");

        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()) {
            String pais = scan.nextLine();
            if (feliz_natal.containsKey(pais)){
                System.out.println(feliz_natal.get(pais));
            } else {
                System.out.println("--- NOT FOUND ---");
            }
        }
        scan.close();
    }
}
