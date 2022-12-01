package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Search_SuperMarket {

    public static SuperMercado search(){
        SuperMercado s = new SuperMercado();
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement("select * from supermercado where id = 1");
            ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    s.setId(rs.getInt("id"));
                    s.setNome(rs.getString("nome"));
                    s.setEndereco(rs.getString("endereco"));
                    s.setCnpj(rs.getString("cnpj"));
                    s.setGerente(rs.getInt("gerente"));
                }
        }catch (SQLException e){
            App.getInstance().registerLogError(e);
        };
        return s;
    }

    public static void updateSuperMarket(SuperMercado s){
        String sql = "update supermercado set nome=?, endereco=?, cnpj=?, gerente=? where id=1";
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, s.getNome());
            stmt.setString(2, s.getEndereco());
            stmt.setString(3, s.getCnpj());
            if (s.getGerente() == 0){
                stmt.setNull(4, 0);
            }else{
                stmt.setInt(4, s.getGerente());
            }
            stmt.execute();
        }catch (SQLException | NumberFormatException ex){
            App.getInstance().registerLogError(ex);
        }
    }
}
