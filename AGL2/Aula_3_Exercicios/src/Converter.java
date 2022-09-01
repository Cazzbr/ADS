import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Converter {
    private static final int C_TO_F = 1;
    private static final int C_TO_K = 2;
    private static final int F_TO_C = 3;
    private static final int F_TO_K = 4;
    private static final int K_TO_C = 5;
    private static final int K_TO_F = 6;
    private static final int EXIT = 0;

    static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        boolean login = true;
        System.out.println();
        System.out.println("Bem vindo ao Temperature Converter! Por favor, digite o número correspondete à conversão desejada e tecle enter:");
        do {
            int menu_entry = show_menu();
            if (menu_entry != EXIT){
                enter_transaction(menu_entry);
            }else{
                login = false;
                do_exit();
            }
        }while(login);
    }

    private static void print_menu(){
        System.out.println();
        System.out.println(C_TO_F + " --> Converter de Celsius (Tc) para Fahrenheit (Tf)!");
        System.out.println(C_TO_K + " --> Converter de Celsius (Tc) para Kelvin (Tk)!");
        System.out.println(F_TO_C + " --> Converter de Fahrenheit (Tf) para Celsius (Tc)!");
        System.out.println(F_TO_K + " --> Converter de Fahrenheit (Tf) para Kelvin (Tk)!");
        System.out.println(K_TO_C + " --> Converter de Kelvin (Tk) para Celsius (Tc)!");
        System.out.println(K_TO_F + " --> Converter de Kelvin (Tk) para Fahrenheit (Tf)!");
        System.out.println(EXIT + " --> Para sair do Conversor!");
        System.out.println();
    }

    private static int show_menu() throws Exception {
        boolean wrong_menu_choice = true;
        int m_entry;
        do{
            print_menu();
            m_entry = Integer.parseInt(bf.readLine());
            if (m_entry == C_TO_F || m_entry == C_TO_K || m_entry == F_TO_C || m_entry == F_TO_K || m_entry == K_TO_C || m_entry == K_TO_F || m_entry == EXIT){
                wrong_menu_choice = false;
            }else{
                    System.out.println();
                    System.out.println("__________________________________________________________________________________________");
                    System.out.println("Entrada de Menu inexistente, por favor, entre um número de conversão válido!");
            }
        } while(wrong_menu_choice);
        return m_entry;
    }

    private static void enter_transaction(int transation) throws Exception {
        switch(transation){
            case C_TO_F: convert_C_TO_F(); break;
            case C_TO_K: convert_C_TO_K(); break;
            case F_TO_C: convert_F_TO_C(); break;
            case F_TO_K: convert_F_TO_K(); break;
            case K_TO_C: convert_K_TO_C(); break;
            case K_TO_F: convert_K_TO_F();            
        }
    }

    private static void convert_C_TO_F() throws Exception {
        double input_temp = get_input_temperature("Entre a temperatura em Celsius (Tc):");
        double converted_temp = (input_temp*9/5) + 32;
        print_result(input_temp, converted_temp, "Celsius", "Fahrenheit");
    }

    private static void convert_C_TO_K() throws Exception {
        double input_temp = get_input_temperature("Entre a temperatura em Celsius (Tc):");
        double converted_temp = input_temp + 273.0;
        print_result(input_temp, converted_temp, "Celsius", "Kelvin");
    }

    private static void convert_F_TO_C() throws Exception {
        double input_temp = get_input_temperature("Entre a temperatura em Fahrenheit (Tf):");
        double converted_temp = ((input_temp - 32)/9)*5;
        print_result(input_temp, converted_temp, "Fahrenheit", "Celsius");
    }

    private static void convert_F_TO_K() throws Exception {
        double input_temp = get_input_temperature("Entre a temperatura em Fahrenheit (Tf):");
        double converted_temp = (((input_temp - 32)/9)*5) + 273;
        print_result(input_temp, converted_temp, "Fahrenheit", "Kelvin");
    }

    private static void convert_K_TO_C() throws Exception {
        double input_temp = get_input_temperature("Entre a temperatura em Kelvin (Tk):");
        double converted_temp = input_temp - 273;
        print_result(input_temp, converted_temp, "Kelvin", "Celsius");
    }

    private static void convert_K_TO_F() throws Exception {
        double input_temp = get_input_temperature("Entre a temperatura em Kelvin (Tk):");
        double converted_temp = (((input_temp - 273)/5)*9) + 32;
        print_result(input_temp, converted_temp, "Kelvin", "Fahrenheit");
    }

    private static void do_exit() {
        System.out.println("Você escolheu a opção " + EXIT + "! O programa será encerrado. Obrigado por sua visita!");
    }

    private static double get_input_temperature(String txt) throws Exception {
        System.out.println(txt);
        return Double.parseDouble(bf.readLine());
    }

    private static void print_result(double input_temp, double converted_temp, String input_name, String output_name) {
        System.out.println();
        System.out.println(input_temp + " graus " + input_name + " equivalem à " + converted_temp + " graus " + output_name + "! Voltando para o Menu!");
    
    }
}
