package AlyssonDornelles;

import java.time.LocalDateTime;

public class Estacionamento {

    private Carro[] vagas = new Carro[269];

    private int qtyVeiculos = 0;

    private double ValorInicial = 2.00;
    private double Add = 2.50;


    public boolean novoCarro(String modelo, int anofab, String placa, LocalDateTime hr) {
        for (int i = 0; i < this.vagas.length; i++) {
            Carro carro = new Carro(modelo, anofab, placa, hr);
            if (this.vagas[i] == null) {
                this.vagas[i] = carro;
                qtyVeiculos++;
            }
        }
        return true;
    }

    public void saidaCarro(String placa) {
        float conta = 0;
        for (int i = 0; i < this.vagas.length; i++) {
            if (this.vagas[i] != null) {
                if (placa.equals(this.vagas[i].getPlaca())) {
                    removerCarro(i);
                }
            }
        }
    }

    private void removerCarro(int v) {
        this.vagas[v] = null;
        qtyVeiculos--;
    }
}
