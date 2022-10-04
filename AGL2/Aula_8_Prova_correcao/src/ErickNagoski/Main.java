package ErickNagoski;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {//5,0/5,0
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        System.out.print("Informe o número de alunos: ");
        int nAlunos = Integer.parseInt(br.readLine());
        ArrayList<Aluno> alunos = new ArrayList<>();
        ArrayList<Aluno> alunosChamada = new ArrayList<>();


        System.out.println("Preencha a lista de alunos: ");
        for (int i = 0; i < nAlunos; i++) {
            System.out.print("Nome: ");
            String nome = br.readLine();
            System.out.print("Assinatura: ");
            String ass = br.readLine();
            Aluno a = new Aluno(nome, ass);
            alunos.add(a);
        }
        System.out.println("\nChamada da aula\n");
        System.out.print("Quantidade de alunos presentes na aula: ");
        int qtdAlunos = Integer.parseInt(br.readLine());

        for (int i = 0; i < qtdAlunos; i++) {
            while (true) {
                System.out.print("Nome: ");
                String nome = br.readLine();
                System.out.print("Assinatura: ");
                String ass = br.readLine();
                if (verificaAlunoExiste(alunos, nome)) {
                    Aluno ab = new Aluno(nome, ass);
                    alunosChamada.add(ab);
                    System.out.println("\n");
                    break;
                } else {
                    System.out.println("O aluno " + nome + " não está na disciplina! Tente novamente\n");
                }
            }
        }

        System.out.println(verificaFalsidades(alunos, alunosChamada));

    }

    public static boolean verificaAlunoExiste(ArrayList<Aluno> alunos, String nome) {
        boolean existe = false;
        for (Aluno al : alunos) {
            if (al.getNome().equalsIgnoreCase(nome)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    public static int verificaFalsidades(ArrayList<Aluno> alunosDisciplina, ArrayList<Aluno> alunosChamada) {
        int falsas = 0;
        for (Aluno aluno : alunosChamada) {
            boolean ehVerdadeira = verificaAssinatura(aluno, alunosDisciplina);
            if (!ehVerdadeira) {
                falsas++;
            }
        }
        return falsas;
    }

    public static boolean verificaAssinatura(Aluno aluno, ArrayList<Aluno> alunosDisciplina) {
        boolean ehVerdadeira = false;
        for (Aluno al : alunosDisciplina) {
            if (al.getNome().equalsIgnoreCase(aluno.getNome())) {
                char[] assinaturaOriginal = al.getAssinatura().toCharArray();
                char[] assinaturaAula = aluno.getAssinatura().toCharArray();
                ehVerdadeira = comparaAssinatura(assinaturaOriginal, assinaturaAula);
            }
        }
        return ehVerdadeira;
    }

    public static boolean comparaAssinatura(char[] original, char[] aula) {
        int diferencas = 0;

        for (int i = 0; i < original.length; i++) {
            if (original[i] != aula[i]) {
                diferencas++;
            }
        }
        return diferencas <= 1;
    }


}