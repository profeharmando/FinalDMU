/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.teupa.controlador;

import com.teupa.basedatos.ConexionMySql;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hector
 */
public class ConfigurarBDCtrl extends Thread implements Initializable {

    @FXML private TextField txtServidor;
    @FXML private TextField txtUsuario;
    @FXML private TextField txtContraseña;
    @FXML private Label lblMensaje;
    @FXML private Button btnConectar;
    @FXML private Button btnCancelar;
    @FXML private Button btnSalir;
    
    Properties propiedades = new Properties();
    ConexionMySql conexion = new ConexionMySql();
    PrincipalCtrl principal;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obtenerDatosConexion();
        gestionarEventos();
        Platform.runLater(() -> btnSalir.requestFocus());
    }
    
    private void gestionarEventos(){
        //Ocurre cuando se edita el TextField
        txtServidor.textProperty().addListener((obs, oldText, newText) -> {
            if(datosLlenos()){
                btnConectar.setDisable(false);
                btnCancelar.setDisable(false);
            }else{
                btnConectar.setDisable(true);
                btnCancelar.setDisable(true);
            }
        });
        txtUsuario.textProperty().addListener((obs, oldText, newText) -> {
            if(datosLlenos()){
                btnConectar.setDisable(false);
                btnCancelar.setDisable(false);
            }else{
                btnConectar.setDisable(true);
                btnCancelar.setDisable(true);
            }
        });
        txtContraseña.textProperty().addListener((obs, oldText, newText) -> {
            if(datosLlenos()){
                btnConectar.setDisable(false);
                btnCancelar.setDisable(false);
            }else{
                btnConectar.setDisable(true);
                btnCancelar.setDisable(true);
            }
        });
    }
    
    private boolean datosLlenos(){
        if(txtServidor.getText().isEmpty()){
            return false;
        }
        if(txtUsuario.getText().isEmpty()){
            return false;
        }
        if(txtContraseña.getText().isEmpty()){
            return false;
        }
        return true;
    }
    
    private void obtenerDatosConexion(){        
        InputStream archivo = null;
        try {
            archivo = new FileInputStream("configuracion.properties");
            propiedades.load(archivo);
            txtServidor.setText(propiedades.getProperty("servidor"));
            txtUsuario.setText(propiedades.getProperty("usuario"));
            txtContraseña.setText(propiedades.getProperty("password"));
        } catch (FileNotFoundException ex) {
            System.err.println("\nNo se encontró el archivo de configuración.\n"
                    + "Error: " + ex.getMessage());
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public void inicializarDatos(PrincipalCtrl p_principal){
        this.principal = p_principal;
        lblMensaje.setText("");
        btnConectar.setDisable(true);                
        if(conexion.establecerConexion() != null){
            lblMensaje.setText("Estado: conectado  :)");
            lblMensaje.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
            btnCancelar.setDisable(false);
        }else{
            lblMensaje.setText("Estado: desconectado  :(");
            lblMensaje.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
            System.err.println("\nNo se pudo conectar con la BD");
            btnConectar.setDisable(false);
            btnCancelar.setDisable(false);
        }
    }
    
    @FXML
    private void conectar() throws InterruptedException{
        lblMensaje.setText("...espere");
        lblMensaje.setStyle("-fx-text-fill: blue; -fx-font-size: 16px;");
        InputStream archivo = null;
        try {
            archivo = new FileInputStream("configuracion.properties");
            propiedades.load(archivo);
            propiedades.setProperty("servidor", txtServidor.getText());
            propiedades.setProperty("usuario", txtUsuario.getText());
            propiedades.setProperty("password", txtContraseña.getText());
            propiedades.store(new FileWriter("configuracion.properties"), "Datos de conexion a BD");            
            if((propiedades.getProperty("servidor") != null) || (propiedades.getProperty("usuario") != null) || (propiedades.getProperty("password") != null)){
                conexion.establecerConexion();
            }
            if(conexion.hayConexion()){
                lblMensaje.setText("Estado: conectado  :)");
                lblMensaje.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
                principal.lblEstado.setText("Está conectado al servidor de Base de Datos  :)");
                principal.lblEstado.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
            }else{
                System.out.println("\nNo está conectado al servidor de Base de Datos.\n");
                lblMensaje.setText("Estado: desconectado  :(");
                lblMensaje.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                principal.lblEstado.setText("NO está conectado a ningún servidor de Base de Datos  :(");
            }
        } catch (FileNotFoundException ex) {
            System.err.println("\nNo se encontró el archivo de configuración.\n"
                    + "Error: " + ex.getMessage());
        } catch(IOException ex){
            System.err.println("Error de IO tratando de leer el archivo de configuración");
        }
    }
    
    @FXML
    private void cancelar(){
        txtServidor.setText("");
        txtUsuario.setText("");
        txtContraseña.setText("");
        lblMensaje.setText("");
        btnConectar.setDisable(true);
        btnCancelar.setDisable(true);
    }
    
    @FXML
    private void salir(){
        System.out.println("\n\t\tSalir de ventana ConfigurarBD.\n");
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
}
