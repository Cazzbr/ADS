package DhiuliaAntunes.Questao2;

import java.time.LocalDateTime;
import java.util.Objects;

public class Registro { //ótima solução!
    private String vaga;
    private LocalDateTime hora_entrada;
    private LocalDateTime hora_saida;
    private double valor;

    public Registro() {

    }

    public Registro(String vaga, LocalDateTime hora_entrada, LocalDateTime hora_saida, double valor) {
        this.vaga = vaga;
        this.hora_entrada = hora_entrada;
        this.hora_saida = hora_saida;
        this.valor = valor;
    }

    public String getVaga() {
        return vaga;
    }

    public void setVaga(String vaga) {
        this.vaga = vaga;
    }

    public LocalDateTime getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(LocalDateTime hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public LocalDateTime getHora_saida() {
        return hora_saida;
    }

    public void setHora_saida(LocalDateTime hora_saida) {
        this.hora_saida = hora_saida;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registro registro = (Registro) o;
        return Double.compare(registro.valor, valor) == 0 && vaga.equals(registro.vaga) && hora_entrada.equals(registro.hora_entrada) && hora_saida.equals(registro.hora_saida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vaga, hora_entrada, hora_saida, valor);
    }

    @Override
    public String toString() {
        return "Registro{" +
                "vaga='" + vaga + '\'' +
                ", hora_entrada=" + hora_entrada +
                ", hora_saida=" + hora_saida +
                ", valor=" + valor +
                '}';
    }
}
