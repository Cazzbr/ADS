public class Curso {

    private String nome;
    private Disciplina disciplinas[] = new Disciplina[56];
    private int carga_horaria_total;
    private Professor coordenador_curso;

    public Curso (String nome){
        this.nome = nome;
    }
    public Curso (String nome, Professor coordenador_curso){
        this(nome);
        this.coordenador_curso = coordenador_curso;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Disciplina[] getDisciplinas() {
        return disciplinas;
    }
    public boolean setDisciplina(Disciplina disciplina) {
        for (int i = 0; i < disciplinas.length; i++) {
            if (disciplinas[i] == null){
                disciplinas[i] = disciplina;
                return true;
            }
        }
        return false;
    }
    
    public Professor getCoordenador_curso() {
        return coordenador_curso;
    }
    public void setCoordenador_curso(Professor coordenador_curso) {
        this.coordenador_curso = coordenador_curso;
    }

    public int getCarga_horaria_total() {
        return carga_horaria_total;
    }
    public void setCarga_horaria_total(int carga_horaria_total) {
        this.carga_horaria_total = carga_horaria_total;
    }

    @Override
    public String toString(){
        String str = "";
        str += " *** Curso ***\n";
        str += "   Nome: " + this.nome + "\n";
        str += "   Carga Horária: " + this.carga_horaria_total + "\n";
        if(this.coordenador_curso == null){
            str += "   Coordenador: Não cadastrado!\n";
        }else{
            str += "   Coordenador: " + this.coordenador_curso + "\n";
        }
        return str;
    }

    @Override
    public boolean equals(Object x){
        if (x == this){
            return true;
        }
        if (!(x instanceof Curso) || x == null){
            return false;
        }
        Curso c = (Curso) x;
        return c.nome.equals(this.nome);
    }
}
