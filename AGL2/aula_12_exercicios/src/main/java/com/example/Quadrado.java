package com.example;

public class Quadrado extends FormaBidimensional{

    public Quadrado(double dimPrincipal, String nome){
        super(dimPrincipal, nome);
    }

    @Override
    public double getArea(){
        return Math.pow(this.dimPrincipal, 2);
    }
}
