import java.util.LinkedList;

public class HashTable {
    private int N; // Número de elementos na Hash
    private LinkedList<Aluno>[] tabela;
    
    public HashTable(int m) {
        this.tabela = new LinkedList[m];
        this.N = 0;
        for (int i = 0; i < m; i++){
            this.tabela[i] = new LinkedList<>();
        }
    }

    private int hash (int chave) {
        return chave % this.tabela.length;
    }
    
    public void insere(Aluno valor){
        this.tabela[this.hash(valor.getMatricula())].add(valor);
        this.N++;
    }

    public void imprime(){
        for(int i = 0; i < this.tabela.length; i++) {
            System.out.println(i + ": " + this.tabela[i]);
        }
    }

    public Aluno pesquisa(int chave) {
        for (Aluno a : this.tabela[this.hash(chave)]){
            if (a.getMatricula() == chave) {
                return a;
            }
        }
        return null;
    }
}
