package PedroBrogliato;

public class Assinaturas {
    private int qntd_alunos;
    private int presentes;
    private String nome_aluno;
    private String assinatura;

    public Assinaturas() {
    }

    public void setNome_aluno(String nome_aluno) {
        if (nome_aluno.length() <= 20) {
            this.nome_aluno = nome_aluno;
        }
    }

    public void setAssinatura(String assinatura) {
        this.assinatura = assinatura;
    }

    public void setQntd_alunos(int qntd_alunos) {
        if (qntd_alunos >= 1 && qntd_alunos <= 50) {
            this.qntd_alunos = qntd_alunos;
        }
    }

    public void setPresentes(int presentes) {
        if (presentes >= 0 && presentes <= qntd_alunos) {
            this.presentes = presentes;
        }
    }

    public String getNome_aluno() {
        return nome_aluno;
    }

    public String getAssinatura() {
        return assinatura;
    }

    public int getQntd_alunos() {
        return qntd_alunos;
    }

    public int getPresentes() {
        return qntd_alunos;
    }

    @Override
    public String toString() {
        String out = "";
        out += "Nome do aluno: " + nome_aluno + "\n";
        out += "Assinatura: " + assinatura + "\n";
        return out;
    }

    @Override //não funciona
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
