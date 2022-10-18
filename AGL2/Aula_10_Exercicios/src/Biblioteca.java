import java.io.Serializable;
import java.util.ArrayList;

public class Biblioteca implements Serializable{
    private String nome;
    private int numero_filial;
    private String endereco;
    private ArrayList<Biblioteca_Obra> livros = new ArrayList<>();
    private ArrayList<Biblioteca_User> users = new ArrayList<>();

    public Biblioteca(String nome){
        this.nome = nome;
    }
    public Biblioteca(String nome, int numero_filial, String endereco){
        this.nome = nome;
        this.numero_filial = numero_filial;
        this.endereco = endereco;
    }

    public Biblioteca_Obra search_Obra(String name) {
        for (Biblioteca_Obra biblioteca_Obra : livros) {
            if (biblioteca_Obra.getTitulo().equals(name)){
                return biblioteca_Obra;
            }
        }
        return null;
    }

    public Biblioteca_User search_User(String name) {
        for (Biblioteca_User biblioteca_User : users) {
            if (biblioteca_User.getNome().equals(name)){
                return biblioteca_User;
            }
        }
        return null;
    }

    public void cadastrar_Livro(Biblioteca_Obra livro) {
        livros.add(livro);
        
    }

    public void cadastrar_Usuario(Biblioteca_User user) {
        users.add(user);
        
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getNumero_filial() {
        return numero_filial;
    }
    
    public void setNumero_filial(int numero_filial) {
        this.numero_filial = numero_filial;
    }
    
    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
