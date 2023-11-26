/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.teupa.controlador;

import com.teupa.basedatos.ConexionMySql;
import com.teupa.basedatos.Seleccionar;
import com.teupa.modelo.BuscarCentro;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class BuscarCentroCtrl implements Initializable {

    //Conexion a la BD
    ConexionMySql conn = new ConexionMySql();
    Connection conexion = conn.establecerConexion();

    //Elementos GUI
    @FXML private TextField txtCodigoSace;
    @FXML private Button btnBuscar;
    @FXML private TableView<BuscarCentro> tblCentro;
    @FXML private TableColumn<BuscarCentro, String> clmCentro;
    @FXML private TableColumn<BuscarCentro, String> clmCodigoSace;
    @FXML private TableColumn<BuscarCentro, String> clmAdministracion;
    @FXML private TableColumn<BuscarCentro, String> clmZona;
    @FXML private TableColumn<BuscarCentro, String> clmNivel;
    @FXML private TableColumn<BuscarCentro, String> clmCiclo;
    @FXML private TableColumn<BuscarCentro, String> clmJornada;
    @FXML private TableColumn<BuscarCentro, String> clmCobertura;
    @FXML private TableColumn<BuscarCentro, Integer> clmMatriculaFinal;
    @FXML private Button btnAceptar;
    @FXML private Button btnCancelar;
    @FXML private Button btnSalir;
    
    //Variables para obtener los datos del centro seleccionado en la tabla
    public int codigo;
    public String nombre_centro;
    public String codigo_sace;
    public String administracion_centro;
    public String zona_centro;
    public String nivel_centro;
    public String ciclo_centro;
    public String jornada_centro;
    public String cobertura_centro;
    
    //Para tener una copia de IngresarResultadoFinal
    IngresarResultadoFinalCtrl ingresarResultadoFinal;
    EliminarInformacionCtrl eliminarInformacion;
    ReprobadosEspañolMatematicasCtrl reprobados;

    //ObservableList para los llenar el TableView
    private ObservableList<BuscarCentro> lista_centros = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnBuscar.setDisable(true);
        clmCentro.setCellValueFactory(dato -> dato.getValue().CentroProperty());
        clmCodigoSace.setCellValueFactory(dato -> dato.getValue().CodigoSaceProperty());
        clmAdministracion.setCellValueFactory(dato -> dato.getValue().AdministracionProperty());
        clmZona.setCellValueFactory(dato -> dato.getValue().AreaProperty());
        clmNivel.setCellValueFactory(dato -> dato.getValue().NivelProperty());
        clmCiclo.setCellValueFactory(dato -> dato.getValue().CicloProperty());
        clmJornada.setCellValueFactory(dato -> dato.getValue().JornadaProperty());
        clmCobertura.setCellValueFactory(dato -> dato.getValue().CoberturaProperty());
        clmMatriculaFinal.setCellValueFactory(dato -> dato.getValue().MatriculaProperty().asObject());
        gestionarEventos();
        Platform.runLater(() -> txtCodigoSace.requestFocus());
    }
    
    public void inicializarDatos(IngresarResultadoFinalCtrl controlador_1, EliminarInformacionCtrl controlador_2, ReprobadosEspañolMatematicasCtrl controlador_3){
        ingresarResultadoFinal = controlador_1;
        eliminarInformacion = controlador_2;
        reprobados = controlador_3;
        if(ingresarResultadoFinal != null){
            Seleccionar.obtenerDatosCentro(conexion, lista_centros);
            System.out.println("\nSe muestran datos de todos los centros");
        }
        if(eliminarInformacion != null){
            if(eliminarInformacion.eliminar_resultado){
                Seleccionar.obtenerDatosCentrosResultadoFinalLlenos(conexion, lista_centros);
                System.out.println("\nSe muestran datos de centro llenos");
            }
            if(eliminarInformacion.eliminar_centro){
                Seleccionar.obtenerDatosCentro(conexion, lista_centros);
                System.out.println("\nSe muestran datos de centro llenos");
            }
        }
        if(reprobados != null){
            Seleccionar.obtenerDatosCentrosConMatriculaInicialFinal(conexion, lista_centros);
            System.out.println("\nSe muestran datos de Reprobados de todos los centros");
        }
        tblCentro.setItems(lista_centros);
    }
    
    public void gestionarEventos() {
        //Ocurre cada vez que se selecciona un item del TableView
        tblCentro.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BuscarCentro>() {
            @Override
            public void changed(ObservableValue<? extends BuscarCentro> ov, BuscarCentro valorAnterior, BuscarCentro valorSeleccionado) {
                if (valorSeleccionado != null) {
                    codigo = valorSeleccionado.getCodigo();
                    nombre_centro = valorSeleccionado.getCentro();
                    codigo_sace = valorSeleccionado.getCodigoSace();
                    administracion_centro = valorSeleccionado.getAdministracion();
                    zona_centro = valorSeleccionado.getArea();
                    nivel_centro = valorSeleccionado.getNivel();
                    ciclo_centro = valorSeleccionado.getCiclo();
                    jornada_centro = valorSeleccionado.getJornada();
                    cobertura_centro = valorSeleccionado.getCobertura();
                }
            }
        });
        txtCodigoSace.textProperty().addListener((obs, oldText, newText) -> {
            if(txtCodigoSace.getText().length() > 6){
                //btnBuscar.setDisable(false);
                lista_centros.clear();
                Seleccionar.obtenerDatosCentroCodigo(conexion, lista_centros, txtCodigoSace.getText());
            }
            if(txtCodigoSace.getText().length() == 0){
                //btnBuscar.setDisable(false);
                lista_centros.clear();
                Seleccionar.obtenerDatosCentro(conexion, lista_centros);
            }
        });
    }
    
    @FXML private void buscarCentro(){
        lista_centros.clear();
        Seleccionar.obtenerDatosCentroCodigo(conexion, lista_centros, txtCodigoSace.getText());
    }
    
    @FXML private void aceptar(){
        //Mandar datos al formulario que solicita
        if(ingresarResultadoFinal != null){
            System.out.println("Mandando datos a formulario\n");
            System.out.println("\nCódigo> " + codigo + "\nCentro> " + nombre_centro + "\nCodigo SACE> " + codigo_sace + "\nAdministracion> " + administracion_centro + "\nArea> " + zona_centro + "\nNivel> " + nivel_centro + "\nCiclo> " + ciclo_centro + "\nJornada> " + jornada_centro + "\nCobertura> " + cobertura_centro + "\n");
            ingresarResultadoFinal.codigo = this.codigo;
            ingresarResultadoFinal.nombre_centro = this.nombre_centro;
            ingresarResultadoFinal.codigo_sace = this.codigo_sace;
            ingresarResultadoFinal.administracion_centro = this.administracion_centro;
            ingresarResultadoFinal.zona_centro = this.zona_centro;
            ingresarResultadoFinal.nivel_centro = this.nivel_centro;
            ingresarResultadoFinal.ciclo_centro = this.ciclo_centro;
            ingresarResultadoFinal.jornada_centro = this.jornada_centro;
            ingresarResultadoFinal.cobertura_centro = this.cobertura_centro;
            ingresarResultadoFinal.txtCentro.setText(nombre_centro + " (" + codigo_sace + ")  Nivel: " + nivel_centro + "  Ciclo: " + ciclo_centro + "  -" + cobertura_centro);
            ingresarResultadoFinal.tblResultadoFinal.setDisable(false);            
            Stage stage = (Stage) btnAceptar.getScene().getWindow();
            stage.close();
        }        
        if(eliminarInformacion != null){
            System.out.println("Mandando datos a formulario\n");
            System.out.println("\nCódigo> " + codigo + "\nCentro> " + nombre_centro + "\nCodigo SACE> " + codigo_sace + "\n");
            eliminarInformacion.codigo = this.codigo;
            eliminarInformacion.nombre_centro = this.nombre_centro;
            eliminarInformacion.codigo_sace = this.codigo_sace;
            Stage stage = (Stage) btnAceptar.getScene().getWindow();
            stage.close();
        }
        if(reprobados != null){
            System.out.println("Mandando datos a formulario\n");
            System.out.println("\nCódigo> " + codigo + "\nCentro> " + nombre_centro + "\nCodigo SACE> " + codigo_sace + "\nAdministracion> " + administracion_centro + "\nArea> " + zona_centro + "\nNivel> " + nivel_centro + "\nCiclo> " + ciclo_centro + "\nJornada> " + jornada_centro + "\nCobertura> " + cobertura_centro + "\n");
            reprobados.codigo = this.codigo;
            reprobados.nombre_centro = this.nombre_centro;
            reprobados.codigo_sace = this.codigo_sace;
            reprobados.administracion_centro = this.administracion_centro;
            reprobados.zona_centro = this.zona_centro;
            reprobados.nivel_centro = this.nivel_centro;
            reprobados.ciclo_centro = this.ciclo_centro;
            reprobados.jornada_centro = this.jornada_centro;
            reprobados.cobertura_centro = this.cobertura_centro;
            reprobados.txtCentro.setText(nombre_centro + " (" + codigo_sace + ")  Nivel: " + nivel_centro + "  Ciclo: " + ciclo_centro + "  -" + cobertura_centro);
            reprobados.tblReprobados.setDisable(false);
            reprobados.obtieneCentro(this.codigo, this.nombre_centro, this.codigo_sace, this.administracion_centro, this.zona_centro, this.nivel_centro, this.ciclo_centro, this.jornada_centro, this.cobertura_centro);
            Stage stage = (Stage) btnAceptar.getScene().getWindow();
            stage.close();
        }
    }
    
    @FXML private void cancelar(){
        txtCodigoSace.clear();
        lista_centros.clear();
        Seleccionar.obtenerDatosCentro(conexion, lista_centros);
        tblCentro.setItems(lista_centros);
        btnAceptar.setDisable(true);
        btnCancelar.setDisable(true);
        btnBuscar.requestFocus();
    }
    
    @FXML
    private void salir(){
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
}
