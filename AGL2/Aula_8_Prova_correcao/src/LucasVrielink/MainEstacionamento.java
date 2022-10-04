package LucasVrielink;

import java.time.LocalDateTime;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainEstacionamento { //3,0/5,0

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int option = 0;
		ArrayList<Estacionamento> carrosEstacionados = new ArrayList<Estacionamento>();
		double valorHora = 10;
		while(option!=0) {
			menu();
			switch(option) {
			case 1:
				System.out.println("Digite o modelo do carro:");
				String modelo = br.readLine();
				System.out.println("Digite o ano do carro:");
				int ano = Integer.parseInt(br.readLine());
				System.out.println("Digite a placa do carro:");
				String placa = br.readLine();
				
				Carro carro = new Carro(modelo, ano, placa);
				
				Estacionamento ticket = new Estacionamento(LocalDateTime.now(), carro);
				break;
			
			case 2:
				for(int i = 0; i<carrosEstacionados.size(); i++ ) {
					System.out.println(i + "- " + carrosEstacionados.get(i));
				}
				System.out.println("Selecione o carro para dar saida:");
				int index = Integer.parseInt(br.readLine());			
		}
	}
}
	
	public static void menu() {
		System.out.println("1- Registrar entrada:");
		System.out.println("2- Registrar saida");
		System.out.println("3- Mostrar valor");
		System.out.println("4- Carros no estacionamento");
		System.out.println("5- Mostrar tal carro");
		System.out.println("6- Pesquisar se ainda está estacionado");
		System.out.println("0- Fechar sistema");
	}
	
}
