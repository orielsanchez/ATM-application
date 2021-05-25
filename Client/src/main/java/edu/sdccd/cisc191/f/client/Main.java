package edu.sdccd.cisc191.f.client;

import edu.sdccd.cisc191.f.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Generates a GUI that is used to interact with the ATM Server,
 * sending requests through the client.
 *
 * @author Oriel Sanchez
 * @author Noah Widders
 */
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

        // Login Title
        Label ATMLabel = new Label("Welcome to the Falcon Credit Union ATM");
        ATMLabel.setFont(new Font("Helvetica", 20));

        // Setup Login GridPane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setPadding(new Insets(10));
        gridPane.setMinWidth(300);
        gridPane.setVgap(5);
        gridPane.setHgap(5);


        // Login Labels, Buttons, and Text Fields
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

        // Setup login Stage
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Falcon Credit Union");
        primaryStage.setResizable(false);
        primaryStage.show();


        // Main Menu Window
        VBox mainMenuVBox = new VBox();
        mainMenuVBox.setAlignment(Pos.CENTER);
        mainMenuVBox.setPadding(new Insets(10));
        mainMenuVBox.setSpacing(10);

        // Main Menu Title
        Label mainMenuTitleLabel = new Label("Welcome to the Falcon Credit Union ATM");

        // Main Menu GridPane
        GridPane mainMenuGridPane = new GridPane();
        mainMenuGridPane.setPadding(new Insets(10));
        mainMenuGridPane.setAlignment(Pos.CENTER);
        mainMenuGridPane.setVgap(5);
        mainMenuGridPane.setHgap(5);
        mainMenuGridPane.setMinWidth(300);

        // Main Menu labels, buttons, and fields
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


        // Setup Main Menu scene
        mainMenuVBox.getChildren().addAll(mainMenuTitleLabel, mainMenuGridPane);
        Scene mainMenuScene = new Scene(mainMenuVBox);

        // Deposit Window
        Stage depositStage = new Stage();

        VBox depositVBox = new VBox();
        depositVBox.setAlignment(Pos.CENTER);
        depositVBox.setPadding(new Insets(10));
        depositVBox.setSpacing(10);

        // Deposit title
        Label depositMenuTitle = new Label("DEPOSIT");
        depositMenuTitle.setFont(new Font("Helvetica", 20));


        // Deposit GridPane
        GridPane depositMenuGridPane = new GridPane();
        depositMenuGridPane.setPadding(new Insets(10));
        depositMenuGridPane.setAlignment(Pos.CENTER);
        depositMenuGridPane.setVgap(5);
        depositMenuGridPane.setHgap(5);
        depositMenuGridPane.setMinWidth(200);

        // Deposit Menu labels, buttons, and fields
        Label currentBalance = new Label("Current Balance: ");
        Label depositMenuCurrentBalanceField = new Label();
        Label depositTextLabel = new Label("How much would you like to deposit?");
        RestrictiveTextField depositAmountField = new RestrictiveTextField();
        depositAmountField.setRestrict("[0-9]");
        depositAmountField.setMaxLength(10);

        Button depositButton = new Button("Deposit");
        depositButton.setMaxWidth(Double.MAX_VALUE);
        GridPane.setFillWidth(depositButton, true);

        depositMenuGridPane.add(currentBalance, 0, 0);
        depositMenuGridPane.add(depositMenuCurrentBalanceField, 1, 0);
        depositMenuGridPane.add(depositTextLabel, 0, 1);
        depositMenuGridPane.add(depositAmountField, 1, 1);
        depositMenuGridPane.add(depositButton, 1, 2);

        // Setup Deposit Scene
        depositVBox.getChildren().addAll(depositMenuTitle, depositMenuGridPane);
        Scene depositScene = new Scene(depositVBox);
        depositStage.setScene(depositScene);
        depositStage.initModality(Modality.APPLICATION_MODAL);
        depositStage.setTitle("Deposit");
        depositStage.setResizable(false);

        // Show Deposit Stage
        depositMenuButton.setOnAction(event -> {
            depositMenuCurrentBalanceField.setText(String.valueOf(account[0].getBalance()));
            depositStage.showAndWait();
        });

        // Send Deposit Request
        depositButton.setOnAction(event -> {
            if (!depositAmountField.getText().equals("")) {
                try {
                    DepositResponse depositResponse = client.sendDepositRequest(account[0].getCardNumber(), Double.parseDouble(depositAmountField.getText()));
                    account[0] = new Account(depositResponse.getId(), depositResponse.getCardNumber(), depositResponse.getPIN(), depositResponse.getBalance());
                    currentBalanceField.setText(String.valueOf(account[0].getBalance()));
                    depositStage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Withdraw Window
        Stage withdrawStage = new Stage();

        VBox withdrawVBox = new VBox();
        withdrawVBox.setAlignment(Pos.CENTER);
        withdrawVBox.setPadding(new Insets(10));
        withdrawVBox.setSpacing(10);

        // Withdraw Menu title
        Label withdrawMenuTitle = new Label("WITHDRAW");
        withdrawMenuTitle.setFont(new Font("Helvetica", 20));

        // Withdraw Menu GridPane
        GridPane withdrawMenuGridPane = new GridPane();
        withdrawMenuGridPane.setPadding(new Insets(10));
        withdrawMenuGridPane.setAlignment(Pos.CENTER);
        withdrawMenuGridPane.setVgap(5);
        withdrawMenuGridPane.setHgap(5);
        withdrawMenuGridPane.setMinWidth(200);

        // Withdraw Menu labels, buttons, and fields
        Label withdrawMenuCurrentBalance = new Label("Current Balance: ");
        Label withdrawMenuCurrentBalanceField = new Label();
        Label withdrawTextLabel = new Label("How much would you like to withdraw?");
        RestrictiveTextField withdrawAmountField = new RestrictiveTextField();
        withdrawAmountField.setRestrict("[0-9]");
        withdrawAmountField.setMaxLength(10);

        Button withdrawButton = new Button("Withdraw");
        withdrawButton.setMaxWidth(Double.MAX_VALUE);
        GridPane.setFillWidth(withdrawButton, true);

        withdrawMenuGridPane.add(withdrawMenuCurrentBalance, 0, 0);
        withdrawMenuGridPane.add(withdrawMenuCurrentBalanceField, 1, 0);
        withdrawMenuGridPane.add(withdrawTextLabel, 0, 1);
        withdrawMenuGridPane.add(withdrawAmountField, 1, 1);
        withdrawMenuGridPane.add(withdrawButton, 1, 2);

        // Setup Withdraw Menu Scene
        withdrawVBox.getChildren().addAll(withdrawMenuTitle, withdrawMenuGridPane);
        Scene withdrawScene = new Scene(withdrawVBox);
        withdrawStage.setScene(withdrawScene);
        withdrawStage.initModality(Modality.APPLICATION_MODAL);
        withdrawStage.setTitle("Withdraw");
        withdrawStage.setResizable(false);

        // Show Withdraw Stage
        withdrawMenuButton.setOnAction(event -> {
            withdrawMenuCurrentBalanceField.setText(String.valueOf(account[0].getBalance()));
            withdrawStage.showAndWait();
        });

        // Send Withdraw Request
        withdrawButton.setOnAction(event -> {
            if (!withdrawAmountField.getText().equals("")) {
                try {
                    WithdrawResponse withdrawResponse = client.sendWithdrawRequest(account[0].getCardNumber(), Double.parseDouble(withdrawAmountField.getText()));
                    account[0] = new Account(withdrawResponse.getId(), withdrawResponse.getCardNumber(), withdrawResponse.getPIN(), withdrawResponse.getBalance());
                    currentBalanceField.setText(String.valueOf(account[0].getBalance()));
                    withdrawStage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Transfer Window
        Stage transferStage = new Stage();

        VBox transferVBox = new VBox();
        transferVBox.setAlignment(Pos.CENTER);
        transferVBox.setPadding(new Insets(10));
        transferVBox.setSpacing(10);

        // Transfer title
        Label transferMenuTitle = new Label("TRANSFER");
        transferMenuTitle.setFont(new Font("HELVETICA", 20));


        // Transfer Menu GridPane
        GridPane transferMenuGridPane = new GridPane();
        transferMenuGridPane.setPadding(new Insets(10));
        transferMenuGridPane.setAlignment(Pos.CENTER);
        transferMenuGridPane.setHgap(5);
        transferMenuGridPane.setVgap(5);
        transferMenuGridPane.setMinWidth(300);

        // Transfer Menu labels, buttons, and fields
        Label transferMenuCurrentBalanceText = new Label("Current Balance: ");
        Label transferMenuCurrentBalance = new Label();
        Label transferText = new Label("How much would you like to transfer to recipient?");
        RestrictiveTextField transferAmountField = new RestrictiveTextField();
        transferAmountField.setRestrict("[0-9]");
        transferAmountField.setMaxLength(10);
        Label recipientText = new Label("Recipient's Card Number: ");
        RestrictiveTextField recipientField = new RestrictiveTextField();
        recipientField.setMaxLength(16);
        recipientField.setRestrict("[0-9]");

        Button transferButton = new Button("Transfer");
        transferButton.setMaxWidth(Double.MAX_VALUE);
        GridPane.setFillWidth(transferButton, true);

        transferMenuGridPane.add(transferMenuCurrentBalanceText, 0, 0);
        transferMenuGridPane.add(transferMenuCurrentBalance, 1, 0);
        transferMenuGridPane.add(transferText, 0, 1);
        transferMenuGridPane.add(transferAmountField, 1, 1);
        transferMenuGridPane.add(recipientText, 0, 2);
        transferMenuGridPane.add(recipientField, 1, 2);
        transferMenuGridPane.add(transferButton, 1, 3);

        // Setup Transfer Menu Scene
        transferVBox.getChildren().addAll(transferMenuTitle, transferMenuGridPane);
        Scene transferScene = new Scene(transferVBox);
        transferStage.setScene(transferScene);
        transferStage.initModality(Modality.APPLICATION_MODAL);
        transferStage.setTitle("Transfer");
        transferStage.setResizable(false);

        // Show Transfer Stage
        transferFundsMenuButton.setOnAction(event -> {
            transferMenuCurrentBalance.setText(String.valueOf(account[0].getBalance()));
            transferStage.showAndWait();
        });

        // Send Transfer Request
        transferButton.setOnAction(event -> {
            if (!transferAmountField.getText().equals("") && !recipientField.getText().equals("")) {
                try {
                    TransferFundsResponse transferFundsResponse = client.sendTransferFundsRequest(
                            account[0].getCardNumber(),
                            Double.parseDouble(transferAmountField.getText()),
                            Long.parseLong(recipientField.getText()));

                    boolean transferSuccessful = transferFundsResponse.isTransferSuccessful();
                    Alert alert;
                    if (transferSuccessful) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Transfer Successful!");
                        String contentText = String.format("Transfer of %s to %s was successful!\nYour balance is now %s", transferAmountField.getText(), recipientField.getText(), transferFundsResponse.getBalance());
                        account[0] = new Account(account[0].getID(), account[0].getCardNumber(), account[0].getPIN(), transferFundsResponse.getBalance());
                        currentBalanceField.setText(String.valueOf(account[0].getBalance()));
                        alert.setContentText(contentText);
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Transfer Failed");
                        alert.setContentText("Transfer was unsuccessful :(");
                    }
                    alert.showAndWait();
                    transferStage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        // Send a login request to server
        loginButton.setOnAction(event -> {
            if (!cardNumberField.getText().equals("") && !PINField.getText().equals("")) {
                try {
                    long cardNumber = Long.parseLong(cardNumberField.getText());
                    String PIN = PINField.getText();
                    System.out.println("login button pressed, send account request info: " + cardNumber + " " + PIN);
                    AccountResponse accountResponse = client.sendAccountRequest(cardNumber, PIN);
                    System.out.println(accountResponse.toString());
                    if (accountResponse.getId() != -1) {
                        account[0] = new Account(accountResponse.getId(), accountResponse.getCardNumber(), accountResponse.getPIN(), accountResponse.getBalance());
                        activeCardNumberField.setText(String.valueOf(account[0].getCardNumber()));
                        currentBalanceField.setText(String.valueOf(account[0].getBalance()));
                        primaryStage.setScene(mainMenuScene);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Invalid Card Number or PIN!");
                        alert.setContentText("Please try again");
                        alert.showAndWait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

        primaryStage.setOnCloseRequest(event -> {
            try {
                client.stopConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
