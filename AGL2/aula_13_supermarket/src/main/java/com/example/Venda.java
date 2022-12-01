package com.example;

import java.sql.Date;

public class Venda {
    private int id;
    private Date data_venda;
    private float valor_total;
    private int funcionario_id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Date getData_venda() {
        return data_venda;
    }
    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }
    
    public float getValor_total() {
        return valor_total;
    }
    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    public int getFuncionario_id() {
        return funcionario_id;
    }
    public void setFuncionario_id(int funcionario_id) {
        this.funcionario_id = funcionario_id;
    }
    @Override
    public String toString(){
        String str;
        str = padLeft((this.id + ": "), 4);
        str += padRight((this.data_venda.toString()), 20);
        str += " -> Valor Total da nota R$: " + this.valor_total;

        return str;
    }

    private static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);  
    }
   
    private static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);  
    }

    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if (!(o instanceof Venda) || o == null){
            return false;
        }
        Venda v = (Venda) o;
        return v.id == this.id;
    }
}
