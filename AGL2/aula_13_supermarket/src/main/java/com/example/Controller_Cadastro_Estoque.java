package com.example;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller_Cadastro_Estoque implements Initializable {
    
    private Produto produto;
    private Search_Product search;

    @FXML
    private TextField textCodigoEstoque
                    , textDescricaoEstoque
                    , textValorUnEstoque
                    , textQtdeEstoque
                    , textDescricaoEstoqueNew
                    , textValorUnEstoqueNew
                    , textQtdeEstoquePlus
                    ;

    @FXML
    public void onActionButtomBuscarPressed(ActionEvent e){
        if (textDescricaoEstoque.getText() == ""){
            onKeyPressedCodigo(null);
        }else{
            onKeyPressedDescricao(null);
        }
    }

    @FXML
    public void onKeyPressedCodigo(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            searchForProducts(textCodigoEstoque.getText(), "codigo");
       }
    }

    @FXML
    public void onKeyPressedDescricao(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            searchForProducts(textDescricaoEstoque.getText(),"descricao");
       }
    }

    private void searchForProducts(String val, String field){
        search = new Search_Product();
        produto = search.getResults(val, field);
        if (produto != null){
            textCodigoEstoque.setText(produto.getCodigo() + "");
            textDescricaoEstoque.setText(produto.getDescricao());
            textValorUnEstoque.setText(produto.getValor() + "");
            textQtdeEstoque.setText(produto.getQuantidade_estoque() + "");
            textDescricaoEstoqueNew.setText(produto.getDescricao());
            textValorUnEstoqueNew.setText(produto.getValor() + "");
        }
    }

    @FXML
    public void onActionButtonSalvarPressed(ActionEvent e){
        try {
            if (textQtdeEstoquePlus.getText() != ""){
                produto.setQuantidade_estoque(produto.getQuantidade_estoque() + Integer.parseInt(textQtdeEstoquePlus.getText()));
            }
            produto.setValor(Float.parseFloat(textValorUnEstoqueNew.getText()));
            produto.setDescricao(textDescricaoEstoqueNew.getText());
            search.updateProduct(produto);
            clearfields();
        } catch (NumberFormatException ex) {
            App.getInstance().registerLogError(ex);
        }
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }

    private void clearfields(){
        textCodigoEstoque.setText("");
        textDescricaoEstoque.setText("");
        textValorUnEstoque.setText("");
        textQtdeEstoque.setText("");
        textDescricaoEstoqueNew.setText("");
        textValorUnEstoqueNew.setText("");
        textQtdeEstoquePlus.setText("");
    }
    
}
