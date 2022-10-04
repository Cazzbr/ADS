package GustavoHerpich;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Estacionamento { //1,0/5,0
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int opcao = Integer.parseInt(br.readLine());

        String[] listaCarro = {};

        while (opcao != 0) {
            System.out.println("Digite a opção que deseja: \n " +
                    "1. Registrar entrada de carro \n " +
                    "2. Registrar saída de carro \n " +
                    "3. Mostrar valor recebido \n " +
                    "4. Carros no estacionamento \n " +
                    "5. Buscar carro por modelo \n" +
                    "6. Buscar carro por ano de fabricação \n" +
                    "7. Pesquisar carro por placa \n" +
                    "0. Sair");
        }
    }


    public static void registroEntrada(String[] listaCarro) throws IOException {
        String Modelo = br.readLine();
        int anoFabricacao = Integer.parseInt(br.readLine());
        String placa = br.readLine();

        Carro novo = new Carro(Modelo, anoFabricacao, placa);
        //listaCarro.add(novo); add() só existe em ArrayList
    }
}



