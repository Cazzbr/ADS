public class Invoice {

    private String numero_peca;
    private String descricao;
    private int quantidade;
    private double preco;

    public Invoice(String numero_peca, String descricao, int quantidate, double preco){
        this.numero_peca = numero_peca;
        this.descricao = descricao;
        this.quantidade = quantidate;
        this.preco = preco;
    }

    public double getInvoiceAmount(){
        return (double)(this.preco * this.quantidade);
    }

    public String getNumero_peca() {
        return numero_peca;
    }
    public void setNumero_peca(String numero_peca) {
        this.numero_peca = numero_peca;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        if(quantidade > 0){
            this.quantidade = quantidade;
        }else{
            this.quantidade = 0;
        }
    }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        if(preco > 0.0){
            this.preco = preco;
        }else{
            this.preco = 0;
        }
    }
}
