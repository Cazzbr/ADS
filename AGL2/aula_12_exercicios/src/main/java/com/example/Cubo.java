package com.example;

public class Cubo extends FormaTridimensional{

    public Cubo(double dimPrincipal, String nome){
        super(dimPrincipal, nome);
    }

    @Override
    public double getArea(){
        return 6 * Math.pow(this.dimPrincipal, 2);
    }

    @Override
    public double getVolume(){
        return Math.pow(dimPrincipal, 3);
    }

    
}
