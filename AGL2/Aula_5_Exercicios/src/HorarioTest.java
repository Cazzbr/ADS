public class HorarioTest {
    public static void main(String[] args) {
        
        Horario h = new Horario(3, 45, 20);
        
        System.out.println();
        h.exibir();

        System.out.println("Em segudnos é: " + h.calcularSegundos() + " s");
    }
}
