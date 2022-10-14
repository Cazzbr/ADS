package ScriptsJava;

import java.util.Scanner;

public class ScriptCalculadora{
    public static void main(String[] args) throws InterruptedException {
        Calculadora calculadora = new Calculadora();
        int controle = 0;

        while (controle != -1) {
            mostraMenu();
            controle = escolheOpcao(1, 5);
            switch (controle) {
                case 1:
                    System.out.println("Resultado: " + calculadora.somar()); 
                    break;
                case 2:
                    System.out.println("Resultado: " + calculadora.subtrair());
                    break;
                case 3:
                    System.out.println("Resultado: " + calculadora.multiplicar()); 
                    break;
                case 4:
                    System.out.println("Resultado: " + calculadora.dividir()); 
                    break;
                case 5:
                    escolheNumeros(calculadora);
                    break;
                case 6:
                    controle = -1;
            }
            Thread.sleep(2000);
        }
    }

    private static void escolheNumeros(Calculadora calculadora) {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o primeiro número");
        double n1 = in6put.nextDouble();

        System.out.println("Digite o segundo número");
        double n2 = input.nextDouble();

        calculadora.setTermo1(n1);
        calculadora.setTermo2(n2);
    }

    public static void mostraMenu(){
        System.out.println("=-=-=- Calculadora -=-=-="
        + System.lineSeparator() + "1 - Somar"
        + System.lineSeparator() + "2 - Subtrair"
        + System.lineSeparator() + "3 - Multiplicar"
        + System.lineSeparator() + "4 - Dividir"
        + System.lineSeparator() + "5 - Escolher os números"
        + System.lineSeparator() + "6 - Encerrar a execução");
    }

    public static int escolheOpcao(int min, int max) {
        Scanner in = new Scanner(System.in);
        int opcao = in.nextInt();

        if (opcao < min
                && opcao > max) {
            System.out.println("ERRO. Opcão invalida");
            escolheOpcao(min, max);
        }
        return opcao;
    }
}
