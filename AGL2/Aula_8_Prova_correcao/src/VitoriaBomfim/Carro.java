package VitoriaBomfim;

public class Carro {
    private String modelo;
    private int anoFabricacao;
    private String placa;

    public Carro(String modelo, int anoFabricacao, String placa) {
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "Carro:\n\tModelo: " + modelo + "\n\tAno de fabricação: " + anoFabricacao + "\n\tPlaca: " + placa;
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
}
