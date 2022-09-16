public class JogadorTest {
    public static void main(String[] args) {
        Jogador j1 = new Jogador(3245, "Manuel henrique Macedo");
        Jogador j2 = new Jogador(4536, "Marcos Jorel");
        Jogador j3 = new Jogador(1236, "João Pacheco");
        
        j2.aplicarCartao(3);
        j3.setSuspenso(true);

        System.out.println();
        System.out.println(j1);
        System.out.println(j2);
        System.out.println(j3);
    }
}
