public class Main {
    public static void main(String[] args) {
        // Criar e executar operações na lista
        ListaTAD lista = new ListaTAD();
        System.out.println(lista.estaVazia());
        lista.insereFinal(30);
        lista.insereFinal(50);
        lista.insereFinal(10);
        lista.insereFinal(20);
        lista.insereFinal(100);
        lista.insereFinal(70);
        lista.insereFinal(1000);
        lista.removeFinal();
        lista.removeFinal();
        lista.removeFinal();
        lista.imprime();
        System.out.println(lista.tamanho());
        lista.imprimeReverso();
        System.out.println(lista.acessa(2)); 
        lista.insereInicio( 45); 
        lista.insere(1, 46);
        System.out.println(lista.toString());
        lista.removeFinal();
        System.out.println(lista.toString());
        lista.removeInicio();
        System.out.println(lista.toString());
        lista.remove(3);
        System.out.println(lista.pesquisa(45));
        lista.altera(0, 47);
        System.out.println(lista.toString());
        lista.limpa();
        System.out.println(lista.toString());
    }
}
