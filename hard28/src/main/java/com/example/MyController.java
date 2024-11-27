package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MyController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    private final String CORRECT_USERNAME = "user";
    private final String CORRECT_PASSWORD = "password";

    @FXML
    private void handleLogin() {

        if (usernameField == null || passwordField == null || messageLabel == null) {
            System.err.println("FXML elements not initialized properly!");
            return;
        }

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (CORRECT_USERNAME.equals(username) && CORRECT_PASSWORD.equals(password)) {
            messageLabel.setStyle("-fx-text-fill: green;");
            messageLabel.setText("Logged in!");
        } else {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Incorrect username or password.");
        }
    }
}
