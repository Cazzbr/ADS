package com.example;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "update supermercado set nome=?, endereco=?, cnpj=?, gerente=? where id=1";
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, textNomeSupermarket.getText());
            stmt.setString(2, textEnderecoSupermarket.getText());
            stmt.setString(3, textCnpjSupermarket.getText());
            stmt.setInt(4, choiceGerenteSupermaket.getSelectionModel().getSelectedItem().getId());
            stmt.execute();
        }catch (SQLException | NumberFormatException ex){
            App.getInstance().registerLogError(ex);
        }
        App.getInstance().getRootwindow().setCenter(new FXMLLoader(App.class.getResource("App_Vendas_Transacoes.fxml")).load());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        choiceGerenteSupermaket.getItems().clear();
        List<Funcionario> gerentes = Funcionario.getfuncionarios("select * from funcionario where funcao = 'gerente'");
        for (Funcionario funcionario : gerentes) {
            choiceGerenteSupermaket.getItems().add(funcionario);
        }
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement("select * from supermercado where id = 1");
            ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    textNomeSupermarket.setText(rs.getString("nome"));
                    textEnderecoSupermarket.setText(rs.getString("endereco"));
                    textCnpjSupermarket.setText(rs.getString("cnpj"));
                    int gerente_id = rs.getInt("gerente");
                    if (gerente_id != 0){
                        for (Funcionario g : gerentes) {
                            if(g.getId() == gerente_id){
                                choiceGerenteSupermaket.getSelectionModel().select(g);
                            }
                        }
                    }
                }
        }catch (SQLException e){
            App.getInstance().registerLogError(e);
        };
    }
}
