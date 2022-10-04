package TiagoMoroni;

public class Carro {
    private String modelo;
    private int ano;
    private String placa;


    public Carro(String modelo, int ano, String placa) {
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
    }

    public Carro(String modelo, String placa) {
        this.modelo = modelo;
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
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
        return modelo.equals(carro.modelo) && ano == carro.ano && placa.equals(carro.placa);
    }

    @Override
    public String toString() {
        return "AlyssonDornelles.RafaelliSantos.Carro{" +
                "modelo='" + modelo + '\'' +
                ", ano='" + ano + '\'' +
                ", placa='" + placa + '\'' +
                '}';
    }
}
