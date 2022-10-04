package MauricioVerona;

import java.time.LocalDateTime;

public class Estacionamento {
    private int numEstacionamento = 0;
    private int horaE;
    private int horaS;
    private double valorH;

    public double sair(int horaS){
        return calculaValor(horaS, horaE);

    };

    public double calculaValor(int horaS, int horaE){
        int horas = horaS - horaE;
        return horas*this.valorH;
    }

    public boolean setNumVaga() {
        if (numEstacionamento+1>269){
            return false;
        } else {
            this.numEstacionamento++;
        }
        return true;
    }

    public int getNumVaga() {
        return numEstacionamento;
    }

    public Estacionamento(int horaE, int horaS, double valorH) {
        this.horaE = horaE;
        this.horaS = horaS;
        this.valorH = valorH;
        setNumVaga();
    }

    public Estacionamento(int horaE, int horaS) {
        this.horaE = horaE;
        this.horaS = horaS;
        this.valorH = 10;
        setNumVaga();
    }

    public int getHoraS() {
        return horaS;
    }

    public void setHoraS(int horaS) {
        if(horaS>24){
            System.out.println("Erro, tente novamente");
        } else {
            this.horaS = horaS;
        }
    }

    public int getHoraE() {
        return horaE;
    }

    public void setHoraE() {
        LocalDateTime now = LocalDateTime.now();
        this.horaE = now.getHour();
    }

    public double getValorH() {
        return valorH;
    }

    public void setValorH(double valorH) {
        this.valorH = valorH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estacionamento estacionamento)) return false;

        return numEstacionamento == estacionamento.numEstacionamento;
    }

}
