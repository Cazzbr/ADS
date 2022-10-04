package PedroBrogliato;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class EstacionamentoMain { //1,0/5,0
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Carro novo_cliente = new Carro();

        novo_cliente.mostraMenu();
        int input = sc.nextInt();

        switch (input) {
            case 1:
                novo_cliente.setHora_entrada(LocalDateTime.now());
                novo_cliente.getHora_entrada().format(DateTimeFormatter.ofPattern("HH:mm"));

        }


    }
}
