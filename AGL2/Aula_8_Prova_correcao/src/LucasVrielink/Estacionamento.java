package LucasVrielink;

import java.time.LocalDateTime;
import java.util.Objects;

public class Estacionamento {
	private double valorHora;
	private LocalDateTime horaEntrada;
	private LocalDateTime horaSaioda;
	private Carro carro;
	private static double faturado = 0;
	
	public double getValorHora() {
		return valorHora;
	}
	public void setValorHora(double valorHora) {
		this.valorHora = valorHora;
	}
	public LocalDateTime getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(LocalDateTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public LocalDateTime getHoraSaioda() {
		return horaSaioda;
	}
	public void setHoraSaioda(LocalDateTime horaSaioda) {
		this.horaSaioda = horaSaioda;
	}
	public Carro getCarro() {
		return carro;
	}
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	@Override
	public String toString() {
		return "AlyssonDornelles.RafaelliSantos.Estacionamento [valorHora=" + valorHora + ", horaEntrada=" + horaEntrada + ", horaSaioda=" + horaSaioda
				+ ", carro=" + carro + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(carro, horaEntrada, horaSaioda, valorHora);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estacionamento other = (Estacionamento) obj;
		return Objects.equals(carro, other.carro) && Objects.equals(horaEntrada, other.horaEntrada)
				&& Objects.equals(horaSaioda, other.horaSaioda)
				&& Double.doubleToLongBits(valorHora) == Double.doubleToLongBits(other.valorHora);
	}
	public Estacionamento(LocalDateTime horaEntrada, Carro carro) {
		this.horaEntrada = horaEntrada;
		this.carro = carro;
	}
	
	
	
	
	
	
}
