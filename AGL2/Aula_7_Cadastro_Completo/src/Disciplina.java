public class Disciplina {

    private String nome;
    private EixoTematico eixo_tematico;
    private int carga_horaria_individual;
    private Professor professor;
    private Aluno [] alunos = new Aluno[25];
    
    public Disciplina(String nome, int carga_horaria_individual){
        this.nome = nome;
        this.carga_horaria_individual = carga_horaria_individual;
    }
    public Disciplina(String nome, int carga_horaria_individual, EixoTematico eixo_tematico){
        this(nome, carga_horaria_individual);
        this.eixo_tematico = eixo_tematico;
    }
    public Disciplina(String nome, int carga_horaria_individual, EixoTematico eixo_tematico, Professor professor){
        this(nome, carga_horaria_individual, eixo_tematico);
        this.professor = professor;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public EixoTematico getEixo_tematico() {
        return eixo_tematico;
    }
    public void setEixo_tematico(EixoTematico eixo_tematico) {
        this.eixo_tematico = eixo_tematico;
    }

    public int getCarga_horaria_individual() {
        return carga_horaria_individual;
    }
    public void setCarga_horaria_individual(int carga_horaria_individual) {
        this.carga_horaria_individual = carga_horaria_individual;
    }
    
    public Professor getProfessor() {
        return professor;
    }
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    
    public Aluno[] getAlunos() {
        return alunos;
    }
    public boolean setAlunos(Aluno aluno) {
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] == null){
                alunos[i] = aluno;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        String str = "";
        str += " *** Disciplina ***\n";
        str += "   Nome: " + this.nome + "\n";
        str += "   Eixo Temático: " + this.eixo_tematico + "\n";
        str += "   Carga Horária: " + this.carga_horaria_individual + "\n";
        str += "   Professor Regente: " + this.professor + "\n";
        return str;
    }

    @Override
    public boolean equals(Object x){
        if (x == this){
            return true;
        }
        if (!(x instanceof Disciplina) || x == null){
            return false;
        }
        Disciplina d = (Disciplina) x;
        return d.nome.equals(this.nome);
    }
}
