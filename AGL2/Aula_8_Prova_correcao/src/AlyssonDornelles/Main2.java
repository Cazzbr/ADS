package AlyssonDornelles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;



public class Main2 {//4,0/5,0
    public static void main(String[] args) {
        Scanner s  = new Scanner(System.in);
        LocalDateTime agora = LocalDateTime.now();
        ArrayList estacionamento = new ArrayList<>(269);
        int opc = 0;
        do {
            System.out.println("----------------Bem vindo ao estacionamento----------");
            System.out.println("1 - Entrada de veiculo.");
            System.out.println("2 - Saída do estacionamento.");
            System.out.println("3 - Valor total recebido.");
            System.out.println("4 - Carros ainda no estacionamento.");
            System.out.println("5 - Pesquisa");
            System.out.println("6 - Pesquisar veiculo estacionado");
            System.out.println("7 - sair");
            opc = s.nextInt();

            switch (opc){
                case 1:
                    //novoCarro();
                    break;
                case 2:
                    //saidaCarro();
                    break;
                case 3:
                    //valorTotal();
                    break;
                case 4:
                    //vagasPreenchidas();
                    break;
                case 5:

            }



        }while(opc != 7);

    }
}