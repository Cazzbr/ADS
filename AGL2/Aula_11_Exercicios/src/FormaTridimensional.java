public abstract class FormaTridimensional extends Forma{

    protected FormaTridimensional(double dimPrincipal){
        super(dimPrincipal);
    }
    
    public abstract double getVolume();

    public abstract double getArea();
}
