import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Funcoes {
    public static String metodo_1_convert_to_upper(String text) {
        return text.toUpperCase();
    }

    public static boolean metodo_2_is_multiple(int numero_1, int numero_2) {
        if (numero_1 % numero_2 == 0 || numero_2 % numero_1 == 0){
            return true;
        }
        return false;
    }

    public static boolean metodo_3_is_even(int number) {
        if (number % 2 == 0){
            return true;
        }
        return false;
    }

    public static double metodo_4_circle_area(double raio) {
        return (Math.PI * Math.pow(raio, 2));    
    }

    public static int metodo_5_roll_the_dice() {
        int max = 6;
        int min = 1;
        int range = max - min + 1;
        return (int) ((Math.random() * range) + min);
    }

    public static String metodo_6_format_month_to_string(int month) {   
        switch(month){
            case 1: {return "Janeiro";}
            case 2:{return "Fevereiro";}
            case 3:{return "Março";}
            case 4:{return "Abril";}
            case 5:{return "Maio";}
            case 6:{return "Junho";}
            case 7:{return "Julho";}
            case 8:{return "Agosto";}
            case 9:{return "Setembro";}
            case 10:{return "Outubro";}
            case 11:{return "Novembro";}
            case 12:{return "Desembro";}
        }
        return "O número nao pode ser convertido, pois não é um mês válido";
    }

    public static int metodo_7_return_minor_value(int value_1, int value_2, int value_3) {
        if (value_1 < value_2 && value_1 < value_3){
            return value_1;
        }
        else if (value_2 < value_3){
            return value_2;
        }
        else {
            return value_3;
        }
    }

    public static float metodo_8_multiply(int val_1, int val_2, int val_3, int val_4) {
        return (float)(val_1 * val_2 * val_3 * val_4);
    }

    public static String metodo_9_score(int grade) {
        if (grade > 100){return "Nota inválida, deve ser manor que 100";}
        else if (grade > 90) {return "AA";}
        else if (grade > 80) {return "AB";}
        else if (grade > 70) {return "BB";}
        else if (grade > 60) {return "BC";}
        else if (grade > 50) {return "CD";}
        else if (grade > 40) {return "DD";}
        return "Fail";
    }

    public static int metodo_10_read_and_return_int() throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        float number = Float.parseFloat(bf.readLine());
        return Math.round(number);
    }

    public static void metodo_11_classify_number(float number) {
        if (number == 0){
            System.out.println("O número recebido é zero");
        }
        else if (number  > 0 ){
            System.out.println("O número recebido é positivo");
        }
        else{
            System.out.println("O número recebido é negativo");
        }
    
    }

    public enum Numbers{
        NEGATIVO("O número recebido é negativo"),
        ZERO("O número recebido é zero"),
        POSITIVO("O número recebido é positivo");
    
        private final String classificacao;
        Numbers(String classificacao){
            this.classificacao = classificacao;
        }
        public String getClassificacao(){
            return this.classificacao;
        }
    }

    public static void metodo_12_classify_number_using_enum(float number) {
        if (number == 0){
            System.out.println(Numbers.ZERO.getClassificacao());
        }
        else if (number  > 0 ){
            System.out.println(Numbers.POSITIVO.getClassificacao());
        }
        else{
            System.out.println(Numbers.NEGATIVO.getClassificacao());
        }
    
    }

    public static void metodo_13_intermediate_letter(String text) {
        int string_len = text.length();
        if (string_len % 2 == 0){
            System.out.println(text.substring((string_len/2)-1, (string_len/2)+1));
        }
        else {
            System.out.println(text.substring((string_len-1)/2, (string_len+1)/2));
        }
    }

    public static void metodo_14_show_the_vowels(String text) {
        for (int i = 0; i < text.length(); i++){
            char ch = text.charAt(i);
            int ascii = (int) ch;
            if (ascii == 65 || ascii == 69  || ascii == 73 || ascii == 79 || ascii == 85 || 
                ascii == 97 || ascii == 101 || ascii == 105 || ascii == 111 ||  ascii == 117 || 
                (ascii > 192 && ascii < 255 && ascii != 231 && ascii != 199)){
                System.out.println(ch);
            }
        }
    }

    public static int metodo_15_how_many_consoants(String text) {
        int number_of_consoants = 0;
        for (int i = 0; i < text.length(); i++){
            char ch = text.charAt(i);
            int ascii = (int) ch;
            if ((ascii >= 66 && ascii <= 90) || (ascii >= 98 && ascii <= 122) || ascii == 231 || ascii == 199){
                if (ascii != 69 && ascii != 73 && ascii != 79 && ascii != 85 &&
                    ascii != 101 && ascii != 105 && ascii != 111 &&  ascii != 117){
                        number_of_consoants += 1;
                }
            }
        }
        return number_of_consoants;
    }    
}