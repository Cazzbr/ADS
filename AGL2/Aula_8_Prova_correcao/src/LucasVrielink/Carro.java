package LucasVrielink;

import java.util.Objects;

public class Carro {
	private String modelo;
	private int ano;
	private String placa;
	
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
	public String toString() {
		return "AlyssonDornelles.RafaelliSantos.Carro [modelo=" + modelo + ", ano=" + ano + ", placa=" + placa + "]";
	}
	public Carro(String modelo, int ano, String placa) {
		this.modelo = modelo;
		this.ano = ano;
		this.placa = placa;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carro other = (Carro) obj;
		return ano == other.ano && Objects.equals(modelo, other.modelo) && Objects.equals(placa, other.placa);
	}
	
	
	
}
