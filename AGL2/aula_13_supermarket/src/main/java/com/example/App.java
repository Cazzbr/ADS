package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    //tabpane

    @Override
    public void start(Stage stage) throws IOException {
        Menu menu = new Menu("Cadastro");
        MenuItem mi1 = new MenuItem("Cadastro item");
        MenuItem mi2 = new MenuItem("Cadastro estoque");
        MenuItem mi3 = new MenuItem("Cadastro funcionario");
        
        menu.getItems().add(mi1);
        menu.getItems().add(mi2);
        menu.getItems().add(mi3);

        MenuBar bar = new MenuBar();
        bar.getMenus().add(menu);

        VBox v = new VBox(bar);
        Scene scene = new Scene(v,640,480);
        //add them
        v.getChildren().addAll(bar, loadFXML("primary"));
        //scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}