package Prova_E1;

public class ValidadorAssinaturas {
    private int quantidade_alunos;
    private Assinatura [] assinaturas_originais;
    
    public ValidadorAssinaturas(int quantidade_alunos){
        this.quantidade_alunos = quantidade_alunos;
        assinaturas_originais = new Assinatura[quantidade_alunos];
    }

    public int getQuantidade_alunos() {
        return quantidade_alunos;
    }

    public Assinatura[] getAssinaturas_originais() {
        return assinaturas_originais;
    }

    public void setAssinaturas_originais(Assinatura[] assinatura_original) {
        this.assinaturas_originais = assinatura_original;
    }

    public boolean validarAssinatura(Assinatura a){
        for (Assinatura assinatura : assinaturas_originais) {
            if (assinatura.equals(a)){
                return true;
            }
        }
        return false;
    }

}
