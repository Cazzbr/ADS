public class Cubo extends FormaTridimensional{

    public Cubo(double dimPrincipal){
        super(dimPrincipal);
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
