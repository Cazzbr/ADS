package com.example;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
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

    @FXML
    public void onActionButtomFinalizarVendaPressed(ActionEvent e){
        float total = Float.parseFloat(labelSubTotal.getText());
        int inserted_id = insertVendaOnDB(total);
        insertProductVendaOnDB(inserted_id);
        try {
            ImpresaoNotaFiscal nf = new ImpresaoNotaFiscal(notaFiscalListView.getItems(), total);
            nf.printNf();
        } catch (ClassNotFoundException | IOException | NumberFormatException ex ) {
            App.getInstance().registerLogError(ex);
        }
        notaFiscalListView.getItems().clear();
        labelSubTotal.setText("0.00");
    }

    private int insertVendaOnDB(Float total){
        int last_inserted_id = -1;
        String sql = "INSERT INTO venda VALUES (NULL, ?, ?, null)";
        try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, Date.valueOf(java.time.LocalDate.now()));
            stmt.setFloat(2, total);
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

    private void insertProductVendaOnDB(int venda_id){
        ObservableList<Produto> p = notaFiscalListView.getItems();
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
        stmtGetEstoque.setInt(1, produto.getId());
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