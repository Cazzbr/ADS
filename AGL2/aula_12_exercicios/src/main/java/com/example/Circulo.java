package com.example;

public class Circulo extends FormaBidimensional{

    public Circulo(double dimPrincipal, String nome){
        super(dimPrincipal, nome);
    }
    
    @Override
    public double getArea(){
        return Math.PI * Math.pow(this.dimPrincipal, 2);
    }
}
