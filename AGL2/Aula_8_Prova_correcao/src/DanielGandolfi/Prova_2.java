package DanielGandolfi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Prova_2 {
    public static void main(String[] args) throws IOException {
        Menu();
    }

    public static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void Menu() throws IOException {

        int opcao = 999;
        while (opcao != 0){
            ArrayList<Carro> carros_estacionados = new ArrayList<>();
            System.out.println(" ");
            System.out.println("CRUD - CREATE, REMOVE, UPDATE, DELETE");
            System.out.println("-------------------------------------");
            System.out.println("0 - Finalizar o Programa");
            System.out.println("1 - Registrar Entrada");
            System.out.println("2 - Registrar Saida");
            System.out.println("3 - Total recebido no dia");
            System.out.println("4 - Listar carros estacionados ");
            System.out.println("5 - Pesquisar por modelo ou ano de fabricacao ");
            System.out.println("6 - Pesquisar carro estacionado");
            System.out.println("-------------------------------------");
            System.out.print("Digite a opcao desejada: ");
            opcao = Integer.parseInt(bf.readLine());
            switch (opcao){
                case 0:
                    System.out.println("Finalizando o programa ...");
                    opcao = 0;
                    break;
                case 1:
                    Prova_2_Registros.Registro_Entrada(carros_estacionados);
                    break;
                case 2:
                    Prova_2_Registros.Registro_Saida(carros_estacionados);
                    break;
                case 3:
                    //
                    break;
                case 4:
                    Prova_2_Registros.Lista_Carros(carros_estacionados);
                    break;
                case 5:
                    Prova_2_Registros.Modelo_Fabricacao(carros_estacionados);
                    break;
                case 6:
                    //
                    break;
                default:
                    System.out.println("Opcao Invalida");
                    break;
            }
        }
    }
}
