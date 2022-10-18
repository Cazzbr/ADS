import java.util.Date;
import java.util.ArrayList;
import java.io.Serializable;

public class Biblioteca_Obra  implements Serializable{
    private String titulo;
    private String genero;
    private String classificacao;
    private String lingua_da_obra;
    private String formato_midia;
    private ArrayList<Biblioteca_Autor> autores = new ArrayList<>();
    private String editora;
    private Date data_publicacao;
    private Boolean disponivel;
    private Date data_devolucao;
    
    public Biblioteca_Obra(String titulo){
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public String getClassificacao() {
        return classificacao;
    }
    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }
    
    public String getLingua_da_obra() {
        return lingua_da_obra;
    }
    public void setLingua_da_obra(String lingua_da_obra) {
        this.lingua_da_obra = lingua_da_obra;
    }
    
    public String getFormato_midia() {
        return formato_midia;
    }
    public void setFormato_midia(String formato_midia) {
        this.formato_midia = formato_midia;
    }
    
    public String nome_Autores() {
        String autores_nome = "";
        for (Biblioteca_Autor autor : autores) {
            autores_nome += autor.getNome() + ",";
        }
        return autores_nome;
    }
    public void setAutores(ArrayList<Biblioteca_Autor> autores) {
        this.autores = autores;
    }
    
    public String getEditora() {
        return editora;
    }
    public void setEditora(String editora) {
        this.editora = editora;
    }
    
    public Date getData_publicacao() {
        return data_publicacao;
    }
    public void setData_publicacao(Date data_publicacao) {
        this.data_publicacao = data_publicacao;
    }
    
    public Boolean getDisponivel() {
        return disponivel;
    }
    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Date getData_devolucao() {
        return data_devolucao;
    }
    public void setData_devolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    @Override
    public String toString(){
        String str = "";
        str += " *** Livro ***\n";
        str += "   Título: " + this.titulo;
        return str;
    }

    @Override
    public boolean equals(Object x){
        if (x == this){
            return true;
        }
        if (!(x instanceof Biblioteca_Obra) || x == null){
            return false;
        }
        Biblioteca_Obra a = (Biblioteca_Obra) x;
        return a.titulo.equals(this.titulo) ;
    }
}