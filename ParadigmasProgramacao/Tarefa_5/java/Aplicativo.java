import java.util.ArrayList;
import java.util.Scanner;

public class Aplicativo {
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        BlocoDeNotas notas = new BlocoDeNotas();
        int opcao = 0;
        int indiceNota = 0;
        String nota;

        do{
            mostraMenu();
            opcao = escolheOpcao(1, 5);
            switch (opcao){
                case 1:
                    nota = escreveNota();

                    notas.criarNota(nota);
                    break;
                case 2:
                    indiceNota = pegaIndiceNota(notas);
                    notas.deletarNota(indiceNota);
                case 3:
                    indiceNota = pegaIndiceNota(notas);
                    nota = escreveNota();
                    notas.alterarNota(indiceNota ,nota);
                case 4:
                    System.out.println(notas);
            }
        }while(opcao != 5);
    }

    public static void mostraMenu(){
        System.out.println("=-=-=- Anotações -=-=-="
                + System.lineSeparator() + "1 - Criar nota"
                + System.lineSeparator() + "2 - Deletar nota"
                + System.lineSeparator() + "3 - Modificar nota"
                + System.lineSeparator() + "4 - Listar notas"
                + System.lineSeparator() + "5 - Encerrar a execução");
    }

    public static int escolheOpcao(int min, int max) {
        int opcao = in.nextInt();

        if (opcao < min
                && opcao > max) {
            System.out.println("ERRO. Opcão invalida");
            escolheOpcao(min, max);
        }
        return opcao;
    }

    public static String escreveNota(){
        String nota;
        System.out.println("Digite sua nota");
        nota = in.next();

        return nota;
    }

    public static int pegaIndiceNota(BlocoDeNotas notas){
        System.out.println("Qual nota você deseja alterar?");
        int indiceNota = escolheOpcao(0, notas.getNotas().size());
        return indiceNota;
    }
}
