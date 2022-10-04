package GabrielFarinela;

import java.util.Scanner;

public class Main2 {//2,0/5,0
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int lerMenuInt;
        String lerMenuString;
        Vaga [] estacionamento = new Vaga[269];

        do {
            System.out.println("-----------------------------Menu-------------------------------");
            System.out.println("----------------------------------------------------------------");
            System.out.println("1- Registrar entrada de carro no estacionamento");
            System.out.println("2- Registrar saída de carro no estacionamento");
            System.out.println("3- Mostrar total recebido no dia");
            System.out.println("4- Mostrar carros no estacionamento");
            System.out.println("5- Mostrar carros de um determinado modelo ou ano de fabricação");
            System.out.println("6- Pesquisar carro com determinada placa");
            System.out.println("10- --------------------------fim-------------------------------");

            lerMenuInt = scan.nextInt();

            switch (lerMenuInt) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:

            }
        }
        while (lerMenuInt != 10);
    }
}