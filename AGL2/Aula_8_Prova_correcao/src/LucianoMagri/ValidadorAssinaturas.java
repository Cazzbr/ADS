package LucianoMagri;

public class ValidadorAssinaturas { //1,0/5,0
    private int quantidade_alunos;
    private String [] nomes;
    private String [] assinatura_original;
    
    public ValidadorAssinaturas(int quantidade_alunos, String[] nomes, String [] assinatura){
        this.quantidade_alunos = quantidade_alunos;
        this.nomes = nomes;
        this.assinatura_original = assinatura;
    }
    
    public int getQuantidade_alunos() {
        return quantidade_alunos;
    }
    public void setQuantidade_alunos(int quantidade_alunos) {
        this.quantidade_alunos = quantidade_alunos;
    }

    public String[] getNome() {
        return nomes;
    }
    public void setNome(String[] nome) {
        this.nomes = nome;
    }

    public String[] getAssinatura_original() {
        return assinatura_original;
    }

    public void setAssinatura_original(String[] assinatura_original) {
        this.assinatura_original = assinatura_original;
    }



}
