package ErickNagoski;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

public class Main2 {//4,5/5,0
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        boolean execucao = true;

        ArrayList<Vaga> vagas = new ArrayList<>();
        float totalDia = 0;
        while (execucao) {


            switch (menu()) {
                case 1 -> {
                    System.out.print("Modelo:");
                    String modelo = br.readLine();
                    System.out.print("ano: ");
                    int ano = Integer.parseInt(br.readLine());
                    System.out.print("Placa*: ");
                    String placa = br.readLine();
                    Carro c = new Carro(modelo, ano, placa);
                    Vaga v = new Vaga(c, new Date(), 1.50f);
                    vagas.add(v);
                }
                case 2 -> {
                    System.out.println("Selecione a placa");
                    for (int i = 0; i < vagas.size(); i++) {
                        System.out.println("1 - " + vagas.get(i).getCarro().getPlaca());
                    }
                    System.out.println("Opção: ");
                    int id = Integer.parseInt(br.readLine());
                    Vaga fechar = vagas.get(id);
                    Date hora = new Date();
                    fechar.setHoraSaida(hora);
                    totalDia += fechar.getValorTotal();
                    vagas.remove(id);
                }
                case 3 -> System.out.println("Total do dia: " + totalDia);
                case 4 -> {
                    System.out.println("Carros");
                    for (Vaga vaga : vagas) {
                        System.out.println(vaga.getCarro().toString());
                    }
                }
                case 5 -> {
                    System.out.print("Placa*: ");
                    String P = br.readLine();
                    System.out.print("Ano: ");
                    int year = Integer.parseInt(br.readLine());
                    for (Vaga vaga : vagas) {

                    }
                }
                case 6 -> {
                    System.out.print("Placa*: ");
                    String pl = br.readLine();
                    for (Vaga vaga : vagas) {
                        if (vaga.getCarro().getPlaca().equals(pl)) {
                            System.out.println("Ainda está estacionado");
                        }
                    }
                }
                case 0 -> execucao = false;
            }

        }


    }

    public static int menu() throws IOException {
        System.out.println("1 - Registrar entrada");
        System.out.println("2 - Registrar saída");
        System.out.println("3 - Faturamento do dia");
        System.out.println("4 - buscar carros por modelo ou ano");
        System.out.println("5 - Buscar por placa");
        System.out.println("0 - Fechar");
        System.out.print("Opção: ");
        int op = Integer.parseInt(br.readLine());
        return op;
    }
}