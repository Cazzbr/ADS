package ErickNagoski;

import java.util.Objects;

public class Carro {
    private String modelo;
    private int anoFabricacao;
    private String placa;

    public Carro(String modelo, int anoFabricacao, String placa) {
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.placa = placa;
    }

    public Carro(String placa) {
        this.placa = placa;
        this.modelo = "";
        this.anoFabricacao = 0;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return anoFabricacao == carro.anoFabricacao && Objects.equals(modelo, carro.modelo) && Objects.equals(placa, carro.placa);
    }

    @Override
    public String toString() {
        return "AlyssonDornelles.RafaelliSantos.Carro{" +
                "modelo='" + modelo + '\'' +
                ", anoFabricacao=" + anoFabricacao +
                ", placa='" + placa + '\'' +
                '}';
    }
}
