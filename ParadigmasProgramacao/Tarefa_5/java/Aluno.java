public class Aluno {
    private String nome;
    private String ASSINATURAPADRAO;
    private String assinatura;

    public Aluno(String nome, String ASSINATURAPADRAO){
        this.nome = nome;
        setAssinatura_padrao(ASSINATURAPADRAO);
    }

    public void assinarPresenca(String assinatura){
        setAssinatura(assinatura);
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setAssinatura_padrao(String ASSINATURAPADRAO){
        if(this.ASSINATURAPADRAO == null){
            this.ASSINATURAPADRAO = ASSINATURAPADRAO;
        } else {
            System.out.println("ERRO, o aluno não pode mudar sua assinatura padrão");
        } 
    }

    public void setAssinatura(String assinatura){
        this.assinatura = assinatura;
    }

    public String getAssinatura(){
        return this.assinatura;
    }

    public String getASSINATURAPADRAO(){
        return this.ASSINATURAPADRAO;
    }

    public String getNome(){
        return this.nome;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Aluno))
            return false;
        Aluno other = (Aluno)o;
        
        return other.getASSINATURAPADRAO().equals(other.getAssinatura());
    }
}
