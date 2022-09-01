import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Informatics {
    private static final int SAQUES = 1;
    private static final int DEPOSITOS = 2;
    private static final int SALDO = 3;
    private static final int CADASTRO = 4;
    private static final int ALTERAR = 5;
    private static final int EXIT = 0;

    private static final int SAQUES_50 = 1;
    private static final int SAQUES_100 = 2;
    private static final int SAQUES_500 = 3;
    private static final int SAQUES_1000 = 4;
    private static final int SAQUES_CUSTON = 0;

    private static final int PADRAO = 1;
    private static final int EXECUTIVO = 2;
    private static final int PREMIUM = 3;

    static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Client> clients = new ArrayList<Client>();
    public static void main(String[] args) throws Exception {
        boolean login = true;
        System.out.println();
        System.out.println("Bem vindo ao banco Informatics! Por favor, digite o número correspondete à transação desejada e tecle enter:");
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
        System.out.println("Menu Principal:");
        System.out.println(SAQUES + " --> Para Saques!");
        System.out.println(DEPOSITOS + " --> Para Depositos!");
        System.out.println(SALDO + " --> Para Verificar seu Saldo!");
        System.out.println(CADASTRO + " --> Para Criar nova Conta!");
        System.out.println(ALTERAR + " --> Para Alterar os dados de Cadastro da Conta!");
        System.out.println(EXIT + " --> Para sair do sistema!");
        System.out.println();
    }

    private static int show_menu() throws Exception {
        boolean wrong_menu_choice = true;
        int m_entry;
        do{
            print_menu();
            m_entry = Integer.parseInt(bf.readLine());
            if (m_entry == SAQUES || m_entry == DEPOSITOS || m_entry == SALDO || m_entry == CADASTRO || m_entry == ALTERAR ||m_entry == EXIT){
                wrong_menu_choice = false;
            }else{
                    System.out.println();
                    System.out.println("__________________________________________________________________________________________");
                    System.out.println("Entrada de Menu inexistente, por favor, entre o número correspondete à transação desejada!");
            }
        } while(wrong_menu_choice);
        return m_entry;
    }

    private static void enter_transaction(int transation) throws Exception {
        switch(transation){
            case SAQUES: do_saques(); break;
            case DEPOSITOS: do_deposits(); break;
            case SALDO: do_saldo(); break;
            case CADASTRO: do_cadastro(); break;
            case ALTERAR: do_alterar();            
        }
    }

    private static void do_exit() {
        System.out.println("Você escolheu a opção " + EXIT + "! O programa será encerrado. Obrigado por sua visita!");
    }

    private static Client get_acc_info_for_transations() throws Exception {
        boolean need_acc_number = true;
        String user_input;
        int acc_entered;
        Client cli = new Client();
        do{
            System.out.println("Digite o número da sua conta ou tecle enter com o campo em branco para sair:");
            user_input = bf.readLine();
            if (user_input.isEmpty()){return null;}
            acc_entered = Integer.parseInt(user_input);
            for (Client client : clients) {
                if (client.acc_number == acc_entered){
                    cli = client;
                    need_acc_number = false;
                }
            }
            if (need_acc_number){
                System.out.println();
                System.out.println("O número de conta '" + acc_entered + "' não existe!");
            }
        }while(need_acc_number);
        return cli;
    }

    private static void do_saques() throws Exception {
        System.out.println("Banco Informatics - Menu de Saques!");
        Client acc_info = get_acc_info_for_transations();
        if (acc_info != null){
            print_menu_saque();
            int requested_amount = Integer.parseInt(bf.readLine());
            requested_amount = convert_requested_amount_to_reais(requested_amount);
            if (verify_if_operation_is_valid(requested_amount, acc_info)){            
                for (Client cli : clients){
                    if (cli.acc_number == acc_info.acc_number){
                        cli.saldo -= requested_amount;
                        System.out.println();
                        System.out.println("Deseja realizar nova transação?");
                    }
                }
            }
        }
    }

    private static boolean verify_if_operation_is_valid(int requested_amount, Client acc_info) {
        if (acc_info.saldo < requested_amount){
            System.out.println("Saldo insuficiente!");
            return false;
        }else if (acc_info.tipo_conta == PADRAO && requested_amount > 2500){
            System.out.println("Contas Padrão tem limite diário de saque de R$ 2.500! Converse com seu gerente!");
            return false;            
        }else if (acc_info.tipo_conta == EXECUTIVO && requested_amount > 5000){
            System.out.println("Contas Executivas tem limite diário de saque de R$ 5.000! Converse com seu gerente!");
            return false;
        }else{
            System.out.println("Seus saque de R$" + requested_amount +" foi autorizado!");
            return true;
        }
    }

    private static int convert_requested_amount_to_reais(int requested_amount) throws Exception{
        if (requested_amount == SAQUES_CUSTON){
            System.out.println("Digite o valor desejado para saque:");
            requested_amount = Integer.parseInt(bf.readLine());
        }else{
            switch(requested_amount){
                case SAQUES_50:requested_amount = 50; break;
                case SAQUES_100:requested_amount = 100; break;
                case SAQUES_500:requested_amount = 500; break;
                case SAQUES_1000:requested_amount = 1000;
            }
        }
        return requested_amount;
    }

    private static void print_menu_saque() {
        System.out.println();
        System.out.println("Menu de saques:");
        System.out.println(SAQUES_50 + " --> Para Saque de R$ 50,00!");
        System.out.println(SAQUES_100 + " --> Para Saque de R$ 100,00!");
        System.out.println(SAQUES_500 + " --> Para Saque de R$ 500,00!");
        System.out.println(SAQUES_1000 + " --> Para Saque de R$ 1.000,00!");
        System.out.println(SAQUES_CUSTON + " --> Para entrar o valor desejado!");
        System.out.println();
    }

    private static void do_deposits() throws Exception {
        System.out.println("Banco Informatics - Menu de depósito!");
        Client acc_info = get_acc_info_for_transations();
        if (acc_info != null){
            System.out.println("Digite o valor desejado para depósito:");
            double deposit_amount = Double.parseDouble(bf.readLine());
            for (Client cli : clients){
                if (cli.acc_number == acc_info.acc_number){
                    cli.saldo += deposit_amount;
                    System.out.println();
                    System.out.println("Seu depósito foi realizado com sucesso! Deseja realizar nova transação?");
                }
            }
        }
    }

    private static void do_saldo() throws  Exception{
        System.out.println("Banco Informatics - Menu de consulta de salo!");
        Client acc_info = get_acc_info_for_transations();
        if (acc_info != null){
            for (Client cli : clients){
                if (cli.acc_number == acc_info.acc_number){
                    System.out.println();
                    System.out.println("Caro "+ cli.name + ", O saldo atual da sua conta " + cli.acc_number + " é de R$" + cli.saldo + "! Deseja realizar nova transação?");
                }
            }
        }
    }

    private static int generate_acc_number(){
        boolean already_used_number;
        int attempt_to_acc_number;
        do {
            already_used_number = false;
            attempt_to_acc_number = (int) ((Math.random() * (9999 - 1000)) + 1000);
            for (Client cli : clients) {
                if (cli.acc_number == attempt_to_acc_number){
                    already_used_number = true;
                    break;
                }
            }
        }while(already_used_number);

        return attempt_to_acc_number;
    }

    private static void do_cadastro() throws Exception {
        String name;
        System.out.println("Você selecionou a opção de casdastro de nova contra!");
        System.out.println("Digite seu nome ou prescione enter com o campo em branco para cancelar:");
        name = bf.readLine();
        if (!name.isEmpty()){
            Client c = new Client();
            c.name = name;
            c.acc_number = generate_acc_number();
            System.out.println();
            System.out.println(c.name + ", sua conta foi criada com sucesso! O número da sua conta é: " + c.acc_number);
            clients.add(c);
        }else{
            System.out.println("Canelando aberturta de conta, você será direcionado para sua homepage!");
        }
    }

    private static void do_alterar() throws Exception {
        System.out.println("Banco Informatics - Menu de alteração de dados de cadastro!");
        Client acc_info = get_acc_info_for_transations();
        if (acc_info != null){
            System.out.println("Digite o novo nome para conta ou deixe em branco para manter seu nome atual '"+ acc_info.name + "':");
            String new_client_name = bf.readLine();
            if (new_client_name.isEmpty()){
                new_client_name = acc_info.name;
            }
            System.out.println("a classificação da sua conta atual é '" + acc_info.tipo_conta + "'. Selecione o número correspondete para alerar ou deixe em branco para manter!");
            print_menu_change_acc_type();
            String new_acc_type = bf.readLine();
            int new_acc_type_int = acc_info.tipo_conta;
            if (!new_acc_type.isEmpty()){
                new_acc_type_int = Integer.parseInt(new_acc_type);
            }
            save_to_data_base(acc_info.acc_number, new_client_name, new_acc_type_int);
        }
    }

    private static void save_to_data_base(int acc_number, String new_client_name, int new_acc_type) {
        for (Client cli : clients){
            if (cli.acc_number == acc_number){
                cli.name = new_client_name;
                cli.tipo_conta = new_acc_type;
                System.out.println();
                System.out.println("Os dados da sua conta foram atualizado! Deseja realizar nova transação?");
            }
        }
    }

    private static void print_menu_change_acc_type() {
        System.out.println();
        System.out.println("Menu de mudança de conta:");
        System.out.println(PADRAO + " --> Para conta padrão!");
        System.out.println(EXECUTIVO + " --> Para conta Executiva!");
        System.out.println(PREMIUM + " --> Para nossa conta Premuim sem limite de saque diário!");
        System.out.println();
    }
}