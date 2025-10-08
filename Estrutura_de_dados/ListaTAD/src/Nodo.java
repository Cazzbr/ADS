public class Nodo {
    public int item;
    public Nodo prev, next;

    public Nodo(int item) {
        prev = null;
        next = null;
        this.item = item;
    }
}
