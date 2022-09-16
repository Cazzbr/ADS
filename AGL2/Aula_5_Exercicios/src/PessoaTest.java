public class PessoaTest {
    public static void main(String[] args) {
        
        Pessoa p1 = new Pessoa("Maria");
        Pessoa p2 = new Pessoa("José", p1);
        Pessoa p3 = new Pessoa("José", p1);
        Pessoa p4 = new Pessoa("Marcos", p1);
        Pessoa p5 = new Pessoa("Maria");

        System.out.println();
        System.out.println(p2.equals(p3));
        System.out.println();
        System.out.println(p1.equals(p4));
        System.out.println();
        System.out.println(p1.equals(p5));
        System.out.println();
        System.out.println(p1.equals(p2));
        System.out.println();
        System.out.println(Pessoa.saoIrmaos(p1, p2));
        System.out.println();
        System.out.println(Pessoa.saoIrmaos(p3, p4));
        System.out.println();
        System.out.println(p1.ehAntecessor(p2));
        System.out.println();
        System.out.println(p2.ehAntecessor(p1));
    }
}
