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

public class Controller_Cadastro_Funcionario implements Initializable {

    @FXML
    private TextField textNomeFuncionario
                    , textFuncaoFuncionario
                    , textEnderecoFuncionario
                    , textCpfFuncionario
                    , textLoginFuncionario
                    , textSenhaFuncionario
                    ;

    @FXML
    public void onActionCadastrarPressed(ActionEvent e){
        String sql = "INSERT INTO funcionario VALUES (NULL, ?, ?, ?, ?, ?, ?, 1)";
        try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, textNomeFuncionario.getText());
            stmt.setString(2, textFuncaoFuncionario.getText());
            stmt.setString(3, textEnderecoFuncionario.getText());
            stmt.setString(4, textCpfFuncionario.getText());
            stmt.setString(5, textLoginFuncionario.getText());
            stmt.setString(6, textSenhaFuncionario.getText());
            stmt.execute();
        }catch (SQLException ex){
            App.getInstance().registerLogError(ex);
        };
        clearfields();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    } 

    private void clearfields(){
        textNomeFuncionario.setText("");
        textFuncaoFuncionario.setText("");
        textEnderecoFuncionario.setText("");
        textCpfFuncionario.setText("");
        textLoginFuncionario.setText("");
        textSenhaFuncionario.setText("");
    }
}
