package exercicio_2_biblioteca;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Biblioteca_User {
    public String nome;
    public String rua;
    public int numero;
    public String complemento;
    public String bairro;
    public String cidade;
    public String estado;
    public String pais;
    public String telefone;

    public String grupo_usuario;

    public ArrayList<Biblioteca_Obra> livros_retirados = new ArrayList<>();

    public void retirar_Livro(Biblioteca_Obra livro, int dias_devolver){
        Date today = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DATE, dias_devolver);
        livro.data_devolucao = cal.getTime();
        livro.disponivel = false;
        this.livros_retirados.add(livro);
    }
}