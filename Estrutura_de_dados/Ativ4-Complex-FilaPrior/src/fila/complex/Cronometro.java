package fila.complex;

public class Cronometro {

    private long inicio;
    private long fim;
    private boolean rodando;

    // Inicializa o cronômetro
    public Cronometro() {
        this.inicio = 0;
        this.fim = 0;
        this.rodando = false;
    }

    // Inicia a contagem de tempo
    public void inicia() {
        this.inicio = System.currentTimeMillis();
        this.rodando = true;
    }

    // Finaliza a contagem de tempo
    public void finaliza() {
        if (rodando) {
            this.fim = System.currentTimeMillis();
            this.rodando = false;
        }
    }

    // Retorna o tempo de execução em milissegundos
    public long getTempoEmMilissegundos() {
        if (rodando) {
            return System.currentTimeMillis() - inicio;
        } else {
            return fim - inicio;
        }
    }

    // Retorna o tempo de execução em segundos
    public double getTempoEmSegundos() {
        return getTempoEmMilissegundos() / 1000.0;
    }

    // Reinicia o cronômetro
    public void reseta() {
        this.inicio = 0;
        this.fim = 0;
        this.rodando = false;
    }
}
