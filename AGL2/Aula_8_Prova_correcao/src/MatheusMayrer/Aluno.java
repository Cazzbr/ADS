package MatheusMayrer;

public class Aluno { //ótimo!
    private String nome;
    private String assinatura_original;

    public String getAssinatura_original() {
        return assinatura_original;
    }

    public String getNome() {
        return nome;
    }

    public void setAssinatura_original(String assinatura_original) {
        this.assinatura_original = assinatura_original;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public Aluno (String nome, String assinatura_original){
        this.nome = nome;
        this.assinatura_original = assinatura_original;
    }
}
