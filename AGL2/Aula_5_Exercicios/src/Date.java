public class Date {
    
    private int mes;
    private int dia;
    private int ano;

    public Date(int mes, int dia, int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public String displayDate(){
        return (String.format("%02d", this.mes) + "/" + 
                String.format("%02d", this.dia) + "/" + this.ano);
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
