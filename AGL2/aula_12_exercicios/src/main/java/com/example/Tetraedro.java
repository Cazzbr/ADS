package com.example;

public class Tetraedro extends FormaTridimensional{

    public Tetraedro(double dimPrincipal, String nome){
        super(dimPrincipal, nome);
    }
    
    @Override
    public double getArea(){
        return Math.pow(dimPrincipal, 2) * Math.pow(3, 1/2);
    }

    @Override
    public double getVolume(){
        return (double) (Math.pow(dimPrincipal, 3) * Math.pow(2, 1/2)) / 12;
    }
}
