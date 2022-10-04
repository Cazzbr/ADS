package LeonardoBoniatti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Atividade1 { //3,0/5,0 (código não-modularizado)


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner ler = new Scanner(System.in);
        System.out.println("Informe a quantidade de alunos da turma:");
        int n=parseInt(br.readLine());
        String[] alunos= new String[50]; //se usasse orientação à Objetos, precisaria de apenas dois arrays
        String[] assinatura = new String[50];
        String[] alunos_aula = new String[50];
        String[] assinatura_alunos = new String[50];
        System.out.println("Informe o nome dos alunos e sua assinatura:");
        for(int i=0;i<=n;i++){
            System.out.println("Informe o nome do aluno:");
            String Aluno;
            Aluno=ler.nextLine();
            alunos[i]=Aluno;
            System.out.println("Informe a assinatura do aluno:");
        }

        System.out.println("Informe a quantidade de alunos presentes na aula:");
        int m=parseInt(br.readLine());
        System.out.println("Informe o nome e a assinatura do aluno na aula:");
        for(int i=0;i<=n;i++){
            System.out.println("Informe o nome do aluno na aula:");
            String Aluno_aula;
            Aluno_aula=ler.nextLine();
            alunos_aula[i]=Aluno_aula;
            System.out.println("Informe a assinatura na aula do aluno:");
            String Assinatura_Aluno;
            Assinatura_Aluno=ler.nextLine();
            assinatura_alunos[i]=Assinatura_Aluno;
        }
        int a=0;
        for(int i=0;i<=50;i++){
            if(assinatura!=assinatura_alunos){ //não pode comparar o array. Precisa acessar acessar a posição
                a++;
            }
        }
    }
}
