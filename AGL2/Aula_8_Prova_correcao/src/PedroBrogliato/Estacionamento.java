package PedroBrogliato;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Estacionamento {
    private int qntd_carros;
    private double valor_hora;

    public Estacionamento() {}

    public Estacionamento(int valor_hora, int qntd_carros) {
        this.valor_hora = valor_hora;
        this.qntd_carros = qntd_carros;
    }

    public void setValor_hora(double valor_hora) {
        this.valor_hora = valor_hora;
    }

    public void setQntd_carros(int qntd_carros) {
        if (qntd_carros > 0 && qntd_carros <= 269) {
            this.qntd_carros = qntd_carros;
        }
    }

    public double getValor_hora() {
        return valor_hora;
    }

    public int getQntd_carros() {
        return qntd_carros;
    }

    //public double valor_a_pagar(LocalDateTime hora_entrada, LocalDateTime hora_saida) {
    //    return (hora_saida-hora_entrada)*valor_hora;
    //}

    @Override
    public String toString() {
        String out = "";
        out += "Quantidade de carros: " + qntd_carros + "\n";
        return out;
    }

}
