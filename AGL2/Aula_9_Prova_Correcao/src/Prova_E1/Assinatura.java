package Prova_E1;

public class Assinatura {
    private String nome;
    private String assinatura;
    
    public Assinatura(String [] nome_assinatura){
        this.nome = nome_assinatura[0];
        this.assinatura = nome_assinatura[1];
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(String assinatura) {
        this.assinatura = assinatura;
    }

    @Override
    public String toString() {
        String str = "";
        str += "   Nome: " + this.nome + "\n";
        str += "   Assinatura: " + this.assinatura + "\n";
        return str;
    }

    @Override
    public boolean equals(Object x){
        if (x == this){
            return true;
        }
        if (!(x instanceof Assinatura) || x == null){
            return false;
        }
        Assinatura a = (Assinatura) x;
        return a.assinatura.equalsIgnoreCase(this.assinatura);
    }
}
