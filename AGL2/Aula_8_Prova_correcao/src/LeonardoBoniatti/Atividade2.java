package LeonardoBoniatti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Atividade2 { //2,0/5,0
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner ler = new Scanner(System.in);
        String[] estacionamento= new String[269];
        while(true) {
            System.out.println("Informe o que deseja fazer \n 1-Registrar novo carro \n 2-Registrar saida de um carro \n 3-Mostrar total recebido do dia \n 4-Mostrar os carros no estacionamento \n 5-Mostrar carros de um determinado modelo ou ano \n 6-Pesquisar carro por placa \n 7-Fechar sistema");
            int n = parseInt(br.readLine());
            if (n == 1) {
                System.out.println("Informe as caracteristicas do carro e suas informacoes");
                System.out.println("Informe a vaga do veiculo");
                int vaga = parseInt(br.readLine());
                Carro novocarro = new Carro();
                System.out.println("Informe o modelo do carro");
                String modelo = ler.nextLine();
                System.out.println("Informe o ano do carro");
                int ano = parseInt(br.readLine());
                System.out.println("Informe a placa do carro");
                String placa = ler.nextLine();
                novocarro.setModelo(modelo);
                novocarro.setAno(ano);
                novocarro.setPlaca(placa);
                estacionamento[vaga] = String.valueOf(novocarro);
                System.out.println("Informe a hora de entrada");
                double entrada= ler.nextDouble();
            }
            if (n == 2) {
                System.out.println("Informe a vaga do veiculo que esta saindo");
                int vaga=parseInt(br.readLine());
                //estacionamento[]-estacionamento[vaga];

            }
            if (n == 3) {
                System.out.println("Total recebido do dia");
            }
            if (n == 4) {
                System.out.println("Veiculos ainda estacionados:");
            }
            if (n == 5) {
                System.out.println("Informe o modelo ou o ano de fabricacao do veiculo desejado");
            }
            if (n==6){
                System.out.println("Informe a placa do veiculo que deseja buscar:");
            }
            if (n==7){
                System.out.println("Fim do sistema");
                break;
            }
        }
    }
}
