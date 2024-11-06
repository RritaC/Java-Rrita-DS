package com.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;
    private final Font globalFont = Font.font("Arial", 16);

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showInitialScreen();
    }

    // Screen 0
    private void showInitialScreen() {
        VBox initialLayout = new VBox(10);
        initialLayout.setAlignment(Pos.CENTER);

        Label welcomeLabel = new Label("Welcome! Please Log In or Sign Up");
        welcomeLabel.setFont(globalFont);

        Button loginButton = new Button("Log In");
        loginButton.setFont(globalFont);

        Button signupButton = new Button("Sign Up");
        signupButton.setFont(globalFont);

        loginButton.setOnAction(e -> showLoginScreen());
        signupButton.setOnAction(e -> showSignupScreen());

        initialLayout.getChildren().addAll(welcomeLabel, loginButton, signupButton);
        Scene initialScene = new Scene(initialLayout, 400, 300);
        primaryStage.setScene(initialScene);
        primaryStage.setTitle("Welcome");
        primaryStage.show();
    }

    // Log In Screen
    private void showLoginScreen() {
        GridPane loginLayout = new GridPane();
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setVgap(10);
        loginLayout.setHgap(10);

        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(globalFont);
        TextField usernameField = new TextField();
        usernameField.setFont(globalFont);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(globalFont);
        PasswordField passwordField = new PasswordField();
        passwordField.setFont(globalFont);

        Button loginButton = new Button("Log In");
        loginButton.setFont(globalFont);

        Button backButton = new Button("Back");
        backButton.setFont(globalFont);

        loginButton.setOnAction(e -> showDashboard());
        backButton.setOnAction(e -> showInitialScreen());

        loginLayout.add(usernameLabel, 0, 0);
        loginLayout.add(usernameField, 1, 0);
        loginLayout.add(passwordLabel, 0, 1);
        loginLayout.add(passwordField, 1, 1);
        loginLayout.add(loginButton, 1, 2);
        loginLayout.add(backButton, 0, 2);

        Scene loginScene = new Scene(loginLayout, 400, 300);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Log In");
    }

    // Sign Up Screen
    private void showSignupScreen() {
        GridPane signupLayout = new GridPane();
        signupLayout.setAlignment(Pos.CENTER);
        signupLayout.setVgap(10);
        signupLayout.setHgap(10);

        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(globalFont);
        TextField usernameField = new TextField();
        usernameField.setFont(globalFont);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(globalFont);
        PasswordField passwordField = new PasswordField();
        passwordField.setFont(globalFont);

        Label confirmPasswordLabel = new Label("Confirm Password:");
        confirmPasswordLabel.setFont(globalFont);
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setFont(globalFont);

        Button signupButton = new Button("Sign Up");
        signupButton.setFont(globalFont);

        Button backButton = new Button("Back");
        backButton.setFont(globalFont);

        signupButton.setOnAction(e -> showDashboard());
        backButton.setOnAction(e -> showInitialScreen());

        signupLayout.add(usernameLabel, 0, 0);
        signupLayout.add(usernameField, 1, 0);
        signupLayout.add(passwordLabel, 0, 1);
        signupLayout.add(passwordField, 1, 1);
        signupLayout.add(confirmPasswordLabel, 0, 2);
        signupLayout.add(confirmPasswordField, 1, 2);
        signupLayout.add(signupButton, 1, 3);
        signupLayout.add(backButton, 0, 3);

        Scene signupScene = new Scene(signupLayout, 400, 300);
        primaryStage.setScene(signupScene);
        primaryStage.setTitle("Sign Up");
    }

    private void showDashboard() {
        BorderPane dashboardLayout = new BorderPane();

        // Top Bar (Title/Navigation)
        Label titleLabel = new Label("Dashboard");
        titleLabel.setFont(Font.font("Arial", 24));
        titleLabel.setStyle("-fx-text-fill: white;");
        HBox topBar = new HBox(titleLabel);
        topBar.setAlignment(Pos.CENTER);
        topBar.setStyle("-fx-background-color: darkblue;");
        topBar.setPrefHeight(60);

        // Left Sidebar (Menu)
        VBox sidebar = new VBox(10);
        sidebar.setStyle("-fx-background-color: lightgray;");
        sidebar.setPrefWidth(150);
        Button homeButton = new Button("Home");
        homeButton.setFont(globalFont);

        Button profileButton = new Button("Profile");
        profileButton.setFont(globalFont);

        Button settingsButton = new Button("Settings");
        settingsButton.setFont(globalFont);

        Button logoutButton = new Button("Logout");
        logoutButton.setFont(globalFont);

        logoutButton.setOnAction(e -> showInitialScreen());

        sidebar.getChildren().addAll(homeButton, profileButton, settingsButton, logoutButton);

        VBox homeContent = new VBox(10);
        Label viewLabel = new Label("View");
        viewLabel.setFont(globalFont);

        Label editLabel = new Label("Edit");
        editLabel.setFont(globalFont);

        Label commentLabel = new Label("Comment");
        commentLabel.setFont(globalFont);
        homeContent.getChildren().addAll(viewLabel, editLabel, commentLabel);

        VBox profileList = new VBox(10);
        Label nameLabel = new Label("Name");
        nameLabel.setFont(globalFont);

        Label surnameLabel = new Label("Surname");
        surnameLabel.setFont(globalFont);

        Label emailLabel = new Label("Email");
        emailLabel.setFont(globalFont);
        profileList.getChildren().addAll(nameLabel, surnameLabel, emailLabel);

        VBox settingsContent = new VBox(10);
        Label removeLabel = new Label("Remove");
        removeLabel.setFont(globalFont);

        Label addLabel = new Label("Add");
        addLabel.setFont(globalFont);

        Label pairLabel = new Label("Pair");
        pairLabel.setFont(globalFont);
        settingsContent.getChildren().addAll(removeLabel, addLabel, pairLabel);

        homeButton.setOnAction(e -> dashboardLayout.setCenter(homeContent));
        profileButton.setOnAction(e -> dashboardLayout.setCenter(profileList));
        settingsButton.setOnAction(e -> dashboardLayout.setCenter(settingsContent));

        dashboardLayout.setTop(topBar);
        dashboardLayout.setLeft(sidebar);
        dashboardLayout.setCenter(homeContent);

        Scene dashboardScene = new Scene(dashboardLayout, 800, 600);
        primaryStage.setScene(dashboardScene);
        primaryStage.setTitle("Dashboard");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
