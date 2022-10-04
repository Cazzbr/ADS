package GabrielFarinela;

import java.util.*;

public class Main { //3,0/5,0
    public static void main(String[] args) { //código não-modularizado
        Scanner scan = new Scanner(System.in);
        Map<String,String> original = new HashMap<>();
        Map<String,String> aula = new HashMap<>();
        Queue<String> filaInfixa = new LinkedList<String>();
        int count = 0;

        int N = scan.nextInt();

        if(1<= N && N <=50) {
            while(N != 0) {
                for (int i = 0; i < N-1; i++) {
                    String expressao = scan.nextLine();
                    String[] s = expressao.split(" ");

                    for (String simb : s) {
                        filaInfixa.add(simb);
                    }

                    original.put(filaInfixa.poll(), filaInfixa.poll());
                }
                int M = scan.nextInt();

                for (int i = 0; i < M-1; i++) {
                    String expressao = scan.nextLine();
                    String[] s = expressao.split(" ");

                    for (String simb : s) {
                        filaInfixa.add(simb);
                    }

                    original.put(filaInfixa.poll(), filaInfixa.poll());
                }

                if (original.equals(aula)) {// isto não funciona pois está comparando se os dois mapas são iguais
                    count ++;
                }

                System.out.println(count);

                N = scan.nextInt();
            }
        }
    }
}
