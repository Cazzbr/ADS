import java.util.HashMap;

public class ExemplosMapas_1 {
    public static void main(String[] args) throws Exception {
        System.out.println("========   HASH ==========");

        HashMap<String, Integer> meses = new HashMap<>();

        meses.put("janeiro", 1);
        meses.put("fevereiro", 2);
        meses.put("marco", 3);
        meses.put("abril", 4);
        meses.put("maio", 5);
        meses.put("junho", 6);
        meses.put("julho", 7);
        meses.put("agosto", 8);
        meses.put("setembro", 9);
        meses.put("outubro", 10);
        meses.put("novembro", 11);
        meses.put("dezembro", 12);

        System.out.println(meses.get("Janeiro".toLowerCase()));
 
    }
}
