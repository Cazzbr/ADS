package AlexPimentel;

import java.time.LocalDate;
import java.util.Scanner;

public class Ex_2 { //4,0/5,0
    public static void main(String[] args) {
        Carro carro;
        Estacionamento estacionamento;

        menu();
    }

    private static void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Menu" +
                "\n1 | Registrar carro no estacionamento" +
                "\n2 | Registrar saida de um carro" +
                "\n3 | Total de pagamentos no dia corrente" +
                "\n4 | Mostrar carros que estão no estacionamento" +
                "\n5 | Pesquisar carros por modelo" +
                "\n6 | Pesquisar carros por ano de fabricação" +
                "\n7 | Pesquisar se um carro está estacionado por placa" +
                "\n8 | Fechar o sistema");

        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1: {
                registrarCarro();
                return;
            }
            case 2: {
                //saidaCarro();
            }
        }
    }

    private static void saidaCarro(Estacionamento estacionamento) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha o carro para registrar saída:");
        estacionamento.carrosEstacionados();

    }

    private static void registrarCarro() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Ano de fabricação: ");
        int anoFabricacao = scanner.nextInt();
        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        Carro carro = new Carro(modelo, anoFabricacao, placa);
        Estacionamento.addCarro(carro);
    }
}
