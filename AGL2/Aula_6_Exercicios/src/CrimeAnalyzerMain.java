import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CrimeAnalyzerMain {

    static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        CrimeAnalyzer crime1 = new CrimeAnalyzer();

        System.out.println();
        System.out.println(" *** Por favor, responda as questões abaixo: ***");

        for (int i = 0; i < crime1.getNumeroPerguntas(); i++){
            crime1.setResposta(get_user_input(crime1.getPergunta(i)), i);
        }
        System.out.println();
        System.out.println(crime1.getClassificacao());
        System.out.println();
    }

    private static int get_user_input(String pergunta) throws Exception {
        boolean wrong_answer = true;
        String user_input;
        int resposta = -1;
        do{
            System.out.println();
            System.out.println(pergunta);
            System.out.println("Digite 'S' para Sim ou 'N' para Não: ");
            user_input = bf.readLine();
            if ("S".equals(user_input) || "s".equals(user_input) || "SIM".equals(user_input) || "sim".equals(user_input) || "Sim".equals(user_input)){
                resposta = 1;
                wrong_answer = false;
            } else if ("N".equals(user_input) || "n".equals(user_input) || "NÃO".equals(user_input) || "não".equals(user_input) || "Não".equals(user_input)){
                resposta = 0;
                wrong_answer = false;
            } else {
                System.out.println();
                System.out.println("Sua resposta foi inválida! Tente novamente!");
            }
        }while(wrong_answer);
        return resposta;
    }
}
