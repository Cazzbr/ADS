package GuilhermeFarinela;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Carro {
    private int anoFabricacao;
    private String modelo;
    private String placa;
    private String vaga;
    private int horaEntrada;
    private int horaSaida;

    LocalDateTime now = LocalDateTime.now();
    Scanner scan = new Scanner(System.in);
    String[] lista = new String[269];



    public Carro(int anoFabricacao, String modelo, String placa, String vaga, int horaEntrada, int horaSaida) {
        this.anoFabricacao = anoFabricacao;
        this.modelo = modelo;
        this.placa = placa;
        this.vaga = vaga;

    }
    public Carro() {
        this.anoFabricacao = anoFabricacao;
        this.modelo = modelo;
        this.placa = placa;
        this.vaga = vaga;

    }

    public void registraCarro(int anoFabricacao, String Modelo, String placa, String vaga){

    }

    public void registraSaida(){

    }


    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getVaga() {
        return vaga;
    }

    public void setVaga(String vaga) {
        this.vaga = vaga;
    }

    public int getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(int horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public int getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(int horaSaida) {
        this.horaSaida = horaSaida;
    }


}
