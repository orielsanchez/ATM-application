package edu.sdccd.cisc191.f.client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Main Menu Scene
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
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
        primaryStage.setMinWidth(1280);
        primaryStage.setMinHeight(720);
        primaryStage.setResizable(false);
        primaryStage.show();


        // Character Preview Scene


        // Map/Level Selection Scene


        // Room Scene
    }
}
