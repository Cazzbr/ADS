public class Professor {
    private String nome;
    private EixoTematico area_atuacao;
    private String email;
    private float salario;
    
    public Professor(String nome, EixoTematico area_atuacao, String email){
        this.nome = nome;
        this.area_atuacao = area_atuacao;
        this.email = email;
    }
    public Professor(String nome, EixoTematico area_atuacao, String email, float salario){
        this(nome, area_atuacao, email);
        this.salario = salario;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public EixoTematico getArea_atuacao() {
        return area_atuacao;
    }
    public void setArea_atuacao(EixoTematico area_atuacao) {
        this.area_atuacao = area_atuacao;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public float getSalario() {
        return salario;
    }
    public void setSalario(float salario) {
        this.salario = salario;
    }

    @Override
    public String toString(){
        String str = "";
        str += " *** Professor ***\n";
        str += "   Nome: " + this.nome + "\n";
        str += "   Área de Atuação: " + this.area_atuacao + "\n";
        str += "   Email: " + this.email + "\n";
        return str;
    }

    @Override
    public boolean equals(Object x){
        if (x == this){
            return true;
        }
        if (!(x instanceof Professor) || x == null){
            return false;
        }
        Professor p = (Professor) x;
        return p.nome.equals(this.nome) && p.email.equals(this.email);
    }
}
