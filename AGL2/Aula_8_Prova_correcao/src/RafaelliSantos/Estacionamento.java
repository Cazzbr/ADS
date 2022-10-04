package RafaelliSantos;

public class Estacionamento {
    private int vaga;
    private String entrada;
    private String saida;
    private Carro c1; //tem somente 1 carro no estacionamento?

    public Estacionamento(int vaga, String entrada, String saida, Carro c1){
        this.vaga = vaga;
        this.entrada = entrada;
        this.saida = saida;
        this.c1 = c1;
    }
    public Estacionamento(int vaga, Carro c1, String entrada){
        this.vaga = vaga;
        this.entrada = entrada;
        this.c1 = c1;

    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public void setVaga(int vaga) {
        this.vaga = vaga;
    }

    public void setPlaca(Carro placa) {
        this.c1 = placa;
    }

    public String getEntrada() {
        return entrada;
    }

    public String getSaida() {
        return saida;
    }

    public int getVaga() {
        return vaga;
    }

    public Carro getPlaca() {
        return c1;
    }

    @Override
    public String toString() {
        return  "Vaga: " + vaga +"\n Placa: " + c1.getPlaca() + "\n Hora de Entrada: " + entrada;
    }

    @Override
    public boolean equals(Object e) {
        if (!(e instanceof Carro)) {
            return false;
        }
        Estacionamento x = (Estacionamento) e;
        return (vaga == x.vaga);
    }



}
