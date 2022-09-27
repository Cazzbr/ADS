public class Professor {

    private String nome;
    private String area_atuacao;
    private int sala_atendimento;
    
    public Professor(String nome, String area_atuacao, int sala_atendimento){
        this.nome = nome;
        this.area_atuacao = area_atuacao;
        this.sala_atendimento = sala_atendimento;
    }

    @Override
    public String toString(){
        return this.nome 
        + " -- Area de atuação: " + this.area_atuacao 
        + " -- Sala para atendimento: " + this.sala_atendimento;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArea_atuacao() {
        return area_atuacao;
    }
    public void setArea_atuacao(String area_atuacao) {
        this.area_atuacao = area_atuacao;
    }

    public int getSala_atendimento() {
        return sala_atendimento;
    }
    public boolean setSala_atendimento(int sala_atendimento) {
        if (sala_atendimento > 0 && sala_atendimento < 401){
            this.sala_atendimento = sala_atendimento;
            return true;
        }
        return false;
    }
}
