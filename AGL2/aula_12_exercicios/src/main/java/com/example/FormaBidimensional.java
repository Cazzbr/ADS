package com.example;

public abstract class FormaBidimensional extends Forma{

    protected FormaBidimensional(double dimPrincipal, String nome){
        super(dimPrincipal, nome);
    }
    
    public abstract double getArea();
}
