package AlexPimentel;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Estacionamento {
    private static Carro[] totalCarros = new Carro[269];
    private static int qtdAtualCarros;

    public static void addCarro(Carro carro) {
        totalCarros[qtdAtualCarros] = carro;
        qtdAtualCarros++;
        carro.setHoraEntrada(LocalDateTime.now());
    }

    public void carrosEstacionados() {
        for (int i = 0; i < totalCarros.length; i++) {
            System.out.println(i+1 + ":");
            System.out.println(totalCarros[i]);
        }
    }

    public void registrarSaida(int n) {
        totalCarros[n].setHoraSaida(LocalDateTime.now());
        System.out.println("Você entrou às:" + totalCarros[n].getHoraEntrada() +
                "\nE saiu às: " + totalCarros[n].getHoraSaida() +
                "\nPermanecendo um total de: ");
        totalCarros[n] = null;
    }

    public void pesquisaPorModelo(String modelo) {
        for (int i = 0; i < totalCarros.length; i++) {
            if (totalCarros[i].getModelo().equals(modelo)); {
                System.out.println(totalCarros[i]);
            }
        }
    }

    public void pesquisaPorAnoFabricacao(int anoFabricacao) {
        for (int i = 0; i < totalCarros.length; i++) {
            if (totalCarros[i].getAnoFabricacao() == anoFabricacao); {
                System.out.println(totalCarros[i]);
            }
        }
    }



}
