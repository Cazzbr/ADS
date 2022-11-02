package com.example;

public abstract class Forma {
    protected double dimPrincipal;
    protected String nome;
    
    protected Forma(double dimPrincipal, String nome){
        this.dimPrincipal = dimPrincipal;
        this.nome = nome;
    }
    
    public Double getDimPrincipal(){
        return this.dimPrincipal;
    }
    public void setDimPrincipla(double d){
        this.dimPrincipal = d;
    }
    
        public String getNome() {
            return nome;
        }
    
        public void setNome(String nome) {
            this.nome = nome;
        }
    
    public abstract double getArea();
}