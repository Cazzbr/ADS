import java.io.Serializable;

public class Biblioteca_Autor implements Serializable{
    private String nome;
    private String nacionalidade;
    private String nascimento;

    public Biblioteca_Autor(String nome){
        this.nome = nome;
    }
    public Biblioteca_Autor(String nome, String nacionalidade, String nascimento){
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.nascimento = nascimento;
    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNascimento() {
        return nascimento;
    }
    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    @Override
    public String toString(){
        String str = "";
        str += " *** Autor ***\n";
        str += "   Nome: " + this.nome;
        return str;
    }

    @Override
    public boolean equals(Object x){
        if (x == this){
            return true;
        }
        if (!(x instanceof Biblioteca_Autor) || x == null){
            return false;
        }
        Biblioteca_Autor a = (Biblioteca_Autor) x;
        return a.nome.equals(this.nome);
    }
}