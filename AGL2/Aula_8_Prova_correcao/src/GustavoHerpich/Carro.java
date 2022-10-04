package GustavoHerpich;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Carro {

    private String modelo;
    private int anoFabricacao;
    private String placa;
    private String vaga;
    private String saida;
    private String entrada;

    public Carro(String modelo, int anoFabricacao, String placa) {
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
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

    public void setSaida(String saida) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("aaaa MM dd");
        this.saida = localDateTime.format(formatarData);
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("aaaa MM dd");
        this.entrada = localDateTime.format(formatarData);
    }
}

