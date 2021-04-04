package edu.sdccd.cisc191.f.client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Main Menu Scene
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        Label mainTitle = new Label("Group F Final Project");
        mainTitle.setFont(new Font(65));
        Pane middlePane = new Pane();
        middlePane.setMinSize(0, 50);
        Button playButton = new Button("Play");
        playButton.setPrefSize(300, 100);
        vBox.getChildren().addAll(mainTitle, middlePane, playButton);
        vBox.setAlignment(Pos.CENTER);
        Scene mainMenu = new Scene(vBox);
        primaryStage.setScene(mainMenu);
        primaryStage.setTitle("Group F");
        primaryStage.setHeight(720);
        primaryStage.setWidth(1280);
        primaryStage.setResizable(false);
        primaryStage.show();


        // Character Selection Scene
        VBox characterPreviewVBox = new VBox();
        HBox characterSelectionBox = new HBox();
        characterSelectionBox.setSpacing(10);
        Label characterPreviewTitle = new Label("Choose Your Character!");
        characterPreviewTitle.setFont(new Font(65));
        Button warriorSelectionButton = new Button("Warrior");
        Button mageSelectionButton = new Button("Mage");
        Button thiefSelectionButton = new Button("Thief");
        warriorSelectionButton.setPrefSize(300, 100);
        mageSelectionButton.setPrefSize(300, 100);
        thiefSelectionButton.setPrefSize(300, 100);
        characterSelectionBox.getChildren().addAll(warriorSelectionButton, mageSelectionButton, thiefSelectionButton);
        characterSelectionBox.setAlignment(Pos.CENTER);
        characterPreviewVBox.getChildren().addAll(characterPreviewTitle, middlePane, characterSelectionBox);
        characterPreviewVBox.setAlignment(Pos.CENTER);
        characterPreviewVBox.setPrefHeight(720);
        Scene characterPreviewScene = new Scene(characterPreviewVBox);


        // Map/Level Selection Scene


        // Room Scene



        // Button Actions
        playButton.setOnAction(event -> {
            primaryStage.setScene(characterPreviewScene);

        });
    }
}
