public class Circulo extends FormaBidimensional{

    public Circulo(double dimPrincipal){
        super(dimPrincipal);
    }
    
    @Override
    public double getArea(){
        return Math.PI * Math.pow(this.dimPrincipal, 2);
    }
}
