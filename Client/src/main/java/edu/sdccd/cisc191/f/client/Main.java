package edu.sdccd.cisc191.f.client;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Client
        Client client = new Client();
        try {
            client.startConnection("127.0.0.1", 4444);
            System.out.println(client.sendAccountRequest().toString());
            client.stopConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        // Create Formatter to force numeric only fields
//        TextFormatter sixteenDigitFormatter = getTextFormatter("[0-9].{0,15}");
//        TextFormatter fourDigitFormatter = getTextFormatter("[0-9].{0,3}");
//
//        cardNumberField.setTextFormatter(sixteenDigitFormatter);
//        PINField.setTextFormatter(fourDigitFormatter);

        // Setup LoginScene
        loginVBox.getChildren().addAll(ATMLabel, gridPane);
        Scene loginScene = new Scene(loginVBox);

        // Setup primaryStage
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Bank of Group F");
        primaryStage.setResizable(false);
        primaryStage.show();


        // Show Balance Scene

        // Deposit Scene

        // Withdraw Scene

        // Transfer Funds Scene

        // Close Account Scene


        // Button Actions
        //playButton.setOnAction(event -> primaryStage.setScene(loginScene));
    }

//    private TextFormatter getTextFormatter(String s) {
//        Pattern pattern = Pattern.compile(s);
//        return new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
//            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
//        });
//    }
}
