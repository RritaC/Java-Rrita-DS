package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * JavaFX App
 */
public class BorderPaneExample extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane borderpane = new BorderPane();

        HBox topMenu = new HBox(new Button("Home"), new Button("Settings"));
        topMenu.setSpacing(20);

        VBox leftMenu = new VBox(new Button("Option 1"), new Button("Option  2"));
        topMenu.setSpacing(20);

        borderpane.setLeft(leftMenu);
        borderpane.setTop(topMenu);

        Scene scene = new Scene(borderpane, 500, 500);
        stage.setScene(scene);
        stage.setTitle("BorderPane Example");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}