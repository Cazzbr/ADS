package ErickNagoski;

import java.util.Date;
import java.util.Objects;

public class Vaga {
    private Carro carro;
    private Date horaEntrada;
    private Date horaSaida;
    private float valor;
    private float valorHora;

    public Vaga(Carro carro, Date horaEntrada, Date horaSaida, float valorHora) {
        this.carro = carro;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.valor = valor;
        this.valorHora = valorHora;
    }

    public Vaga(Carro carro, Date horaEntrada, float valorHora) {
        this.carro = carro;
        this.horaEntrada = horaEntrada;
        this.horaSaida = null;
        this.valor = valor;
        this.valorHora = valorHora;
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

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getValorHora() {
        return valorHora;
    }

    public void setValorHora(float valorHora) {
        this.valorHora = valorHora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaga vaga = (Vaga) o;
        return horaEntrada == vaga.horaEntrada && horaSaida == vaga.horaSaida && Float.compare(vaga.valor, valor) == 0 && Float.compare(vaga.valorHora, valorHora) == 0 && Objects.equals(carro, vaga.carro);
    }

    @Override
    public String toString() {
        return "carro: " + carro +
                ", horaEntrada: " + horaEntrada +
                ", horaSaida: " + horaSaida +
                ", valor: " + valor +
                ", valorHora: " + valorHora +
                '}';
    }

    public float getValorTotal(){
        float diferenca = this.horaSaida.getTime() - this.horaEntrada.getTime();
        return this.valorHora * diferenca;
    }
}
