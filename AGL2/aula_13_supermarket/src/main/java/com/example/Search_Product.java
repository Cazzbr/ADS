package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Search_Product {
    
    private String keyWord;
    private String field;

    public Produto getResults(String keyWord, String field){
        setKeyWordAndField(keyWord, field);
        String sql;
        ObservableList<Produto> produtcArrayList = FXCollections.observableArrayList();
        if (keyWord == ""){
            sql = "SELECT * FROM mercadoria WHERE supermercado_id = 1";
        } else {
            sql = "SELECT * FROM mercadoria WHERE " + field + " LIKE '%" + keyWord + "%' AND supermercado_id = 1";
        }
        try (Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Produto p = new Produto();
                    p.setId(rs.getInt("id"));
                    p.setCodigo(rs.getInt("codigo"));
                    p.setDescricao(rs.getString("descricao"));
                    p.setValor(rs.getFloat("valor"));
                    p.setQuantidade_estoque(rs.getInt("quantidade_estoque"));
                    p.setSupermercado_id(rs.getInt("supermercado_id"));
                    produtcArrayList.add(p);
                }
        }catch (SQLException e){ // TODO Auto-generated catch block 
            System.out.println(e);};
        if (produtcArrayList.size() == 1){
            return produtcArrayList.get(0);
        }
        SearchPopup chooseResult = new SearchPopup();
        chooseResult.display(produtcArrayList);
        if (chooseResult.getProduto() != null){
            return chooseResult.getProduto();
        }
        return null;
    }

    public void updateProduct(Produto p){
        String sql = "update mercadoria set descricao=?, valor=?, quantidade_estoque=? where id=?";
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, p.getDescricao());
            stmt.setFloat(2, p.getValor());
            stmt.setInt(3, p.getQuantidade_estoque());
            stmt.setInt(4, p.getId());
            stmt.execute();
        }catch (SQLException ex){ System.out.println(ex);};
    }

    public String getField() {
        return field;
    }
    public String getKeyWord() {
        return keyWord;
    }
    public void setKeyWordAndField(String keyWord, String field) {
        this.keyWord = keyWord;
        this.field = field;
    }
}
