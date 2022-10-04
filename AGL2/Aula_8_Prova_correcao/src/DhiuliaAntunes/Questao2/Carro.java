package DhiuliaAntunes.Questao2;

import java.util.Objects;

public class Carro {
    private String modelo;
    private int ano_fabricacao;
    private String placa;

    private Registro registros;

    public Carro() {
    }

    public Carro(String modelo, int ano_fabricacao, String placa, Registro registros) {
        this.modelo = modelo;
        this.ano_fabricacao = ano_fabricacao;
        this.placa = placa;
        this.registros = registros;
    }

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

    public Registro getRegistros() {
        return registros;
    }

    public void setRegistros(Registro registros) {
        this.registros = registros;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return ano_fabricacao == carro.ano_fabricacao && modelo.equals(carro.modelo) && placa.equals(carro.placa) && registros.equals(carro.registros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelo, ano_fabricacao, placa, registros);
    }

    @Override
    public String toString() {
        return "AlyssonDornelles.RafaelliSantos.Carro{" +
                "modelo='" + modelo + '\'' +
                ", ano_fabricacao=" + ano_fabricacao +
                ", placa='" + placa + '\'' +
                ", registros=" + registros +
                '}';
    }
}
