public class Quadrado extends FormaBidimensional{

    public Quadrado(double dimPrincipal){
        super(dimPrincipal);
    }

    @Override
    public double getArea(){
        return Math.pow(this.dimPrincipal, 2);
    }
}
