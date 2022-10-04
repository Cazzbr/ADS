package MatheusMayrer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Carro {
    private String modelo;
    private int ano_fabricacao;
    private String placa;
    private double valor_hora;
    private LocalDateTime horario_entrada;
    private int vaga;

    public String getModelo() {
        return modelo;
    }
    public int getAno_fabricacao() {
        return ano_fabricacao;
    }
    public double getValor_hora() {
        return valor_hora;
    }
    public String getPlaca() {
        return placa;
    }
    public void setAno_fabricacao(int ano_fabricacao) {
        this.ano_fabricacao = ano_fabricacao;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public void setValor_hora(double valor_hora) {
        this.valor_hora = valor_hora;
    }

    public int getVaga() {
        return vaga;
    }

    public LocalDateTime getHorario_entrada() {
        return horario_entrada;
    }

    public void setHorario_entrada(LocalDateTime horario_entrada) {
        this.horario_entrada = horario_entrada;
    }

    public void setVaga(int vaga) {
        this.vaga = vaga;
    }

    public Carro (){}
    public Carro (String modelo, int ano_fabricacao, String placa, double valor_hora){
        this.modelo = modelo;
        this.ano_fabricacao = ano_fabricacao;
        this.placa = placa;
        this.valor_hora = valor_hora;
        this.horario_entrada = LocalDateTime.now();
    }


    @Override
    public String toString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return ("Modelo: " + modelo +
                "\nAno de fabricação: " + ano_fabricacao +
                "\nPlaca: " + placa +
                "\nValor da hora: " + valor_hora +
                "\nHorario de entrada: " + dtf.format(horario_entrada));
    }
}

