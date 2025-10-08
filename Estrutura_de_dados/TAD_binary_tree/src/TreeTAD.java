import java.util.ArrayList;

public class TreeTAD {
    private Nodo raiz;

    public TreeTAD() { }


    public boolean estaVazia() {
        return this.raiz == null;
    }

    public int tamanho() {
        return tamanhoRec(raiz);

    }

    private int tamanhoRec(Nodo n) {
        if (n == null){
            return 0;
        }
        return tamanhoRec(n.esq) + 1 + tamanhoRec(n.dir);
    }

    public int altura(){
        return alturaRec(this.raiz);
    }

    private int alturaRec(Nodo n) {
        if (n == null) {
            return 0;
        }
        int esq = alturaRec(n.esq);
        int dir = alturaRec(n.dir);
        return Math.max(esq, dir) + 1;
    }

    public void limpa() {
        this.limpaRec(raiz);
        this.raiz = null;
    }
    private void limpaRec(Nodo n){
        if (n != null) {
            this.limpaRec(n.esq);
            this.limpaRec(n.dir);
            n.esq = null;
            n.dir = null;
        }

    }

    public void insere(int elem) {
        if (estaVazia()) {
            this.raiz = new Nodo(elem);
        } else {
            insereRec(elem, raiz);
        }
    }

    private void insereRec(int elem, Nodo n) {
        if (elem == n.elem) {
            System.out.println("Elemento já existe!");
        } else if (elem < n.elem){
            if (n.esq == null){
                n.esq = new Nodo(elem);
            } else {
                insereRec(elem, n.esq);
            }
        } else {
            if (n.dir == null){
                n.dir = new Nodo(elem);
            } else {
                insereRec(elem, n.dir);
            }

        }
    }

    public boolean pesquisa(int elem) {
        return pesquisaRec(elem, raiz);
    }

    private boolean pesquisaRec(int elem, Nodo n) {
        if (n == null) { // base 1 - fim da arvore e elemento não encontrado
            return false;
        }
        if (elem == n.elem){ // base 2 - elemento encontrado
            return true;
        }
        if (elem < n.elem){ // passo 1 - elemento é menor que o nodo atual
            return pesquisaRec(elem, n.esq);
        }
        return pesquisaRec(elem, n.dir); // passo 2 - elemento é maior que nodo atual
    }

    public void imprimeEmOrdem() {
        this.imprimeEmOrdemRec(raiz);
    }
    
    private void imprimeEmOrdemRec(Nodo n) {
        if (n != null) {
            this.imprimeEmOrdemRec(n.esq);
            System.out.println(n.elem);
            this.imprimeEmOrdemRec(n.dir);
        }
    }

    public void imprimePosOrdem() {
        this.imprimePosOrdemRec(raiz);
    }
 
    private void imprimePosOrdemRec(Nodo n) {
        if (n != null) {
            this.imprimePosOrdemRec(n.esq);
            this.imprimePosOrdemRec(n.dir);
            System.out.println(n.elem);
        }
    }

    public void imprimePreOrdem() {
        this.imprimePreOrdemRec(raiz);
    }

    private void imprimePreOrdemRec(Nodo n) {
        if (n != null) {
            System.out.println(n.elem);
            this.imprimePreOrdemRec(n.esq);
            this.imprimePreOrdemRec(n.dir);
        }
    }

    public int acessaMaior() {
        return acessaMaiorRec(raiz);
    }
    
    private int acessaMaiorRec(Nodo n) {
        if (n.dir == null) { // base - fim da arvore para direita, maior valor
            return n.elem;
        }
        return acessaMaiorRec(n.dir); // passo - segue para direita da arvore
    }
    
    public int acessaMenor() {
        return acessaMenorRec(raiz);
    }

    private int acessaMenorRec(Nodo n) {
        if (n.esq == null) { // base - fim da arvore para esquerda, menor valor
            return n.elem;
        }
        return acessaMenorRec(n.esq); // passo - segue para esquerda da arvore
    }

    public ArrayList<Integer> criaVetorEmOrdem() {
        ArrayList<Integer> vetor = new ArrayList<>();
        this.criaVetorEmOrdem(raiz, vetor);
        return vetor;
    }

    private void criaVetorEmOrdem(Nodo n, ArrayList<Integer> vetor) {
        if (n != null) {
            this.criaVetorEmOrdem(n.esq, vetor);
            vetor.add(n.elem);
            this.criaVetorEmOrdem(n.dir, vetor);
        }
    }

    public void balanceamentoEstatico() {
        ArrayList<Integer> vetorEmOrdem = this.criaVetorEmOrdem();
        this.limpa();
        this.preencheArvoreBinaria(vetorEmOrdem, 0, vetorEmOrdem.size() - 1);
    }

    private void preencheArvoreBinaria(ArrayList<Integer> vetorEmOrdem, int i, int f) {
        if (i <= f){
            int m = (i + f) / 2;
            this.insere(vetorEmOrdem.get(m));
            this.preencheArvoreBinaria(vetorEmOrdem, i, m - 1);
            this.preencheArvoreBinaria(vetorEmOrdem, m + 1, f);
        }
    }

    /****************************/
    /* MÉTODOS JÁ IMPLEMENTADOS */
    /****************************/

    public void imprimeArvore() {
        this.imprimeArvoreRec(this.raiz, 0);
    }

    private void imprimeArvoreRec(Nodo n, int depth) {
        if (n == null) {
            return;
        }
        this.imprimeArvoreRec(n.dir, depth + 1);
        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
        System.out.println(n.elem);
        this.imprimeArvoreRec(n.esq, depth + 1);
    }

    public void remove(int elem) {
        this.raiz = this.removeRec(elem, this.raiz);
    }

    private Nodo removeRec(int elem, Nodo n) {
        if (n == null) {
            return null;
        } else if (elem == n.elem) {
            if (n.esq == null && n.dir == null) {
                return null;
            } else if (n.esq == null) {
                return n.dir;
            } else if (n.dir == null) {
                return n.esq;
            } else {
                Nodo aux = n.dir;
                while (aux.esq != null) {
                    aux = aux.esq;
                }
                n.elem = aux.elem;
                n.dir = this.removeRec(aux.elem, n.dir);
            }
        } else if (elem < n.elem) {
            n.esq = removeRec(elem, n.esq);
        } else {
            n.dir = removeRec(elem, n.dir);
        }
        return n;
    }
}

