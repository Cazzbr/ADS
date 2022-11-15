package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT",
                    "root",
                    "");
            System.out.println("Conectado!");
            return con;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar no banco de dados!");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        getConnection();
    }
}
