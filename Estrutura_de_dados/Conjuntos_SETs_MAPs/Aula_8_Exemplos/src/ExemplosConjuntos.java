import java.util.HashSet;
import java.util.TreeSet;

public class ExemplosConjuntos {
    public static void main(String[] args) throws Exception {
        System.out.println("========   Tree ==========");

        TreeSet<Integer> conjuntos = new TreeSet<>();

        conjuntos.add(10);
        conjuntos.add(26);
        conjuntos.add(46);
        conjuntos.add(65);
        conjuntos.add(2);
        conjuntos.add(76);
        
        System.out.println(conjuntos.contains(65));
        System.out.println(conjuntos.contains(100));
        
        conjuntos.add(10);
        
        System.out.println(conjuntos);

        System.out.println("========   Hash ==========");
        HashSet<Integer> conjuntos2 = new HashSet<>();

        conjuntos2.add(10);
        conjuntos2.add(26);
        conjuntos2.add(46);
        conjuntos2.add(65);
        conjuntos2.add(2);
        conjuntos2.add(76);
        
        System.out.println(conjuntos2.contains(65));
        System.out.println(conjuntos2.contains(100));
        
        conjuntos2.add(10);
        
        System.out.println(conjuntos2);
    }
} 
