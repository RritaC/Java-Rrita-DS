package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ChatMoodBoardApp extends Application {

    private static final String FONT_STYLE = "-fx-font-family: 'Arial'; -fx-font-size: 14px;";
    private final Map<String, UserProfile> userProfiles = new HashMap<>();
    private final Map<String, ListView<HBox>> chatHistories = new HashMap<>();
    private final Map<String, Integer> wordFrequency = new HashMap<>();
    private UserProfile loggedInUser;
    private boolean groupMembersDisplayed = false;

    private static final Set<String> POSITIVE_WORDS = Set.of(
            "happy", "joy", "love", "great", "amazing", "wonderful", "yay",
            "awesome", "cool", "good", "nice", "fun", "sweet", "smile",
            "lol", "haha", "hehe", "xoxo", "gr8", "lit", "vibe", "sunny");

    private static final Set<String> NEGATIVE_WORDS = Set.of(
            "sad", "angry", "hate", "bad", "terrible", "horrible", "ugh",
            "cry", "tears", "depressed", "bored", "meh", "fml", "sucks",
            "lame", "ouch", "nope", "broken", "fail", "low", "dark");

    private static final Set<String> EXCITED_WORDS = Set.of(
            "excited", "thrilled", "yay", "omg", "whoa", "wow", "hyped",
            "pumped", "stoked", "yass", "woohoo", "ecstatic", "epic",
            "lit", "dope", "so good", "fire", "on top", "winning", "love it");

    private static final Set<String> CALM_WORDS = Set.of(
            "calm", "chill", "relaxed", "peace", "ok", "fine", "cool",
            "good vibes", "smooth", "easy", "zen", "balanced", "still",
            "quiet", "cozy", "ahh", "breathe", "relax", "no stress", "all good");

    private static final Set<String> ANGRY_WORDS = Set.of(
            "furious", "mad", "upset", "angry", "pissed", "wtf", "damn",
            "ugh", "grr", "annoyed", "irritated", "frustrated", "fuming",
            "rage", "snapped", "triggered", "over it", "hate it", "smh",
            "kidding me", "done");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sign Up to Chat Application");

        Scene signUpScene = createSignUpScene(primaryStage);
        primaryStage.setScene(signUpScene);
        primaryStage.show();
    }

    private Scene createSignUpScene(Stage primaryStage) {
        VBox signUpLayout = new VBox(15);
        signUpLayout.setPadding(new Insets(20));
        signUpLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #e3f2fd, #bbdefb);");

        Label signUpLabel = new Label("Sign Up");
        signUpLabel.setStyle(FONT_STYLE + "-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setStyle(FONT_STYLE);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setStyle(FONT_STYLE);

        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");
        emailField.setStyle(FONT_STYLE);

        TextField bioField = new TextField();
        bioField.setPromptText("Enter a short bio");
        bioField.setStyle(FONT_STYLE);

        TextField profilePicField = new TextField();
        profilePicField.setPromptText("Enter profile picture URL");
        profilePicField.setStyle(FONT_STYLE);

        Button signUpButton = new Button("Sign Up");
        signUpButton.setStyle(FONT_STYLE + "-fx-background-color: #64b5f6; -fx-border-radius: 10;");

        signUpButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            String email = emailField.getText().trim();
            String bio = bioField.getText().trim();
            String profilePicUrl = profilePicField.getText().trim();

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                showAlert("Invalid Input", "Please fill in all required fields.");
            } else {

                userProfiles.put(username, new UserProfile(username, password, email, bio, profilePicUrl));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Account created successfully! Please log in.");
                alert.getDialogPane().setStyle(FONT_STYLE); // Apply FONT_STYLE here
                alert.showAndWait();
                primaryStage.setScene(createLoginScene(primaryStage));
            }
        });

        signUpLayout.getChildren().addAll(signUpLabel, usernameField, passwordField, emailField, bioField,
                profilePicField, signUpButton);
        return new Scene(signUpLayout, 400, 500);
    }

    private Scene createLoginScene(Stage primaryStage) {
        VBox loginLayout = new VBox(15);
        loginLayout.setPadding(new Insets(20));
        loginLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #e3f2fd, #bbdefb);");

        Label loginLabel = new Label("Login");
        loginLabel.setStyle(FONT_STYLE + "-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setStyle(FONT_STYLE);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setStyle(FONT_STYLE);

        Button loginButton = new Button("Login");
        loginButton.setStyle(FONT_STYLE + "-fx-background-color: #64b5f6; -fx-border-radius: 10;");

        loginButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (userProfiles.containsKey(username) && userProfiles.get(username).getPassword().equals(password)) {
                loggedInUser = userProfiles.get(username);
                Scene chatScene = createChatScene(primaryStage);
                primaryStage.setScene(chatScene);
            } else {
                showAlert("Login Failed", "Invalid username or password.");
            }
        });

        loginLayout.getChildren().addAll(loginLabel, usernameField, passwordField, loginButton);
        return new Scene(loginLayout, 400, 300);
    }

    private Scene createChatScene(Stage primaryStage) {
        BorderPane chatWindow = new BorderPane();

        ListView<HBox> chatList = new ListView<>();
        chatList.setStyle("-fx-background-color: #e8f5e9; -fx-border-radius: 10;");
        chatList.setId("chatList");

        VBox chatArea = createChatArea(chatWindow, chatList);

        VBox moodBoard = createMoodBoard();

        chatWindow.setLeft(chatArea);
        chatWindow.setRight(moodBoard);

        chatWindow.setBottom(createInputBox(loggedInUser.getUsername(), chatList, moodBoard));

        return new Scene(chatWindow, 950, 600);
    }

    private VBox createChatArea(BorderPane chatWindow, ListView<HBox> chatList) {
        VBox chatArea = new VBox(10);
        chatArea.setPadding(new Insets(10));
        chatArea.setStyle("-fx-background-color: linear-gradient(to right, #f9fbe7, #c8e6c9); -fx-border-radius: 10;");

        HBox userAndChat = new HBox(10);

        ListView<HBox> userList = new ListView<>();
        userList.setPrefWidth(250);
        userList.setStyle("-fx-background-color: #ffffff; -fx-border-color: #81c784; -fx-border-radius: 10;");

        addUser(userList, "Group Chat", chatList, chatWindow, "Alice, Bob, Charlie");
        addUser(userList, "Alice", chatList, chatWindow, "");
        addUser(userList, "Bob", chatList, chatWindow, "");
        addUser(userList, "Charlie", chatList, chatWindow, "");

        userAndChat.getChildren().addAll(userList, chatList);
        chatArea.getChildren().add(userAndChat);

        return chatArea;
    }

    private void addUser(ListView<HBox> userList, String username, ListView<HBox> chatList, BorderPane chatWindow,
            String groupMembers) {
        ListView<HBox> userChat = new ListView<>();
        userChat.setStyle("-fx-background-color: #ffffff;");
        chatHistories.put(username, userChat);

        HBox userItem = new HBox(10);
        ImageView profileIcon;

        // Set the profile picture based on the username
        switch (username) {
            case "Alice":
                profileIcon = new ImageView(
                        new Image("https://cdn.pixabay.com/photo/2022/04/06/11/30/girl-7115394_1280.jpg"));
                break;
            case "Bob":
                profileIcon = new ImageView(
                        new Image("https://menshaircuts.com/wp-content/uploads/2024/08/tp-boys-haircuts.jpg"));
                break;
            case "Charlie":
                profileIcon = new ImageView(new Image(
                        "https://www.shutterstock.com/image-photo/photo-adorable-young-happy-boy-600nw-120165631.jpg"));
                break;
            default:
                profileIcon = new ImageView(new Image("https://via.placeholder.com/50")); // Default placeholder
                break;
        }

        profileIcon.setFitWidth(50);
        profileIcon.setFitHeight(50);

        Label nameLabel = new Label(username);
        nameLabel.setStyle(FONT_STYLE + "-fx-font-size: 16px; -fx-font-weight: bold;");
        userItem.getChildren().addAll(profileIcon, nameLabel);

        userList.getItems().add(userItem);

        userItem.setOnMouseClicked(event -> {
            chatWindow.setBottom(createInputBox(username, chatList, (VBox) chatWindow.getRight()));
            chatList.setItems(chatHistories.get(username).getItems());
            if (username.equals("Group Chat") && !groupMembersDisplayed) {
                chatList.getItems().add(createSpeechBubble("Group Members: " + groupMembers, false));
                groupMembersDisplayed = true;
            }
        });
    }

    private VBox createInputBox(String username, ListView<HBox> chatList, VBox moodBoard) {
        VBox inputBoxContainer = new VBox(5);
        HBox inputBox = new HBox(10);
        inputBox.setPadding(new Insets(10));
        inputBox.setStyle("-fx-background-color: #d7ccc8; -fx-border-radius: 10;");

        TextField messageField = new TextField();
        messageField.setPromptText("Type your message here...");
        messageField.setStyle(FONT_STYLE + " -fx-background-color: #ffffff; -fx-border-radius: 10;");
        messageField.setPrefWidth(400);

        Button sendButton = new Button("Send");
        sendButton.setStyle(FONT_STYLE + " -fx-background-color: #ffcc80; -fx-border-radius: 10;");
        sendButton.setOnAction(e -> {
            String message = messageField.getText().trim();
            if (!message.isEmpty()) {
                chatList.getItems().add(createSpeechBubble("You: " + message, true));
                updateMoodBoard(message, moodBoard);
                messageField.clear();
            }
        });

        inputBox.getChildren().addAll(messageField, sendButton);
        inputBoxContainer.getChildren().addAll(createUserProfile(username), inputBox);
        return inputBoxContainer;
    }

    private HBox createUserProfile(String username) {
        HBox profileBox = new HBox(10);
        profileBox.setPadding(new Insets(10));
        profileBox.setStyle("-fx-background-color: #fbe9e7; -fx-border-radius: 10;");

        ImageView profileIcon = new ImageView(new Image("https://via.placeholder.com/80"));
        profileIcon.setFitWidth(80);
        profileIcon.setFitHeight(80);

        Label nameLabel = new Label(username);
        nameLabel.setStyle(FONT_STYLE + "-fx-font-size: 20px; -fx-font-weight: bold;");
        profileBox.getChildren().addAll(profileIcon, nameLabel);
        return profileBox;
    }

    private HBox createSpeechBubble(String message, boolean isUser) {
        HBox bubble = new HBox();
        bubble.setPadding(new Insets(10));
        bubble.setStyle(isUser
                ? "-fx-background-color: #81d4fa; -fx-border-radius: 15; -fx-background-radius: 15;"
                : "-fx-background-color: #e0f7fa; -fx-border-radius: 15; -fx-background-radius: 15;");

        Label messageLabel = new Label(message);
        messageLabel.setStyle(FONT_STYLE);
        bubble.getChildren().add(messageLabel);
        return bubble;
    }

    private VBox createMoodBoard() {
        VBox moodBoard = new VBox(10);
        moodBoard.setPadding(new Insets(10));
        moodBoard.setStyle("-fx-background-color: #fffde7; -fx-border-radius: 10;");

        Label moodLabel = new Label("Mood: Neutral 😐");
        moodLabel.setStyle(FONT_STYLE + "-fx-font-size: 16px;");
        moodLabel.setId("moodLabel");

        Label wordCloudLabel = new Label("Word Cloud:");
        wordCloudLabel.setStyle(FONT_STYLE + "-fx-font-size: 14px;");

        ListView<String> wordCloud = new ListView<>();
        wordCloud.setStyle(FONT_STYLE + " -fx-background-color: #fff3e0; -fx-border-radius: 10;");
        wordCloud.setId("wordCloud");

        Button moodAnalysisButton = new Button("Analyze Mood");
        moodAnalysisButton.setStyle(FONT_STYLE + " -fx-background-color: #ffd54f; -fx-border-radius: 10;");
        moodAnalysisButton.setOnAction(e -> analyzeMoodTrend(moodLabel));

        moodBoard.getChildren().addAll(moodLabel, wordCloudLabel, wordCloud, moodAnalysisButton);
        return moodBoard;
    }

    private void analyzeMoodTrend(Label moodLabel) {
        String currentMood = moodLabel.getText().split(":")[1].trim();

        switch (currentMood) {
            case "Positive 🙂":
                moodLabel.setText("Mood Trend: Keep spreading positivity! 🌟");
                break;
            case "Negative 🙁":
                moodLabel.setText("Mood Trend: Let’s turn things around. 🌈");
                break;
            case "Neutral 😐":
                moodLabel.setText("Mood Trend: Everything's balanced. ⚖️");
                break;
            case "Excited 🤩":
                moodLabel.setText("Mood Trend: Keep the energy high! 🎉");
                break;
            case "Calm 😌":
                moodLabel.setText("Mood Trend: Enjoy the peace. ☕");
                break;
            case "Angry 😠":
                moodLabel.setText("Mood Trend: Take a deep breath. 🌿");
                break;
            case "Sad 😢":
                moodLabel.setText("Mood Trend: Better days are ahead. 🌞");
                break;
            default:
                moodLabel.setText("Mood Trend: Unable to analyze. 🤔");
                break;
        }
    }

    private void updateMoodBoard(String message, VBox moodBoard) {
        String[] words = message.split("\\s+");
        int positiveCount = 0;
        int negativeCount = 0;
        int excitedCount = 0;
        int calmCount = 0;
        int angryCount = 0;

        for (String word : words) {
            String lowerWord = word.toLowerCase();
            if (POSITIVE_WORDS.contains(lowerWord))
                positiveCount++;
            else if (NEGATIVE_WORDS.contains(lowerWord))
                negativeCount++;
            else if (EXCITED_WORDS.contains(lowerWord))
                excitedCount++;
            else if (CALM_WORDS.contains(lowerWord))
                calmCount++;
            else if (ANGRY_WORDS.contains(lowerWord))
                angryCount++;

            if (!word.matches("the|it|and|or|of|a|an|to|in|on|at|by|is|are|was|were")) {
                wordFrequency.put(lowerWord, wordFrequency.getOrDefault(lowerWord, 0) + 1);
            }
        }

        Label moodLabel = (Label) moodBoard.lookup("#moodLabel");
        if (excitedCount > positiveCount && excitedCount > negativeCount && excitedCount > calmCount
                && excitedCount > angryCount) {
            moodLabel.setText("Mood: Excited 🤩");
        } else if (calmCount > positiveCount && calmCount > negativeCount && calmCount > excitedCount
                && calmCount > angryCount) {
            moodLabel.setText("Mood: Calm 😌");
        } else if (angryCount > positiveCount && angryCount > negativeCount && angryCount > calmCount
                && angryCount > excitedCount) {
            moodLabel.setText("Mood: Angry 😠");
        } else if (positiveCount > negativeCount) {
            moodLabel.setText("Mood: Positive 🙂");
        } else if (negativeCount > positiveCount) {
            moodLabel.setText("Mood: Negative 🙁");
        } else {
            moodLabel.setText("Mood: Neutral 😐");
        }

        ListView<String> wordCloud = (ListView<String>) moodBoard.lookup("#wordCloud");
        wordCloud.getItems().clear();
        wordFrequency.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(entry -> wordCloud.getItems().add(entry.getKey() + " (" + entry.getValue() + ")"));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static class UserProfile {
        private final String username;
        private final String password;
        private final String email;
        private final String bio;
        private final String profilePicUrl;

        public UserProfile(String username, String password, String email, String bio, String profilePicUrl) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.bio = bio;
            this.profilePicUrl = profilePicUrl;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }

        public String getBio() {
            return bio;
        }

        public String getProfilePicUrl() {
            return profilePicUrl;
        }
    }
}