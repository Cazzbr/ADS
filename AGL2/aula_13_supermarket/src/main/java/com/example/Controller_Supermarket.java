package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Controller_Supermarket implements Initializable {

    @FXML
    private TextField textNomeSupermarket
                    , textEnderecoSupermarket
                    , textCnpjSupermarket
                    ;

    @FXML
    private ChoiceBox<Funcionario> choiceGerenteSupermaket;


    @FXML
    public void onActionAtualizarPressed(ActionEvent e) throws IOException{
        SuperMercado s = new SuperMercado();
        s.setId(1);
        s.setNome(textNomeSupermarket.getText());
        s.setEndereco(textEnderecoSupermarket.getText());
        s.setCnpj(textCnpjSupermarket.getText());
        int gerente = 0;
        Funcionario g = choiceGerenteSupermaket.getSelectionModel().getSelectedItem();
        if (g != null){
            gerente = g.getId();
        }
        s.setGerente(gerente);
        Search_SuperMarket.updateSuperMarket(s);
        App.getInstance().getRootwindow().setCenter(new FXMLLoader(App.class.getResource("App_Vendas_Transacoes.fxml")).load());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        choiceGerenteSupermaket.getItems().clear();
        List<Funcionario> gerentes = Funcionario.getfuncionarios("select * from funcionario where funcao = 'gerente'");
        for (Funcionario funcionario : gerentes) {
            choiceGerenteSupermaket.getItems().add(funcionario);
        }
        SuperMercado s  = Search_SuperMarket.search();
        textNomeSupermarket.setText(s.getNome());
        textEnderecoSupermarket.setText(s.getEndereco());
        textCnpjSupermarket.setText(s.getCnpj());
        int gerente_id = s.getGerente();
        if (gerente_id != 0){
            for (Funcionario g : gerentes) {
                if(g.getId() == gerente_id){
                    choiceGerenteSupermaket.getSelectionModel().select(g);
                }
            }
        }
    }
}
