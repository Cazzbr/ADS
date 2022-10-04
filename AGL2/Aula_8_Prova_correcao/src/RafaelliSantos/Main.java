package RafaelliSantos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main { //4,0/5,0
    private static int contEst = 0;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        Carro c1 = new Carro("Hb20",
                2013,
                "IPF2A10");

        Carro c2 = new Carro("Sandero",
                2019,
                "OXE7705");

        Estacionamento e[] = new Estacionamento[269];

        do {
            menu();
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    registraEstacionamento(e, c1);
                case 2:
                    registrarSaida(c1.getPlaca(), e);
                case 3:

                case 4:
                    for (int i = 0; i < contEst; i++) {
                        if (e[i].getSaida() == null) {
                            System.out.println(e[i]);
                        }
                    }
                case 5:

                case 6:

                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao == 0);
    }

    public static void menu() {
        System.out.println("Menu AlyssonDornelles.RafaelliSantos.Estacionamento:");
        System.out.println("1. Registrar Entrada");
        System.out.println("2. Registrar Saída");
        System.out.println("3. Lucro do dia");
        System.out.println("4. Vagas ocupadas");
        System.out.println("5. Listar Carros");
        System.out.println("6. Pesquisar por placa");
        System.out.println("0. SAIR");
        System.out.println("Opcao:");
    }

    public static void registraEstacionamento(Estacionamento es[], Carro c1) {
        String horaEntrada = registrarHora();

        if (contEst < 269) {
            es[contEst] = new Estacionamento(contEst, c1, horaEntrada);
        }
        contEst++;
    }

    public static String registrarHora() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        String hora = agora.format(formatter);
        return hora;
    }

    public static void registrarSaida(String placa, Estacionamento es[]) {
        String horaSaida = registrarHora();

        for (int i = 0; i < es.length; i++) {

            if (es[i].getPlaca().equals(placa)) {
                es[i].setSaida(horaSaida);
            }
        }
    }
    public static void mostrarAnoModelo(String ano, String modelo){

    }
 }
