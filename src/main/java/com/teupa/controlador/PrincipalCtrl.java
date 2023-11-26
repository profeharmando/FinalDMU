/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teupa.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.teupa.basedatos.Verificar;
import com.teupa.basedatos.ConexionMySql;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javafx.scene.control.Label;

/**
 *
 * @author hector
 */
public class PrincipalCtrl implements Initializable{

    @FXML public Label lblEstado;
    @FXML private Button btnImportarCentros;
    @FXML private Button btnCentro;
    @FXML private Button btnIngresarResultadosFinales;
    @FXML private Button btnConsolidadoResultadosFinales;
    @FXML private Button btnLlenosPendientes;
    @FXML private Button btnConfigurarBD;
    @FXML private Button btnSalir;
    
    Verificar verificar = new Verificar();
    
    ConexionMySql conn = new ConexionMySql();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("\nEl user.dir es: " + System.getProperty("user.dir"));
        System.out.println("\nEl user.home es: " + System.getProperty("user.home"));
        conn.establecerConexion();
        if(conn.hayConexion()){
            System.out.println("\n\tVerificando si existen las tablas del sistema en la BD");
            verificar.verificarExisteTablaCentro(conn.establecerConexion());
            verificar.verificarExisteTablaResultadoFinal(conn.establecerConexion());
            verificar.verificarExisteTablaResultadoFinalCeros(conn.establecerConexion());
            lblEstado.setText("Está conectado al servidor de Base de Datos :)    Su IP local es " + optenerIP());
            lblEstado.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
        }else{
            System.out.println("\nNo está conectado a la Base de Datos  :(");
            lblEstado.setText("NO está conectado a ningún servidor de Base de Datos  :(");
            lblEstado.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
        }
    }
    
    private String optenerIP(){
        String ip = null;
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
            socket.close();
        }catch(Exception ex){
            System.err.println("\nError al tratar de optener la IP");
        }
        return ip;
    }
    ////////////////PESTAÑA INGRESAR DATOS************************
    
    @FXML
    private void ingresarResultadoFinal(ActionEvent event) {
        String formulario = "/com/teupa/vista/IngresarResultadoFinal.fxml";
        try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(formulario));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene( new Scene((Pane) loader.load()));
            IngresarResultadoFinalCtrl controlador =  loader.<IngresarResultadoFinalCtrl>getController();
            controlador.inicializarDatos();
            Image icon = new Image(getClass().getResourceAsStream("/com/teupa/imagenes/icono.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Ingresar Resultado Final");
            stage.initModality(Modality.APPLICATION_MODAL);
            System.out.println("\n\nMostrado formulario: " + formulario);
            stage.show();
            System.out.println("\n\t\t...iniciado Ingresar Resultado Final");
        } catch (IOException e) {
            System.err.println("Error al intentar mostrar la ventana " + formulario + ": \n" + e.getMessage());
        }
    }
    
    
    @FXML
    private void reprobadosEspañolMatematicas(ActionEvent event) {
        String formulario = "/com/teupa/vista/ReprobadosEspanolMatematicas.fxml";
        try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(formulario));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene( new Scene((Pane) loader.load()));
            ReprobadosEspañolMatematicasCtrl controlador =  loader.<ReprobadosEspañolMatematicasCtrl>getController();
            controlador.inicializarDatos();
            Image icon = new Image(getClass().getResourceAsStream("/com/teupa/imagenes/icono.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Reprobados en Español y Matemáticas");
            stage.initModality(Modality.APPLICATION_MODAL);
            System.out.println("\n\nMostrado formulario: " + formulario);
            stage.show();
            System.out.println("\n\t\t...iniciado Reprobados en Español y Matemáticas");
        } catch (IOException e) {
            System.err.println("Error al intentar mostrar la ventana " + formulario + ": \n" + e.getMessage());
        }
    }
    
    ////////////////PESTAÑA VER INFORMACIÓN************************
    
    @FXML
    private void consolidadoResultadoFinal(ActionEvent event) {
        String formulario = "/com/teupa/vista/ConsolidadoResultadoFinal.fxml";
        try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(formulario));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene( new Scene((Pane) loader.load()));
            ConsolidadoResultadoFinalCtrl controlador =  loader.<ConsolidadoResultadoFinalCtrl>getController();
            controlador.inicializarDatos();
            Image icon = new Image(getClass().getResourceAsStream("/com/teupa/imagenes/icono.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Consolidado Resultado Final");
            stage.initModality(Modality.APPLICATION_MODAL);
            System.out.println("\n\nMostrado formulario: " + formulario);
            stage.show();
            System.out.println("\n\t\t...iniciado Consolidado Resultado Final");
        } catch (IOException e) {
            System.err.println("Error al intentar mostrar la ventana " + formulario + ": \n" + e.getMessage());
        }
    }
    
    @FXML
    private void consolidadoReprobadosEspMat(ActionEvent event) {
        String formulario = "/com/teupa/vista/ConsolidadoReprobadosEspMat.fxml";
        try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(formulario));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene( new Scene((Pane) loader.load()));
            ConsolidadoReprobadosEspMatCtrl controlador =  loader.<ConsolidadoReprobadosEspMatCtrl>getController();
            controlador.inicializarDatos();
            Image icon = new Image(getClass().getResourceAsStream("/com/teupa/imagenes/icono.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Consolidado Reprobados Español y Matemáticas");
            stage.initModality(Modality.APPLICATION_MODAL);
            System.out.println("\n\nMostrado formulario: " + formulario);
            stage.show();
            System.out.println("\n\t\t...iniciado Consolidado Reprobados Español y Matemáticas");
        } catch (IOException e) {
            System.err.println("Error al intentar mostrar la ventana " + formulario + ": \n" + e.getMessage());
        }
    }
    
    @FXML
    private void progreso(ActionEvent event) {
        String formulario = "/com/teupa/vista/Progreso.fxml";
        try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(formulario));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene( new Scene((Pane) loader.load()));
            ProgresoCtrl controlador =  loader.<ProgresoCtrl>getController();
            controlador.inicializarDatos();
            Image icon = new Image(getClass().getResourceAsStream("/com/teupa/imagenes/icono.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Progreso de Centros");
            stage.initModality(Modality.APPLICATION_MODAL);
            System.out.println("\n\nMostrado formulario: " + formulario);
            stage.show();
            System.out.println("\n\t\t...iniciado Progreso de Centros");
        } catch (IOException e) {
            System.err.println("Error al intentar mostrar la ventana " + formulario + ": \n" + e.getMessage());
        }
    }
    
    ////////////////PESTAÑA CONFIGURAR************************
    
    @FXML
    private void importarCentros(ActionEvent event) {
        String formulario = "/com/teupa/vista/ImportarCentro.fxml";
        try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(formulario));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene( new Scene((Pane) loader.load()));
            ImportarCentroCtrl controlador =  loader.<ImportarCentroCtrl>getController();
            controlador.inicializarDatos();
            Image icon = new Image(getClass().getResourceAsStream("/com/teupa/imagenes/icono.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Importar Centros");
            stage.initModality(Modality.APPLICATION_MODAL);
            System.out.println("\n\nMostrado formulario: " + formulario);
            stage.show();
            System.out.println("\n\t\t...iniciado Importar Centros");
        } catch (IOException e) {
            System.err.println("Error al intentar mostrar la ventana " + formulario + ": \n" + e.getMessage());
        }
    }
    
    @FXML
    private void centro(ActionEvent event) {
        String formulario = "/com/teupa/vista/Centro.fxml";
        try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(formulario));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene( new Scene((Pane) loader.load()));
            CentroCtrl controlador =  loader.<CentroCtrl>getController();
            controlador.inicializarDatos();
            Image icon = new Image(getClass().getResourceAsStream("/com/teupa/imagenes/icono.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Agregar Centro");
            stage.initModality(Modality.APPLICATION_MODAL);
            System.out.println("\n\nMostrado formulario: " + formulario);
            stage.show();
            System.out.println("\n\t\t...iniciado Agregarar Centros");
        } catch (IOException e) {
            System.err.println("Error al intentar mostrar la ventana " + formulario + ": \n" + e.getMessage());
        }
    }
    
    @FXML
    private void configurarBD(ActionEvent event) {
        String formulario = "/com/teupa/vista/ConfigurarBD.fxml";
        try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(formulario));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene( new Scene((Pane) loader.load()));
            ConfigurarBDCtrl controlador =  loader.<ConfigurarBDCtrl>getController();
            controlador.inicializarDatos(this);
            Image icon = new Image(getClass().getResourceAsStream("/com/teupa/imagenes/icono.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Configurar conexión al servidor de Base de Datos");
            stage.initModality(Modality.APPLICATION_MODAL);
            System.out.println("\n\nMostrado formulario: " + formulario);
            stage.show();
            System.out.println("\n\t\t...iniciado Configurar BD");
        } catch (IOException e) {
            System.err.println("Error al intentar mostrar la ventana " + formulario + ": \n" + e.getMessage());
        }
    }
    
    @FXML
    private void configurar(ActionEvent event) {
        String formulario = "/com/teupa/vista/Configurar.fxml";
        try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(formulario));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene( new Scene((Pane) loader.load()));
            ConfigurarCtrl controlador =  loader.<ConfigurarCtrl>getController();
            controlador.inicializarDatos(this);
            Image icon = new Image(getClass().getResourceAsStream("/com/teupa/imagenes/icono.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Configurar cosas de la aplicación");
            stage.initModality(Modality.APPLICATION_MODAL);
            System.out.println("\n\nMostrado formulario: " + formulario);
            stage.show();
            System.out.println("\n\t\t...iniciado Configurar aplicación");
        } catch (IOException e) {
            System.err.println("Error al intentar mostrar la ventana " + formulario + ": \n" + e.getMessage());
        }
    }
    
    ////////////////////////BOTONES*******************************
    @FXML
    private void acercade(){
       System.out.println("\n\t\t...iniciando ACercaDe.fxml");
        String formulario = "/com/teupa/vista/ACercaDe.fxml";
        try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(formulario));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene( new Scene((Pane) loader.load()));
            ACercaDeCtrl controlador =  loader.<ACercaDeCtrl>getController();
            //controlador.inicializarDatos();
            Image icon = new Image(getClass().getResourceAsStream("/com/teupa/imagenes/icono.png"));
            stage.getIcons().add(icon);
            stage.setTitle("A cerca de esto");
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            System.out.println("Mostrado formulario: " + formulario);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error al intentar mostrar la ventana " + formulario + ": \n" + e.getMessage());
        } 
    }
    
    @FXML
    private void salir(){
        System.out.println("\n\t\tSalir de ventana principal.\n");
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
}
