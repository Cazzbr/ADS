package com.example;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Controller_Cadastro_EditarFuncionario implements Initializable {

    @FXML
    private TextField textNomeFuncionario
                    , textFuncaoFuncionario
                    , textEnderecoFuncionario
                    , textCpfFuncionario
                    , textLoginFuncionario
                    , textSenhaFuncionario
                    ;

    @FXML
    private ChoiceBox<Funcionario> choicefuncionariosEditarCadastro;

    @FXML
    public void onEventSelectChangeEditFunc(ActionEvent event) {
        Funcionario f = choicefuncionariosEditarCadastro.getSelectionModel().getSelectedItem();
         if (f == null || f.toString().isEmpty()){
            clearfields();
        } else {
            textNomeFuncionario.setText(f.getNome());
            textFuncaoFuncionario.setText(f.getFuncao());
            textEnderecoFuncionario.setText(f.getEndereco());
            textCpfFuncionario.setText(f.getCpf());
            textLoginFuncionario.setText(f.getLogin());
            textSenhaFuncionario.setText(f.getSenha());
        }
    }

    @FXML
    public void onActionRemoverPressed(ActionEvent e){
        Funcionario f = choicefuncionariosEditarCadastro.getSelectionModel().getSelectedItem();
        if (f == null || f.toString().isEmpty()){
            System.out.println("Empty or null");
            clearfields();
        } else {
            String sql = "DELETE FROM funcionario WHERE id=?";
            try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
                stmt.setInt(1, f.getId());
                stmt.execute();
            }catch (SQLException ex){
                App.getInstance().registerLogError(ex);
            }
            choicefuncionariosEditarCadastro.getSelectionModel().clearSelection();
            choicefuncionariosEditarCadastro.getItems().remove(f);
            clearfields();
        }
    }

    @FXML
    public void onActionAtualizarPressed(ActionEvent e){
        Funcionario f = choicefuncionariosEditarCadastro.getSelectionModel().getSelectedItem();
        if (f == null || f.toString().isEmpty()){
            System.out.println("Empty or null");
        } else {
            String sql = "UPDATE funcionario SET nome = ?, funcao = ?, endereco = ?, cpf = ?, login = ?, senha = ? WHERE id = ?";
            try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, textNomeFuncionario.getText());
                stmt.setString(2, textFuncaoFuncionario.getText());
                stmt.setString(3, textEnderecoFuncionario.getText());
                stmt.setString(4, textCpfFuncionario.getText());
                stmt.setString(5, textLoginFuncionario.getText());
                stmt.setString(6, textSenhaFuncionario.getText());
                stmt.setInt(7, f.getId());
                stmt.execute();
                f.setNome(textNomeFuncionario.getText());
                f.setFuncao(textFuncaoFuncionario.getText());
                f.setEndereco(textEnderecoFuncionario.getText());
                f.setCpf(textCpfFuncionario.getText());
                f.setLogin(textLoginFuncionario.getText());
                f.setSenha(textSenhaFuncionario.getText());
            }catch (SQLException ex){
                App.getInstance().registerLogError(ex);
            }
            clearfields();

            choicefuncionariosEditarCadastro.getSelectionModel().clearSelection();
        }
    }

    private void clearfields(){
        textNomeFuncionario.setText("");
        textFuncaoFuncionario.setText("");
        textEnderecoFuncionario.setText("");
        textCpfFuncionario.setText("");
        textLoginFuncionario.setText("");
        textSenhaFuncionario.setText("");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        choicefuncionariosEditarCadastro.getItems().clear();
        List<Funcionario> funcionarios = Funcionario.getfuncionarios("select * from funcionario");
        for (Funcionario funcionario : funcionarios) {
            choicefuncionariosEditarCadastro.getItems().add(funcionario);
        }
    }
}
