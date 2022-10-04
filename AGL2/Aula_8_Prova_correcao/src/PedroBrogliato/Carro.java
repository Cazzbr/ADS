package PedroBrogliato;

import java.time.LocalDateTime;

public class Carro {
    private String modelo_Carro;
    private int ano_fab;
    private String placa;
    private LocalDateTime hora_entrada;
    private LocalDateTime hora_saida;
    private int n_vaga;

    public void setHora_entrada(LocalDateTime hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public void setHora_saida(LocalDateTime hora_saida) {
        this.hora_saida = hora_saida;
    }

    public void setN_vaga(int n_vaga) {
        this.n_vaga = n_vaga;
    }

    public void mostraMenu() { //não faz sentido estar aqui (deve ser método estático do main)
        System.out.println("----------------ESTACIONAMENTO--------------");
        System.out.println("1.Registrar entrada de carro");
        System.out.println("2.Registrar a saída de carro");
        System.out.println("3.Mostrar ganhos do dia;");
        System.out.println("4.Mostrar carros estacionados no momento");
        System.out.println("5.Procurar carros por ano/modelo");
        System.out.println("6.Procurar carro estacionado pela placa");
        System.out.println("7.Sair");
        System.out.print("Digite sua opção: ");
    }

    public LocalDateTime getHora_entrada() {
        return hora_entrada;
    }

    public LocalDateTime getHora_saida() {
        return hora_saida;
    }

    public int getN_vaga() {
        return n_vaga;
    }

}
