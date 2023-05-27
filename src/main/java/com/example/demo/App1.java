package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App1 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("/Fxml/Login.fxml"));
        Scene scene= new Scene(parent);
        stage.setTitle("GESTION DES AVOCATS");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        launch();
    }

}
