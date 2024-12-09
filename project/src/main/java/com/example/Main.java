package com.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Main extends Application {
    private final Map<String, String> users = new HashMap<>();
    private Stage primaryStage;
    private String currentUser;
    private String userEmail = "example@example.com";
    private String userProfession = "Engineer";

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        primaryStage.setTitle("Enhanced E-Commerce App");
        showWelcomePage();
    }

    private void showWelcomePage() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, #eaeaea, #d6d6d6); -fx-padding: 30px;");

        Label titleLabel = createStyledLabel("Welcome to My Page", 36, Color.DARKSLATEGRAY);
        Label subtitleLabel = createStyledLabel("Your personalized shopping experience awaits!", 16, Color.GRAY);

        Button loginButton = createStyledButton("Log In");
        loginButton.setOnAction(e -> showLoginScene());

        Button signUpButton = createStyledButton("Sign Up");
        signUpButton.setOnAction(e -> showSignUpScene());

        layout.getChildren().addAll(titleLabel, subtitleLabel, loginButton, signUpButton);

        primaryStage.setScene(new Scene(layout, 600, 400));
        primaryStage.show();
    }

    private void showLoginScene() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 30px;");

        Label titleLabel = createStyledLabel("Log In", 28, Color.DARKSLATEGRAY);

        TextField usernameField = createStyledInput("Username");
        PasswordField passwordField = createStyledInputPassword("Password");

        Label messageLabel = createStyledLabel("", 14, Color.RED);

        Button loginButton = createStyledButton("Log In");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (users.containsKey(username) && users.get(username).equals(password)) {
                currentUser = username;
                showDashboard();
            } else {
                messageLabel.setText("Invalid username or password.");
            }
        });

        Button backButton = createStyledButton("Back to Welcome");
        backButton.setOnAction(e -> showWelcomePage());

        layout.getChildren().addAll(titleLabel, usernameField, passwordField, loginButton, backButton, messageLabel);

        primaryStage.setScene(new Scene(layout, 600, 400));
    }

    private void showSignUpScene() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 30px;");

        Label titleLabel = createStyledLabel("Sign Up", 28, Color.DARKSLATEGRAY);

        TextField usernameField = createStyledInput("Username");
        PasswordField passwordField = createStyledInputPassword("Password");

        Label messageLabel = createStyledLabel("", 14, Color.RED);

        Button signUpButton = createStyledButton("Sign Up");
        signUpButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (!users.containsKey(username)) {
                users.put(username, password);
                messageLabel.setText("User registered successfully!");
                showLoginScene();
            } else {
                messageLabel.setText("User already exists.");
            }
        });

        Button backButton = createStyledButton("Back to Welcome");
        backButton.setOnAction(e -> showWelcomePage());

        layout.getChildren().addAll(titleLabel, usernameField, passwordField, signUpButton, backButton, messageLabel);

        primaryStage.setScene(new Scene(layout, 600, 400));
    }

    private void showDashboard() {
        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 30px;");

        // Sidebar
        VBox sidebar = new VBox(20);
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setStyle("-fx-background-color: #d6d6d6; -fx-padding: 15px;");
        sidebar.setPrefWidth(200);

        Button profileButton = createStyledButton("Profile");
        Button shopButton = createStyledButton("Go to Shop");
        Button logoutButton = createStyledButton("Logout");

        profileButton.setOnAction(e -> showProfile());
        shopButton.setOnAction(e -> showShopPage());
        logoutButton.setOnAction(e -> showWelcomePage());

        sidebar.getChildren().addAll(profileButton, shopButton, logoutButton);

        // Main Content
        Label welcomeLabel = createStyledLabel("Welcome, " + currentUser + "!", 20, Color.DARKSLATEGRAY);

        layout.setLeft(sidebar);
        layout.setCenter(welcomeLabel);

        primaryStage.setScene(new Scene(layout, 800, 600));
    }

    private void showProfile() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 30px;");

        Label titleLabel = createStyledLabel("Edit Profile", 28, Color.DARKSLATEGRAY);

        TextField nameField = createStyledInput("Name");
        nameField.setText(currentUser);

        TextField emailField = createStyledInput("Email");
        emailField.setText(userEmail);

        TextField professionField = createStyledInput("Profession");
        professionField.setText(userProfession);

        Button saveButton = createStyledButton("Save");
        saveButton.setOnAction(e -> {
            currentUser = nameField.getText();
            userEmail = emailField.getText();
            userProfession = professionField.getText();
            System.out.println(
                    "Profile Updated: Name=" + currentUser + ", Email=" + userEmail + ", Profession=" + userProfession);
            showDashboard();
        });

        Button backButton = createStyledButton("Back to Dashboard");
        backButton.setOnAction(e -> showDashboard());

        layout.getChildren().addAll(titleLabel, nameField, emailField, professionField, saveButton, backButton);

        primaryStage.setScene(new Scene(layout, 600, 400));
    }

    private void showShopPage() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 30px;");

        Label titleLabel = createStyledLabel("Shop", 28, Color.DARKSLATEGRAY);

        HBox itemsBox = new HBox(20);
        itemsBox.setAlignment(Pos.CENTER);

        String[] images = {
                "https://www.imagineonline.store/cdn/shop/files/iPhone_15_Pink_PDP_Image_Position-1__en-IN.jpg?v=1694605258&width=1445",
                "https://www.istudio.store/cdn/shop/files/iPhone_16_Pro_Desert_Titanium_TH_1.jpg?v=1725929129&width=823",
                "https://mac-center.com/cdn/shop/files/IMG-10942145_9f7ece93-39fc-4310-a98d-9c11efa3a51e.jpg?v=1723752783"
        };

        for (int i = 0; i < images.length; i++) {
            VBox itemBox = new VBox(10);
            itemBox.setAlignment(Pos.CENTER);

            ImageView imageView = new ImageView(new Image(images[i]));
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);

            Button buyButton = createStyledButton("Buy Item " + (i + 1));
            buyButton.setOnAction(e -> System.out.println("Item " + (+1) + " bought!"));

            itemBox.getChildren().addAll(imageView, buyButton);
            itemsBox.getChildren().add(itemBox);
        }

        Button backButton = createStyledButton("Back to Dashboard");
        backButton.setOnAction(e -> showDashboard());

        layout.getChildren().addAll(titleLabel, itemsBox, backButton);

        primaryStage.setScene(new Scene(layout, 800, 600));
    }

    private Label createStyledLabel(String text, int fontSize, Color color) {
        Label label = new Label(text);
        label.setStyle(String.format("-fx-font-family: 'Arial'; -fx-font-size: %dpx; -fx-text-fill: %s;", fontSize,
                toHex(color)));
        return label;
    }

    private TextField createStyledInput(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle(
                "-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-padding: 8px; -fx-border-color: #d6d6d6;");
        return textField;
    }

    private PasswordField createStyledInputPassword(String promptText) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(promptText);
        passwordField.setStyle(
                "-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-padding: 8px; -fx-border-color: #d6d6d6;");
        return passwordField;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle(
                "-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-background-color: #5a5a5a; -fx-text-fill: #ffffff;");
        return button;
    }

    private String toHex(Color color) {
        return String.format("#%02X%02X%02X", (int) (color.getRed() * 255), (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
