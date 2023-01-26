package com.example.huevos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public Scene scene;
    String golden_egg = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\imgs\\golden_egg.png";
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ViewGame.fxml"));
        scene = new Scene(fxmlLoader.load(), 973, 736);
        stage.setTitle("Alberto Vázquez Miranda - 201425");
        stage.getIcons().add(new Image(golden_egg));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}