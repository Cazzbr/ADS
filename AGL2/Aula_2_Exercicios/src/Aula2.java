public class Aula2 {
    public static void main(String[] args) throws Exception {

        System.out.println(" ");
        System.out.println("Utilizando 'metodo_1_convert_to_upper'. Entrada:'Texto convertido para uppercase'");
        String um = Funcoes.metodo_1_convert_to_upper("Texto convertido para uppercase");
        System.out.println("Saída da funçao: " + um);
        
        System.out.println(" ");
        System.out.println("Utilizando 'metodo_2_is_multiple'. Entrada:'(10,20)'");
        boolean dois_1 = Funcoes.metodo_2_is_multiple(10, 20);
        System.out.println("Saída da funçao: " + dois_1);
        System.out.println("Utilizando 'metodo_2_is_multiple'. Entrada:'(10,19)'");
        boolean dois_2 = Funcoes.metodo_2_is_multiple(10, 19);
        System.out.println("Saída da funçao: " + dois_2);

        System.out.println(" ");
        System.out.println("Utilizando 'metodo_3_is_even'. Entrada:'10'");
        boolean tres_1 = Funcoes.metodo_3_is_even(10);
        System.out.println("Saída da funçao: " + tres_1);
        System.out.println("Utilizando 'metodo_3_is_even'. Entrada:'9'");
        boolean tres_2 = Funcoes.metodo_3_is_even(9);
        System.out.println("Saída da funçao: " + tres_2);

        System.out.println(" ");
        System.out.println("Utilizando 'metodo_4_circle_area'. Entrada:'10'");
        double quatro = Funcoes.metodo_4_circle_area(10);
        System.out.println("Saída da funçao: " + quatro);

        System.out.println(" ");
        System.out.println("Utilizando 'metodo_5_roll_the_dice' 6 vezes");
        int cinco_1 = Funcoes.metodo_5_roll_the_dice();
        System.out.println("Saída da funçao: " + cinco_1);
        int cinco_2 = Funcoes.metodo_5_roll_the_dice();
        System.out.println("Saída da funçao: " + cinco_2);
        int cinco_3 = Funcoes.metodo_5_roll_the_dice();
        System.out.println("Saída da funçao: " + cinco_3);
        int cinco_4 = Funcoes.metodo_5_roll_the_dice();
        System.out.println("Saída da funçao: " + cinco_4);
        int cinco_5 = Funcoes.metodo_5_roll_the_dice();
        System.out.println("Saída da funçao: " + cinco_5);
        int cinco_6 = Funcoes.metodo_5_roll_the_dice();
        System.out.println("Saída da funçao: " + cinco_6);

        System.out.println(" ");
        System.out.println("Utilizando 'metodo_6_format_month_to_string'. Entrada:'10'");
        String seis = Funcoes.metodo_6_format_month_to_string(10);
        System.out.println("Saída da funçao: " + seis);

        System.out.println(" ");
        System.out.println("Utilizando 'metodo_7_return_minor_value'. Entrada:'(9, 10, 11)'");
        int sete_1 = Funcoes.metodo_7_return_minor_value(9, 10, 11);
        System.out.println("Saída da funçao: " + sete_1);
        System.out.println("Utilizando 'metodo_7_return_minor_value'. Entrada:'(10, 11, 9)'");
        int sete_2 = Funcoes.metodo_7_return_minor_value(10, 11, 9);
        System.out.println("Saída da funçao: " + sete_2);
        System.out.println("Utilizando 'metodo_7_return_minor_value'. Entrada:'(11, 9, 10)'");
        int sete_3 = Funcoes.metodo_7_return_minor_value(11, 9, 10);
        System.out.println("Saída da funçao: " + sete_3);

        System.out.println(" ");
        System.out.println("Utilizando 'metodo_8_multiply'. Entrada:'(8, 9, 10, 11)'");
        float oito = Funcoes.metodo_8_multiply(8, 9, 10, 11);
        System.out.println("Saída da funçao: " + oito);

        System.out.println(" ");
        System.out.println("Utilizando 'metodo_9_score'. Entrada:'(100)'");
        String nove_1 = Funcoes.metodo_9_score(100);
        System.out.println("Saída da funçao: " + nove_1);
        System.out.println("Utilizando 'metodo_9_score'. Entrada:'(90)'");
        String nove_2 = Funcoes.metodo_9_score(90);
        System.out.println("Saída da funçao: " + nove_2);
        System.out.println("Utilizando 'metodo_9_score'. Entrada:'(76)'");
        String nove_3 = Funcoes.metodo_9_score(76);
        System.out.println("Saída da funçao: " + nove_3);
        System.out.println("Utilizando 'metodo_9_score'. Entrada:'(65)'");
        String nove_4 = Funcoes.metodo_9_score(65);
        System.out.println("Saída da funçao: " + nove_4);
        System.out.println("Utilizando 'metodo_9_score'. Entrada:'(58)'");
        String nove_5 = Funcoes.metodo_9_score(58);
        System.out.println("Saída da funçao: " + nove_5);
        System.out.println("Utilizando 'metodo_9_score'. Entrada:'(41)'");
        String nove_6 = Funcoes.metodo_9_score(41);
        System.out.println("Saída da funçao: " + nove_6);
        System.out.println("Utilizando 'metodo_9_score'. Entrada:'(40)'");
        String nove_7 = Funcoes.metodo_9_score(40);
        System.out.println("Saída da funçao: " + nove_7);
        
        System.out.println(" ");
        System.out.println("Utilizando 'metodo_10_read_and_return_int'.");
        int dez = Funcoes.metodo_10_read_and_return_int();
        System.out.println("Saída da funçao: " + dez);

        System.out.println(" ");
        System.out.println("Utilizando 'metodo_11_classify_number'. Entrada:'(10)'");
        Funcoes.metodo_11_classify_number(10);
        System.out.println("Utilizando 'metodo_11_classify_number'. Entrada:'(0)'");
        Funcoes.metodo_11_classify_number(0);
        System.out.println("Utilizando 'metodo_11_classify_number'. Entrada:'(-5)'");
        Funcoes.metodo_11_classify_number(-5);

        System.out.println(" ");
        System.out.println("Utilizando 'metodo_12_classify_number_using_enum'. Entrada:'(10)'");
        Funcoes.metodo_12_classify_number_using_enum(10);
        System.out.println("Utilizando 'metodo_12_classify_number_using_enum'. Entrada:'(0)'");
        Funcoes.metodo_12_classify_number_using_enum(0);
        System.out.println("Utilizando 'metodo_12_classify_number_using_enum'. Entrada:'(-5)'");
        Funcoes.metodo_12_classify_number_using_enum(-5);
        
        System.out.println(" ");
        System.out.println("Utilizando 'metodo_13_intermediate_letter'. Entrada:'ABCDE'");
        Funcoes.metodo_13_intermediate_letter("ABCDE");
        System.out.println("Utilizando 'metodo_13_intermediate_letter'. Entrada:'ABCDEF'");
        Funcoes.metodo_13_intermediate_letter("ABCDEF");

        System.out.println(" ");
        System.out.println("Utilizando 'metodo_14_show_the_vowels'. Entrada:'A vida é bela!'");
        Funcoes.metodo_14_show_the_vowels("A vida é bela!");

        System.out.println(" ");
        System.out.println("Utilizando 'metodo_15_how_many_consoants'. Entrada:'Se quiser bem feito, faça você mesmo!'");
        int quinze = Funcoes.metodo_15_how_many_consoants("Se quiser bem feito, faça você mesmo!");
        System.out.println(quinze);
    }
}