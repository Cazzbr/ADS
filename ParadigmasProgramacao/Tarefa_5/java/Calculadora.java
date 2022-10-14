package ScriptsJava;

public class Calculadora{
    private double termo1;
    private double termo2;

    //CONSTRUTORES
    public Calculadora(){
        termo1 = 0;
        termo2 = 0;
    }
    public Calculadora(double termo1, double termo2){
        this.termo1 = termo1;
        this.termo2 = termo2;
    }

    //GETTERS
    public double getTermo1(){
        return termo1;
    }
    public double getTermo2(){
        return termo2;
    }

    //SETTERS
    public void setTermo1(double termo1){
        this.termo1 = termo1;
    }
    public void setTermo2(double termo2){
        this.termo2 = termo2;
    }

    //METODOS
    public double somar(){
        System.out.println(termo1 + " + " + termo2);
        return termo1 + termo2;
    }

    public double subtrair(){
        System.out.println(termo1 + " - " + termo2);
        return termo1 - termo2;
    }

    public double multiplicar(){
        System.out.println(termo1 + " * " + termo2);
        return termo1 * termo2;
    }

    public double dividir(){
        System.out.println(termo1 + " / " + termo2);
        return termo1 / termo2;
    }

    @Override
    public String toString() {
        return "Termo 1: " + termo1
        + System.lineSeparator() + "Termo 2: " + termo2;
    }
}