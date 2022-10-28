public abstract class Forma {
    protected double dimPrincipal;

    protected Forma(double dimPrincipal){
        this.dimPrincipal = dimPrincipal;
    }
    
    public Double getDimPrincipal(){
        return this.dimPrincipal;
    }
    public void setDimPrincipla(double d){
        this.dimPrincipal = d;
    }

    public abstract double getArea();
}