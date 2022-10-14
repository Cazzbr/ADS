public class Aluno {
    private String nome;
    private int matricula;
    private String telefone;
    private String cidade_natal;

    public Aluno(String nome, int matricula){
        this.nome = nome;
        this.matricula = matricula;
    }
    public Aluno(String nome, int matricula, String telefone){
        this(nome, matricula);
        this.telefone = telefone;
    }
    public Aluno(String nome, int matricula, String telefone, String cidade_natal){
        this(nome, matricula, cidade_natal);
        this.cidade_natal = cidade_natal;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade_natal() {
        return cidade_natal;
    }
    public void setCidade_natal(String cidade_natal) {
        this.cidade_natal = cidade_natal;
    }

    @Override
    public String toString(){
        String str = "";
        str += " *** Aluno ***\n";
        str += "   Nome: " + this.nome + "\n";
        str += "   Matricula: " + this.matricula + "\n";
        str += "   Telefone: " + this.telefone + "\n";
        str += "   Cidade Natal: " + this.cidade_natal + "\n";
        return str;
    }

    @Override
    public boolean equals(Object x){
        if (x == this){
            return true;
        }
        if (!(x instanceof Aluno) || x == null){
            return false;
        }
        Aluno a = (Aluno) x;
        return a.nome.equals(this.nome) && a.matricula == this.matricula;
    }
}