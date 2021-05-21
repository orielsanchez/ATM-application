package edu.sdccd.cisc191.f.client;

import edu.sdccd.cisc191.f.Account;
import edu.sdccd.cisc191.f.AccountResponse;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Client
        Client client = new Client();
        client.startConnection("127.0.0.1", 4444);
        final Account[] account = new Account[1];

        // Login Scene
        VBox loginVBox = new VBox();
        loginVBox.setPadding(new Insets(10));
        loginVBox.setSpacing(10);
        loginVBox.setAlignment(Pos.CENTER);

        // Title
        Label ATMLabel = new Label("Welcome to the Bank of Group F");
        ATMLabel.setFont(new Font("Helvetica", 20));

        // Setup GridPane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setPadding(new Insets(10));
        gridPane.setMinWidth(300);
        gridPane.setVgap(5);
        gridPane.setHgap(5);


        // Labels, Buttons, and Text Fields
        Label cardNumberLabel = new Label("Card Number: ");
        gridPane.add(cardNumberLabel, 0, 0);
        RestrictiveTextField cardNumberField = new RestrictiveTextField();
        cardNumberField.setMaxLength(16);
        cardNumberField.setRestrict("[0-9]");
        gridPane.add(cardNumberField, 1, 0);

        Label PINLabel = new Label("PIN: ");
        gridPane.add(PINLabel, 0, 1);
        RestrictiveTextField PINField = new RestrictiveTextField();
        PINField.setRestrict("[0-9]");
        PINField.setMaxLength(4);
        gridPane.add(PINField, 1, 1);

        Button loginButton = new Button("Login");
        gridPane.add(loginButton, 0, 2);
        Button createAccountButton = new Button("Create Account");
        createAccountButton.setMaxWidth(Double.MAX_VALUE);
        GridPane.setFillWidth(createAccountButton, true);
        gridPane.add(createAccountButton, 1, 2);

        // Setup LoginScene
        loginVBox.getChildren().addAll(ATMLabel, gridPane);
        Scene loginScene = new Scene(loginVBox);

        // Setup primaryStage
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Bank of Group F");
        primaryStage.setResizable(false);
        primaryStage.show();


        // Main Menu Scene
        VBox mainMenuVBox = new VBox();
        mainMenuVBox.setAlignment(Pos.CENTER);
        mainMenuVBox.setPadding(new Insets(10));
        mainMenuVBox.setSpacing(10);

        Label mainMenuTitleLabel = new Label("Welcome to the Bank of Group F");

        GridPane mainMenuGridPane = new GridPane();
        mainMenuGridPane.setPadding(new Insets(10));
        mainMenuGridPane.setAlignment(Pos.CENTER);
        mainMenuGridPane.setVgap(5);
        mainMenuGridPane.setHgap(5);
        mainMenuGridPane.setMinWidth(300);

        Label activeCardNumberLabel = new Label("Card Number: ");
        Label activeCardNumberField = new Label();
        mainMenuGridPane.add(activeCardNumberLabel, 0, 0);
        mainMenuGridPane.add(activeCardNumberField, 1, 0);

        Label currentBalanceLabel = new Label("Balance: ");
        Label currentBalanceField = new Label();
        mainMenuGridPane.add(currentBalanceLabel, 0, 1);
        mainMenuGridPane.add(currentBalanceField, 1, 1);

        Button depositMenuButton = new Button("Deposit");
        Button withdrawMenuButton = new Button("Withdraw");
        Button transferFundsMenuButton = new Button("Transfer Funds");
        Button logOutMenuButton = new Button("Log Out");

        depositMenuButton.setMaxWidth(Double.MAX_VALUE);
        withdrawMenuButton.setMaxWidth(Double.MAX_VALUE);
        transferFundsMenuButton.setMaxWidth(Double.MAX_VALUE);
        logOutMenuButton.setMaxWidth(Double.MAX_VALUE);
        GridPane.setFillWidth(depositMenuButton, true);
        GridPane.setFillWidth(withdrawMenuButton, true);
        GridPane.setFillWidth(transferFundsMenuButton, true);
        GridPane.setFillWidth(logOutMenuButton, true);

        mainMenuGridPane.add(depositMenuButton, 0, 2);
        mainMenuGridPane.add(withdrawMenuButton, 1, 2);
        mainMenuGridPane.add(transferFundsMenuButton, 0, 3);
        mainMenuGridPane.add(logOutMenuButton, 1, 3);

        mainMenuVBox.getChildren().addAll(mainMenuTitleLabel, mainMenuGridPane);
        Scene mainMenuScene = new Scene(mainMenuVBox);

        // Send a login request to server
        loginButton.setOnAction(event -> {
            try {
                AccountResponse accountResponse = client.sendAccountRequest(Long.parseLong(cardNumberField.getText()), PINField.getText());
                if (accountResponse != null) {
                    account[0] = new Account(accountResponse.getId(), accountResponse.getCardNumber(), accountResponse.getPIN(), accountResponse.getBalance());
                    activeCardNumberField.setText(String.valueOf(account[0].getCardNumber()));
                    currentBalanceField.setText(String.valueOf(account[0].getBalance()));
                    primaryStage.setScene(mainMenuScene);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Send a blank request to server to create an account
        createAccountButton.setOnAction(event -> {
            try {
                AccountResponse accountResponse = client.sendAccountRequest(0L, "0");
                account[0] = new Account(accountResponse.getId(), accountResponse.getCardNumber(), accountResponse.getPIN(), accountResponse.getBalance());
                cardNumberField.setText(String.valueOf(account[0].getCardNumber()));
                PINField.setText(account[0].getPIN());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Log out button pressed
        logOutMenuButton.setOnAction(event -> {
            account[0] = null;
            primaryStage.setScene(loginScene);
        });


    }
}
