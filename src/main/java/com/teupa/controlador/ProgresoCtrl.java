/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.teupa.controlador;

import com.teupa.basedatos.ConexionMySql;
import com.teupa.basedatos.Seleccionar;
import com.teupa.modelo.Progreso;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hector
 */
public class ProgresoCtrl implements Initializable {
   
    @FXML private Button btnSalir;
    @FXML private ComboBox<String> cmbEstadoCentros;
    @FXML private Label lblResultado;
    @FXML private ComboBox<String> cmbAdministracion;
    @FXML private ComboBox<String> cmbZona;
    @FXML private ComboBox<String> cmbNivel;
    @FXML private ComboBox<String> cmbCiclo;
    @FXML private ComboBox<String> cmbJornada;
    @FXML private ComboBox<String> cmbCobertura;
    @FXML private ComboBox<String> cmbResultado;
    @FXML private Button btnMostrarCentros;
    
    @FXML private TableView <Progreso> tblCentro;
    @FXML private TableColumn <Progreso, String> clmCentro;
    @FXML private TableColumn <Progreso, String> clmCodigoCentro;
    @FXML private TableColumn <Progreso, String> clmAdministracion;
    @FXML private TableColumn <Progreso, String> clmArea;
    @FXML private TableColumn <Progreso, String> clmNivel;
    @FXML private TableColumn <Progreso, Integer> clmMatricula;
    @FXML private TableColumn <Progreso, String> clmEstado;
    
    //Conexion a la BD
    ConexionMySql conn = new ConexionMySql();
    Connection conexion = conn.establecerConexion();
   
    //Listas de listas para Combobox
    ObservableList<String> lista_estado = FXCollections.observableArrayList("INGRESADOS", "PENDIENTES");
    ObservableList<String> lista_administracion = FXCollections.observableArrayList("GUBERNAMENTAL", "PRIVADO");
    ObservableList<String> lista_zona = FXCollections.observableArrayList("URBANA", "RURAL");
    ObservableList<String> lista_nivel = FXCollections.observableArrayList("BÁSICA", "PREBÁSICA", "MEDIA");
    ObservableList<String> lista_ciclo = FXCollections.observableArrayList();
    ObservableList<String> lista_jornada = FXCollections.observableArrayList("MATUTINA", "VESPERTINA", "NOCTURNA", "MATUTINA Y VESPERTINA", "TRES JORNADAS", "DISTANCIA");
    ObservableList<String> lista_cobertura = FXCollections.observableArrayList("OFICIAL", "PROHECO", "ALCALDÍA", "OTRO");
    ObservableList<String> lista_resultado = FXCollections.observableArrayList("RESULTADO FINAL", "TASA REPROBADOS ESP Y MAT");
    
    //Lista para la tabla tblCentro
    private ObservableList<Progreso> lista_centros = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbEstadoCentros.setItems(lista_estado);
        cmbAdministracion.setItems(lista_administracion);
        cmbZona.setItems(lista_zona);
        cmbNivel.setItems(lista_nivel);
        cmbCiclo.setItems(lista_ciclo);
        cmbJornada.setItems(lista_jornada);
        cmbCobertura.setItems(lista_cobertura);
        cmbResultado.setItems(lista_resultado);
        clmCentro.setCellValueFactory(dato -> dato.getValue().CentroProperty());
        clmCodigoCentro.setCellValueFactory(dato -> dato.getValue().CodigoCentroProperty());
        clmAdministracion.setCellValueFactory(dato -> dato.getValue().AdministracionProperty());
        clmArea.setCellValueFactory(dato -> dato.getValue().AreaProperty());
        clmNivel.setCellValueFactory(dato -> dato.getValue().NivelProperty());
        clmMatricula.setCellValueFactory(dato -> dato.getValue().MatriculaFinalProperty().asObject());
        clmEstado.setCellValueFactory(dato -> dato.getValue().EstadoProperty());
        lblResultado.setText("");
        if(Seleccionar.hayCentros(conexion)){
            tblCentro.setPlaceholder(new Label("Seleccione las opciones de centros que desea y seleccione Mostrar Costrar"));
        }else{
            tblCentro.setPlaceholder(new javafx.scene.control.Label("Aún no hay centros registrados para mostrar"));
            tblCentro.setDisable(false);
        }
        cmbAdministracion.setDisable(false);
        cmbZona.setDisable(true);
        cmbNivel.setDisable(true);
        cmbCiclo.setDisable(true);
        cmbJornada.setDisable(true);
        cmbCobertura.setDisable(true);
        cmbEstadoCentros.setDisable(true);
        cmbResultado.setDisable(true);
        btnMostrarCentros.setDisable(true);
        btnSalir.setDisable(false);
        Platform.runLater(() -> cmbAdministracion.requestFocus());
    }
    
    public void inicializarDatos(){
        
    }
    
    @FXML
    private void cambiaNivel(){
        habilitarCiclo();
        System.out.println("\nSe eligio = " + cmbNivel.getValue());
        if(cmbNivel.getValue().equals("BÁSICA")){
            ObservableList<String> lista_ciclos = FXCollections.observableArrayList("I Y II CICLO", "I, II Y III CICLO");
            cmbCiclo.setItems(lista_ciclos);
        }
        if(cmbNivel.getValue().equals("PREBÁSICA")){
            ObservableList<String> lista_ciclos = FXCollections.observableArrayList("NO APLICA");
            cmbCiclo.setItems(lista_ciclos);
        }
        if(cmbNivel.getValue().equals("MEDIA")){
            ObservableList<String> lista_ciclos = FXCollections.observableArrayList( "BTP", "BCH", "III CICLO Y BTP", "CBT, BTP Y BCH");
            cmbCiclo.setItems(lista_ciclos);
        }        
    }
    
    @FXML private void habilitaZona(){
        cmbZona.setDisable(false);
        lista_centros.clear();
    }
    
    @FXML private void habilitaNivel(){        
        cmbNivel.setDisable(false);
        lista_centros.clear();
    }
    
    private void habilitarCiclo(){
        cmbCiclo.setDisable(false);
        lista_centros.clear();
    }
    
    @FXML private void habilitarJornada(){
        cmbJornada.setDisable(false);
        lista_centros.clear();
    }
    
    @FXML private void habilitarCobertura(){
        cmbCobertura.setDisable(false);
        lista_centros.clear();
    }
    
    @FXML private void activarEstadoCentros(){
        cmbEstadoCentros.setDisable(false);
        lista_centros.clear();
    }
    
    @FXML private void habilitarMostrarResultado(){
        cmbResultado.setDisable(false);
        lista_centros.clear();
    }
    
    @FXML private void habilitarMostrarCentros(){
        btnMostrarCentros.setDisable(false);
        lista_centros.clear();
    }
    
    @FXML private void mostrarCentros(){
        int centros_llenos = 0;
        int centros_pendientes = 0;
        int matricula = 0;        
        String administracion = cmbAdministracion.getValue();
        String zona = cmbZona.getValue();
        String nivel = cmbNivel.getValue();
        String ciclo = cmbCiclo.getValue();
        String jornada = cmbJornada.getValue();
        String cobertura = cmbCobertura.getValue();
        int total_centros = Seleccionar.obtenerTotalCentrosTipo(conexion, administracion, zona, nivel, ciclo, jornada, cobertura);
        if(cmbResultado.getValue().equals("RESULTADO FINAL")){
            if(cmbEstadoCentros.getValue().equals("INGRESADOS")){
                lista_centros.clear();
                Seleccionar.obtenerDatosCentrosLlenosTipo(conexion, lista_centros, administracion, zona, nivel, ciclo, jornada, cobertura);            
                if(lista_centros.size() > 0){
                    tblCentro.setItems(lista_centros);
                    for (Progreso lst : lista_centros) {
                      matricula = matricula + lst.getMatriculaFinal();
                    }
                    centros_llenos = lista_centros.size();
                }else{
                    centros_llenos = 0;
                    tblCentro.setPlaceholder(new Label("Aun no hay centros ingresados en el sistema"));
                }
                tblCentro.setDisable(false);
                lblResultado.setText("Ingresados " + centros_llenos + " de " + total_centros + " centros \t\tMatrícula " + matricula);
            }
            if(cmbEstadoCentros.getValue().equals("PENDIENTES")){
                lista_centros.clear();
                Seleccionar.obtenerDatosCentrosEstadoPendientes(conexion, lista_centros, administracion, zona, nivel, ciclo, jornada, cobertura);            
                if(lista_centros.size() > 0){
                    tblCentro.setItems(lista_centros);
                    System.out.println("\nCentros Pendientes> " + lista_centros.size());
                    for (Progreso lst : lista_centros) {
                      matricula = matricula + lst.getMatriculaFinal();
                    }
                    centros_pendientes = lista_centros.size();
                }else{
                    centros_pendientes = 0;
                    tblCentro.setPlaceholder(new Label("No hay centros para los criterios elegidos."));
                }
                tblCentro.setDisable(false);
                lblResultado.setText("Pendientes " + centros_pendientes + " de " + total_centros + " centros \t\tMatrícula " + matricula);
            }
        }
        if(cmbResultado.getValue().equals("TASA REPROBADOS ESP Y MAT")){
            if(cmbEstadoCentros.getValue().equals("INGRESADOS")){
                lista_centros.clear();
                Seleccionar.obtenerDatosTasaReprobadosLlenosTipo(conexion, lista_centros, administracion, zona, nivel, ciclo, jornada, cobertura);            
                if(lista_centros.size() > 0){
                    tblCentro.setItems(lista_centros);
                    for (Progreso lst : lista_centros) {
                      matricula = matricula + lst.getMatriculaFinal();
                    }
                    centros_llenos = lista_centros.size();
                }else{
                    centros_llenos = 0;
                    tblCentro.setPlaceholder(new Label("Aun no hay centros ingresados en el sistema"));
                }
                tblCentro.setDisable(false);
                lblResultado.setText("Ingresados " + centros_llenos + " de " + total_centros + " centros \t\tMatrícula " + matricula);
            }
            if(cmbEstadoCentros.getValue().equals("PENDIENTES")){
                lista_centros.clear();
                Seleccionar.obtenerDatosTasaReprobadosPendientes(conexion, lista_centros, administracion, zona, nivel, ciclo, jornada, cobertura);            
                if(lista_centros.size() > 0){
                    tblCentro.setItems(lista_centros);
                    System.out.println("\nCentros Pendientes> " + lista_centros.size());
                    for (Progreso lst : lista_centros) {
                      matricula = matricula + lst.getMatriculaFinal();
                    }
                    centros_pendientes = lista_centros.size();
                }else{
                    centros_pendientes = 0;
                    tblCentro.setPlaceholder(new Label("No hay centros para los criterios elegidos."));
                }
                tblCentro.setDisable(false);
                lblResultado.setText("Pendientes " + centros_pendientes + " de " + total_centros + " centros \t\tMatrícula " + matricula);
            }
        }
    }
    
    private void gestionarEventos(){
        
    }
 
    
    @FXML
    private void salir(){
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
}
