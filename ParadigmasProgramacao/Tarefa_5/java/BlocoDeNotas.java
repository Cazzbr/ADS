import java.util.ArrayList;
import java.util.Objects;

public class BlocoDeNotas {
    private ArrayList<String> notas;

    public BlocoDeNotas(){
        notas = new ArrayList<String>(96);
    }

    public void deletarNota(int i){
        notas.remove(i);
    }

    public void criarNota(String nota){
        notas.add(nota);
    }

    public void alterarNota(int i, String nota){
        notas.remove(i);
        notas.add(i, nota);
    }

    public void setNotas(ArrayList<String> notas) {
        this.notas = notas;
    }

    public ArrayList<String> getNotas() {
        return notas;
    }

    @Override
    public String toString() {
        return notas.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlocoDeNotas that)) return false;

        return Objects.equals(notas, that.notas);
    }
}
