import java.util.ArrayList;

public class FormasMain {
    public static void main(String[] args) throws Exception {

        ArrayList<Forma> formas = new ArrayList<>();
        
        formas.add(new Circulo(10));
        formas.add(new Quadrado(10));
        formas.add(new Triangulo(10,10,10));
        formas.add(new Esfera(10));
        formas.add(new Cubo(10));
        formas.add(new Tetraedro(10));

        for (Forma f : formas) {
            System.out.println(f.getArea());
            if (f instanceof FormaTridimensional){
                System.out.println(((FormaTridimensional) f).getVolume());
            }
        }
    }
}
