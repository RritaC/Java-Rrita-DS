// package com.example;

// import javafx.application.Application;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;

// import java.io.IOException;

// /**
//  * JavaFX App
//  */
// public class App extends Application {

//     private static Scene scene;

//     @Override
//     public void start(Stage stage) throws IOException {
//         scene = new Scene(loadFXML("primary"), 640, 480);
//         stage.setScene(scene);
//         stage.show();
//     }

//     static void setRoot(String fxml) throws IOException {
//         scene.setRoot(loadFXML(fxml));
//     }

//     private static Parent loadFXML(String fxml) throws IOException {
//         FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//         return fxmlLoader.load();
//     }

//     public static void main(String[] args) {
//         launch();
//     }

// }

package com.example;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
//import javafx.scene.layout.StackPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    Stage window;
    Scene scene1, scene2, scene3;

    public void start(Stage primStage) {
        window = primStage;

        // Scene 1
        Label label1 = new Label("Welcome to the first Scene");
        Button button1 = new Button("Go to scene 2");
        button1.setOnAction(e -> window.setScene(scene2));

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 400, 300);

        // Scene 2
        Label label2 = new Label("Welcome to the second Scene");
        Button button2 = new Button("Go to scene 3");
        button2.setOnAction(e -> window.setScene(scene3));

        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(label2, button2);
        scene2 = new Scene(layout2, 400, 300);

        // Scene 3
        Label label3 = new Label("Welcome to the third Scene");
        Button button3 = new Button("Go back to scene 1");
        button3.setOnAction(e -> window.setScene(scene1));

        VBox layout3 = new VBox(20);
        layout3.getChildren().addAll(label3, button3);
        scene3 = new Scene(layout3, 400, 300);

        window.setScene(scene1);
        window.setTitle("JavaFX Scenes");
        window.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
