package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private Scene scene;
    private BorderPane rootWindow;

    @Override
    public void start(Stage stage) throws IOException {
        // criar a janela principal como uma BorderPane
        rootWindow = new BorderPane();
        // Adicionar o menu no top janela principal e a tela de início do App abaixo 
        rootWindow.setTop(createMenuBar());
        rootWindow.setCenter(loadFXML("App_Vendas_Transacoes"));
        // Criar a Scene e adicionar no stage;
        scene = new Scene(rootWindow, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    private MenuBar createMenuBar(){
        // Menu Arquivo
        MenuItem exitMenuItem = new MenuItem("Sair");
        exitMenuItem.setOnAction(e -> Platform.exit());
        Menu fileMenu = new Menu("Arquivo", null, exitMenuItem);
        // Menu de vendas
        MenuItem mainWindow = new MenuItem("Vendas");
        mainWindow.setOnAction( e -> setAction("App_Vendas_Transacoes"));
        Menu vendasMenu = new Menu("Transações", null, mainWindow);
        // Menu de cadastro
        MenuItem miItem = new MenuItem("Cadastro item");
        miItem.setOnAction( e -> setAction("App_Cadastro_Item"));
        MenuItem miEstoque = new MenuItem("Cadastro estoque");
        miEstoque.setOnAction( e -> setAction("App_Cadastro_Estoque"));
        MenuItem miFuncionario = new MenuItem("Cadastro funcionario");
        miFuncionario.setOnAction(e -> setAction("App_Cadastro_Funcionario"));
        Menu cadastroMenu = new Menu("Cadastro", null, miItem, miEstoque, miFuncionario);
        // Retorna menu bar
        return new MenuBar(fileMenu, vendasMenu, cadastroMenu);
    }

    private void setAction(String fxml_string) {
            try {
                rootWindow.setCenter(loadFXML(fxml_string));
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}