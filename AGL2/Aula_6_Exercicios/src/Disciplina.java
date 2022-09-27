public class Disciplina {
    private String nome;
    private Professor professor_regente;
    private int carga_horaria;
    private Disciplina requisito;

    public Disciplina(String nome, Professor professor_regente, int carga_horaria){
        this.nome = nome;
        this.professor_regente = professor_regente;
        this.carga_horaria = carga_horaria;
    }
    public Disciplina(String nome, Professor professor_regente, int carga_horaria, Disciplina requisito){
        this(nome, professor_regente, carga_horaria);
        this.requisito = requisito;
    }

    @Override
    public String toString(){
        String prerequisito;
        if (this.requisito == null){
            prerequisito = "Essa disciplina não possui nenhum pré-requisito!";
        } else{
            prerequisito = this.requisito.nome;
        }
        return " ** Disciplina: " + this.nome + " ** \n"
        + " ** Professor Regente: " + this.professor_regente + "\n"
            + " __ Requisitos: " + prerequisito
        ;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Professor getProfessor_regente() {
        return professor_regente;
    }
    public void setProfessor_regente(Professor professor_regente) {
        this.professor_regente = professor_regente;
    }
    
    public int getCarga_horaria() {
        return carga_horaria;
    }
    public void setCarga_horaria(int carga_horaria) {
        this.carga_horaria = carga_horaria;
    }
    
    public Disciplina getRequisito() {
        return requisito;
    }
    public void setRequisito(Disciplina requisito) {
        this.requisito = requisito;
    }
}
