package MatheusMayrer;

import java.util.Scanner;

public class Att2 { //5,0/5,0
    public static void main(String[] args) {
        Carro[] lista_carros = new Carro [269];
        int valor_recebido = 0;
        int controlador_carros = 0;
        char controlador_menu = 'a';
        while (controlador_menu != 'g'){
            Menu();
            controlador_menu = RecebeChar();
            if (controlador_menu == 'a'){
                if (controlador_carros < 269){
                    lista_carros [controlador_carros] = CriaCarro();
                    lista_carros [controlador_carros].setVaga(controlador_carros);
                    controlador_carros++;
                } else {
                    System.out.println("AlyssonDornelles.RafaelliSantos.Estacionamento cheio");
                }
            } //usar else if nesses casos ou um switch
            if (controlador_menu == 'b') {
                System.out.println("O carro de qual vaga queres retirar");
                int var_retirada_carro = RecebeInteiro();
                int valor_pagar = 10;
                //int valor_pagar = (lista_carros[var_retirada_carro].getHorario_entrada() - LocalDateTime.now()) * lista_carros[var_retirada_carro].getValor_hora();
                valor_recebido = valor_recebido + valor_pagar;
                System.out.println("Valor a pagar" + valor_pagar);
                lista_carros[var_retirada_carro] = null;
            }
            if (controlador_menu == 'c') {
                System.out.println("Valor total recebido no dia: " + valor_recebido);
            }
            if (controlador_menu == 'd'){
                for (int i = 0; i < controlador_carros; i++){
                    System.out.println(lista_carros[i].toString());
                }
            }
            if (controlador_menu == 'e'){
                System.out.println("Insira o número referene a o que queres comparar: ano de fabricação ou modelo" +
                        "\n1 - modelo" +
                        "\n2 - ano de fabricação");
                int variavel_comparador = RecebeInteiro();
                if (variavel_comparador == 1) {
                    System.out.println("Qual modelo queres saber?");
                    String variavel_modelo = RecebeString();
                    for (int i = 0; i < controlador_carros; i++){
                        if (lista_carros[i].getModelo().equals(variavel_modelo)){
                            System.out.println(lista_carros[i].toString());
                        }
                    }
                }
                if (variavel_comparador == 2){
                    System.out.println("Qual ano queres saber?");
                    int variavel_ano = RecebeInteiro();
                    for (int i = 0; i < controlador_carros; i++){
                        if (lista_carros[i].getAno_fabricacao() == variavel_ano){
                            System.out.println(lista_carros[i].toString());
                        }
                    }
                }
            }
            if (controlador_menu == 'f'){
                System.out.println("Qual placa queres pesquisar");
                String variavel_placa = RecebeString();
                int variavel_controle_placa = 0;
                for (int i = 0; i < controlador_carros; i++) {
                    if (lista_carros[i].getPlaca().equals(variavel_placa)) {
                        variavel_controle_placa = 1;
                    }
                }
                    if (variavel_controle_placa == 1){
                        System.out.println("Ainda está estacionado");
                    } else{
                        System.out.println("Não há carros com essa placa estacionado");
                    }
                }
            }
        }
    public static void Menu (){
        System.out.println("O que desejas fazer?" +
                "\na - registrar entrada de novo carro" +
                "\nb - regustrar sauda de carro" +
                "\bc - ver o valor total do dia" +
                "\nd - ver quais carros estão no estacionamento" +
                "\ne - ver carros de determinado modelo ou ano" +
                "\nf - pesquisar carro pela placa" +
                "\ng - fechar o sistema");
    }
    public static char RecebeChar(){
        Scanner input = new Scanner(System.in);
        return input.next().charAt(0);
    }
    public static String RecebeString (){
        Scanner input = new Scanner(System.in);
        return input.next();
    }
    public static int RecebeInteiro (){
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
    public static Carro CriaCarro (){
        System.out.println("Qual o modelo do carro?");
        String modelo = RecebeString();
        System.out.println("Qual o ano de fabricação?");
        int ano = RecebeInteiro();
        System.out.println("Qual a placa do carro?");
        String placa = RecebeString();
        int valor = 0;
        if (ano > 2020){
            valor = 10;
        } else {
            valor = 5;
        }
        return new Carro (modelo, ano, placa, valor);
    }
}
