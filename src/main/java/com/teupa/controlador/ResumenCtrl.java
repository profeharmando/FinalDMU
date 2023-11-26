/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.teupa.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hector
 */
public class ResumenCtrl implements Initializable {

    @FXML private Label lblResumen;
    @FXML private TextArea txtMensaje;
    @FXML private Button btnSalir;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void inicializarDatos(String mensaje, String titulo){
        lblResumen.setText(titulo);
        if(!mensaje.equals("")){
            txtMensaje.setText(mensaje + "\n\n\nAlgo no cuadra por aquí...   :(");
        }else{
            txtMensaje.setText("Todo bien...!"
                    + "\nNo hay nada raro por aquí."
                    + "\n\nLos datos del centro parecen correctos   :)");
        }
        Platform.runLater(() -> btnSalir.requestFocus());
    }
    
    @FXML
    public void salir(){
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
}
