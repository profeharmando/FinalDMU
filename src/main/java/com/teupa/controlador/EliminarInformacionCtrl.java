/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.teupa.controlador;

import com.teupa.basedatos.ConexionMySql;
import com.teupa.basedatos.Crear;
import com.teupa.basedatos.Eliminar;
import com.teupa.basedatos.Seleccionar;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class EliminarInformacionCtrl implements Initializable {
   
    @FXML private Button btnEliminarResultadoFinal;
    @FXML private Button btnEliminarResultadoFinalCentro;
    @FXML private Button btnEliminarTodosCentros;
    @FXML private Button btnEliminarUnCentro;
    @FXML private Button btnEliminarTasas;
    @FXML private Button btnEliminarTasasCentro;
    @FXML private Button limpiarBD;
    @FXML private Button btnSalir;
    
    //Conexion a la BD
    ConexionMySql conn = new ConexionMySql();
    Connection conexion = conn.establecerConexion();
    
    //Se llenan desde el formulario BuscarCentro
    public int codigo;
    public String nombre_centro;
    public String codigo_sace;
    //Se leen desde BuscarCentro
    public boolean eliminar_centro;
    public boolean eliminar_resultado;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(Seleccionar.hayCentros(conn.establecerConexion())){
           btnEliminarUnCentro.setDisable(false);
           btnEliminarTodosCentros.setDisable(false);
        }else{
           btnEliminarUnCentro.setDisable(true);
           btnEliminarTodosCentros.setDisable(true);
        }
        if(Seleccionar.hayResultadoFinal(conn.establecerConexion())){
           btnEliminarResultadoFinal.setDisable(false);
           btnEliminarResultadoFinalCentro.setDisable(false);
        }else{
            btnEliminarResultadoFinal.setDisable(true);
            btnEliminarResultadoFinalCentro.setDisable(true);
        }
        if(Seleccionar.hayTasasReprobados(conn.establecerConexion())){
           btnEliminarTasas.setDisable(false);
           btnEliminarTasasCentro.setDisable(false);
        }else{
            btnEliminarTasas.setDisable(true);
           btnEliminarTasasCentro.setDisable(true);
        }
    }
    
    public void inicializarDatos(){
        
    }
    
    @FXML
    public void eliminarTodosCentros(){
        Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
        confirmar.setTitle("Eliminar Todos Los Centros");
        confirmar.setHeaderText("Se dispone a eliminar TODOS los centros educativos del sistema."
                + "\nEsta información no se podrá recuparar."
                + "\n\nSeleccione ACEPTAR si desea Eliminar o"
                + "\nseleccione CANCELAR para salir.");
        confirmar.setContentText("");
        Optional<ButtonType> result = confirmar.showAndWait();
        if (result.get() == ButtonType.OK) {
            if(Eliminar.eliminarCentros(conexion)){
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Datos Eliminados");
                mensaje.setHeaderText("Se ha eliminado TODOS los centros educativos.");
                mensaje.setContentText("");
                mensaje.showAndWait();
            }else{
                Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setTitle("Datos No eliminados");
                mensaje.setHeaderText("No se pudo eliminar los centros.");
                mensaje.setContentText("");
                mensaje.showAndWait();
            }
        }
    }
    
    @FXML
    public void eliminarUnCentro(){
        eliminar_centro = true;
        String formulario = "/com/teupa/vista/BuscarCentro.fxml";
        try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(formulario));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene( new Scene((Pane) loader.load()));
            BuscarCentroCtrl controlador =  loader.<BuscarCentroCtrl>getController();
            controlador.inicializarDatos(null, this, null);
            Image icon = new Image(getClass().getResourceAsStream("/com/teupa/imagenes/icono.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Buscar Centros");
            stage.initModality(Modality.APPLICATION_MODAL);
            System.out.println("\n\nMostrado formulario: " + formulario);
            stage.showAndWait();
            System.out.println("\n\t\t...iniciado Buscar Centros");
        } catch (IOException e) {
            System.err.println("Error al intentar mostrar la ventana " + formulario + ": \n" + e.getMessage());
        }
        if(codigo > 0){
            Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
            confirmar.setTitle("Eliminar Centro");
            confirmar.setHeaderText("Se dispone a eliminar el centro:"
                    + "\n" + nombre_centro + " (" + codigo_sace +  ")"
                    + "\n\nSeleccione ACEPTAR si desea Eliminar o"
                    + "\nseleccione CANCELAR para salir.");
            confirmar.setContentText("");
            Optional<ButtonType> result = confirmar.showAndWait();
            if (result.get() == ButtonType.OK) {
                if(Eliminar.eliminarCentro(conexion, codigo)){
                    Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                    mensaje.setTitle("Datos Eliminados");
                    mensaje.setHeaderText("Se ha eliminado el centro.");
                    mensaje.setContentText("");
                    mensaje.showAndWait();
                }else{
                    Alert mensaje = new Alert(Alert.AlertType.ERROR);
                    mensaje.setTitle("Datos No eliminados");
                    mensaje.setHeaderText("No se pudo eliminar el centro.");
                    mensaje.setContentText("");
                    mensaje.showAndWait();
                }
            }
        }
    }
    
    @FXML
    public void eliminarResultadoFinal(){
        Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
        confirmar.setTitle("Eliminar Resultados Finales");
        confirmar.setHeaderText("Se dispone a eliminar TODOS los datos de Resultado Final del sistema."
                + "\nEsta información no se podrá recuparar."
                + "\n\nSeleccione ACEPTAR si desea Eliminar o"
                + "\nseleccione CANCELAR para salir.");
        confirmar.setContentText("");
        Optional<ButtonType> result = confirmar.showAndWait();
        if (result.get() == ButtonType.OK) {
            if(Eliminar.eliminarResultadoFinales(conexion)){
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Datos Eliminados");
                mensaje.setHeaderText("Se ha eliminado TODA la información de Resultados Finales.");
                mensaje.setContentText("");
                mensaje.showAndWait();
            }
        }
    }
    
    @FXML
    public void eliminarResultadoFinalCentro(){
        eliminar_resultado = true;
        String formulario = "/com/teupa/vista/BuscarCentro.fxml";
        try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(formulario));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene( new Scene((Pane) loader.load()));
            BuscarCentroCtrl controlador =  loader.<BuscarCentroCtrl>getController();
            controlador.inicializarDatos(null, this, null);
            Image icon = new Image(getClass().getResourceAsStream("/com/teupa/imagenes/icono.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Buscar Centros");
            stage.initModality(Modality.APPLICATION_MODAL);
            System.out.println("\n\nMostrado formulario: " + formulario);
            stage.showAndWait();
            System.out.println("\n\t\t...iniciado Buscar Centros");
        } catch (IOException e) {
            System.err.println("Error al intentar mostrar la ventana " + formulario + ": \n" + e.getMessage());
        }
        if(codigo > 0){
            Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
            confirmar.setTitle("Eliminar Resultados Final de Centro");
            confirmar.setHeaderText("Se dispone a eliminar los datos de Resultado Final del centro:"
                    + "\n" + nombre_centro + " (" + codigo_sace +  ")"
                    + "\n\nSeleccione ACEPTAR si desea Eliminar o"
                    + "\nseleccione CANCELAR para salir.");
            confirmar.setContentText("");
            Optional<ButtonType> result = confirmar.showAndWait();
            if (result.get() == ButtonType.OK) {
                if(Eliminar.eliminarResultadoFinalCentro(conexion, codigo)){
                    Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                    mensaje.setTitle("Datos Eliminados");
                    mensaje.setHeaderText("Se ha eliminado la información de Resultados Final del centro.");
                    mensaje.setContentText("");
                    mensaje.showAndWait();
                }
            }
        }
    }
    
    @FXML
    public void eliminarTasas(){
        Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
        confirmar.setTitle("Eliminar tasas de reprobados");
        confirmar.setHeaderText("Se dispone a eliminar TODOS los datos de Tasas de Reprobados en Español y Matemáticas del sistema."
                + "\nEsta información no se podrá recuparar."
                + "\n\nSeleccione ACEPTAR si desea Eliminar o"
                + "\nseleccione CANCELAR para salir.");
        confirmar.setContentText("");
        Optional<ButtonType> result = confirmar.showAndWait();
        if (result.get() == ButtonType.OK) {
            if(Eliminar.eliminarTasas(conexion)){
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Datos Eliminados");
                mensaje.setHeaderText("Se ha eliminado TODA la información de Tasas de Reprobados en Español y Matemáticas del sistema.");
                mensaje.setContentText("");
                mensaje.showAndWait();
            }
        }
    }
    
    @FXML
    public void eliminarTasasCentro(){
        eliminar_resultado = true;
        String formulario = "/com/teupa/vista/BuscarCentro.fxml";
        try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(formulario));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene( new Scene((Pane) loader.load()));
            BuscarCentroCtrl controlador =  loader.<BuscarCentroCtrl>getController();
            controlador.inicializarDatos(null, this, null);
            Image icon = new Image(getClass().getResourceAsStream("/com/teupa/imagenes/icono.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Buscar Centros");
            stage.initModality(Modality.APPLICATION_MODAL);
            System.out.println("\n\nMostrado formulario: " + formulario);
            stage.showAndWait();
            System.out.println("\n\t\t...iniciado Buscar Centros");
        } catch (IOException e) {
            System.err.println("Error al intentar mostrar la ventana " + formulario + ": \n" + e.getMessage());
        }
        if(codigo > 0){
            Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
            confirmar.setTitle("Eliminar Tasas de Reprobados de un Centro");
            confirmar.setHeaderText("Se dispone a eliminar los datos de Tasas de Reprobados del centro:"
                    + "\n" + nombre_centro + " (" + codigo_sace +  ")"
                    + "\n\nSeleccione ACEPTAR si desea Eliminar o"
                    + "\nseleccione CANCELAR para salir.");
            confirmar.setContentText("");
            Optional<ButtonType> result = confirmar.showAndWait();
            if (result.get() == ButtonType.OK) {
                if(Eliminar.eliminarTasasCentro(conexion, codigo)){
                    Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                    mensaje.setTitle("Datos Eliminados");
                    mensaje.setHeaderText("Se ha eliminado la información de Tasas de Reprobación del centro.");
                    mensaje.setContentText("");
                    mensaje.showAndWait();
                }
            }
        }
    }
    
    @FXML
    public void limpiarBD(){
        Crear crear = new Crear();
        try {
            System.out.println("\n\nEliminando tablas...");
            eliminarTablaTasasReprobados();            
            eliminarTablaResultadosFinales();            
            eliminarTablaCentros();
            Thread.sleep(500);
            //Crea las tablas a continuacion
            System.out.println("\n\nCreando tablas...");
            crear.tbl_centro(conexion);
            crear.tbl_resultado_final(conexion);
            crear.tbl_reprobados(conexion);
        } catch (InterruptedException ex) {
            System.err.println("\n\nOcurrió un error al esperar para crear las tablas en la BD"
                    + "\nError: " + ex.getMessage());
        }
        
    }
    
    @FXML
    public void eliminarTablaCentros(){
        Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
        confirmar.setTitle("Eliminar tabla de Centros");
        confirmar.setHeaderText("Se dispone a eliminar la tabla Centros de las Base de Datos."
                + "\nEsta información que contiene la tabla no se podrá recuparar."
                + "\n\nSeleccione ACEPTAR si desea Eliminar o"
                + "\nseleccione CANCELAR para salir.");
        confirmar.setContentText("");
        Optional<ButtonType> result = confirmar.showAndWait();
        if (result.get() == ButtonType.OK) {
            if(Eliminar.eliminarTabla(conexion, "tbl_centro")){
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Tabla Eliminada");
                mensaje.setHeaderText("Se ha eliminado la tabla que contenía la información de los Centros.");
                mensaje.setContentText("");
                mensaje.showAndWait();
            }
        }
    }
    
    @FXML
    public void eliminarTablaResultadosFinales(){
        Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
        confirmar.setTitle("Eliminar tabla de Resultados Finales");
        confirmar.setHeaderText("Se dispone a eliminar la tabla Resultados Finales de las Base de Datos."
                + "\nEsta información que contiene la tabla no se podrá recuparar."
                + "\n\nSeleccione ACEPTAR si desea Eliminar o"
                + "\nseleccione CANCELAR para salir.");
        confirmar.setContentText("");
        Optional<ButtonType> result = confirmar.showAndWait();
        if (result.get() == ButtonType.OK) {
            if(Eliminar.eliminarTabla(conexion, "tbl_resultado_final")){
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Tabla Eliminada");
                mensaje.setHeaderText("Se ha eliminado la tabla que contenía la información de los Resultados Finales.");
                mensaje.setContentText("");
                mensaje.showAndWait();
            }
        }
    }
    
    @FXML
    public void eliminarTablaTasasReprobados(){
        Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
        confirmar.setTitle("Eliminar tabla de Reprobados");
        confirmar.setHeaderText("Se dispone a eliminar la tabla de Reprobados de las Base de Datos."
                + "\nEsta información que contiene la tabla no se podrá recuparar."
                + "\n\nSeleccione ACEPTAR si desea Eliminar o"
                + "\nseleccione CANCELAR para salir.");
        confirmar.setContentText("");
        Optional<ButtonType> result = confirmar.showAndWait();
        if (result.get() == ButtonType.OK) {
            if(Eliminar.eliminarTabla(conexion, "tbl_reprobados")){
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Tabla Eliminada");
                mensaje.setHeaderText("Se ha eliminado la tabla que contenía la información de los Reprobados.");
                mensaje.setContentText("");
                mensaje.showAndWait();
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
