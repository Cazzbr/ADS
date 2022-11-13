package com.example;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller_Transacoes implements Initializable {


    @FXML
    private TextField textCodigoTransacoes
                    , textDescricaoTransacoes
                    , textValorUnTransacoes
                    ,textQtdeTransacoes
                    ;

    @FXML
    public void onActionButtomInserirPressed(ActionEvent e){
        System.out.println("Pressed");
    }

    @FXML
    public void onKeyPressedCodigo(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)) {
            if (textCodigoTransacoes.getText().equals("")){
                textDescricaoTransacoes.setText("");
                textValorUnTransacoes.setText("");
                textQtdeTransacoes.setText("");
            }else{
                textDescricaoTransacoes.setText("Descição do Item");
                textValorUnTransacoes.setText("3.25");
                textQtdeTransacoes.setText("1");
                textQtdeTransacoes.requestFocus();
            }
       }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        textCodigoTransacoes.setPromptText("Código");

    } 
}
