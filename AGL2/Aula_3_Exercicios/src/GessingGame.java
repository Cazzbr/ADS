import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GessingGame {
    static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception{
        print_welcome_msg();
        int max_value;
        do{ 
            max_value = get_user_input("Digite um valor inteiro para valor máximo ou enter com campo em branco para encerrar o jogo:");
            if (max_value != 0){
                game_loop(max_value);
            }
        }while(max_value != 0);
    }

    private static void game_loop(int max_value) throws Exception {
        int sorted_value = (int) ((Math.random() * (max_value - 1)) + 1);
        int user_input_value;
        for (int i = 20; i > 0; i--){
            user_input_value = get_user_input("Tente adivinhar o número sorteado, ele está entre '1' e '" + max_value + "'!");
            if (user_input_value == sorted_value){
                print_won_the_Game(sorted_value, i);
                break;
            }else{
                if ( i != 1){ 
                    System.out.println("O número sorteado não é '" + user_input_value + "'! Tente novamente:");
                }else{
                    print_game_over(user_input_value, sorted_value);
                    
                }
            }
        }        
    }

    private static void print_game_over(int user_input_value, int sorted_value) {
        System.out.println("O número sorteado não é '" + user_input_value + "'!");
        System.out.println();
        System.out.println("###################### GAME OVER #####################");
        System.out.println("Suas 20 tentativas terminaram!");
        System.out.println("O número sorteado era '" + sorted_value + "'!");
        System.out.println("#####################################################");
        System.out.println();
        System.out.println("Vamos jogar novamente?");
    }

    private static void print_won_the_Game(int sorted_value, int i) {
        System.out.println();
        System.out.println("#####################################################");
        System.out.println("PARABENS! Você adivinhou, o número sorteado foi '" + sorted_value + "'!");
        System.out.println("Você fez " + i + " pontos!");
        System.out.println("#####################################################");
        System.out.println();
        System.out.println("Vamos jogar novamente?");
    }

    private static int get_user_input(String txt) throws Exception{
        System.out.println();
        System.out.println(txt);
        String input = bf.readLine();
        if (input.isEmpty()){return 0;}
        return Integer.parseInt(input);
    }

    public static void print_welcome_msg() {
        System.out.println("Bem vindo ao Jogo da Adivinhação!");
        System.out.println("O jogo funciona desta forma:");
        System.out.println("  * Primeiro você digita um número que representa o valor máximo do jogo!");
        System.out.println("  * Nós vamos sortear um número inteiro entre 1 e o número escolhido por você!");
        System.out.println("  * Você tem 20 tentativas para adivinhar o número sorteado!");
        System.out.println();
        System.out.println("Parece divertido? Vamos começar!");
    }
}
