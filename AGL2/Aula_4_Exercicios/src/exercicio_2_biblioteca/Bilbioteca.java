package exercicio_2_biblioteca;

import java.util.ArrayList;

public class Bilbioteca {
    public String nome;
    public int numero_filial;
    public String endereco;

    static ArrayList<Biblioteca_Obra> livros = new ArrayList<>();
    static ArrayList<Biblioteca_User> users = new ArrayList<>();

    public static Biblioteca_Obra search_Obra(String name) {
        for (Biblioteca_Obra biblioteca_Obra : livros) {
            if (biblioteca_Obra.titulo == name){
                return biblioteca_Obra;
            }
        }
        return null;
    }

    public static Biblioteca_User search_User(String name) {
        for (Biblioteca_User biblioteca_User : users) {
            if (biblioteca_User.nome == name){
                return biblioteca_User;
            }
        }
        return null;
    }

    public static void cadastrar_Livro(Biblioteca_Obra livro) {
        livros.add(livro);
        
    }

    public static void cadastrar_Usuario(Biblioteca_User user) {
        users.add(user);
        
    }
}
