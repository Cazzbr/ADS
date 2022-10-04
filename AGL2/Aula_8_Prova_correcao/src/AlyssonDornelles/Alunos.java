package AlyssonDornelles;

import java.util.Objects;

public class Alunos {

    private String nome;

    private String assinatura;

    public Alunos(String nome, String assinatura) {
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
        return "Alunos:\n" +
                "Nome:"+ nome + '\'' +
                "Assinatura:'" + assinatura + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alunos alunos)) return false;
        return Objects.equals(getNome(), alunos.getNome()) && Objects.equals(getAssinatura(), alunos.getAssinatura());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getAssinatura());
    }
}
