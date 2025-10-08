import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        TreeTAD t = new TreeTAD();
        Scanner scan = new Scanner(System.in);
        
        final String[] menu = {"#################### MENU DE OPERAÇÕES: ####################", "  1 - Imprime formato da árvore", "  2 - Insere um elemento na árvore", "  3 - Pesquisa por um elemento na árvore"
            , "  4 - Verifica se a árvore está vazia", "  5 - Verifica o tamanho da árvore", "  6 - Verifica a altura da árvore", "  7 - Remove elemento da árvore"
            , "  8 - Limpa a árvore", "  9 - Acessa o maior elemento da árvore", " 10 - Acessa o menor elemento da árvore", " 11 - Cria um vetor em ordem a partir dos elementos da árvore"
            , " 12 - Realiza balanceamento estático da árvore", " 13 - Imprime elementos da árvore (em ordem)", " 14 - Imprime elementos da árvore (Pós ordem)", " 15 - Imprime elementos da árvore (Pré ordem)"
            , "  0 - SAIR", "----------------------------------------------------------------"};
        
        int op = -1;
        while (op != 0) {
            switch (op) {
                case 1:
                    t.imprimeArvore();
                    break;
                case 2:
                    System.out.print("Digite o valor a ser inserido na árvore: ");
                    t.insere(scan.nextInt());
                    break;
                case 3:
                    System.out.print("Digite o valor a ser pesquisado na árvore: ");
                    System.out.println(t.pesquisa(scan.nextInt()));
                    break;
                case 4:
                    System.out.println(t.estaVazia());
                    break;
                case 5:
                    System.out.println("O tamanho da árvore é: " + t.tamanho());
                    break;
                case 6:
                    System.out.println("A altura da árvore é: " + t.altura());
                    break;
                case 7:
                    System.out.print("Digite o valor a ser removido da árvore: ");
                    t.remove(scan.nextInt());
                    break;
                case 8:
                    t.limpa();
                    break;
                case 9:
                    System.out.println("O maior elemento da árvore é: " + t.acessaMaior());
                    break;
                case 10:
                    System.out.println("O menor elemento da árvore é: " + t.acessaMenor());
                    break;
                case 11:
                    System.out.println("Vetor em ordem: " + t.criaVetorEmOrdem());
                    break;
                case 12:
                    t.balanceamentoEstatico();
                    break;
                case 13:
                    t.imprimeEmOrdem();
                    break;
                case 14:
                    t.imprimePosOrdem();
                    break;
                case 15:
                    t.imprimePreOrdem();
                    break;
                default:
                    break;
            }

            printMenu(menu);
            op = scan.nextInt();
            System.out.println("");
        }
        scan.close();    
    }

    private static void printMenu(String[] menu) {
        System.out.println("");
        for (String m : menu ){
            System.out.println(m);
        }
        System.out.print("Escolha uma opção do menu: ");

    }
}
