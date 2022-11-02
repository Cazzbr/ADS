package com.example;

public abstract class FormaTridimensional extends Forma{

    protected FormaTridimensional(double dimPrincipal, String nome){
        super(dimPrincipal, nome);
    }
    
    public abstract double getVolume();

    public abstract double getArea();
}
