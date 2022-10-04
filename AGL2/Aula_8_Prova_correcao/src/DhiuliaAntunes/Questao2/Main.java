package DhiuliaAntunes.Questao2;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Scanner;

public class Main { //4,0/5,0
    public static void main(String[] args) {
        Carro[] carros = new Carro[269];

    }

    public static void resgistra_entrada(Carro carro){
        Scanner sc = new Scanner(System.in);
        System.out.println("Vaga: ");
        String vaga = sc.nextLine();
        System.out.println("Hora entrada: ");
        var hora_entrada = LocalDateTime.now();
        carro.getRegistros().setHora_entrada(hora_entrada);
    }

    public static void registra_saida(Carro carro){
        Scanner sc = new Scanner(System.in);
        System.out.println("Hora saida: ");
        var saida = sc.nextLine();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime hora_saida = LocalDateTime.parse(saida, formatter);
        carro.getRegistros().setHora_saida(hora_saida);

    //    var tempo_total = hora_saida.minusDays(carro.getRegistros().getHora_entrada());
    }

    //saida

    //calcula valor total dia

    public static ArrayList<Carro> estao_estacionados(LocalDateTime data, Carro[] carros){
        ArrayList<Carro> estacionados = new ArrayList<Carro>();

        for(Carro carro : carros){
            var entrada = carro.getRegistros().getHora_entrada();
            var saida = carro.getRegistros().getHora_saida();
            if(data.isBefore(saida) && data.isAfter(entrada)){
                estacionados.add(carro);
            }
        }
        return estacionados;
    }

    public static ArrayList<Carro> modelo_fabricacao(String modelo, int ano, Carro[] carros){
        ArrayList<Carro> pesquisa = new ArrayList<Carro>();

        for(Carro carro : carros){
            if(carro.getAno_fabricacao() == ano || carro.getModelo().equals(modelo)){
                pesquisa.add(carro);
            }
        }
        return pesquisa;
    }

    public static Carro pesquisa_placa(String placa, Carro[] carros){
        for(Carro carro : carros){
            if(carro.getPlaca().equals(placa)){
                return carro;
            }
        }
        return null;
    }


}
