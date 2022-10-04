package DanielGandolfi;

import java.util.Objects;

public class Carro {

    private String modelo;
    private int ano;
    private String placa;

    private String horario;

    private int vaga;

    public Carro(String modelo, int ano, String placa, String hora_entrada, int vaga) {
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.horario = hora_entrada;
        this.vaga = vaga;
    }

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

    public String getHora_entrada() {
        return horario;
    }

    public void setHora_entrada(String hora_entrada) {
        this.horario = hora_entrada;
    }

    public int getVaga() {
        return vaga;
    }

    public void setVaga(int vaga) {
        this.vaga = vaga;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Carro carro = (Carro) o;

        if (ano != carro.ano) return false;
        if (vaga != carro.vaga) return false;
        if (!Objects.equals(modelo, carro.modelo)) return false;
        if (!Objects.equals(placa, carro.placa)) return false;
        return Objects.equals(horario, carro.horario);
    }

    @Override
    public String toString() {
        return "AlyssonDornelles.RafaelliSantos.Carro{" +
                "modelo='" + modelo + '\'' +
                ", ano=" + ano +
                ", placa='" + placa + '\'' +
                ", hora_entrada='" + horario + '\'' +
                ", vaga=" + vaga +
                '}';
    }
}
