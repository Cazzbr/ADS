package PedroBrogliato;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class AssinaturasMain { //4,0/5,0

    public static void main(String[] args) { //faltou implementar corretamente o equals
        Scanner sc = new Scanner(System.in);
        Assinaturas turma = new Assinaturas();
        List<Assinaturas> turminhaTotal = new ArrayList<>();
        List<Assinaturas> turminhaPresentes = new ArrayList<>();


        System.out.print("Digite a quantidade de alunos da turma: ");
        turma.setQntd_alunos(sc.nextInt());

        for (int i = 0; i < turma.getQntd_alunos(); i++) {
            Assinaturas aluno = new Assinaturas();
            System.out.println("---");
            aluno.setNome_aluno(sc.next());
            aluno.setAssinatura(sc.next());
            turminhaTotal.add(aluno);

        }

        System.out.println("Digite a qntd de alunos presentes:");
        turma.setPresentes(sc.nextInt());

        for (int i = turma.getPresentes(); i > 0; i--) {
            Assinaturas aluno = new Assinaturas();
            System.out.println("---");
            aluno.setNome_aluno(sc.next());
            aluno.setAssinatura(sc.next());
            turminhaPresentes.add(aluno);
        }

        System.out.println(turminhaTotal);
        System.out.println(turminhaPresentes);

        int fakes = 0;
        for (int i = 0; i < turma.getPresentes(); i++) {
            if (!turminhaTotal.get(i).equals(turminhaPresentes.get(i))) {
                    fakes++;
                    System.out.println(fakes);
            }
        }




    }
}
