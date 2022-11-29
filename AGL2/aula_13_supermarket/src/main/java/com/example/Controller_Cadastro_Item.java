package com.example;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class Controller_Cadastro_Item implements Initializable {

    @FXML
    private TextField textCodigoItem
                    , textDescricaoItem
                    , textValorItem
                    , textQuantidadeEstoqueItem
                    ;

    @FXML
    public void onActionCadastrarPressed(ActionEvent e){
        String sql = "INSERT INTO mercadoria VALUES (NULL, ?, ?, ?, ?, 1)";
        try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(textCodigoItem.getText()));
            stmt.setString(2, textDescricaoItem.getText());
            stmt.setFloat(3, Float.parseFloat(textValorItem.getText()));
            stmt.setInt(4, Integer.parseInt(textQuantidadeEstoqueItem.getText()));
            stmt.execute();
        }catch (SQLException | NumberFormatException ex){
            App.getInstance().registerLogError(ex);
        }
        clearfields();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    } 

    private void clearfields(){
        textCodigoItem.setText("");
        textDescricaoItem.setText("");
        textValorItem.setText("");
        textQuantidadeEstoqueItem.setText("");
    }
}
