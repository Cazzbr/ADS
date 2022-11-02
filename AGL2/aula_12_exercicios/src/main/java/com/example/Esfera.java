package com.example;

public class Esfera extends FormaTridimensional{

    public Esfera(double dimPrincipal, String nome){
        super(dimPrincipal, nome);
    }
    
    @Override
    public double getArea(){
        return 4 * Math.PI * Math.pow(dimPrincipal, 2);
    }

    @Override
    public double getVolume(){
        return (4/3) * Math.PI * Math.pow(dimPrincipal, 3);
    }

}
