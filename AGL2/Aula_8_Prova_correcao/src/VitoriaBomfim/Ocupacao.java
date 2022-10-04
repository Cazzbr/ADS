package VitoriaBomfim;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Ocupacao {
    private Carro carro;
    private int vaga;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private float precoHora;

    public Ocupacao(Carro carro, int vaga, LocalDateTime horaEntrada, LocalDateTime horaSaida, float precoHora) {
        this.carro = carro;
        this.vaga = vaga;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.precoHora = precoHora;
    }

    public Ocupacao(Carro carro, int vaga, LocalDateTime horaEntrada, float precoHora) {
        this.carro = carro;
        this.vaga = vaga;
        this.horaEntrada = horaEntrada;
        this.precoHora = precoHora;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ocupacao:");
        sb.append("\n\t").append(carro);
        sb.append("\n\tVaga: ").append(vaga);
        sb.append("\n\tHora de entrada: ").append(horaEntrada);
        
        if (horaSaida != null) {
            sb.append("\n\tHora de saída: ").append(horaSaida);
            sb.append("\n\tValor pago: ").append(getValorPago());
        }
        
        sb.append("\n\tValor unitário da hora: ").append(precoHora);
        
        return sb.toString();
    }
    
    public float getValorPago() {
        long horas = ChronoUnit.HOURS.between(horaEntrada, horaSaida);
        
        return horas * precoHora;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
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

    public float getPrecoHora() {
        return precoHora;
    }

    public void setPrecoHora(float precoHora) {
        this.precoHora = precoHora;
    }
}
