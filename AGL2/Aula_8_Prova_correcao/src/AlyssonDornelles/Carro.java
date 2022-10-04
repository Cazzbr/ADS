package AlyssonDornelles;

import java.time.LocalDateTime;
import java.util.Objects;

public class Carro {

    private String Modelo;
    private int anoFab;
    private String placa;

    private LocalDateTime agora;

    public Carro() {
    }

    public Carro(String modelo, int anoFab, String placa, LocalDateTime hr) {
        Modelo = modelo;
        this.anoFab = anoFab;
        this.placa = placa;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public int getAnoFab() {
        return anoFab;
    }

    public void setAnoFab(int anoFab) {
        this.anoFab = anoFab;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "AlyssonDornelles.RafaelliSantos.Carro:" +
                "Modelo: " + Modelo + '\'' +
                "\n Ano de Fabricacao: " + anoFab +
                "\n Placa='" + placa + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carro carro)) return false;
        return getAnoFab() == carro.getAnoFab() && Objects.equals(getModelo(), carro.getModelo()) && Objects.equals(getPlaca(), carro.getPlaca());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModelo(), getAnoFab(), getPlaca());
    }

}
