public class Contador {
    private static int contador = 0;

    public static int getContador() {
        return contador;
    }

    public static void incrementContador() {
        Contador.contador += Contador.contador;
    }

    public static void zerarContador(){
        Contador.contador = 0;
    }
}
