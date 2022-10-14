package Prova_E2;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Estacionamento {
    private String nome;
    private Double valor_hora;
    private Carro [] vagas = new Carro[269];
    private Date [] hora_entrada = new Date[269];
    private Double valor_total = 0.0;

    private final TimeUnit time = TimeUnit.HOURS;

    public Estacionamento(String nome, double valor_hora){
        this.nome = nome;
        this.valor_hora = valor_hora;
    }
    public Estacionamento(String nome, float valor_hora, Carro carro){
        this(nome, valor_hora);
        this.setCarros(carro);
    }

    public Double saida_carro(int vaga){
        long diff = this.hora_entrada[vaga].getTime() - Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()).getTime();
        this.vagas[vaga] = null;
        this.hora_entrada[vaga] = null;
        Double valor_pagar = (Double)(time.convert(diff, TimeUnit.MILLISECONDS) * this.valor_hora);
        this.valor_total += valor_pagar;
        return valor_pagar;
    }

    public String mostrar_carros_estacionados(){
        String str = "";
        
        for (int i = 0; i < this.vagas.length; i++){
            if (vagas[i] != null){
                str += " *** Carro na vaga " + i + " ***\n";
                str += "     " + vagas[i];
                str += "      Hora de entrada: "+ this.hora_entrada[i];
            }
        }
        return str;
    }

    public boolean procurar_carro_placa(String placa){
        for (Carro c : vagas) {
            if (c != null){
                if (c.getPlaca().equals(placa)){
                    return true;
                }
            }
        }  
        return false;
    }

    public Double getValor_hora() {
        return valor_hora;
    }
    public void setValor_hora(Double valor_hora) {
        this.valor_hora = valor_hora;
    }

    public Carro[] getCarros() {
        return vagas;
    }
    public boolean setCarros(Carro carro) {
        for (int i = 0; i < vagas.length; i++) {
            if (vagas[i] == null){
                vagas[i] = carro;
                setHora_entrada(i, Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
                return true;
            }
        }
        return false;
    }

    public Date getHora_entrada(int vaga) {
        return hora_entrada[vaga];
    }
    public void setHora_entrada(int vaga, Date hora_entrada) {
        this.hora_entrada[vaga] = hora_entrada;
    }

    public Double getValor_total() {
        return valor_total;
    }

    @Override
    public String toString(){
        String str = "";
        str += " *** Estacionamento ***\n";
        str += "   Valor/hora: " + this.valor_hora + "\n";
        str += "   Valor arrecadado hoje: " + this.valor_total + "\n";
        return str;
    }

    @Override
    public boolean equals(Object x){
        if (x == this){
            return true;
        }
        if (!(x instanceof Estacionamento) || x == null){
            return false;
        }
        Estacionamento e = (Estacionamento) x;
        return e.nome.equals(this.nome);
    }
}
