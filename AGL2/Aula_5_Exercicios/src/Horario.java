public class Horario {

    private int hora;
    private int minuto;    
    private int segundo;

    public Horario(int hora, int minuto, int segundo){
        this.hora = hora;
        this.minuto = minuto;
        this.segundo = segundo;
    } 

    public void exibir(){
        System.out.println(this.hora + ":" + this.minuto + ":" + this.segundo);
    }

    public int calcularSegundos(){
        return (this.segundo + (this.minuto * 60) + (this.hora * 3600));
    }

    public int getSegundo() {
        return segundo;
    }
    public void setSegundo(int segundo) {
        this.segundo = segundo;
    }

    public int getMinuto() {
        return minuto;
    }
    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getHora() {
        return hora;
    }
    
    public void setHora(int hora) {
        this.hora = hora;
    }
}
