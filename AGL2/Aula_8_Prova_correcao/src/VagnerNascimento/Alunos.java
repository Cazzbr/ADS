package VagnerNascimento;

public class Alunos { //1,0/10,0
    String nome; //private
    private String AssAluno;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public boolean compara(String TestaAluno) {
        return (this.AssAluno.equals(TestaAluno));

            }

    /*public static void main(String[] args) {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int N = (1<= N <= 50);  // representa a quantidade de alunos na turma

    int M = (0 <= M <= N); // quantidade de alunos que compareceram a aula;
*/



}
