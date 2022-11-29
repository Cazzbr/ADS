package com.example;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller_Transacoes implements Initializable {

    private Produto produto;

    @FXML
    private TextField textCodigoTransacoes
                    , textDescricaoTransacoes
                    , textValorUnTransacoes
                    , textQtdeTransacoes
                    ;

    @FXML
    private Button botaoInserirTransacoes;

    @FXML
    private ListView<Produto> notaFiscalListView;

    @FXML
    private Label labelSubTotal;

    @FXML
    public void onActionButtomInserirPressed(ActionEvent e){
        if (this.produto != null) {
            try{
            produto.setQuantiade_nota(Integer.parseInt(textQtdeTransacoes.getText()));
            notaFiscalListView.getItems().add(produto);
            float currentSubTotal = Float.parseFloat(labelSubTotal.getText());
            labelSubTotal.setText((currentSubTotal + produto.getValor() * produto.getQuantiade_nota()) + "");
            }catch (NumberFormatException ex){
                App.getInstance().registerLogError(ex);
            }
            clearSearchFields();
        }
        textCodigoTransacoes.requestFocus();
    }

    @FXML
    public void onActionButtomFinalizarVendaPressed(ActionEvent e){
        try {
            ImpresaoNotaFiscal nf = new ImpresaoNotaFiscal(notaFiscalListView.getItems(), Float.parseFloat(labelSubTotal.getText()));
            nf.printNf();
        } catch (ClassNotFoundException | IOException | NumberFormatException ex ) {
            App.getInstance().registerLogError(ex);
        }
        
        notaFiscalListView.getItems().clear();
    }

    @FXML
    private void onKeyPressedQtde(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)) {
            onActionButtomInserirPressed(null);    
        }
    }

    @FXML
    public void onKeyPressedCodigo(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            textDescricaoTransacoes.requestFocus();
       }
    }

    @FXML
    public void onKeyPressedDescricao(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            textValorUnTransacoes.requestFocus();
       }
    }

    private void clearSearchFields() {
        textCodigoTransacoes.setText("");
        textDescricaoTransacoes.setText("");
        textValorUnTransacoes.setText("");
        textQtdeTransacoes.setText("");
    }

    private void searchForProducts(String val, String field){
        Search_Product search = new Search_Product();
        produto = search.getResults(val, field);
        if (produto != null){
            textCodigoTransacoes.setText(produto.getCodigo() + "");
            textDescricaoTransacoes.setText(produto.getDescricao());
            textValorUnTransacoes.setText(produto.getValor() + "");
            textQtdeTransacoes.setText("1");
            textQtdeTransacoes.requestFocus();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        textCodigoTransacoes.setPromptText("Pesquisar");
        textDescricaoTransacoes.setPromptText("Pesquisar");
        textCodigoTransacoes.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue & textDescricaoTransacoes.isFocused()){searchForProducts(textCodigoTransacoes.getText(), "codigo");}
        });
        textDescricaoTransacoes.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue & textValorUnTransacoes.isFocused()){searchForProducts(textDescricaoTransacoes.getText(),"descricao");}
        });
        try (Connection connection = ConnectionFactory.getConnection();) {           
        }catch (SQLException e){ 
            App.getInstance().registerLogError(e);
        };
    } 
}