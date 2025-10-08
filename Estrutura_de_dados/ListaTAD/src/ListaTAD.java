public class ListaTAD {
    private Nodo head, tail;
    private int length;

    public ListaTAD() {
        head = null;
        tail = null;
        length = 0;
    }

    public int tamanho() {
        return this.length;
    }

    public boolean estaVazia() {
        if(length == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void imprime() {
        Nodo aux = head;
        while(aux != null) {
            System.out.println(aux.item);
            aux = aux.next;
        }
    }

    public void imprimeReverso() {
        Nodo aux = tail;
        while(aux != null) {
            System.out.println(aux.item);
            aux = aux.prev;
        }
    }

    public String toString() {
        String str = "[";
        Nodo aux = head;
        while(aux != null) {
            str += aux.item + ", ";
            aux = aux.next;
        }
        if (!estaVazia()){
            str = str.substring(0, str.length() - 2);
        } 
        return str + "]";
    }

    public int acessa(int pos) {
        if(pos < 0 || pos >= length) {
            throw new IndexOutOfBoundsException();
        }
        else {
            Nodo aux = head;
            for (int i = 0; i < pos; i++) {
                aux = aux.next;
            }
            return aux.item;
        }
    }

    public void insereFinal(int elem) {
        Nodo novo = new Nodo(elem);
        if(estaVazia()) {
            head = novo;
            tail = novo;
        }
        else {
            novo.prev = tail;
            tail.next = novo;
            tail = novo;
        }
        length = length + 1;
    }

    public void insereInicio(int valor) {
        Nodo novo = new Nodo(valor);
        if(estaVazia()) {
            head = novo;
            tail = novo;
        }
        else {
            novo.next = head;
            head.prev = novo;
            head = novo;
        }
        length = length + 1;

    }

    public void insere(int pos, int valor) {
        if (pos == 0){
            insereInicio(valor);
        }else if (pos == this.length){
            insereFinal(valor);
        } else {
            Nodo aux = this.head;
            for (int i = 0; i < pos; i++ ){
                aux = aux.next;
            }
            Nodo novo = new Nodo(valor);
            novo.prev = aux.prev;
            novo.next = aux;
            aux.prev.next = novo;
            aux.prev = novo;
            length += 1;
        }
    }

    public void removeFinal(){
        if(estaVazia()) {
            throw new IndexOutOfBoundsException();
        }
        else {
            if(length == 1) {
                head = null;
                tail = null;
                length = 0;
            }
            else {
                tail = tail.prev;
                tail.next = null;
                length = length - 1;
            }
        }
    }

    public void removeInicio() {
        if(estaVazia()) {
            throw new IndexOutOfBoundsException();
        }
        else {
            if(length == 1) {
                head = null;
                tail = null;
                length = 0;
            }
            else {
                head = head.next;
                head.prev = null;
                length = length - 1;
            }
        }

    }

    public void remove(int pos) {
        if (length == 0 ){
            throw new IndexOutOfBoundsException();
        }
        else if (pos == 0 ){
            removeInicio();
        } else if (pos == this.length - 1){
            removeFinal();
        } else {
            Nodo aux = this.head;
            for (int i = 0; i < pos; i++ ){
                aux = aux.next;
            }
            aux.prev.next = aux.next;
            aux.next.prev = aux.prev;
            this.length -= 1;
        }
    }

    public boolean pesquisa(int valor) {
        Nodo aux = head;
        while(aux != null) {
            if (aux.item == valor){
                return true;
            }
            aux = aux.next;
        }
        return false;
    }
    

    public void altera(int pos, int valor) {
        if(pos < 0 || pos >= length) {
            throw new IndexOutOfBoundsException();
        }
        else {
            Nodo aux = head;
            for (int i = 0; i < pos; i++) {
                aux = aux.next;
            }
            aux.item = valor;
        }

    }

    public void limpa() {
        Nodo aux = head;
        while(aux != null) {
            aux.prev = null;
            aux = aux.next;
            if (aux != null){
                aux.prev.next = null; 
            }
        }
        this.tail = null;
        this.head = null;
        this.length = 0;
    }
}