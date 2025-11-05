import java.util.HashMap;

public class ExemplosMapas_2 {
    public static void main(String[] args) throws Exception {
        System.out.println("========   Tree ==========");

        String input_word_1 = "O rato roeu a roupa do rei de roma";
        String input_word_2 = "tres pratos de trigo para tres tigres tristes";

        conta_letras(input_word_1);
        conta_letras(input_word_2);

    }

    public static void conta_letras(String input_word) {
        HashMap<Character, Integer> conta = new HashMap<>();

        for (int i = 0; i < input_word.length(); i++){
            char c = input_word.charAt(i);
            if (conta.containsKey(c)){
                conta.replace(c, conta.get(c) + 1);
            } else {
                conta.put(c, 1);
            }
        }
        System.out.println(conta);
    }
}
