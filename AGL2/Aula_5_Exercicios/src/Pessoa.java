public class Pessoa {
    
    private String nome;
    private Pessoa mae;
    private Pessoa pai;
    
    public Pessoa(String nome) {
        this.nome = nome;

    }
    public Pessoa (String nome, Pessoa mae){
        this(nome);
        this.mae = mae;
    }

    public Pessoa (String nome, Pessoa mae, Pessoa pai){
        this(nome, mae);
        this.pai = pai;

    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pessoa getMae() {
        return mae;
    }
    public void setMae(Pessoa mae) {
        this.mae = mae;
    }

    public Pessoa getPai() {
        return pai;
    }
    public void setPai(Pessoa pai) {
        this.pai = pai;
    }

    public boolean equals(Object x){
        if (x == this){
            return true;
        }
        if (!(x instanceof Pessoa) || x == null){
            return false;
        }
        Pessoa p = (Pessoa) x;
        if (this.mae == null && p.mae == null){
           return true;
        }
        if (this.mae == null || p.mae == null){
            return false;
        }
        return p.nome.equals(this.nome) && p.mae.equals(this.mae);
    }

    public boolean ehAntecessor(Pessoa p){
        if (p.equals(this.mae) || p.equals(this.pai)){
            return true;
        }
        if (this.mae instanceof Pessoa){
            if (this.mae.ehAntecessor(p)){
                return true;
            }
        }
        if (this.pai instanceof Pessoa){
            if (this.pai.ehAntecessor(p)){
                return true;
            }
        }
        return false;
    }

    public static boolean saoIrmaos(Pessoa p1, Pessoa p2){
        if (p1.equals(p2)){
            return false;
        }
        if (p1.mae instanceof Pessoa && p2.mae instanceof Pessoa){
            if (p1.mae.equals(p2.mae)){
                return true;
            }
        }
        if (p1.pai instanceof Pessoa && p2.pai instanceof Pessoa){
            if (p1.pai.equals(p2.pai)){
                return true;
            }
        }
        return false;
    }
}
