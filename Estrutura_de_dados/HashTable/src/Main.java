public class Main {
    public static void main(String[] args) throws Exception {
        
        HashTable hash = new HashTable(5);

        hash.insere(new Aluno(65287, "Alisson Marques", "alisson.marques@ifrs.edu.br"));
        hash.insere(new Aluno(82736, "Maria Souza", "maria.souza@ifrs.edu.br"));
        hash.insere(new Aluno(19825, "Aline Torres", "aline.torres@ifrs.edu.br"));
        hash.insere(new Aluno(98352, "Marcio Alves", "marcio.alves@ifrs.edu.br"));
        hash.insere(new Aluno(87453, "Tabata Cruz", "tabata.cruz@ifrs.edu.br"));
        hash.insere(new Aluno(76243, "Alan Moura", "alan.moura@ifrs.edu.br"));
        hash.insere(new Aluno(87461, "Naiara Sette", "naiara.sette@ifrs.edu.br"));

        hash.imprime();

        System.out.println(hash.pesquisa(65287));
        System.out.println(hash.pesquisa(22222));
    }
}