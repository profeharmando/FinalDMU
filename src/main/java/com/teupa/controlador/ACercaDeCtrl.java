/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.teupa.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hector
 */
public class ACercaDeCtrl implements Initializable {

    @FXML private ImageView imgYo;
    @FXML private Button btnSalir;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void mensaje(){
        Alert mensaje = new Alert(Alert.AlertType.WARNING);
            mensaje.setTitle(":)");
            mensaje.setHeaderText("...dedicado a \u2665.Jesé y \u2665.Julian");
            mensaje.setContentText("");
            mensaje.showAndWait();
    }
    
    @FXML
    private void salir(){
        System.out.println("\n\t\tSalir de ventana principal.\n");
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
}
