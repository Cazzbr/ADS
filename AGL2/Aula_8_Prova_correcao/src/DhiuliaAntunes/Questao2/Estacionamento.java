package DhiuliaAntunes.Questao2;

import java.util.Arrays;

public class Estacionamento {
    private Carro[] carros;

    public Estacionamento() {
    }

    public Estacionamento(Carro[] carros) {
        this.carros = carros;
    }

    public Carro[] getCarros() {
        return carros;
    }

    public void setCarros(Carro[] carros) {
        this.carros = carros;
    }

    @Override
    public String toString() {
        return "AlyssonDornelles.RafaelliSantos.Estacionamento{" +
                "carros=" + Arrays.toString(carros) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estacionamento that = (Estacionamento) o;
        return Arrays.equals(carros, that.carros);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(carros);
    }
}
