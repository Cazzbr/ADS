package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

public class Controller_NotasFiscais implements Initializable {

    Search_NotaFiscal snf = new Search_NotaFiscal();

    @FXML
    private ChoiceBox<Venda> choiceNotaFiscal;

    @FXML
    private ListView<Produto> notaFiscalListView;

    @FXML
    public void onEventSelectChangeNotaFiscal(ActionEvent event) {
        Venda v = choiceNotaFiscal.getSelectionModel().getSelectedItem();
        notaFiscalListView.getItems().clear();
        if (!(v == null || v.toString().isEmpty())){
            ArrayList<Produto> produtosNF = snf.getProductsOnNF(v);
            for (Produto produto : produtosNF) {
                notaFiscalListView.getItems().add(produto);
            }
        }
    }

    @FXML
    public void onActionButtomImprimirNotaPressed(ActionEvent e){
        Venda v = choiceNotaFiscal.getSelectionModel().getSelectedItem();
        if (v == null || v.toString().isEmpty()){
            System.out.println("Empty or null");
        } else {
            try {
                ImpresaoNotaFiscal nf = new ImpresaoNotaFiscal(notaFiscalListView.getItems(), v.getValor_total());
                nf.printNf();
            } catch (ClassNotFoundException | IOException | NumberFormatException ex ) {
                App.getInstance().registerLogError(ex);
            }
            choiceNotaFiscal.getSelectionModel().clearSelection();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        choiceNotaFiscal.getItems().clear();
        List<Venda> notasFiscais = snf.getNotasFiscaisOnDB();
        for (Venda nota : notasFiscais) {
            choiceNotaFiscal.getItems().add(nota);
        }
    }
}
