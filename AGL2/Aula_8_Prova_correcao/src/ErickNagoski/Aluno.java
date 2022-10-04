package ErickNagoski;

import java.util.Objects;

public class Aluno {
    private String nome;
    private String assinatura;

    public Aluno(String nome, String assinatura) {
        this.nome = nome;
        this.assinatura = assinatura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(String assinatura) {
        this.assinatura = assinatura;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", assinatura='" + assinatura + '\'' +
                '}';
    }
}
