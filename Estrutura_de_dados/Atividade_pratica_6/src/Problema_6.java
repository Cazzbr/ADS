import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;


public class Problema_6 {
    public static void main(String[] args) {
        System.out.println("HALL OF MURDERERS");

        HashMap<String, Integer> assassinos = new HashMap<>();
        HashSet<String> assassinados = new HashSet<>();

        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String[] assino_assassinado = scan.nextLine().split("\\s+");

            assassinos.put(assino_assassinado[0], assassinos.getOrDefault(assino_assassinado[0], 0) + 1);   
            assassinados.add(assino_assassinado[1]);
        }

        assassinos.entrySet().stream()
            .filter(entry -> !assassinados.contains(entry.getKey()))
            .sorted(Map.Entry.comparingByKey())
            .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));

        scan.close();
    }
}