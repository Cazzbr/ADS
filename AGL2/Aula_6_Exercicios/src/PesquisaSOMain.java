import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PesquisaSOMain {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {

        boolean keep_voting = true;

        System.out.println();
        System.out.println(" *** Bem Vindo ao consolidador de pesquisa! ***");
        System.out.println();

        int entradas = get_user_input_int("Digite o número de entradas (deve ser maior que 0)!", "quantidade");

        PesquisaSO minha_pesquisa = new PesquisaSO(entradas);
        
        do{
            int vote = get_user_input_int("Digite o voto (1-6) ou '0' para sair:", "voto");
            if (vote == 0){
                keep_voting = false;
            }else{
                keep_voting = minha_pesquisa.addVote(vote);
            }
        }while(keep_voting && !minha_pesquisa.vote_ended());
        System.out.println(" *** Preenchimento da votação finalziado! Veja o resultado abaixo: ***");
        System.out.println(minha_pesquisa);
        if (minha_pesquisa.vote_ended()){
            System.out.println("INFO: Todos os votos previstos foram computados!");
        }else{
            System.out.println("INFO: A contagem dos votos foi encerrada antes do fechar o total de votos previstos!");
        }
    }

    public static int get_user_input_int(String txt, String validator) throws Exception {
        String user_input;
        boolean entry_not_valid = true;
        do{
            System.out.println(txt);
            user_input = bf.readLine();
            
            if ("quantidade".equals(validator)){
                entry_not_valid = !validate_size(user_input);
            }else if ("voto".equals(validator)){
                entry_not_valid = !validate_vote(user_input);
            }else{ return -1;}

            if (entry_not_valid){
                System.out.println();
                System.out.println("O valor digitado é inválido (" + user_input + "). Tente novamente:");
            }
        }while(entry_not_valid);
        
        return Integer.parseInt(user_input);
    }

    public static boolean validate_vote(String txt){
        if (is_int(txt)){
            if("0".equals(txt) || "1".equals(txt) || "2".equals(txt) 
            || "3".equals(txt) || "4".equals(txt) || "5".equals(txt) || "6".equals(txt)){
                return true;
            }
        }
        return false;
    }

    public static boolean validate_size(String txt){
        if (is_int(txt)){
            if(Integer.parseInt(txt) > 0){
                return true;
            }
        }
        return false;
    }

    public static boolean is_int(String txt){
        try{
            Integer.parseInt(txt);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
