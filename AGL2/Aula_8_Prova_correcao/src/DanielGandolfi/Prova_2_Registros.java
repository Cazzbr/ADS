package DanielGandolfi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Prova_2_Registros { //4,0/5,0

    public static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void Registro_Entrada(ArrayList<Carro> carros_estacionados) throws IOException {
        if (carros_estacionados.size() == 269){
            System.out.println("O estacionamento esta cheio, todas as vagas foram ocupadas");
        }
        else{
            System.out.print("Digite o modelo do carro: ");
            String modelo = bf.readLine();
            System.out.println("Digite o ano de fabricacao: ");
            int ano = Integer.parseInt(bf.readLine());
            System.out.print("Digite a placa: ");
            String placa = bf.readLine();
            String horario = null;
            horario = Formato_Hora(horario);
            System.out.println("Horario de entrada: " + horario);
            int vaga = carros_estacionados.size() + 1;
            System.out.println("Vaga: " + vaga);
            Carro carro_estacionado = new Carro(modelo, ano, placa, horario, vaga);
            carros_estacionados.add(carro_estacionado);
            System.out.println("RafaelliSantos.Carro Cadastrada");
        }
    }

    public static String Formato_Hora(String horario){
        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime agora = LocalDateTime.now();
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        horario = agora.format(formatter);
        return horario;
    }

    public static void Registro_Saida(ArrayList<Carro> carros_estacionados){
        if (carros_estacionados.size() == 0) {
            System.out.println("O estacionamento esta vazio");
        }
        else{
            System.out.println("Carros: ");
            for (int i = 0; i < carros_estacionados.size(); i++) {
                System.out.println(i + 1 + " - " + carros_estacionados.get(i));
            }
            System.out.print("Escolha o numero do carro que vai sair: ");
            try {
                int remover = Integer.parseInt(bf.readLine());
                carros_estacionados.remove(remover - 1);
            } catch (Exception e) {
                System.out.println("Opcao Invalida");
            }
        }
    }

    public static void Lista_Carros(ArrayList<Carro> carros_estacionados){
        if (carros_estacionados.size() == 0) {
            System.out.println("A lista de carros estacionados esta vazia");
        }
        else {
            for (int i = 0; i < carros_estacionados.size(); i++) {
                System.out.println(i + 1 + " - " + carros_estacionados.get(i));
            }
        }
    }

    public static void Modelo_Fabricacao(ArrayList<Carro> carros_estacionados) throws IOException {
        if (carros_estacionados.size() == 0) {
            System.out.println("A lista de carros estacionados esta vazia");
        }
        else {
            int opcao = 999;
            System.out.println("Selecione o que deseja pesquisar: ");
            System.out.println("1 - Modelo");
            System.out.println("2 - Ano de fabricacao ");
            opcao = Integer.parseInt(bf.readLine());
            System.out.println("Digite a opcao desejada: ");
            switch (opcao){
                case 1:
                    System.out.println("Digite o modelo: ");
                    String modelo = bf.readLine();
                    for (int i = 0; i < carros_estacionados.size(); i++) {
                        if (modelo.equals(carros_estacionados)){// faltou o get (.get(i).getModelo())
                            System.out.println(carros_estacionados.get(i));
                        }
                        else{
                            System.out.println("Modelo nao encontrado");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Digite o modelo: ");
                    int ano  = Integer.parseInt(bf.readLine());
                    for (int i = 0; i < carros_estacionados.size(); i++) {
                        //if (carros_estacionados.get(i)){
                            System.out.println(carros_estacionados.get(i));
                       // }
                        //else{
                            System.out.println("Ano nao encontrado");
                       // }
                    }
                    break;
                default:
                    System.out.println("Opcao Invalida");
                    break;
            }

        }
    }



}
