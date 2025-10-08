import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String exp = scan.nextLine();
        String[] simbolos = exp.split(" ");

        Queue<String> filaInfixa = new LinkedList<>();

        for (String simb : simbolos) {
            filaInfixa.add(simb);
        }

        Queue<String> posFixa = convertQueue(filaInfixa);
        System.out.println(posFixa);

        System.out.println(avaliaExpressao(posFixa));

        scan.close();
    }

    private static double avaliaExpressao (Queue<String> q) {
        Stack<Double> pilhaCalc = new Stack<>();
        while (!q.isEmpty()) {
            String simbFila = q.poll();
            if (isInteger(simbFila)){
                pilhaCalc.add(Double.parseDouble(simbFila));
            } else {
                double a = pilhaCalc.pop();
                double b = pilhaCalc.pop();
                pilhaCalc.add(calculaExpressao(a, simbFila, b));
            }
        }
        return pilhaCalc.pop();
    }

    private static double calculaExpressao(double a, String op, double b) {
        switch (op) {
            case "+":
                return b + a;
            case "-":
                return b - a;
            case "*":
                return b * a;
            case "/":
                if  (a == 0.0) throw new ArithmeticException("Dividido por zero");
                return b / a;
            default:
                throw new IllegalArgumentException("Operador inválido: " + op);
        }
    }

    private static Queue<String> convertQueue(Queue<String> filaInfixa) {
        Set<String> operadores = Set.of("+", "-", "*", "/");
        Stack<String> pilhaConv = new  Stack<>();
        Queue<String> filaPosFixa = new LinkedList<>();
        Map<String, Integer> operatorsPrio = getOperatorsPrioMap();
        while (!filaInfixa.isEmpty()) {
            String simbFila = filaInfixa.poll();
            if (isInteger(simbFila)) {
                filaPosFixa.add(simbFila);
            } else if (simbFila.equals("(")) {
                pilhaConv.add(simbFila);
            } else if (operadores.contains(simbFila)) {
                while (!pilhaConv.isEmpty() && isHighOrEqualPrioOperator(operatorsPrio, pilhaConv.peek(), simbFila)) {
                    filaPosFixa.add(pilhaConv.pop());
                }
                pilhaConv.add(simbFila);
            } else if (simbFila.equals(")")){
                while (!pilhaConv.peek().equals("(")) {
                    filaPosFixa.add(pilhaConv.pop());
                }
                pilhaConv.pop();
            } else {
                throw new IllegalArgumentException("Argumnto inválido: '" + simbFila +  "'");
            }

        }
        while (!pilhaConv.isEmpty()) {
            filaPosFixa.add(pilhaConv.pop());
        }
        return filaPosFixa;
    }

    private static Map<String, Integer> getOperatorsPrioMap() {
        Map<String, Integer> operatorsPrio =  new HashMap<>();
        operatorsPrio.put("(", 0);
        operatorsPrio.put(")", 0);
        operatorsPrio.put("+", 1);
        operatorsPrio.put("-", 1);
        operatorsPrio.put("*", 2);
        operatorsPrio.put("/", 2);
        return operatorsPrio;
    }

    private static boolean isHighOrEqualPrioOperator(Map<String, Integer> prio, String a, String b) {
        // A calculadora irá suportar a seguintes operações:
        // Abre e fecha parênteses (símbolos “(“ e “)”) - prioridade 0 (*)
        // Adição (símbolo “+”) e Subtração (símbolo “-”) - prioridade 1 (*)
        // Multiplicação (símbolo “*” e Divisão (símbolo “/”) - prioridade 2 (*)

        if (prio.get(a) >= prio.get(b)) {
            return true;
        }
        return false;
    }

    private static boolean isInteger(String str){ 
        if (str == null || str.isEmpty()) return false;
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
