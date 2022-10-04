package GuilhermeFarinela;

import java.util.Scanner;

public class Exercicio2 { //2,0/5,0
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] lista = new String[269];
        Carro c = new Carro();

        System.out.println("1- Registrar entrada de novo carro no estacionamento: ");
        System.out.println("2- Registrar entrada de novo carro no estacionamento: ");
        System.out.println("3- Registrar entrada de novo carro no estacionamento: ");
        System.out.println("4- Registrar entrada de novo carro no estacionamento: ");
        System.out.println("5- Registrar entrada de novo carro no estacionamento: ");
        System.out.println("6- Registrar entrada de novo carro no estacionamento: ");
        System.out.println("7- Sair do sistema: ");

        int n = scan.nextInt();
        switch (n) {
            case 1:
                c.registraCarro(2022,"FOCUS","DHSD", "ZERO");
            case 2:
        }
    }

}
