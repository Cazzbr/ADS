package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.ObservableList;

public class Search_NotaFiscal {

    public int insertNfOnDB(Venda v){
        int last_inserted_id = -1;
        String sql = "INSERT INTO venda VALUES (NULL, ?, ?, null)";
        try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, v.getData_venda());
            stmt.setFloat(2, v.getValor_total());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                last_inserted_id = rs.getInt(1);
            }
        }catch (SQLException | NumberFormatException ex){
            App.getInstance().registerLogError(ex);
        }
        return last_inserted_id;
    }

    public void insertProductVendaOnDB(int venda_id, ObservableList<Produto> p){
        String sql = "INSERT INTO venda_mercadoria VALUES (NULL, ?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Produto produto : p) {
                stmt.setInt(1, produto.getQuantiade_nota());
                stmt.setInt(2, venda_id);
                stmt.setInt(3, produto.getId());
                stmt.execute();
                atualizarEstoque(produto, connection);
            }
        }catch (SQLException | NumberFormatException ex){
            App.getInstance().registerLogError(ex);
        }
    }

    private void atualizarEstoque(Produto p, Connection c) throws SQLException{
        int quantidadeEstoque = -999999999;
        String sqlGetEstoque = "SELECT quantidade_estoque FROM mercadoria where id = ?";
        PreparedStatement stmtGetEstoque = c.prepareStatement(sqlGetEstoque);
        stmtGetEstoque.setInt(1, p.getId());
        ResultSet rs = stmtGetEstoque.executeQuery();
        while (rs.next()) {
            quantidadeEstoque = rs.getInt("quantidade_estoque");
        }
        if(quantidadeEstoque != -999999999){
            String sqlUpdateEstoque = "UPDATE mercadoria SET quantidade_estoque=? where id=?";
            PreparedStatement stmt = c.prepareStatement(sqlUpdateEstoque);
            stmt.setInt(1, quantidadeEstoque - p.getQuantiade_nota());
            stmt.setInt(2, p.getId());
            stmt.execute();
        }else{
            throw new NullPointerException("Não foi possível buscar o estoque atual do Produto");
        }
    }
}
