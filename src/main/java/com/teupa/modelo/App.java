package com.teupa.modelo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    
    Image icono = new Image(getClass().getResourceAsStream("/com/teupa/imagenes/icono.png"));

    @Override
    public void start(Stage stage) throws Exception {
        String formulario = "/com/teupa/vista/Principal.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(formulario));
        Scene scene = new Scene(root);        
        stage.getIcons().add(icono);
        stage.setTitle("Final-DMu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}