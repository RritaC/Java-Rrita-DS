package com.example;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //File Menu
        MenuItem newProject = new MenuItem("New Project");
        MenuItem openFile = new MenuItem("Open File");
        MenuItem saveFile = new MenuItem("Save File");

        Menu fileMenu = new Menu("File");

        fileMenu.getItems().addAll(newProject, openFile, saveFile);

        //Edit Menu
        MenuItem cut = new MenuItem("Cut");
        MenuItem copy = new MenuItem("Copy");
        MenuItem paste = new MenuItem("Paste");

        Menu editMenu = new Menu("Edit");

        editMenu.getItems().addAll(cut, copy, paste);

        //Help Menu 
        MenuItem about = new MenuItem("About");

        Menu helpMenu = new Menu("Help");

        helpMenu.getItems().addAll(about);

        //Menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);


        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);

        Scene scene = new  Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menus JavaFX");
        primaryStage.show();

        //Menu Items click action

        newProject.setOnAction(e -> openNewWindow("New Project", "Create a new project"));
        openFile.setOnAction(e -> openNewWindow("Open File", "Choose an existing file to open"));
        saveFile.setOnAction(e -> openNewWindow("Save File", "Save your current file"));
        cut.setOnAction(e -> openNewWindow("Cut", "Cut selected text"));
        copy.setOnAction(e -> openNewWindow("Copy", "Copy selected text"));
        paste.setOnAction(e -> openNewWindow("Paste", "Paste selected text"));
        about.setOnAction(e -> openNewWindow("About", "This is an about page"));
    }

    //New Window method
    private void openNewWindow(String title, String message){
        Stage window = new Stage();
        window.setTitle(title);

        Label label = new Label(message);
        Button closeBtn = new Button("close");
        closeBtn.setOnAction(e-> window.close());

        BorderPane layout = new BorderPane();
        layout.setCenter(label);
        layout.setBottom(closeBtn);
        BorderPane.setAlignment(closeBtn, javafx.geometry.Pos.CENTER);

        layout.setStyle("-fx-padding: 10; -fx-font-size: 14; -fx-border-color: grey; -fx-border-width: 2;");

        Scene scene = new  Scene(layout, 400, 400);
        window.setScene(scene);
        window.setTitle("Menus JavaFX");
        window.show();
    }
}
