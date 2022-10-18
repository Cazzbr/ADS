import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.io.Serializable;

public class Biblioteca_User  implements Serializable{
    private String nome;
    private String rua;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String telefone;
    private String grupo_usuario;
    private ArrayList<Biblioteca_Obra> livros_retirados = new ArrayList<>();
    
    public Biblioteca_User(String nome){
        this.nome = nome;
    }

    public ArrayList<Biblioteca_Obra> getLivros_retirados() {
        return livros_retirados;
    }
    public void retirar_Livro(Biblioteca_Obra livro, int dias_devolver){
        Date today = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DATE, dias_devolver);
        livro.setData_devolucao(cal.getTime());
        livro.setDisponivel(false);
        this.livros_retirados.add(livro);
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getGrupo_usuario() {
        return grupo_usuario;
    }
    public void setGrupo_usuario(String grupo_usuario) {
        this.grupo_usuario = grupo_usuario;
    }

    @Override
    public String toString(){
        String str = "";
        str += " *** Usuário ***\n";
        str += "   Nome: " + this.nome;
        return str;
    }

    @Override
    public boolean equals(Object x){
        if (x == this){
            return true;
        }
        if (!(x instanceof Biblioteca_User) || x == null){
            return false;
        }
        Biblioteca_User a = (Biblioteca_User) x;
        return a.nome.equals(this.nome) ;
    }
}