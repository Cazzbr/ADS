import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        PriorityQueue<Integer> pInt = new PriorityQueue<>(Comparator.reverseOrder());

        pInt.offer(20);
        pInt.offer(10);
        System.out.println(pInt.poll());

        PriorityQueue<String> pStr = new PriorityQueue<>();
        pStr.offer("Arnaldo");
        pStr.offer("Marcio");
        pStr.offer("Zeno");
        pStr.offer("Zac");
        System.out.println(pStr.poll());
    }
}
