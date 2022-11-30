package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private Scene scene;
    private static BorderPane rootWindow;
    private Stage thisStage;
    private LogHandler logHandler = new LogHandler();

    private static App instance = null;
    public App(){}
    public static App getInstance() {
        if(instance == null) instance = new App();
        return instance;
    }

    public BorderPane getRootwindow(){
        return rootWindow;
    }

    public Stage getThisStage() {
        return thisStage;
    }    

    @Override
    public void start(final Stage stage) throws IOException {
        thisStage = stage;
        rootWindow = new BorderPane();
        rootWindow.setTop(createMenuBar());
        rootWindow.setCenter(loadFXML("App_Vendas_Transacoes"));
        scene = new Scene(rootWindow, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    private MenuBar createMenuBar(){
        Menu fileMenu = createMenuArquivo();
        Menu vendasMenu = createMenuVendas();
        Menu cadastroMenu = createMenuCadastros();
        return new MenuBar(fileMenu, vendasMenu, cadastroMenu);
    }

    private Menu createMenuArquivo(){
        MenuItem superMarket = new MenuItem("Super Mercado");
        superMarket.setOnAction( e -> setAction("App_Supermarket"));
        MenuItem exitMenuItem = new MenuItem("Sair");
        exitMenuItem.setOnAction(e -> Platform.exit());
        return new Menu("Arquivo", null, superMarket, exitMenuItem);
    }

    private Menu createMenuVendas(){
        MenuItem mainWindow = new MenuItem("Vendas");
        mainWindow.setOnAction( e -> setAction("App_Vendas_Transacoes"));
        return new Menu("Transações", null, mainWindow);
    }

    private Menu createMenuCadastros(){
        MenuItem miItem = new MenuItem("Cadastro item");
        miItem.setOnAction( e -> setAction("App_Cadastro_Item"));
        MenuItem miEstoque = new MenuItem("Cadastro estoque");
        miEstoque.setOnAction( e -> setAction("App_Cadastro_Estoque"));
        MenuItem miFuncionario = new MenuItem("Cadastro funcionario");
        miFuncionario.setOnAction(e -> setAction("App_Cadastro_Funcionario"));
        MenuItem edFuncionario = new MenuItem("Editar Funcionario");
        edFuncionario.setOnAction(e -> setAction("App_Cadastro_EditarFuncionario"));
        return new Menu("Cadastros", null, miItem, miEstoque, miFuncionario, edFuncionario);
    }

    private void setAction(String fxml_string) {
            try {
                rootWindow.setCenter(loadFXML(fxml_string));
            } catch (IOException e) {
                registerLogError(e);
            }
    }

    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public void openDocument(String fileLocation){
        getHostServices().showDocument(fileLocation);
    }

    public void registerLogError(Exception error) {
        logHandler.registerLog(error);
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.setHeaderText("Ocorreu um erro durante a execução");
        errorAlert.setContentText(error.toString() + " ...\nPara informações adicionais, consulte o log de erros na pasta 'Logs/'");
        errorAlert.showAndWait();
    }
}