package com.example;

public class Triangulo extends FormaBidimensional{
    private double dimSecundaria;
    private double dimTerciaria;
    
    public Triangulo(double dimPrincipal, String nome, double dimSecundaria, double dimTerciaria){
        super(dimPrincipal, nome);
        this.dimSecundaria = dimSecundaria;
        this.dimTerciaria = dimTerciaria;
    }
    
    @Override
    public double getArea(){
        double p = (this.dimPrincipal + this.dimSecundaria + this.dimTerciaria)/2;
        double area = Math.pow((p*(p - dimPrincipal)*(p-dimSecundaria)*(p-dimTerciaria)),1/2);
        return area;
    }

    public double getDimSecundaria() {
        return dimSecundaria;
    }
    public void setDimSecundaria(double dimSecundaria) {
        this.dimSecundaria = dimSecundaria;
    }

    public double getDimTerciaria() {
        return dimTerciaria;
    }
    public void setDimTerciaria(double dimTerciaria) {
        this.dimTerciaria = dimTerciaria;
    }
}
