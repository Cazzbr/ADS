package TiagoMoroni;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ex1 { //5,0/5,0

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Aluno[] alunos = {};

    public static void main(String[] args) throws IOException {
        int n;
        while (true) {
            System.out.println("Informe a quantidade de alunos na turma(0 < n < 51)");
            while (true) {
                n = Integer.parseInt(br.readLine());
                if(n == 0){
                    return;
                }
                if (1 <= n && n <= 50) {
                    collectOriginalSignatures(n);
                    break;
                } else {
                    System.out.println("Número deve ser maior que 0 e menor que 51");
                }
            }
            while (true) {
                System.out.println("Informe o número de alunos presentes na aula(0 <= m <= " + n + ")");
                int m = Integer.parseInt(br.readLine());
                if (0 <= m && m <= n) {
                    collectClassSignatures(m);
                    alunos = new Aluno[]{};
                    break;
                } else {
                    System.out.println("Número informado deve ser maior igual a 0 e menor igual a " + n);
                }
            }
        }
    }

    static void collectClassSignatures(int m) throws IOException {
        int assinaturasFalsas = 0;
        String nome, assinatura, info;
        Aluno alunoCriado, alunoOriginal;
        for (int i = 0; i < m; i++) {
            while(true) {
                System.out.println("Informe o nome(somente o primeiro) e assinatura do aluno no formato '[nome] [assinatura]'");
                while (true) {
                    info = br.readLine();
                    nome = info.split(" ")[0];
                    if (info.split(" ").length != 2) {
                        System.out.println("Só deve ser informado o primeiro nome do aluno e sua assinatura, informe novamente...");
                    }
                    if (nome.split("").length > 20) {
                        System.out.println("Nome pode ter no máximo vinte letras, informe novamente...");
                    }
                    if (!(info.split(" ").length != 2 || nome.split("").length > 20)) {
                        break;
                    }
                }
                assinatura = info.split(" ")[1];
                alunoCriado = new Aluno(nome, assinatura);
                alunoOriginal = findAluno(nome);
                if(alunoOriginal != null){
                    break;
                }else{
                    System.out.println("Aluno não encontrado, informe de novo...");
                }
            }
            if (!alunoOriginal.equals(alunoCriado)) {
                assinaturasFalsas++;
            }
        }
        System.out.println("Número de assinaturas falsas: " + assinaturasFalsas);
    }

    static Aluno findAluno(String nome) {
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equals(nome)) {
                return aluno;
            }
        }
        return null;
    }

    static void collectOriginalSignatures(int n) throws IOException {
        String nome, assinatura, info;
        for (int i = 0; i < n; i++) {
            System.out.println("Informe o nome e assinatura do aluno no formato '[nome] [assinatura]'");
            while (true) {
                info = br.readLine();
                nome = info.split(" ")[0];
                if (info.split(" ").length != 2) {
                    System.out.println("Só deve ser registrado o primeiro nome do aluno e sua assinatura, informe novamente...");
                }
                if (nome.split("").length > 20) {
                    System.out.println("Nome pode ter no máximo vinte letras, informe novamente...");
                }
                if (!(info.split(" ").length != 2 || nome.split("").length > 20)) {
                    break;
                }
            }
            assinatura = info.split(" ")[1];
            Aluno aluno = new Aluno(nome, assinatura);
            addAlunoToArray(aluno);
        }
    }

    static void addAlunoToArray(Aluno el) {
        int N = alunos.length;
        alunos = Arrays.copyOf(alunos, N + 1);
        alunos[N] = el;
    }
}
