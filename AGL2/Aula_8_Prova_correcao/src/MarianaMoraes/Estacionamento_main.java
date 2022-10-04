package MarianaMoraes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Estacionamento_main { //3,0/5,0
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String escolha;

        ArrayList<Carro> lista_carros = new ArrayList<>();

        do {
            mostra_menu_principal();
            escolha = (bf.readLine());
            switch (Integer.parseInt(escolha)) {
                case 1 -> registra_entrada(lista_carros);
                case 2 -> registra_saida(lista_carros);
                //case 3 -> mostra_valor();
                case 4 -> System.out.println(lista_carros);
                case 5 -> busca_modelo(lista_carros);
                case 6 -> busca_ano(lista_carros);
                case 7 -> busca_placa(lista_carros);
            }
        } while (Integer.parseInt(escolha) != 8);
    }

    public static void mostra_menu_principal() {
        System.out.println("---------------------------");
        System.out.println("Digite a opção que deseja: \n 1. Registrar entrada de carro \n 2. Registrar saída de carro \n 3. Mostrar valor recebido \n 4. Carros no estacionamento \n 5. Buscar carro por modelo \n6. Buscar carro por ano de fabricação \n7. Pesquisar carro por placa \n8. Sair");
        System.out.println("---------------------------");
    }

    public static void registra_entrada(ArrayList lista_carros) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Primeiro informe os dados do carro:");
        System.out.println("Modelo:");
        String modelo = bf.readLine();

        System.out.println("Ano de fabricação:");
        int ano = Integer.parseInt(bf.readLine());

        System.out.println("Placa");
        String placa = bf.readLine();

        Carro c = new Carro(modelo, ano, placa);
        lista_carros.add(c);
        c.setEntrada();
        System.out.println("AlyssonDornelles.RafaelliSantos.Carro registrado com sucesso!");
    }

    public static void registra_saida(ArrayList lista_carros) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Qual é placa do carro que deseja sair do estacionamento?");
        String placa = bf.readLine();
        if (lista_carros.contains(placa)) {

        }
    }

    /*public static double mostra_valor() {
        return valor;
    }*/ //???

    public static void busca_modelo(ArrayList lista_carros) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Qual é o modelo dos carros?");
        String modelo = bf.readLine();
        if (lista_carros.contains(modelo)) { //aqui tem que ser um objeto RafaelliSantos.Carro pra usar o contains
        }
    }

    public static void busca_ano(ArrayList lista_carros) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Qual é ano de fabricação do carro?");
        int ano = Integer.parseInt(bf.readLine());
        if (lista_carros.contains(ano)) {
        }
    }

    public static void busca_placa(ArrayList lista_carros) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Qual é a placa do carro?");
        String placa = bf.readLine();
        if (lista_carros.contains(placa)) {
        }
    }
}





