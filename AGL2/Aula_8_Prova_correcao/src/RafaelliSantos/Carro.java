package RafaelliSantos;

public class Carro {
    private String modelo;
    private int anoFab;
    private String placa;

    public Carro(String modelo, int anoFab, String placa) {
        this.modelo = modelo;
        this.anoFab = anoFab;
        this.placa = placa;
    }

    public Carro(String modelo, String placa) {
        this.modelo = modelo;
        this.placa = placa;
    }

    public void setAnoFab(int anoFab) {
        this.anoFab = anoFab;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAnoFab() {
        return anoFab;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }

    @Override
    public String toString() {
       return "AlyssonDornelles.RafaelliSantos.Carro: " + modelo + "\n Placa: " + placa;
    }

    @Override
    public boolean equals(Object c) {
        if (!(c instanceof Carro)) {
            return false;
        }
        Carro x = (Carro) c;
        return (placa.equals(x.placa));
    }
}
