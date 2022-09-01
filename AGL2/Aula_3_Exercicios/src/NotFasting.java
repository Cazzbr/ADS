import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NotFasting {
    private static final int HAMBURGER_TRADICIONAL = 1;
    private static final double HAMBURGER_TRADICIONAL_PRECO = 29.50;
    private static final int HAMBURGER_VEGANO = 2;
    private static final double HAMBURGER_VEGANO_PRECO = 25.00;
    private static final int HAMBURGER_DUPLO = 3;
    private static final double HAMBURGER_DUPLO_PRECO = 33.20;
    private static final int POKE_SALMAO = 4;
    private static final double POKE_SALMAO_PRECO = 40.00;
    private static final int POKE_ATUM = 5;
    private static final double POKE_ATUM_PRECO = 22.30;
    private static final int POKE_VEGETAIS = 6;
    private static final double POKE_VEGETAIS_PRECO = 19.90;
    private static final int SAND_MORTADELA = 7;
    private static final double SAND_MORTADELA_PRECO = 12.30;
    private static final int SAND_COPA = 8;
    private static final double SAND_COPA_PRECO = 14.00;
    private static final int SAND_PARMA = 9;
    private static final double SAND_PARMA_PRECO= 14.50;
    private static final int PIZZA_MARG = 10;
    private static final double PIZZA_MARG_PRECO = 49.60;
    private static final int PIZZA_CALA = 11;
    private static final double PIZZA_CALA_PRECO = 54.60;
    private static final int PIZZA_DOCE = 12;
    private static final double PIZZA_DOCE_PRECO = 42.60;
    private static final int FRANGO_APIM = 13;
    private static final double FRANGO_APIM_PRECO = 26.70;
    private static final int FRANGO_NORMAL = 14;
    private static final double FRANGO_NORMAL_PRECO = 24.10;
    private static final int TACOS_CARNE = 15;
    private static final double TACOS_CARNE_PRECO = 16.00;
    private static final int TACOS_FILE = 16;
    private static final double TACOS_FILE_PRECO = 18.50;

    static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        System.out.println();
        System.out.println("Bem vindo à rede NotFasting! Veja as delícias do nosso menu:");
        print_menu();
        int quantidade_de_lanches = get_entry_from_user("Quantos lanches vocês gostaria?");
        double total_da_venda = 0.0;
        for (int i=0; i < quantidade_de_lanches; i++){
            int lanche = get_entry_from_user("Digite o número do lanche desejado:");
            total_da_venda += get_food_price(lanche);
        }
        System.out.println("O Total da sua compra foi de R$" + total_da_venda );
    }

    private static double get_food_price(int lanche) {
        switch(lanche){
            case HAMBURGER_TRADICIONAL: return HAMBURGER_TRADICIONAL_PRECO;
            case HAMBURGER_DUPLO: return HAMBURGER_DUPLO_PRECO;
            case HAMBURGER_VEGANO: return HAMBURGER_VEGANO_PRECO;
            case POKE_SALMAO: return POKE_SALMAO_PRECO; 
            case POKE_ATUM: return POKE_SALMAO_PRECO; 
            case POKE_VEGETAIS: return POKE_VEGETAIS_PRECO; 
            case SAND_MORTADELA: return SAND_MORTADELA_PRECO;
            case SAND_COPA: return SAND_COPA_PRECO;
            case SAND_PARMA: return SAND_PARMA_PRECO;
            case PIZZA_MARG: return PIZZA_MARG_PRECO;
            case PIZZA_CALA: return PIZZA_CALA_PRECO;
            case PIZZA_DOCE: return PIZZA_DOCE_PRECO;
            case FRANGO_APIM: return FRANGO_APIM_PRECO;
            case FRANGO_NORMAL: return FRANGO_NORMAL_PRECO;
            case TACOS_CARNE: return TACOS_CARNE_PRECO;
            case TACOS_FILE: return TACOS_FILE_PRECO;
        }
        return 0;
    }

    private static int get_entry_from_user(String txt) throws Exception {
        System.out.println(txt);
        return Integer.parseInt(bf.readLine());
    }

    private static void print_menu(){
        System.out.println();
        System.out.println("HAMBÚRGER");
        System.out.println("    " + HAMBURGER_TRADICIONAL + " --> Hambúrger Tradicional ---------------  R$" + HAMBURGER_TRADICIONAL_PRECO);
        System.out.println("    " + HAMBURGER_VEGANO +      " --> Hambúrger Vegano --------------------  R$" + HAMBURGER_VEGANO_PRECO);
        System.out.println("    " + HAMBURGER_DUPLO +       " --> Hambúrger Duplo ---------------------  R$" + HAMBURGER_DUPLO_PRECO);
        System.out.println("POKE");
        System.out.println("    " + POKE_SALMAO +           " --> Poke de Salmão ----------------------  R$" + POKE_SALMAO_PRECO);
        System.out.println("    " + POKE_ATUM +             " --> Poke de Atum ------------------------  R$" + POKE_ATUM_PRECO);
        System.out.println("    " + POKE_VEGETAIS +         " --> Poke de vegetais --------------------  R$" + POKE_VEGETAIS_PRECO);
        System.out.println("SANDUÍCHE");
        System.out.println("    " + SAND_MORTADELA +        " --> Sanduíche de Mortadela --------------  R$" + SAND_MORTADELA_PRECO);
        System.out.println("    " + SAND_COPA +             " --> Sanduíche de Copa -------------------  R$" + SAND_COPA_PRECO);
        System.out.println("    " + SAND_PARMA +            " --> Sanduíche de Parma-------------------  R$" + SAND_PARMA_PRECO);
        System.out.println("PIZZA");
        System.out.println("    " + PIZZA_MARG +            " --> Pizza Margherita -------------------  R$" + PIZZA_MARG_PRECO);
        System.out.println("    " + PIZZA_CALA +            " --> Pizza Calabresa --------------------  R$" + PIZZA_CALA_PRECO);
        System.out.println("    " + PIZZA_DOCE +            " --> Pizza Doce -------------------------  R$" + PIZZA_DOCE_PRECO);
        System.out.println("FRANGO");
        System.out.println("    " + FRANGO_APIM +           " --> Frango Apimentado ------------------  R$" + FRANGO_APIM_PRECO);
        System.out.println("    " + FRANGO_NORMAL +         " --> Frango Normal ----------------------  R$" + FRANGO_NORMAL_PRECO);
        System.out.println("TACOS");
        System.out.println("    " + TACOS_CARNE +           " --> Tacos de Carne Seca ----------------  R$" + TACOS_CARNE_PRECO);
        System.out.println("    " + TACOS_FILE +            " --> Tacos de Filé ----------------------  R$" + TACOS_FILE_PRECO);
        System.out.println();
    }
}