import java.util.Scanner;

public class VerificaPresenca {
    public static void main(String[] args) {
        int totalAlunos;
        int totalPresencas;
        int controle = 0;
        int indice = 0;

        System.out.println("Quantos alunos estão matriculados na turma");
        totalAlunos = recebeInt(50 , 0, true);
        Aluno[] alunos = new Aluno[totalAlunos];
        for(int i = 0; i<totalAlunos; i++){
            System.out.println("fs" + i);
            Aluno aluno = criaAluno();
            alunos[i] = aluno;
        }

        System.out.println("Quantos alunos estavam presentes");
        totalPresencas = recebeInt(totalAlunos, 0, true);
        for(int i2 = 0; i2 < totalPresencas; i2++){
            System.out.println("Qual seu nome?");
            String nome = recebeString().toLowerCase();

            for(int i3 = 0; i3<alunos.length; i3++){
                if(alunos[i3].getNome().toLowerCase().equals(nome)){
                    indice = i3;
                }
            }
            System.out.println("Assine a chamada");
            String assinatura = recebeString();

            alunos[indice].assinarPresenca(assinatura);
            if(!(alunos[indice].getASSINATURAPADRAO().equals(alunos[indice].getAssinatura()))){
                controle ++;
            }
        }
        System.out.println("O número de assinaturas falsas é: " + controle);
    }

    public static String recebeString(){
        Scanner in = new Scanner(System.in);
        String texto = in.next();

        if(texto.length()>=20){
            System.out.println("ERRO");
            recebeString();
        }
        return texto;
    }

    public static int recebeInt(int max, int min, boolean limitado){
        Scanner in = new Scanner(System.in);
        int inteiro = in.nextInt();

        if(limitado){
            if(inteiro<=max
            && inteiro>=min){
                return inteiro;
            } else {
                recebeInt(max, min, limitado);
            }
        }
        return inteiro;  
    }

    public static Aluno criaAluno(){
        System.out.println("Digite o nome do aluno");
        String nome = recebeString();

        System.out.println("Digite a assinatura padrão do aluno");
        String ASSINATURAPADRAO = recebeString();

        Aluno aluno = new Aluno(nome, ASSINATURAPADRAO);

        return aluno;
    }
}
