package com.example;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
    public void onKeyPressedCodigo(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            searchForProducts(textCodigoTransacoes.getText(), "codigo");
       }
    }

    @FXML
    private void onKeyPressedQtde(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)) {
            onActionButtomInserirPressed(null);    
        }
    }

    @FXML
    public void onKeyPressedDescricao(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            searchForProducts(textDescricaoTransacoes.getText(),"descricao");
       }
    }

    @FXML
    public void onActionButtomFinalizarVendaPressed(ActionEvent e){
        float total = Float.parseFloat(labelSubTotal.getText());
        Venda v = new Venda();
        v.setData_venda(Date.valueOf(java.time.LocalDate.now()));
        v.setValor_total(total);
        Search_NotaFiscal snf = new Search_NotaFiscal();
        int inserted_id = snf.insertNfOnDB(v);
        snf.insertProductVendaOnDB(inserted_id, notaFiscalListView.getItems());
        try {
            ImpresaoNotaFiscal nf = new ImpresaoNotaFiscal(notaFiscalListView.getItems(), total);
            nf.printNf();
        } catch (ClassNotFoundException | IOException | NumberFormatException ex ) {
            App.getInstance().registerLogError(ex);
        }
        notaFiscalListView.getItems().clear();
        labelSubTotal.setText("0.00");
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

    private void clearSearchFields() {
        textCodigoTransacoes.setText("");
        textDescricaoTransacoes.setText("");
        textValorUnTransacoes.setText("");
        textQtdeTransacoes.setText("");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        textCodigoTransacoes.setPromptText("Pesquisar");
        textDescricaoTransacoes.setPromptText("Pesquisar");
        try (Connection connection = ConnectionFactory.getConnection();) {           
        }catch (SQLException e){ 
            App.getInstance().registerLogError(e);
        };
    } 
}