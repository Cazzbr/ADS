package com.example;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.*;


public class SearchPopup {
    private static Stage popupwindow;
    private static VBox layout;
    private Produto produto;

    public void display(ObservableList<Produto> lst){
        popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Resultados da pesquisa");
        layout= new VBox(10);
        if (lst.size() == 0){
            displayEmptyResult();
        }else{
            displayResultsList(lst);
        }
        Button closeBtn= new Button("Fechar");
        closeBtn.setOnAction(e -> popupwindow.close());
        layout.getChildren().add(closeBtn);
        layout.setAlignment(Pos.CENTER);
        Scene scenePopup = new Scene(layout, 500, 250);
        popupwindow.setScene(scenePopup);
        popupwindow.showAndWait();
    }

    private void displayResultsList(ObservableList<Produto> lst) {
        ListView<Produto> searchResultsView = new ListView<>(lst);
        layout.getChildren().add(searchResultsView);
        searchResultsView.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                produto = searchResultsView.getSelectionModel().getSelectedItem();
                popupwindow.close();
            }
        });
    }

    private static void displayEmptyResult(){
        Label notFoundLabel= new Label("Não foram encontrados produtos para essa pesquisa!");
        layout.getChildren().add(notFoundLabel);
    }

    public static Stage getPopupwindow() {
        return popupwindow;
    }

    public static VBox getLayout() {
        return layout;
    }

    public Produto getProduto() {
        return produto;
    }
}