package exercicio_2_biblioteca;

import java.util.Date;
import java.util.ArrayList;

public class Biblioteca_Obra {
    public String titulo;
    public String genero;
    public String classificacao;
    public String lingua_da_obra;
    public String formato_midia;

    static ArrayList<Biblioteca_Autor> autores = new ArrayList<>();

    public String editora;
    public Date data_publicacao;

    public Boolean disponivel;
    public Date data_devolucao;

    public static String nome_Autores() {
        String autores_nome = "";
        for (Biblioteca_Autor autor : autores) {
            autores_nome += autor.nome + ",";
        }
        return autores_nome;
    }
}