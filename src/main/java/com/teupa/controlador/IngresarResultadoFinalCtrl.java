/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.teupa.controlador;

import com.teupa.basedatos.ConexionMySql;
import com.teupa.basedatos.Insertar;
import com.teupa.basedatos.Modificar;
import com.teupa.basedatos.Seleccionar;
import com.teupa.modelo.IngresarResultadoFinal;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author hector
 */
public class IngresarResultadoFinalCtrl implements Initializable {

    @FXML public TextField txtCentro;
    @FXML private Button btnBuscar;
    @FXML private Label lblTitulo;
    @FXML public TableView <IngresarResultadoFinal> tblResultadoFinal;
    @FXML private Label lblEstado;
    @FXML private Button btnNuevo;
    @FXML private Button btnModificar;
    @FXML private Button btnEliminar;
    @FXML private Button btnAceptar;
    @FXML private Button btnCancelar;
    @FXML private Button btnSalir;
    
    //Conexion a la BD
    ConexionMySql conn = new ConexionMySql();
    Connection conexion = conn.establecerConexion();
    
    //Variable para saber si se ha guardado los datos agregados al TableView
    boolean guardado = true;
    
    //Variables banderas para saber
    boolean nuevo;
    boolean modificar;
    private String mensaje_error_fila = "NINGUNO";
    
    //Se llenan desde el formulario BuscarCentro
    public int codigo;
    public String nombre_centro;
    public String codigo_sace;
    public String nivel_centro;
    public String administracion_centro;
    public String zona_centro;
    public String ciclo_centro;
    public String jornada_centro;
    public String cobertura_centro;
    
    //Lista para la tabla
    public ObservableList<IngresarResultadoFinal> lista_resultados_finales = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblTitulo.setText("");        
        btnNuevo.setDisable(true);
        lblEstado.setText("");
        tblResultadoFinal.setDisable(true);
        tblResultadoFinal.setEditable(true);
        // GRADO
        TableColumn<IngresarResultadoFinal, String> clmGrado = new TableColumn("GRADO");
	clmGrado.setMinWidth(120);
        clmGrado.setMaxWidth(120);
        clmGrado.setCellValueFactory(dato -> dato.getValue().GradoProperty());
        // MAT INICIAL
        TableColumn<IngresarResultadoFinal, Integer> clmMatInicialN = new TableColumn("N");
        clmMatInicialN.setMinWidth(35);
        clmMatInicialN.setMaxWidth(35);
        clmMatInicialN.setCellValueFactory(dato -> dato.getValue().MatIni_nProperty().asObject());
        clmMatInicialN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmMatInicialV = new TableColumn("V");
        clmMatInicialV.setMinWidth(35);
        clmMatInicialV.setMaxWidth(35);
        clmMatInicialV.setCellValueFactory(dato -> dato.getValue().MatIni_vProperty().asObject());
        clmMatInicialV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmMatInicialT = new TableColumn("T");
        clmMatInicialT.setMinWidth(45);
        clmMatInicialT.setMaxWidth(45);
        clmMatInicialT.setCellValueFactory(dato -> dato.getValue().MatIni_tProperty().asObject());
        TableColumn clmMatInicial = new TableColumn("MAT INICIAL");	
        clmMatInicial.setMinWidth(150);
        clmMatInicial.setMaxWidth(150);
        clmMatInicial.getColumns().addAll(clmMatInicialN, clmMatInicialV, clmMatInicialT);
        // MAT FINAL
        TableColumn<IngresarResultadoFinal, Integer> clmMatFinalN = new TableColumn("N");
        clmMatFinalN.setMinWidth(35);
        clmMatFinalN.setMaxWidth(35);
        clmMatFinalN.setCellValueFactory(dato -> dato.getValue().MatFin_nProperty().asObject());
        clmMatFinalN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmMatFinalV = new TableColumn("V");
        clmMatFinalV.setMinWidth(35);
        clmMatFinalV.setMaxWidth(35);
        clmMatFinalV.setCellValueFactory(dato -> dato.getValue().MatFin_vProperty().asObject());
        clmMatFinalV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmMatFinalT = new TableColumn("T");
        clmMatFinalT.setMinWidth(45);
        clmMatFinalT.setMaxWidth(45);
        clmMatFinalT.setCellValueFactory(dato -> dato.getValue().MatFin_tProperty().asObject());
        TableColumn clmMatFinal = new TableColumn("MAT FINAL");
        clmMatFinal.setMinWidth(150);
        clmMatFinal.setMaxWidth(150);
        clmMatFinal.getColumns().addAll(clmMatFinalN, clmMatFinalV, clmMatFinalT);
        // INGRESOS
        TableColumn<IngresarResultadoFinal, Integer> clmIngresoN = new TableColumn("N");
        clmIngresoN.setMinWidth(35);
        clmIngresoN.setMaxWidth(35);
        clmIngresoN.setCellValueFactory(dato -> dato.getValue().Ingre_nProperty().asObject());
        clmIngresoN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmIngresoV = new TableColumn("V");
        clmIngresoV.setMinWidth(35);
        clmIngresoV.setMaxWidth(35);
        clmIngresoV.setCellValueFactory(dato -> dato.getValue().Ingre_vProperty().asObject());
        clmIngresoV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmIngresoT = new TableColumn("T");
        clmIngresoT.setMinWidth(45);
        clmIngresoT.setMaxWidth(45);
        clmIngresoT.setCellValueFactory(dato -> dato.getValue().Ingre_tProperty().asObject());
        TableColumn clmIngresos = new TableColumn("INGRESOS");
	clmIngresos.setMinWidth(150);
        clmIngresos.setMaxWidth(150);
        clmIngresos.getColumns().addAll(clmIngresoN, clmIngresoV, clmIngresoT);
        // TRASLADOS
        TableColumn<IngresarResultadoFinal, Integer> clmTrasladoN = new TableColumn("N");
        clmTrasladoN.setMinWidth(35);
        clmTrasladoN.setMaxWidth(35);
        clmTrasladoN.setCellValueFactory(dato -> dato.getValue().Trasla_nProperty().asObject());
        clmTrasladoN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmTrasladoV = new TableColumn("V");
        clmTrasladoV.setMinWidth(35);
        clmTrasladoV.setMaxWidth(35);
        clmTrasladoV.setCellValueFactory(dato -> dato.getValue().Trasla_vProperty().asObject());
        clmTrasladoV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmTrasladoT = new TableColumn("T");
        clmTrasladoT.setMinWidth(45);
        clmTrasladoT.setMaxWidth(45);
        clmTrasladoT.setCellValueFactory(dato -> dato.getValue().Trasla_tProperty().asObject());
        TableColumn clmTraslados = new TableColumn("TRASLADOS");
	clmTraslados.setMinWidth(150);
        clmTraslados.setMaxWidth(150);
        clmTraslados.getColumns().addAll(clmTrasladoN, clmTrasladoV, clmTrasladoT);
        // DESERTORES
        TableColumn<IngresarResultadoFinal, Integer> clmDesertorN = new TableColumn("N");
        clmDesertorN.setMinWidth(35);
        clmDesertorN.setMaxWidth(35);
        clmDesertorN.setCellValueFactory(dato -> dato.getValue().Deser_nProperty().asObject());
        clmDesertorN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmDesertorV = new TableColumn("V");
        clmDesertorV.setMinWidth(35);
        clmDesertorV.setMaxWidth(35);
        clmDesertorV.setCellValueFactory(dato -> dato.getValue().Deser_vProperty().asObject());
        clmDesertorV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmDesertorT = new TableColumn("T");
        clmDesertorT.setMinWidth(45);
        clmDesertorT.setMaxWidth(45);
        clmDesertorT.setCellValueFactory(dato -> dato.getValue().Deser_tProperty().asObject());
        TableColumn clmDesertores = new TableColumn("DESERTORES");
	clmDesertores.setMinWidth(150);
        clmDesertores.setMaxWidth(150);
        clmDesertores.getColumns().addAll(clmDesertorN, clmDesertorV, clmDesertorT);
        // EVALUADOS
        TableColumn<IngresarResultadoFinal, Integer> clmEvaluadosN = new TableColumn("N");
        clmEvaluadosN.setMinWidth(35);
        clmEvaluadosN.setMaxWidth(35);
        clmEvaluadosN.setCellValueFactory(dato -> dato.getValue().Evalu_nProperty().asObject());
        clmEvaluadosN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmEvaluadosV = new TableColumn("V");
        clmEvaluadosV.setMinWidth(35);
        clmEvaluadosV.setMaxWidth(35);
        clmEvaluadosV.setCellValueFactory(dato -> dato.getValue().Evalu_vProperty().asObject());
        clmEvaluadosV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmEvaluadosT = new TableColumn("T");
        clmEvaluadosT.setMinWidth(45);
        clmEvaluadosT.setMaxWidth(45);
        clmEvaluadosT.setCellValueFactory(dato -> dato.getValue().Evalu_tProperty().asObject());
        TableColumn clmEvaluados = new TableColumn("EVALUADOS");
	clmEvaluados.setMinWidth(150);
        clmEvaluados.setMaxWidth(150);
        clmEvaluados.getColumns().addAll(clmEvaluadosN, clmEvaluadosV, clmEvaluadosT);
        // INICIAL
        TableColumn<IngresarResultadoFinal, Integer> clmInicialN = new TableColumn("N");
        clmInicialN.setMinWidth(35);
        clmInicialN.setMaxWidth(35);
        clmInicialN.setCellValueFactory(dato -> dato.getValue().Inic_nProperty().asObject());
        clmInicialN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmInicialV = new TableColumn("V");
        clmInicialV.setMinWidth(35);
        clmInicialV.setMaxWidth(35);
        clmInicialV.setCellValueFactory(dato -> dato.getValue().Inic_vProperty().asObject());
        clmInicialV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmInicialT = new TableColumn("T");
        clmInicialT.setMinWidth(45);
        clmInicialT.setMaxWidth(45);
        clmInicialT.setCellValueFactory(dato -> dato.getValue().Inic_tProperty().asObject());
        TableColumn clmInicial = new TableColumn("INICIAL");
	clmInicial.setMinWidth(150);
        clmInicial.setMaxWidth(150);
        clmInicial.getColumns().addAll(clmInicialN, clmInicialV, clmInicialT);
        // BASICO
        TableColumn<IngresarResultadoFinal, Integer> clmBasicoN = new TableColumn("N");
        clmBasicoN.setMinWidth(35);
        clmBasicoN.setMaxWidth(35);
        clmBasicoN.setCellValueFactory(dato -> dato.getValue().Basi_nProperty().asObject());
        clmBasicoN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmBasicoV = new TableColumn("V");
        clmBasicoV.setMinWidth(35);
        clmBasicoV.setMaxWidth(35);
        clmBasicoV.setCellValueFactory(dato -> dato.getValue().Basi_vProperty().asObject());
        clmBasicoV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmBasicoT = new TableColumn("T");
        clmBasicoT.setMinWidth(45);
        clmBasicoT.setMaxWidth(45);
        clmBasicoT.setCellValueFactory(dato -> dato.getValue().Basi_tProperty().asObject());
        TableColumn clmBasico = new TableColumn("BÁSICO");
	clmBasico.setMinWidth(150);
        clmBasico.setMaxWidth(150);
        clmBasico.getColumns().addAll(clmBasicoN, clmBasicoV, clmBasicoT);
        // AVANZADO
        TableColumn<IngresarResultadoFinal, Integer> clmAvanzadoN = new TableColumn("N");
        clmAvanzadoN.setMinWidth(35);
        clmAvanzadoN.setMaxWidth(35);
        clmAvanzadoN.setCellValueFactory(dato -> dato.getValue().Avan_nProperty().asObject());
        clmAvanzadoN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmAvanzadoV = new TableColumn("V");
        clmAvanzadoV.setMinWidth(35);
        clmAvanzadoV.setMaxWidth(35);
        clmAvanzadoV.setCellValueFactory(dato -> dato.getValue().Avan_vProperty().asObject());
        clmAvanzadoV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmAvanzadoT = new TableColumn("T");
        clmAvanzadoT.setMinWidth(45);
        clmAvanzadoT.setMaxWidth(45);
        clmAvanzadoT.setCellValueFactory(dato -> dato.getValue().Avan_tProperty().asObject());
        TableColumn clmAvanzado = new TableColumn("AVANZADO");
	clmAvanzado.setMinWidth(150);
        clmAvanzado.setMaxWidth(150);
        clmAvanzado.getColumns().addAll(clmAvanzadoN, clmAvanzadoV, clmAvanzadoT);
        // EXCELENTE
        TableColumn<IngresarResultadoFinal, Integer> clmExcelenteN = new TableColumn("N");
        clmExcelenteN.setMinWidth(35);
        clmExcelenteN.setMaxWidth(35);
        clmExcelenteN.setCellValueFactory(dato -> dato.getValue().Exce_nProperty().asObject());
        clmExcelenteN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmExcelenteV = new TableColumn("V");
        clmExcelenteV.setMinWidth(35);
        clmExcelenteV.setMaxWidth(35);
        clmExcelenteV.setCellValueFactory(dato -> dato.getValue().Exce_vProperty().asObject());
        clmExcelenteV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<IngresarResultadoFinal, Integer> clmExcelenteT = new TableColumn("T");
        clmExcelenteT.setMinWidth(45);
        clmExcelenteT.setMaxWidth(45);
        clmExcelenteT.setCellValueFactory(dato -> dato.getValue().Exce_tProperty().asObject());
        TableColumn clmExcelente = new TableColumn("EXCELENTE");
	clmExcelente.setMinWidth(150);
        clmExcelente.setMaxWidth(150);
        clmExcelente.getColumns().addAll(clmExcelenteN, clmExcelenteV, clmExcelenteT);
        //Asigna las columnas
        tblResultadoFinal.getColumns().addAll(clmGrado, clmMatInicial, clmMatFinal, clmIngresos, clmTraslados, clmDesertores, clmEvaluados, clmInicial, clmBasico, clmAvanzado, clmExcelente);        
        tblResultadoFinal.setPlaceholder(new Label("Seleccione un centro educativo en el boton Buscar"));
        gestionarEventosMatInicial(clmMatInicialN, clmMatInicialV);
        gestionarEventosMatFinal(clmMatFinalN, clmMatFinalV);
        gestionarEventosIngresos(clmIngresoN, clmIngresoV);
        gestionarEventosTraslados(clmTrasladoN, clmTrasladoV);
        gestionarEventosDesertores(clmDesertorN, clmDesertorV);
        gestionarEventosEvaluados(clmEvaluadosN, clmEvaluadosV);
        gestionarEventosInicial(clmInicialN, clmInicialV);
        gestionarEventosBasico(clmBasicoN, clmBasicoV);
        gestionarEventosAvanzado(clmAvanzadoN, clmAvanzadoV);
        gestionarEventosExcelente(clmExcelenteN, clmExcelenteV);
        gestionarEventoHayDatosCentro();
        deshabilitarControlesIniciales();
        Platform.runLater(() -> btnBuscar.requestFocus());
    }
    
    public void inicializarDatos(){
        
    }
    
    private void deshabilitarControlesIniciales(){
        btnNuevo.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnAceptar.setDisable(true);
        btnCancelar.setDisable(true);
        btnSalir.setDisable(false);
    }
    
    private void gestionarEventosMatInicial(TableColumn<IngresarResultadoFinal, Integer> clmMatInicialN, TableColumn<IngresarResultadoFinal, Integer> clmMatInicialV){
        clmMatInicialN.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setMatIni_n(dato.getNewValue());
            p.setMatIni_t(p.getMatIni_n() + p.getMatIni_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
        clmMatInicialV.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setMatIni_v(dato.getNewValue());
            p.setMatIni_t(p.getMatIni_n() + p.getMatIni_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
    }
    
    private void gestionarEventosMatFinal(TableColumn<IngresarResultadoFinal, Integer> clmMatFinalN, TableColumn<IngresarResultadoFinal, Integer> clmMatFinalV){
        clmMatFinalN.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setMatFin_n(dato.getNewValue());
            p.setMatFin_t(p.getMatFin_n() + p.getMatFin_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
        clmMatFinalV.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setMatFin_v(dato.getNewValue());
            p.setMatFin_t(p.getMatFin_n() + p.getMatFin_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
    }
    
    private void gestionarEventosIngresos(TableColumn<IngresarResultadoFinal, Integer> clmIngresoN, TableColumn<IngresarResultadoFinal, Integer> clmIngresoV){
        clmIngresoN.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setIngre_n(dato.getNewValue());
            p.setIngre_t(p.getIngre_n() + p.getIngre_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
        clmIngresoV.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setIngre_v(dato.getNewValue());
            p.setIngre_t(p.getIngre_n() + p.getIngre_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
    }
    
    private void gestionarEventosTraslados(TableColumn<IngresarResultadoFinal, Integer> clmTrasladoN, TableColumn<IngresarResultadoFinal, Integer> clmTrasladoV){
        clmTrasladoN.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setTrasla_n(dato.getNewValue());
            p.setTrasla_t(p.getTrasla_n() + p.getTrasla_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
        clmTrasladoV.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setTrasla_v(dato.getNewValue());
            p.setTrasla_t(p.getTrasla_n() + p.getTrasla_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
    }
    
    private void gestionarEventosDesertores(TableColumn<IngresarResultadoFinal, Integer> clmDesertorN, TableColumn<IngresarResultadoFinal, Integer> clmDesertorV){
        clmDesertorN.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setDeser_n(dato.getNewValue());
            p.setDeser_t(p.getDeser_n() + p.getDeser_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
        clmDesertorV.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setDeser_v(dato.getNewValue());
            p.setDeser_t(p.getDeser_n() + p.getDeser_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
    }
    
    private void gestionarEventosEvaluados(TableColumn<IngresarResultadoFinal, Integer> clmEvaluadoN, TableColumn<IngresarResultadoFinal, Integer> clmEvaluadoV){
        clmEvaluadoN.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setEvalu_n(dato.getNewValue());
            p.setEvalu_t(p.getEvalu_n() + p.getEvalu_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
        clmEvaluadoV.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setEvalu_v(dato.getNewValue());
            p.setEvalu_t(p.getEvalu_n() + p.getEvalu_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
    }
    
    private void gestionarEventosInicial(TableColumn<IngresarResultadoFinal, Integer> clmInicialN, TableColumn<IngresarResultadoFinal, Integer> clmInicialV){
        clmInicialN.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setInic_n(dato.getNewValue());
            p.setInic_t(p.getInic_n() + p.getInic_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
        clmInicialV.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setInic_v(dato.getNewValue());
            p.setInic_t(p.getInic_n() + p.getInic_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
    }
    
    private void gestionarEventosBasico(TableColumn<IngresarResultadoFinal, Integer> clmBasicoN, TableColumn<IngresarResultadoFinal, Integer> clmBasicoV){
        clmBasicoN.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setBasi_n(dato.getNewValue());
            p.setBasi_t(p.getBasi_n() + p.getBasi_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
        clmBasicoV.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setBasi_v(dato.getNewValue());
            p.setBasi_t(p.getBasi_n() + p.getBasi_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
    }
    
    private void gestionarEventosAvanzado(TableColumn<IngresarResultadoFinal, Integer> clmAvanzadoN, TableColumn<IngresarResultadoFinal, Integer> clmAvanzadoV){
        clmAvanzadoN.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setAvan_n(dato.getNewValue());
            p.setAvan_t(p.getAvan_n() + p.getAvan_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
        clmAvanzadoV.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setAvan_v(dato.getNewValue());
            p.setAvan_t(p.getAvan_n() + p.getAvan_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
    }
    
    private void gestionarEventosExcelente(TableColumn<IngresarResultadoFinal, Integer> clmExcelenteN, TableColumn<IngresarResultadoFinal, Integer> clmExcelenteV){
        clmExcelenteN.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setExce_n(dato.getNewValue());
            p.setExce_t(p.getExce_n() + p.getExce_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
        clmExcelenteV.setOnEditCommit(dato -> {
            IngresarResultadoFinal p = dato.getRowValue();
            p.setExce_v(dato.getNewValue());
            p.setExce_t(p.getExce_n() + p.getExce_v());
            tblResultadoFinal.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblResultadoFinal.requestFocus();
        });
    }
    
    //En este evento ocurre cuando se traen datos de BuscarCentro y se muestan datos en el control txtCentro
    private void gestionarEventoHayDatosCentro(){        
        txtCentro.textProperty().addListener((obs, oldText, newText) -> {
            if(txtCentro.getText().length() > 0){
                if(Seleccionar.hayDatosResultadoFinalCentros(conexion, codigo)){
                    Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
                    mensaje.setTitle("Aviso: el centro ya contiene datos");
                    mensaje.setHeaderText("El centro " + nombre_centro + ", código " + codigo_sace + " ya contiene datos ingresados."
                            + "\n\n¿Desea modificar dichos datos?"
                            + "\n\nSeleccione Aceptar para modificar o Cancelar para solamente ver los datos.");
                    mensaje.setContentText("");
                    Optional<ButtonType> result = mensaje.showAndWait();
                    if(result.get() == ButtonType.OK){
                        modificar = true;
                        tblResultadoFinal.setEditable(true);
                        lblEstado.setText("MODIFICAR los datos del centro...");
                    }else{
                        modificar = false;
                        tblResultadoFinal.setEditable(false);
                    }
                    lista_resultados_finales.clear();
                    Seleccionar.obtenerResultadosFinalCentro(conexion, lista_resultados_finales, codigo);
                    tblResultadoFinal.setItems(lista_resultados_finales);
                }else{
                    nuevo = true;
                    lista_resultados_finales.clear();
                    tblResultadoFinal.setPlaceholder(new Label("No hay datos en el centro educativo.\nSeleccione el botón Nuevo para agregar"));
                    btnNuevo.setDisable(false);
                }
            }
        });
    }
    
    @FXML private void buscarCentro(){
        lista_resultados_finales.clear();        
        if(Seleccionar.hayCentros(conexion)){
            String formulario = "/com/teupa/vista/BuscarCentro.fxml";
            try {            
                FXMLLoader loader = new FXMLLoader(getClass().getResource(formulario));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene( new Scene((Pane) loader.load()));
                BuscarCentroCtrl controlador =  loader.<BuscarCentroCtrl>getController();
                controlador.inicializarDatos(this, null, null);
                Image icon = new Image(getClass().getResourceAsStream("/com/teupa/imagenes/icono.png"));
                stage.getIcons().add(icon);
                stage.setTitle("Buscar Centros");
                stage.initModality(Modality.APPLICATION_MODAL);
                System.out.println("\n\nMostrado formulario: " + formulario);
                stage.show();
                System.out.println("\n\t\t...iniciado Buscar Centros");
            } catch (IOException e) {
                System.err.println("Error al intentar mostrar la ventana " + formulario + ": \n" + e.getMessage());
            }
        }else{
            Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
            mensaje.setTitle("Aviso: No hay centro educativos");
            mensaje.setHeaderText("No hay centros en el sistema, debe importar o agregar centros educativos.");
            mensaje.setContentText("");
            mensaje.showAndWait();
        }
    }
    
    @FXML private void nuevo(){
        if(nivel_centro.equals("PREBÁSICA") && ciclo_centro.equals("NO APLICA")){
            IngresarResultadoFinal irf1 = new IngresarResultadoFinal(0, "PRIMERO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf1);
            IngresarResultadoFinal irf2 = new IngresarResultadoFinal(0, "SEGUNDO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf2);
            IngresarResultadoFinal irf3 = new IngresarResultadoFinal(0, "TERCERO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf3);
        }
        if(nivel_centro.equals("BÁSICA") && ciclo_centro.equals("I Y II CICLO")){
            IngresarResultadoFinal irf1 = new IngresarResultadoFinal(0, "PRIMERO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf1);
            IngresarResultadoFinal irf2 = new IngresarResultadoFinal(0, "SEGUNDO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf2);
            IngresarResultadoFinal irf3 = new IngresarResultadoFinal(0, "TERCERO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf3);
            IngresarResultadoFinal irf4 = new IngresarResultadoFinal(0, "CUARTO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf4);
            IngresarResultadoFinal irf5 = new IngresarResultadoFinal(0, "QUINTO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf5);
            IngresarResultadoFinal irf6 = new IngresarResultadoFinal(0, "SEXTO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf6);            
        }
        if(nivel_centro.equals("BÁSICA") && ciclo_centro.equals("I, II Y III CICLO")){
            IngresarResultadoFinal irf1 = new IngresarResultadoFinal(0, "PRIMERO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf1);
            IngresarResultadoFinal irf2 = new IngresarResultadoFinal(0, "SEGUNDO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf2);
            IngresarResultadoFinal irf3 = new IngresarResultadoFinal(0, "TERCERO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf3);
            IngresarResultadoFinal irf4 = new IngresarResultadoFinal(0, "CUARTO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf4);
            IngresarResultadoFinal irf5 = new IngresarResultadoFinal(0, "QUINTO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf5);
            IngresarResultadoFinal irf6 = new IngresarResultadoFinal(0, "SEXTO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf6);
            IngresarResultadoFinal irf7 = new IngresarResultadoFinal(0, "SÉPTIMO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf7);
            IngresarResultadoFinal irf8 = new IngresarResultadoFinal(0, "OCTAVO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf8);
            IngresarResultadoFinal irf9 = new IngresarResultadoFinal(0, "NOVENO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf9);
        }
        if(nivel_centro.equals("MEDIA") && ciclo_centro.equals("CBT Y BCH")){
            IngresarResultadoFinal irf7 = new IngresarResultadoFinal(0, "SÉPTIMO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf7);
            IngresarResultadoFinal irf8 = new IngresarResultadoFinal(0, "OCTAVO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf8);
            IngresarResultadoFinal irf9 = new IngresarResultadoFinal(0, "NOVENO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf9);
            IngresarResultadoFinal irf10 = new IngresarResultadoFinal(0, "DÉCIMO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf10);
            IngresarResultadoFinal irf11 = new IngresarResultadoFinal(0, "UNDÉCIMO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf11);
        }
        if(nivel_centro.equals("MEDIA") && ciclo_centro.equals("CBT, BTP Y BCH")){
            IngresarResultadoFinal irf7 = new IngresarResultadoFinal(0, "SÉPTIMO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf7);
            IngresarResultadoFinal irf8 = new IngresarResultadoFinal(0, "OCTAVO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf8);
            IngresarResultadoFinal irf9 = new IngresarResultadoFinal(0, "NOVENO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf9);
            IngresarResultadoFinal irf10 = new IngresarResultadoFinal(0, "DÉCIMO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf10);
            IngresarResultadoFinal irf11 = new IngresarResultadoFinal(0, "UNDÉCIMO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf11);
            IngresarResultadoFinal irf12 = new IngresarResultadoFinal(0, "DUODÉCIMO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_resultados_finales.add(irf12);
        }
        tblResultadoFinal.setItems(lista_resultados_finales);
        int matricula_final = Seleccionar.obtenerMatriculaFinalCentroCodigo(conexion, codigo_sace);
        lblTitulo.setText("Matrícula total según SACE " + matricula_final);
        lblEstado.setText("Ingrese NUEVOS datos...");
        btnNuevo.setDisable(true);
        btnCancelar.setDisable(false);
    }
    
    private void limpiarTabla(ObservableList<IngresarResultadoFinal> lista_resultados_finales){
        for (IngresarResultadoFinal lista : lista_resultados_finales) {
            lista.setMatIni_n(0);
            lista.setMatIni_v(0);
            lista.setMatIni_t(0);
            lista.setMatFin_n(0);
            lista.setMatFin_v(0);
            lista.setMatFin_t(0);
            lista.setIngre_n(0);
            lista.setIngre_v(0);
            lista.setIngre_t(0);
            lista.setTrasla_n(0);
            lista.setTrasla_v(0);
            lista.setTrasla_t(0);
            lista.setDeser_n(0);
            lista.setDeser_v(0);
            lista.setDeser_t(0);
            lista.setEvalu_n(0);
            lista.setEvalu_v(0);
            lista.setEvalu_t(0);
            lista.setInic_n(0);
            lista.setInic_v(0);
            lista.setInic_t(0);
            lista.setBasi_n(0);
            lista.setBasi_v(0);
            lista.setBasi_t(0);
            lista.setAvan_n(0);
            lista.setAvan_v(0);
            lista.setAvan_t(0);
            lista.setExce_n(0);
            lista.setExce_v(0);
            lista.setExce_t(0);            
        }
        tblResultadoFinal.refresh();
    }
    
    @FXML private void modificar(){
        System.out.println("\nModificar");
    }
    
    @FXML private void eliminar(){
        System.out.println("\nEliminar");
    }
    
    @FXML private void aceptar(){
        if(!txtCentro.getText().equals("")){
            if(modificar){
                Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
                mensaje.setTitle("Aviso: Modificar");
                mensaje.setHeaderText("Va a modificar los datos para el centro: " + nombre_centro + ","
                        + "\ncódigo: " + codigo_sace + "  tipo: " + cobertura_centro
                                + "\n\n¿Está de acuerdo?");
                mensaje.setContentText("");
                Optional<ButtonType> result = mensaje.showAndWait();
                if(result.get() == ButtonType.OK){
                    guardado = true;                    
                    btnAceptar.setDisable(true);
                    btnCancelar.setDisable(true);                    
                    modificarDatosCentro(lista_resultados_finales);
                    lista_resultados_finales.clear();
                    Seleccionar.obtenerResultadosFinalCentro(conexion, lista_resultados_finales, codigo);
                    tblResultadoFinal.setItems(lista_resultados_finales);
                    Alert info = new Alert(Alert.AlertType.INFORMATION);
                    info.setTitle("Datos modificados");
                    info.setHeaderText("Los datos se modificaron.");
                    info.setContentText("");
                    info.showAndWait();
                    lista_resultados_finales.clear();
                    validarDatos(lista_resultados_finales);
                }
            }
            if(nuevo){
                Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
                mensaje.setTitle("Aviso: Guardar");
                mensaje.setHeaderText("Va a guardar los datos para el centro: " + nombre_centro + ","
                        + "\ncódigo: " + codigo_sace + "\ttipo: " + cobertura_centro + "\tnivel: " + nivel_centro
                                + "\n\n¿Está de acuerdo?");
                mensaje.setContentText("");
                Optional<ButtonType> result = mensaje.showAndWait();
                if(result.get() == ButtonType.OK){                        
                    btnAceptar.setDisable(true);
                    btnCancelar.setDisable(true);                    
                    guardarDatosCentro(lista_resultados_finales);
                    guardado = true;
                    txtCentro.clear();
                    lista_resultados_finales.clear();
                    tblResultadoFinal.setDisable(true);
                    lblTitulo.setText("");
                    nuevo = false;
                    lblEstado.setText("");
                    Alert info = new Alert(Alert.AlertType.INFORMATION);
                    info.setTitle("Datos guardados");
                    info.setHeaderText("Los datos se guardaron.");
                    info.setContentText("");
                    info.showAndWait();
                    lista_resultados_finales.clear();
                    validarDatos(lista_resultados_finales);
                }
            }
        }else{
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Aviso: nombre de centro educativo vacio");
            mensaje.setHeaderText("Debe seleccionar un centros educativos al cual agregar los datos.");
            mensaje.setContentText("");
            mensaje.showAndWait();
        }
    }
    
    private boolean validarDatos(ObservableList<IngresarResultadoFinal> lista_validacion){
        Seleccionar.obtenerResultadosFinalCentro(conexion, lista_validacion, codigo);
        int matricula_sace = Seleccionar.obtenerMatriculaFinalCentroCodigo(conexion, codigo_sace);
        int mat_inicial = 0;
        int mat_final = 0;
        int evaluados = 0;
        int ingresos = 0;
        int traslados = 0;
        int desertores = 0;
        int inicial = 0;
        int basico = 0;
        int avanzado = 0;
        int excelente = 0;
        int suma_niveles = 0;
        String mensaje = "";
        for(IngresarResultadoFinal lis : lista_validacion){
            if(lis.getGrado().equals("PRIMERO") && lis.getInic_t() > 0){
                mensaje = mensaje + "\nError: No deben haber Reprobados INICIAL(" + lis.getInic_t() + ") en PRIMER grado.";
            }
            if(lis.getGrado().equals("SEGUNDO") && lis.getInic_t() > 0){
                mensaje = mensaje + "\nError: No deben haber Reprobados INICIAL(" + lis.getInic_t() + ") en SEGUNDO grado.";
            }
            if(lis.getGrado().equals("CUARTO") && lis.getInic_t() > 0){
                mensaje = mensaje + "\nError: No deben haber Reprobados INICIAL(" + lis.getInic_t() + ") en CUARTO grado.";
            }
            if(lis.getGrado().equals("QUINTO") && lis.getInic_t() > 0){
                mensaje = mensaje + "\nError: No deben haber Reprobados INICIAL(" + lis.getInic_t() + ") en QUINTO grado.";
            }
            evaluados = evaluados + lis.getEvalu_t();
            mat_final = mat_final + lis.getMatFin_t();
            suma_niveles = suma_niveles + lis.getInic_t() + lis.getBasi_t() + lis.getAvan_t() + lis.getExce_t();
            inicial = inicial + lis.getInic_t();
            basico = basico + lis.getBasi_t();
            avanzado = avanzado + lis.getAvan_t();
            excelente = excelente + lis.getExce_t();
            mat_inicial = mat_inicial + lis.getMatIni_t();
            ingresos = ingresos + lis.getIngre_t();
            traslados = traslados + lis.getTrasla_t();
            desertores = desertores + lis.getDeser_t();
        }
            if(mat_final > matricula_sace){
                mensaje = mensaje + "\nError: la MATRÍCULA FINAL(" + mat_final + ") ingresada es mayor que la Matricula SACE(" + matricula_sace + ") del centro.";
            }
            if(mat_final < matricula_sace){
                mensaje = mensaje + "\nError: la MATRÍCULA FINAL(" + mat_final + ") ingresada es menor que la Matricula SACE(" + matricula_sace + ") del centro.";
            }
            if(evaluados == 0){
                mensaje = mensaje + "\nError: los EVALUADOS no pueden ser cero.";
            }
            if(evaluados != mat_final){
                mensaje = mensaje + "\nError: los EVALUADOS(" + evaluados + ") no son igual a la MATRÍCULA FINAL(" + mat_final + ").";
            }
            if(mat_final != suma_niveles){
                mensaje = mensaje + "\nError: la MATRÍCULA FINAL(" + mat_final + ") no es igual a la suma de INICIAL(" + inicial + ") + BASICO(" + basico + ") + AVANZADO(" + avanzado + ") + EXCELENTE(" + excelente + ") = " + suma_niveles;
            }
            if(mat_final != (mat_inicial + ingresos - traslados - desertores)){
                mensaje = mensaje + "\nError: la MATRÍCULA FINAL(" + mat_final + ") no es igual a MATRÍCULA INICIAL(" + mat_inicial + ") + INGRESOS(" + ingresos + ") - TRASLADOS(" + traslados + ") - DESERTORES(" + desertores + ") = " + (mat_inicial + ingresos - traslados - desertores);
            }
            mostrarResumen(mensaje);
        return true;
    }
        
    @FXML
    public void mostrarResumen(String mensaje){
        String formulario = "/com/teupa/vista/Resumen.fxml";
        try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(formulario));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene( new Scene((Pane) loader.load()));
            ResumenCtrl controlador =  loader.<ResumenCtrl>getController();
            controlador.inicializarDatos(mensaje, nombre_centro + " (" + codigo_sace + ")");
            Image icon = new Image(getClass().getResourceAsStream("/com/teupa/imagenes/icono.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Validación de Resultado Final");
            stage.initModality(Modality.APPLICATION_MODAL);
            System.out.println("\n\nMostrado formulario: " + formulario);
            stage.show();
            System.out.println("\n\t\t...iniciado Resumen");
        } catch (IOException e) {
            System.err.println("\nError al intentar mostrar la ventana " + formulario + ": \n" + e.getMessage());
        }
    }
    
    private void guardarDatosCentro(ObservableList<IngresarResultadoFinal> lista_resultados_finales){        
        for (IngresarResultadoFinal lista : lista_resultados_finales) {
            if(Insertar.guardarResultadoFinal(conexion, lista.getGrado().toUpperCase(), 
                lista.getMatIni_n(), lista.getMatIni_v(), lista.getMatIni_t(), 
                lista.getMatFin_n(), lista.getMatFin_v(), lista.getMatFin_t(), 
                lista.getIngre_n(), lista.getIngre_v(), lista.getIngre_t(), 
                lista.getTrasla_n(), lista.getTrasla_v(), lista.getTrasla_t(), 
                lista.getDeser_n(), lista.getDeser_v(), lista.getDeser_t(), 
                lista.getEvalu_n(), lista.getEvalu_v(), lista.getEvalu_t(), 
                lista.getInic_n(), lista.getInic_v(), lista.getInic_t(), 
                lista.getBasi_n(), lista.getBasi_v(), lista.getBasi_t(), 
                lista.getAvan_n(), lista.getAvan_v(), lista.getAvan_t(), 
                lista.getExce_n(), lista.getExce_v(), lista.getExce_t(),
                codigo)){
            }
        }
    }
    
    private void modificarDatosCentro(ObservableList<IngresarResultadoFinal> lista_resultados_finales){ 
        int contador = 0;
        for (IngresarResultadoFinal lista : lista_resultados_finales) {
            if(Modificar.modificarResultadoFinal(conexion, lista.getCodigo(), lista.getGrado(),
                    lista.getMatIni_n(), lista.getMatIni_v(), lista.getMatIni_t(), 
                    lista.getMatFin_n(), lista.getMatFin_v(), lista.getMatFin_t(), 
                    lista.getIngre_n(), lista.getIngre_v(), lista.getIngre_t(), 
                    lista.getTrasla_n(), lista.getTrasla_v(), lista.getTrasla_t(), 
                    lista.getDeser_n(), lista.getDeser_v(), lista.getDeser_t(), 
                    lista.getEvalu_n(), lista.getEvalu_v(), lista.getEvalu_t(), 
                    lista.getInic_n(), lista.getInic_v(), lista.getInic_t(), 
                    lista.getBasi_n(), lista.getBasi_v(), lista.getBasi_t(), 
                    lista.getAvan_n(), lista.getAvan_v(), lista.getAvan_t(), 
                    lista.getExce_n(), lista.getExce_v(), lista.getExce_t())){
                contador ++;
            }
        }
        System.out.println("\n\t\t " + contador + " filas modificadas");
    }
    
    @FXML private void cancelar(){
        lblTitulo.setText("");
        txtCentro.setText("");
        lblEstado.setText("");
        if(lista_resultados_finales.size() > 0){
            lista_resultados_finales.clear();
        }
        nuevo = false;
        modificar = false;
        tblResultadoFinal.setDisable(true);
        btnCancelar.setDisable(true);
    }
    
    @FXML
    private void salir(){
        if(guardado){
            Stage stage = (Stage) btnSalir.getScene().getWindow();
            stage.close();
        }else{
            Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
            mensaje.setTitle("Aviso: Hay datos sin guardar");
            mensaje.setHeaderText("No ha guardado los datos agregados resientemente a la tabla.\n\nPresione Cancelar para continuar editando los datos,\no Aceptar si desea salir sin guardar, perderá los datos sin guardar.");
            mensaje.setContentText("");
            Optional<ButtonType> result = mensaje.showAndWait();
            if(result.get() == ButtonType.OK){
                Stage stage = (Stage) btnSalir.getScene().getWindow();
                stage.close();
            }
        }
    }
    
}
