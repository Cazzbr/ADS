import java.util.TreeMap;

public class ExemplosMapas_3 {
    public static void main(String[] args) throws Exception {
        System.out.println("========   Tree ==========");

        TreeMap<String, Double> cambio = new TreeMap<>();

        cambio.put("USD", 5.30);
        cambio.put("EUR", 6.30);
        cambio.put("CAD", 4.50);


        System.out.println(" 3 Reais para dolar: " + 3 / cambio.get("USD"));


    }


        


}
