package GabrielFarinela;

import java.util.Objects;

public class Carros {
    private String modelo;
    private String anoFabricacao;
    private String placa;

    public Carros() {
    }

    public Carros(String modelo, String anoFabricacao, String placa) {
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

    public String getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(String anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "Carros{" +
                "modelo='" + modelo + '\'' +
                ", anoFabricacao='" + anoFabricacao + '\'' +
                ", placa='" + placa + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelo, anoFabricacao, placa);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carros)) return false;
        Carros carros = (Carros) o;
        return getModelo().equals(carros.getModelo()) && getAnoFabricacao().equals(carros.getAnoFabricacao()) && getPlaca().equals(carros.getPlaca());
    }
}
