import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CarcteresMain {
    static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        
        int number_chars_to_Get = 10;
        
        Caracteres myChars = new Caracteres(number_chars_to_Get);

        for (int i=0; i < number_chars_to_Get; i++){
            myChars.fillArrayCaracteres(get_input("Digite o caractere para o indice " + i + " do array:"));     
        }
        System.out.println();
        System.out.println("O núemro de consoantes do array é: " + myChars.getNumberOfConsonants());
        System.out.println(myChars.getConsonants());
    }

    public static char get_input (String txt) throws Exception {
        System.out.println(txt);
        return bf.readLine().charAt(0);
    }
}
