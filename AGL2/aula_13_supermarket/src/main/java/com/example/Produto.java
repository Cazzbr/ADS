package com.example;

public class Produto {
    private int id;
    private int codigo;
    private String descricao;
    private float valor;
    private int quantidade_estoque;
    private int quantiade_nota;
    
    private int supermercado_id;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    
    public int getQuantidade_estoque() {
        return quantidade_estoque;
    }
    public void setQuantidade_estoque(int quantidade_estoque) {
        this.quantidade_estoque = quantidade_estoque;
    }

    public int getQuantiade_nota() {
        return quantiade_nota;
    }
    public void setQuantiade_nota(int quantiade_nota) {
        this.quantiade_nota = quantiade_nota;
    }

    public int getSupermercado_id() {
        return supermercado_id;
    }
    public void setSupermercado_id(int supermercado_id) {
        this.supermercado_id = supermercado_id;
    }

    @Override
    public String toString(){
        String str;
        str = padLeft((this.id + " - "), 5);
        str += padLeft((this.codigo + ": "), 10);
        str += padRight((this.descricao), 40);
        str += padLeft((" | R$:" + this.valor), 12);
        if (this.quantiade_nota != 0){
            str += padLeft((" Qtde Venda: " + this.quantiade_nota), 25);
        }
        return str;
    }

    private static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);  
    }
   
    private static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);  
    }

    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if (!(o instanceof Produto) || o == null){
            return false;
        }
        Produto p = (Produto) o;
        return p.codigo == this.codigo;
    }
}
