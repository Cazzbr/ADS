package MauricioVerona;

import java.util.Scanner;

public class Main { //deveria ter modularizado um pouco mais o código
    public static void main(String[] args) { //4,5/5,0 (equals para comparar strings)
        Scanner input = new Scanner(System.in);
        int qtTotal = coletaQtAlunos("Quantos alunos estão matriculados na aula", 1, 50, input);

       int controle;
       String nome;
       String assinatura;
        String[] assinaturaPadrao;
       int qtFalsos = 0;
       String[] nomes;
       int indice = 0;
       int controleFalso = 0;

       controle = 0;
       assinaturaPadrao = new String[qtTotal];
        nomes = new String[qtTotal];
        while (controle != qtTotal){
            nomes[controle] = coletaTexto("Digite seu nome", input).toLowerCase();

            assinaturaPadrao[controle] = coletaTexto("Digite sua assinatura padrao", input);
            //somente para dar um espaço em branco
            System.out.println("");

            controle ++;
        }

        int qtPresencas = coletaQtAlunos("Quantos alunos estavam presentes na aula", 0, qtTotal, input);
        //copiadadoooo
        controle = qtPresencas;
       while (controle != 0){
           nome = coletaTexto("Digite seu nome", input).toLowerCase();

           assinatura = coletaTexto("Digite sua assinatura", input);
           //somente para dar um espaço em branco
           System.out.println("");

           if(nome.length() != assinatura.length()){
               qtFalsos ++;
           } else{
                    for(int i2 = 0; i2 < nomes.length; i2++){
                        if (nomes[i2] == nome){ //comparação entre strings deve ser feita com equals
                            indice = i2;
                        }
                    }
                    for(int i = 0; i<assinaturaPadrao[indice].length(); i++) {
                        if(assinaturaPadrao[indice].toCharArray()[i] != assinatura.toCharArray()[i]){
                            controleFalso ++;
                        }
                    }
           }
           if(controleFalso>1){
               qtFalsos++;
           }

           controle --;
       }

        System.out.println("A quantidade de asinaturas falsas eram: " + qtFalsos);
       input.close();
    }

    public static String coletaTexto(String mensagem, Scanner input){
        System.out.print(mensagem);
        String texto = input.next();

        if(texto.length() > 20){
            coletaTexto("Erro. Não pode possuir mais do que 20 caracteres", input);
        }

        return texto;
    }
    public static int coletaQtAlunos(String mensagem, int min, int max, Scanner input){
        System.out.println(mensagem);
        int qtAlunos = input.nextInt();

        if(qtAlunos <= max
        && qtAlunos >= min){
            return qtAlunos;
        }

        return coletaQtAlunos("Erro digite uma quantidade válida", min, max, input);
    }

}