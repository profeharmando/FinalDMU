/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.teupa.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author hector
 */
public class ConfigurarCtrl implements Initializable {

    @FXML private Button btnSalir;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void inicializarDatos(PrincipalCtrl p_principal){
        
    }
    
    @FXML
    public void eliminarInformacion(){
        Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
        confirmar.setTitle("Eliminar información");
        confirmar.setHeaderText("Se dispone a eliminar información del sistema."
                + "\nHágalo solamente si está seguro."
                + "\n\nSeleccione ACEPTAR si desea continuar o"
                + "\nseleccione CANCELAR para salir.");
        confirmar.setContentText("");
        Optional<ButtonType> result = confirmar.showAndWait();
        if (result.get() == ButtonType.OK) {
            String formulario = "/com/teupa/vista/EliminarInformacion.fxml";
            try {            
                FXMLLoader loader = new FXMLLoader(getClass().getResource(formulario));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene( new Scene((Pane) loader.load()));
                EliminarInformacionCtrl controlador =  loader.<EliminarInformacionCtrl>getController();
                controlador.inicializarDatos();
                Image icon = new Image(getClass().getResourceAsStream("/com/teupa/imagenes/icono.png"));
                stage.getIcons().add(icon);
                stage.setTitle("Eliminar Información");
                stage.initModality(Modality.APPLICATION_MODAL);
                System.out.println("\n\nMostrado formulario: " + formulario);
                stage.show();
                System.out.println("\n\t\t...iniciado Eliminar Información");
            } catch (IOException e) {
                System.err.println("Error al intentar mostrar la ventana " + formulario + ": \n" + e.getMessage());
            }
        }
    }
    
    @FXML
    public void salir(){
        // Obtener el escenario (Stage)
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        // Cerrar el formulario.
        stage.close();
    }
}
