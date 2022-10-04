public class Carro {
    private String modelo;
    private String placa;
    private int ano_fabricacao;
    
    
    public Carro(String modelo, String placa){
        this.modelo = modelo;
        this.placa = placa;
    }
    public Carro(String modelo, String placa, int ano_fabricacao){
        this(modelo, placa);
        this.ano_fabricacao = ano_fabricacao;
    }
    
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public int getAno_fabricacao() {
        return ano_fabricacao;
    }
    public void setAno_fabricacao(int ano_fabricacao) {
        this.ano_fabricacao = ano_fabricacao;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public String toString(){
        String str = "";
        str += " *** Carro ***\n";
        str += "   Modelo: " + this.modelo + "\n";
        str += "   Placa: " + this.placa + "\n";
        if(this.ano_fabricacao != 0){
            str += "   Coordenador: " + this.ano_fabricacao + "\n";
        }
        return str;
    }

    @Override
    public boolean equals(Object x){
        if (x == this){
            return true;
        }
        if (!(x instanceof Carro) || x == null){
            return false;
        }
        Carro c = (Carro) x;
        return c.placa.equals(this.placa);
    }
}