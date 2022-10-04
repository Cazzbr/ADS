package MarianaMoraes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Carro {
    private String modelo;
    private int ano_fabricacao;
    private String placa;
    private String vaga;
    private String saida;
    private String entrada;

    public Carro(String modelo, int ano_fabricacao, String placa) {
        this.modelo = modelo;
        this.ano_fabricacao = ano_fabricacao;
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno_fabricacao() {
        return ano_fabricacao;
    }

    public void setAno_fabricacao(int ano_fabricacao) {
        this.ano_fabricacao = ano_fabricacao;
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

    public String getSaida() {
        return saida;
    }

    public void setSaida() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        this.saida = agora.format(formatter);
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        this.entrada = agora.format(formatter);
    }

    public void registrar_entrada(){
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        this.entrada = agora.format(formatter);
    }

    public void registrar_saida(){
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        this.saida = agora.format(formatter);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Carro))
            return false;

        Carro x = ((Carro) obj);

        return this.placa.equals(x.placa);
    }

    @Override
    public String toString() {
        return "AlyssonDornelles.RafaelliSantos.Carro {" +
                "modelo='" + modelo + '\'' +
                ", ano fabricaçao=" + ano_fabricacao +
                ", placa='" + placa + '\'' +
                ", vaga='" + vaga + '\'' +
                ", saída=" + saida +
                ", entrada=" + entrada +
                '}';
    }
}

