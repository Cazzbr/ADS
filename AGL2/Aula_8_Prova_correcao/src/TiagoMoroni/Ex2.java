package TiagoMoroni;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

public class Ex2 {//3,5/5,0

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //static Carro[] carros = new Carro[269];
    static int nCarros = 0;
    static Vaga[] vagas = {};

    public static void main(String[] args) throws IOException {
        while(true){
            System.out.println("MENU DE OPÇOES\n" +
                    "1 - REGISTRAR ENTRADA\n" +
                    "2 - REGISTRAR SAÍDA\n" +
                    "3 - VALOR RECEBIDO NO DIA\n" +
                    "4 - MOSTRAR CARROS ESTACIONADOS\n" +
                    "5 - FILTRAR POR MODELO OU ANO\n" +
                    "6 - PESQUISAR STATUS POR PLACA\n" +
                    "7 - FECHAR");
            int option = Integer.parseInt(br.readLine());
            switch (option){
                case 1:
                    createEntrance();
                    break;
                case 2:
                    createDeparture();
                    break;
                case 3:
                    showValueEarned();
                    break;
                case 4:
                    showParkedCars();
                    break;
                case 5:
                    showCarsFiltered();
                    break;
                case 6:
                    showCarByPlate();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void showValueEarned(){

    }

    public static void showCarsFiltered(){

    }

    public static void showParkedCars(){
        for (Vaga vaga : vagas) {
            if (vaga != null) {
                System.out.println(vaga.getCarro().toString());
            }
        }
    }

    public static void showCarByPlate() throws IOException {
        System.out.println("Informe a placa do carro...");
        String placa = br.readLine();
        Vaga vaga = findVaga(placa);
        if(vaga != null){
            System.out.println(vaga.getCarro().toString());
        }else{
            System.out.println("Não há carro com essa placa estacionado...");
        }
    }

    public static void createEntrance() throws IOException {
        nCarros++;
        if(nCarros < 270){
            String placa, modelo;
            Date horaEntrada;
            int ano;
            System.out.println("Informe a placa do carro:");
            placa = br.readLine();
            System.out.println("Informe o modelo do carro:");
            modelo = br.readLine();
            System.out.println("Informe o ano do carro:");
            ano = Integer.parseInt(br.readLine());
            Carro carro = new Carro(modelo, ano, placa);

            System.out.println("Informe o valor hora:");
            double valorHora = Double.parseDouble(br.readLine());

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            horaEntrada = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
            Vaga vaga = new Vaga(carro, horaEntrada, valorHora);
            addVagaToArray(vaga);
        }else{
            System.out.println("Vagas Lotadas!");
        }
    }

    public static void createDeparture() throws IOException {
        nCarros--;
        System.out.println("Informe a placa do carro...");
        Vaga vaga = findVaga(br.readLine());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Date horaSaida = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        vaga.setHoraSaida(horaSaida);
        long secs = (vaga.getHoraSaida().getTime() - vaga.getHoraEntrada().getTime()) / 1000;
        long hours = secs / 3600;
        System.out.println("Valor devido pelo carro: R$" + (hours * vaga.getValorHora()));
        vagas[findVagaIndex(vaga)] = null;
    }

    public static int findVagaIndex(Vaga vaga){
        for (int i = 0; i < vagas.length; i++) {
            if(vagas[i].equals(vaga)){
                return i;
            }
        }
        return 0;
    }
    public static Vaga findVaga(String placa){
        for (Vaga vaga : vagas) {
            if(vaga.getCarro().getPlaca().equals(placa)) {
                return vaga;
            }
        }
        return null;
    }

    static void addVagaToArray(Vaga el) {
        int N = vagas.length;
        vagas = Arrays.copyOf(vagas, N + 1);
        vagas[N] = el;
    }
}
