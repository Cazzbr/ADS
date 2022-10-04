package MatheusMayrer;

import java.util.Scanner;

public class Att1 { //5,0/5,0 (perfect!)
    public static void main(String[] args) {
        System.out.println("Qual o tamanho da turma?");
        int tamanho_turma;
        int alunos_presentes = 1;
            tamanho_turma =RecebeInteiro();
            Aluno [] lista_turma = new Aluno[tamanho_turma];
            for (int i = 0; i<tamanho_turma; i++){
                lista_turma [i] = CriaAluno();
            }
        while (alunos_presentes != 0){
            System.out.println("Quantos alunos estão presentes?");
            alunos_presentes = RecebeInteiro();
            while (alunos_presentes > tamanho_turma){
                System.out.println("Valor invalido");
                System.out.println("Quantos alunos estão presentes?");
                alunos_presentes = RecebeInteiro();
            }
            Aluno [] lista_presentes = new Aluno[alunos_presentes];
            for (int i = 0; i<alunos_presentes; i++){
                lista_presentes [i] = CriaAluno();
            }
            int variavel_controle = 0;
            for (int i = 0; i<tamanho_turma; i++){
                for (int c = 0; i<alunos_presentes; i++){
                    if (lista_presentes[c].getNome().equals(lista_turma[i].getNome()) &&
                    !lista_presentes[c].getAssinatura_original().equals(lista_turma[i].getAssinatura_original())){
                        variavel_controle ++;
                    }
                }
            }
            System.out.println("Total de assinaturas falsas: " + variavel_controle);
        }


    }
    public static int RecebeInteiro (){
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
    public static Aluno CriaAluno (){
        Scanner input = new Scanner(System.in);
        System.out.println("Qual o nome do aluno?");
        String nome = input.next();
        System.out.println("Qual a assinatura do aluno");
        String assinatura = input.next();
        return new Aluno(nome, assinatura);
    }
}
