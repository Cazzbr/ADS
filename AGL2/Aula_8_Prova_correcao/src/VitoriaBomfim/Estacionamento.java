package VitoriaBomfim;

import java.util.Scanner;

public class Estacionamento { //1,0/5,0
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String opcao;
        while (true) {
            System.out.println("""
                               \t1. Registrar entrada
                               \t2. Registrar saída
                               \t3. Mostrar valor recebido hoje
                               \t4. Mostrar carros no estacionamento
                               \t5. Mostrar carros de modelo específico
                               \t6. Mostrar carros de ano específico
                               \t7. Pesquisar carro por placa
                               \t8. Sair""");
            System.out.print("Escolha um número: ");
            opcao = s.nextLine();
            
            if (opcao.equals("8")) {
                break;
            }
            
            switch (opcao) {
                case "1" -> {}
                case "2" -> {}
                case "3" -> {}
                case "4" -> {}
                case "5" -> {}
                case "6" -> {}
                default -> {}
            }
        }
    }
    
}
