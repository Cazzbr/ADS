package GabrielFarinela;

import java.time.LocalDateTime;
import java.util.Objects;

public class Vaga {
    private int vaga;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private int valor;

    public Vaga() {
    }

    public Vaga(int vaga, LocalDateTime horaEntrada, LocalDateTime horaSaida, int valor) {
        this.vaga = vaga;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Vaga{" +
                "vaga=" + vaga +
                ", horaEntrada=" + horaEntrada +
                ", horaSaida=" + horaSaida +
                ", valor=" + valor +
                '}';
    }

    public int getVaga() {
        return vaga;
    }

    public void setVaga(int vaga) {
        this.vaga = vaga;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vaga)) return false;
        Vaga vaga1 = (Vaga) o;
        return getVaga() == vaga1.getVaga() && getValor() == vaga1.getValor() && getHoraEntrada().equals(vaga1.getHoraEntrada()) && getHoraSaida().equals(vaga1.getHoraSaida());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVaga(), getHoraEntrada(), getHoraSaida(), getValor());
    }
}
