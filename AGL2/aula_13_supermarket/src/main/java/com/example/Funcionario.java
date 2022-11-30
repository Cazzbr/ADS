package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Funcionario {

    private int id;
    private String nome;
    private String funcao;
    private String endereco;
    private String cpf;
    private String login;
    private String senha;
    private int supermercado_id;

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getSupermercado_id() {
        return supermercado_id;
    }
    public void setSupermercado_id(int supermercado_id) {
        this.supermercado_id = supermercado_id;
    }

    @Override
    public String toString(){
        String str;
        str = this.login;
        return str;
    }

    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if (!(o instanceof Funcionario) || o == null){
            return false;
        }
        Funcionario f = (Funcionario) o;
        return f.id == this.id;
    }

    public static ArrayList<Funcionario> getfuncionarios(String sql){
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                Funcionario f = new Funcionario();

                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setFuncao(rs.getString("funcao"));
                f.setEndereco(rs.getString("endereco"));
                f.setCpf(rs.getString("cpf"));
                f.setLogin(rs.getString("login"));
                f.setSenha(rs.getString("senha"));
                f.setSupermercado_id(rs.getInt("supermercado_id"));

                funcionarios.add(f);
            }
        } catch (Exception e) {
            App.getInstance().registerLogError(e);
        }
        return funcionarios;
    }
}
