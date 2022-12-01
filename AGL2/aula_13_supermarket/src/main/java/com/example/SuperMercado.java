package com.example;

public class SuperMercado {

    private int id;
    private String nome;
    private String endereco; 
    private String cnpj;
    private int gerente;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    } 
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    public int getGerente() {
        return gerente;
    }
    public void setGerente(int gerente) {
        this.gerente = gerente;
    }
    @Override
    public String toString(){
        String str;
        str = padLeft((this.id + ": "), 5);
        str += padRight((this.nome), 40);
        str += " | CNPJ: " + this.cnpj;
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
        if (!(o instanceof SuperMercado) || o == null){
            return false;
        }
        SuperMercado s = (SuperMercado) o;
        return s.id == this.id;
    }
}
