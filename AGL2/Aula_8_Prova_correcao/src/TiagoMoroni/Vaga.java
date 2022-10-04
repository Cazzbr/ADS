package TiagoMoroni;

import java.util.Date;

public class Vaga {
    private Carro carro;
    private Date horaEntrada;
    private Date horaSaida;

    private double valorHora;

    public Vaga(Carro carro, Date horaEntrada, Date horaSaida, double valorHora) {
        this.carro = carro;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
    }

    public Vaga(Carro carro, Date horaEntrada, double valorHora) {
        this.carro = carro;
        this.horaEntrada = horaEntrada;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Date horaSaida) {
        this.horaSaida = horaSaida;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaga vaga = (Vaga) o;
        return carro.equals(vaga.carro) && horaEntrada.equals(vaga.horaEntrada) && horaSaida.equals(vaga.horaSaida);
    }

    @Override
    public String toString() {
        return "Vaga{" +
                "carro=" + carro.toString() +
                ", horaEntrada=" + horaEntrada +
                ", horaSaida=" + horaSaida +
                '}';
    }
}
