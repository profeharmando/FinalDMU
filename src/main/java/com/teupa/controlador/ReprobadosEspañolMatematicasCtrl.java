/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.teupa.controlador;

import com.teupa.basedatos.ConexionMySql;
import com.teupa.basedatos.Insertar;
import com.teupa.basedatos.Modificar;
import com.teupa.basedatos.Seleccionar;
import com.teupa.modelo.ReprobadosEspañolMatematicas;
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
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;


public class ReprobadosEspañolMatematicasCtrl implements Initializable {


    @FXML public TextField txtCentro;
    @FXML private Button btnBuscar;
    @FXML private Label lblTitulo;
    @FXML public TableView <ReprobadosEspañolMatematicas> tblReprobados;
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
    private String error_fila = "NINGUNO";
    
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
    public ObservableList<ReprobadosEspañolMatematicas> lista_reprobados = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        lblTitulo.setText("");        
        btnNuevo.setDisable(true);
        lblEstado.setText("");
        tblReprobados.setDisable(true);
        tblReprobados.setEditable(true);
        nuevo = false;
        modificar = false;
        
        // GRADO
        TableColumn<ReprobadosEspañolMatematicas, String> clmGrado = new TableColumn("GRADO");
	clmGrado.setMinWidth(120);
        clmGrado.setMaxWidth(120);
        clmGrado.setCellValueFactory(dato -> dato.getValue().GradoProperty());
        
        // MAT INICIAL
        TableColumn<ReprobadosEspañolMatematicas, Integer> clmMatInicialN = new TableColumn("N");
        clmMatInicialN.setMinWidth(45);
        clmMatInicialN.setMaxWidth(45);
        clmMatInicialN.setCellValueFactory(dato -> dato.getValue().MatIni_nProperty().asObject());
        clmMatInicialN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ReprobadosEspañolMatematicas, Integer> clmMatInicialV = new TableColumn("V");
        clmMatInicialV.setMinWidth(45);
        clmMatInicialV.setMaxWidth(45);
        clmMatInicialV.setCellValueFactory(dato -> dato.getValue().MatIni_vProperty().asObject());
        clmMatInicialV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ReprobadosEspañolMatematicas, Integer> clmMatInicialT = new TableColumn("T");
        clmMatInicialT.setMinWidth(55);
        clmMatInicialT.setMaxWidth(55);
        clmMatInicialT.setCellValueFactory(dato -> dato.getValue().MatIni_tProperty().asObject());
        TableColumn clmMatInicial = new TableColumn("MAT INICIAL");	
        clmMatInicial.setMinWidth(145);
        clmMatInicial.setMaxWidth(145);
        clmMatInicial.getColumns().addAll(clmMatInicialN, clmMatInicialV, clmMatInicialT);
        
        // MAT FINAL
        TableColumn<ReprobadosEspañolMatematicas, Integer> clmMatFinalN = new TableColumn("N");
        clmMatFinalN.setMinWidth(45);
        clmMatFinalN.setMaxWidth(45);
        clmMatFinalN.setCellValueFactory(dato -> dato.getValue().MatFin_nProperty().asObject());
        clmMatFinalN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ReprobadosEspañolMatematicas, Integer> clmMatFinalV = new TableColumn("V");
        clmMatFinalV.setMinWidth(45);
        clmMatFinalV.setMaxWidth(45);
        clmMatFinalV.setCellValueFactory(dato -> dato.getValue().MatFin_vProperty().asObject());
        clmMatFinalV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ReprobadosEspañolMatematicas, Integer> clmMatFinalT = new TableColumn("T");
        clmMatFinalT.setMinWidth(55);
        clmMatFinalT.setMaxWidth(55);
        clmMatFinalT.setCellValueFactory(dato -> dato.getValue().MatFin_tProperty().asObject());
        TableColumn clmMatFinal = new TableColumn("MAT FINAL");
        clmMatFinal.setMinWidth(145);
        clmMatFinal.setMaxWidth(145);
        clmMatFinal.getColumns().addAll(clmMatFinalN, clmMatFinalV, clmMatFinalT);
        
        // EVALUADOS EN ESPAÑOL
        TableColumn<ReprobadosEspañolMatematicas, Integer> clmEvalEspN = new TableColumn("N");
        clmEvalEspN.setMinWidth(45);
        clmEvalEspN.setMaxWidth(45);
        clmEvalEspN.setCellValueFactory(dato -> dato.getValue().EvalEsp_nProperty().asObject());
        clmEvalEspN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ReprobadosEspañolMatematicas, Integer> clmEvalEspV = new TableColumn("V");
        clmEvalEspV.setMinWidth(45);
        clmEvalEspV.setMaxWidth(45);
        clmEvalEspV.setCellValueFactory(dato -> dato.getValue().EvalEsp_vProperty().asObject());
        clmEvalEspV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ReprobadosEspañolMatematicas, Integer> clmEvalEspT = new TableColumn("T");
        clmEvalEspT.setMinWidth(55);
        clmEvalEspT.setMaxWidth(55);
        clmEvalEspT.setCellValueFactory(dato -> dato.getValue().EvalEsp_tProperty().asObject());
        TableColumn clmEvalEsp = new TableColumn("EVAL. ESPAÑOL");
	clmEvalEsp.setMinWidth(145);
        clmEvalEsp.setMaxWidth(145);
        clmEvalEsp.getColumns().addAll(clmEvalEspN, clmEvalEspV, clmEvalEspT);
        
        // REPROBADOS ESPAÑOL
        TableColumn<ReprobadosEspañolMatematicas, Integer> clmRepEspN = new TableColumn("N");
        clmRepEspN.setMinWidth(45);
        clmRepEspN.setMaxWidth(45);
        clmRepEspN.setCellValueFactory(dato -> dato.getValue().ReproEsp_nProperty().asObject());
        clmRepEspN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ReprobadosEspañolMatematicas, Integer> clmRepEspV = new TableColumn("V");
        clmRepEspV.setMinWidth(45);
        clmRepEspV.setMaxWidth(45);
        clmRepEspV.setCellValueFactory(dato -> dato.getValue().ReproEsp_vProperty().asObject());
        clmRepEspV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ReprobadosEspañolMatematicas, Integer> clmRepEspT = new TableColumn("T");
        clmRepEspT.setMinWidth(55);
        clmRepEspT.setMaxWidth(55);
        clmRepEspT.setCellValueFactory(dato -> dato.getValue().ReproEsp_tProperty().asObject());
        TableColumn clmReproEsp = new TableColumn("REPRO. ESPOAÑOL");
	clmReproEsp.setMinWidth(145);
        clmReproEsp.setMaxWidth(145);
        clmReproEsp.getColumns().addAll(clmRepEspN, clmRepEspV, clmRepEspT);
        
        ////TASA DE REPROBACIÓN ESPAÑOL
        TableColumn<ReprobadosEspañolMatematicas, Double> clmTasaEspN = new TableColumn("N");
        clmTasaEspN.setMinWidth(45);
        clmTasaEspN.setMaxWidth(45);
        clmTasaEspN.setCellValueFactory(dato -> dato.getValue().TasaEsp_nProperty().asObject());
        clmTasaEspN.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        TableColumn<ReprobadosEspañolMatematicas, Double> clmTasaEspV = new TableColumn("V");
        clmTasaEspV.setMinWidth(45);
        clmTasaEspV.setMaxWidth(45);
        clmTasaEspV.setCellValueFactory(dato -> dato.getValue().TasaEsp_vProperty().asObject());
        clmTasaEspV.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        TableColumn<ReprobadosEspañolMatematicas, Double> clmTasaEspT = new TableColumn("T");
        clmTasaEspT.setMinWidth(55);
        clmTasaEspT.setMaxWidth(55);
        clmTasaEspT.setCellValueFactory(dato -> dato.getValue().TasaEsp_tProperty().asObject());
        TableColumn clmTasaEsp = new TableColumn("TASA REP ESP");
	clmTasaEsp.setMinWidth(145);
        clmTasaEsp.setMaxWidth(145);
        clmTasaEsp.getColumns().addAll(clmTasaEspN, clmTasaEspV, clmTasaEspT);
        
        // EVALUADOS EN MATEMATICAS
        TableColumn<ReprobadosEspañolMatematicas, Integer> clmEvalMatN = new TableColumn("N");
        clmEvalMatN.setMinWidth(45);
        clmEvalMatN.setMaxWidth(45);
        clmEvalMatN.setCellValueFactory(dato -> dato.getValue().EvalMat_nProperty().asObject());
        clmEvalMatN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ReprobadosEspañolMatematicas, Integer> clmEvalMatV = new TableColumn("V");
        clmEvalMatV.setMinWidth(45);
        clmEvalMatV.setMaxWidth(45);
        clmEvalMatV.setCellValueFactory(dato -> dato.getValue().EvalMat_vProperty().asObject());
        clmEvalMatV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ReprobadosEspañolMatematicas, Integer> clmEvalMatT = new TableColumn("T");
        clmEvalMatT.setMinWidth(55);
        clmEvalMatT.setMaxWidth(55);
        clmEvalMatT.setCellValueFactory(dato -> dato.getValue().EvalMat_tProperty().asObject());
        TableColumn clmEvalMat = new TableColumn("EVAL. MATEMÁTICAS");
	clmEvalMat.setMinWidth(145);
        clmEvalMat.setMaxWidth(145);
        clmEvalMat.getColumns().addAll(clmEvalMatN, clmEvalMatV, clmEvalMatT);
        
        // REPROBADOS MATEMÁTICAS
        TableColumn<ReprobadosEspañolMatematicas, Integer> clmRepMatN = new TableColumn("N");
        clmRepMatN.setMinWidth(45);
        clmRepMatN.setMaxWidth(45);
        clmRepMatN.setCellValueFactory(dato -> dato.getValue().ReproMat_nProperty().asObject());
        clmRepMatN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ReprobadosEspañolMatematicas, Integer> clmRepMatV = new TableColumn("V");
        clmRepMatV.setMinWidth(45);
        clmRepMatV.setMaxWidth(45);
        clmRepMatV.setCellValueFactory(dato -> dato.getValue().ReproMat_vProperty().asObject());
        clmRepMatV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ReprobadosEspañolMatematicas, Integer> clmRepMatT = new TableColumn("T");
        clmRepMatT.setMinWidth(55);
        clmRepMatT.setMaxWidth(55);
        clmRepMatT.setCellValueFactory(dato -> dato.getValue().ReproMat_tProperty().asObject());
        TableColumn clmReproMat = new TableColumn("REPRO. MATEMÁTICAS");
	clmReproMat.setMinWidth(145);
        clmReproMat.setMaxWidth(145);
        clmReproMat.getColumns().addAll(clmRepMatN, clmRepMatV, clmRepMatT);
        
        ////TASA DE REPROBACIÓN MATEMATICAS
        TableColumn<ReprobadosEspañolMatematicas, Double> clmTasaMatN = new TableColumn("N");
        clmTasaMatN.setMinWidth(45);
        clmTasaMatN.setMaxWidth(45);
        clmTasaMatN.setCellValueFactory(dato -> dato.getValue().TasaMat_nProperty().asObject());
        clmTasaMatN.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        TableColumn<ReprobadosEspañolMatematicas, Double> clmTasaMatV = new TableColumn("V");
        clmTasaMatV.setMinWidth(45);
        clmTasaMatV.setMaxWidth(45);
        clmTasaMatV.setCellValueFactory(dato -> dato.getValue().TasaMat_vProperty().asObject());
        clmTasaMatV.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        TableColumn<ReprobadosEspañolMatematicas, Double> clmTasaMatT = new TableColumn("T");
        clmTasaMatT.setMinWidth(55);
        clmTasaMatT.setMaxWidth(55);
        clmTasaMatT.setCellValueFactory(dato -> dato.getValue().TasaMat_tProperty().asObject());
        TableColumn clmTasaMat = new TableColumn("TASA REP MAT");
	clmTasaMat.setMinWidth(145);
        clmTasaMat.setMaxWidth(145);
        clmTasaMat.getColumns().addAll(clmTasaMatN, clmTasaMatV, clmTasaMatT);
        
        //Asigna las columnas a la tabla
        tblReprobados.getColumns().addAll(clmGrado, clmMatInicial, clmMatFinal, clmEvalEsp, clmReproEsp, clmTasaEsp, clmEvalMat, clmReproMat, clmTasaMat);
        
        tblReprobados.setPlaceholder(new Label("Seleccione un centro educativo en el boton Buscar"));
        gestionarEventosMatInicial(clmMatInicialN, clmMatInicialV);
        gestionarEventosMatFinal(clmMatFinalN, clmMatFinalV);
        gestionarEventosEvaluadosEspañol(clmEvalEspN, clmEvalEspV);
        gestionarEventosReprobadosEspañol(clmRepEspN, clmRepEspV);        
        gestionarEventosEvaluadosMatematicas(clmEvalMatN, clmEvalMatV);
        gestionarEventosReprobadosMatematicas(clmRepMatN, clmRepMatV);
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
    
    private void gestionarEventosMatInicial(TableColumn<ReprobadosEspañolMatematicas, Integer> clmMatInicialN, TableColumn<ReprobadosEspañolMatematicas, Integer> clmMatInicialV){
        clmMatInicialN.setOnEditCommit(dato -> {
            ReprobadosEspañolMatematicas p = dato.getRowValue();
            p.setMatIni_n(dato.getNewValue());
            p.setMatIni_t(p.getMatIni_n() + p.getMatIni_v());
            tblReprobados.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblReprobados.requestFocus();
        });
        clmMatInicialV.setOnEditCommit(dato -> {
            ReprobadosEspañolMatematicas p = dato.getRowValue();
            p.setMatIni_v(dato.getNewValue());
            p.setMatIni_t(p.getMatIni_n() + p.getMatIni_v());
            tblReprobados.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblReprobados.requestFocus();
        });
    }
    
    private void gestionarEventosMatFinal(TableColumn<ReprobadosEspañolMatematicas, Integer> clmMatFinalN, TableColumn<ReprobadosEspañolMatematicas, Integer> clmMatFinalV){
        clmMatFinalN.setOnEditCommit(dato -> {
            ReprobadosEspañolMatematicas p = dato.getRowValue();
            p.setMatFin_n(dato.getNewValue());
            p.setMatFin_t(p.getMatFin_n() + p.getMatFin_v());
            tblReprobados.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblReprobados.requestFocus();
        });
        clmMatFinalV.setOnEditCommit(dato -> {
            ReprobadosEspañolMatematicas p = dato.getRowValue();
            p.setMatFin_v(dato.getNewValue());
            p.setMatFin_t(p.getMatFin_n() + p.getMatFin_v());
            tblReprobados.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblReprobados.requestFocus();
        });
    }
    
    private void gestionarEventosEvaluadosEspañol(TableColumn<ReprobadosEspañolMatematicas, Integer> clmEvalEspN, TableColumn<ReprobadosEspañolMatematicas, Integer> clmEvalEspV){
        clmEvalEspN.setOnEditCommit(dato -> {
            ReprobadosEspañolMatematicas p = dato.getRowValue();
            p.setEvalEsp_n(dato.getNewValue());
            p.setEvalEsp_t(p.getEvalEsp_n() + p.getEvalEsp_v());
            tblReprobados.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblReprobados.requestFocus();
        });
        clmEvalEspV.setOnEditCommit(dato -> {
            ReprobadosEspañolMatematicas p = dato.getRowValue();
            p.setEvalEsp_v(dato.getNewValue());
            p.setEvalEsp_t(p.getEvalEsp_n() + p.getEvalEsp_v());
            tblReprobados.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblReprobados.requestFocus();
        });
    }
    
    private void gestionarEventosReprobadosEspañol(TableColumn<ReprobadosEspañolMatematicas, Integer> clmRepEspN, TableColumn<ReprobadosEspañolMatematicas, Integer> clmRepEspV){
        clmRepEspN.setOnEditCommit(dato -> {
            ReprobadosEspañolMatematicas p = dato.getRowValue();
            p.setReproEsp_n(dato.getNewValue());
            p.setReproEsp_t(p.getReproEsp_n() + p.getReproEsp_v());            
            p.setTasaEsp_n(((double)p.getReproEsp_n()/p.getEvalEsp_n())*100);
            p.setTasaEsp_t(((double)p.getReproEsp_t()/p.getEvalEsp_t())*100);
            tblReprobados.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblReprobados.requestFocus();
        });
        clmRepEspV.setOnEditCommit(dato -> {
            ReprobadosEspañolMatematicas p = dato.getRowValue();
            p.setReproEsp_v(dato.getNewValue());
            p.setReproEsp_t(p.getReproEsp_n() + p.getReproEsp_v());
            p.setTasaEsp_v(((double)p.getReproEsp_v()/p.getEvalEsp_v())*100);
            p.setTasaEsp_t(((double)p.getReproEsp_t()/p.getEvalEsp_t())*100);
            tblReprobados.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblReprobados.requestFocus();
        });
    }
    
    private void gestionarEventosEvaluadosMatematicas(TableColumn<ReprobadosEspañolMatematicas, Integer> clmEvalMatN, TableColumn<ReprobadosEspañolMatematicas, Integer> clmEvalMatV){
        clmEvalMatN.setOnEditCommit(dato -> {
            ReprobadosEspañolMatematicas p = dato.getRowValue();
            p.setEvalMat_n(dato.getNewValue());
            p.setEvalMat_t(p.getEvalMat_n() + p.getEvalMat_v());
            tblReprobados.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblReprobados.requestFocus();
        });
        clmEvalMatV.setOnEditCommit(dato -> {
            ReprobadosEspañolMatematicas p = dato.getRowValue();
            p.setEvalMat_v(dato.getNewValue());
            p.setEvalMat_t(p.getEvalMat_n() + p.getEvalMat_v());
            tblReprobados.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblReprobados.requestFocus();
        });
    }
    
    private void gestionarEventosReprobadosMatematicas(TableColumn<ReprobadosEspañolMatematicas, Integer> clmRepMatN, TableColumn<ReprobadosEspañolMatematicas, Integer> clmRepMatV){
        clmRepMatN.setOnEditCommit(dato -> {
            ReprobadosEspañolMatematicas p = dato.getRowValue();
            p.setReproMat_n(dato.getNewValue());
            p.setReproMat_t(p.getReproMat_n() + p.getReproMat_v());
            p.setTasaMat_n(((double)p.getReproMat_n()/p.getEvalMat_n())*100);
            p.setTasaMat_t(((double)p.getReproMat_t()/p.getEvalMat_t())*100);
            tblReprobados.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblReprobados.requestFocus();
        });
        clmRepMatV.setOnEditCommit(dato -> {
            ReprobadosEspañolMatematicas p = dato.getRowValue();
            p.setReproMat_v(dato.getNewValue());
            p.setReproMat_t(p.getReproMat_n() + p.getReproMat_v());
            p.setTasaMat_v(((double)p.getReproMat_v()/p.getEvalMat_v())*100);
            p.setTasaMat_t(((double)p.getReproMat_t()/p.getEvalMat_t())*100);
            tblReprobados.refresh();
            guardado = false;
            if(!txtCentro.getText().equals("")){
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
            tblReprobados.requestFocus();
        });
    }
    
    public void obtieneCentro(int codigo, String nombre_centro, String codigo_sace, String administracion_centro, String zona_centro, String nivel_centro, String ciclo_centro, String jornada_centro, String cobertura_centro){
        this.codigo = codigo;
        this.nombre_centro = nombre_centro;
        this.codigo_sace = codigo_sace;
        this.administracion_centro = administracion_centro;
        this.zona_centro = zona_centro;
        this.nivel_centro = nivel_centro;
        this.ciclo_centro = ciclo_centro;
        this.jornada_centro = jornada_centro;
        this.cobertura_centro = cobertura_centro;
        if(!txtCentro.getText().equals("")){
            if(Seleccionar.hayReprobadosCentro(conexion, codigo)){
                Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
                mensaje.setTitle("Aviso: el centro ya contiene datos");
                mensaje.setHeaderText("El centro " + nombre_centro + ", código " + codigo_sace + " ya contiene datos ingresados."
                        + "\n\n¿Desea modificar dichos datos?"
                        + "\n\nSeleccione Aceptar para modificar o Cancelar para solamente ver los datos.");
                mensaje.setContentText("");
                Optional<ButtonType> result = mensaje.showAndWait();
                if(result.get() == ButtonType.OK){
                    modificar = true;
                    nuevo = false;
                    tblReprobados.setEditable(true);
                }else{
                    modificar = false;
                    tblReprobados.setEditable(false);
                }
                lista_reprobados.clear();
                Seleccionar.obtenerReprobados(conexion, lista_reprobados, codigo);
                tblReprobados.setItems(lista_reprobados);
            }else{
                nuevo = true;
                modificar = false;
                lista_reprobados.clear();
                tblReprobados.setPlaceholder(new Label("No hay datos en el centro educativo.\nSeleccione el botón Nuevo para agregar"));
                btnNuevo.setDisable(false);
            }
        }
    }
    
    @FXML private void buscarCentro(){
        modificar = false;
        nuevo = false;
        lista_reprobados.clear();
        lblEstado.setText("");
        if(Seleccionar.hayCentros(conexion)){
            String formulario = "/com/teupa/vista/BuscarCentro.fxml";
            try {            
                FXMLLoader loader = new FXMLLoader(getClass().getResource(formulario));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene( new Scene((Pane) loader.load()));
                BuscarCentroCtrl controlador =  loader.<BuscarCentroCtrl>getController();
                controlador.inicializarDatos(null, null,this);
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
            mensaje.setHeaderText("No hay centros en el sistema, debe importar o agregar centros educativos en la pestaña Configurar.");
            mensaje.setContentText("");
            mensaje.showAndWait();
        }
    }
    
    @FXML
    public void nuevo(){        
        if(nivel_centro.equals("PREBÁSICA") && ciclo_centro.equals("NO APLICA")){
            ReprobadosEspañolMatematicas irf1 = new ReprobadosEspañolMatematicas(0, "PRIMERO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf1);
            ReprobadosEspañolMatematicas irf2 = new ReprobadosEspañolMatematicas(0, "SEGUNDO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_reprobados.add(irf2);
            ReprobadosEspañolMatematicas irf3 = new ReprobadosEspañolMatematicas(0, "TERCERO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, codigo);
            lista_reprobados.add(irf3);
        }
        if(nivel_centro.equals("BÁSICA") && ciclo_centro.equals("I Y II CICLO")){
            ReprobadosEspañolMatematicas irf1 = new ReprobadosEspañolMatematicas(0, "PRIMERO", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf1);
            ReprobadosEspañolMatematicas irf2 = new ReprobadosEspañolMatematicas(0, "SEGUNDO", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf2);
            ReprobadosEspañolMatematicas irf3 = new ReprobadosEspañolMatematicas(0, "TERCERO", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf3);
            ReprobadosEspañolMatematicas irf4 = new ReprobadosEspañolMatematicas(0, "CUARTO", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf4);
            ReprobadosEspañolMatematicas irf5 = new ReprobadosEspañolMatematicas(0, "QUINTO", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf5);
            ReprobadosEspañolMatematicas irf6 = new ReprobadosEspañolMatematicas(0, "SEXTO", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf6);            
        }
        if(nivel_centro.equals("BÁSICA") && ciclo_centro.equals("I, II Y III CICLO")){
            ReprobadosEspañolMatematicas irf1 = new ReprobadosEspañolMatematicas(0, "PRIMERO", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf1);
            ReprobadosEspañolMatematicas irf2 = new ReprobadosEspañolMatematicas(0, "SEGUNDO", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf2);
            ReprobadosEspañolMatematicas irf3 = new ReprobadosEspañolMatematicas(0, "TERCERO", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf3);
            ReprobadosEspañolMatematicas irf4 = new ReprobadosEspañolMatematicas(0, "CUARTO", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf4);
            ReprobadosEspañolMatematicas irf5 = new ReprobadosEspañolMatematicas(0, "QUINTO", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf5);
            ReprobadosEspañolMatematicas irf6 = new ReprobadosEspañolMatematicas(0, "SEXTO", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf6);
            ReprobadosEspañolMatematicas irf7 = new ReprobadosEspañolMatematicas(0, "SÉPTIMO", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf7);
            ReprobadosEspañolMatematicas irf8 = new ReprobadosEspañolMatematicas(0, "OCTAVO", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf8);
            ReprobadosEspañolMatematicas irf9 = new ReprobadosEspañolMatematicas(0, "NOVENO", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf9);
        }
        if(nivel_centro.equals("MEDIA") && ciclo_centro.equals("CBT Y BCH")){
            ReprobadosEspañolMatematicas irf7 = new ReprobadosEspañolMatematicas(0, "SÉPTIMO", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf7);
            ReprobadosEspañolMatematicas irf8 = new ReprobadosEspañolMatematicas(0, "OCTAVO", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf8);
            ReprobadosEspañolMatematicas irf9 = new ReprobadosEspañolMatematicas(0, "NOVENO", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf9);
            ReprobadosEspañolMatematicas irf10 = new ReprobadosEspañolMatematicas(0, "DÉCIMO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf10);
            ReprobadosEspañolMatematicas irf11 = new ReprobadosEspañolMatematicas(0, "UNDÉCIMO", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf11);
        }
        if(nivel_centro.equals("MEDIA") && ciclo_centro.equals("CBT, BTP Y BCH")){
            ReprobadosEspañolMatematicas irf7 = new ReprobadosEspañolMatematicas(0, "SÉPTIMO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf7);
            ReprobadosEspañolMatematicas irf8 = new ReprobadosEspañolMatematicas(0, "OCTAVO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf8);
            ReprobadosEspañolMatematicas irf9 = new ReprobadosEspañolMatematicas(0, "NOVENO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf9);
            ReprobadosEspañolMatematicas irf10 = new ReprobadosEspañolMatematicas(0, "DÉCIMO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf10);
            ReprobadosEspañolMatematicas irf11 = new ReprobadosEspañolMatematicas(0, "UNDÉCIMO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf11);
            ReprobadosEspañolMatematicas irf12 = new ReprobadosEspañolMatematicas(0, "DUODÉCIMO", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  codigo);
            lista_reprobados.add(irf12);
        }     
        tblReprobados.setItems(lista_reprobados);
        //Verifica si el centro ya contiene datos ingresados en el formulario de Resultado Final
        if(Seleccionar.hayMatriculaInicialCentro(conexion, codigo)){
            System.out.println("\nHay matricula Inicial y Final para el centro");
            lista_reprobados.clear();
            Seleccionar.obtenerMatriculaInicialFinalCentro(conexion, lista_reprobados, codigo);
            tblReprobados.setItems(lista_reprobados);
            System.out.println("La lista contiene " + lista_reprobados.size());
        }
        int matricula_final = Seleccionar.obtenerMatriculaFinalCentroCodigo(conexion, codigo_sace);
        lblTitulo.setText("Matrícula total según SACE " + matricula_final);
        if(modificar){
            lblEstado.setText("Modifique los datos y luego haga clic en Aceptar");
        }
        if(nuevo){
            lblEstado.setText("Ingrese los datos y luego haga clic en Aceptar");
        }
        btnNuevo.setDisable(true);
        btnCancelar.setDisable(false);        
    }
    
    @FXML
    public void modificar(){
        
    }
    
    @FXML
    public void eliminar(){
        
    }
    
    @FXML
    public void aceptar(){
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
                    if(validarDatos(lista_reprobados)){
                        modificarDatosCentro(lista_reprobados);
                        //modificar = false;
                        lista_reprobados.clear();
                        //Seleccionar.obtenerReprobadosCentro(conexion, lista_reprobados, codigo);
                        //tblReprobados.setItems(lista_reprobados);
                        Alert info = new Alert(Alert.AlertType.INFORMATION);
                        info.setTitle("Datos modificados");
                        info.setHeaderText("Los datos del centro se modificaron correctamente.");
                        info.setContentText("");
                        info.showAndWait();
                        tblReprobados.setDisable(true);
                        txtCentro.clear();
                        lblEstado.setText("");
                    }else{
                        Alert aviso = new Alert(Alert.AlertType.WARNING);
                        aviso.setTitle("Error en datos ingresados");
                        aviso.setHeaderText("Se detectó que hay errores en los datos.\n\n" + error_fila + "."
                                + "\n\nDebe corregir los datos para poder guardar.");
                        aviso.setContentText("");
                        aviso.showAndWait();
                    }
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
                    if(validarDatos(lista_reprobados)){
                        guardarDatosCentro(lista_reprobados);
                        nuevo = false;
                        guardado = true;
                        lista_reprobados.clear();
                        tblReprobados.setDisable(true);
                        lblTitulo.setText("");                        
                        Alert info = new Alert(Alert.AlertType.INFORMATION);
                        info.setTitle("Datos guardados");
                        info.setHeaderText("Los datos del centro se guardaron correctamente.");
                        info.setContentText("");
                        info.showAndWait();
                        lblEstado.setText("");
                    }else{
                        Alert aviso = new Alert(Alert.AlertType.WARNING);
                        aviso.setTitle("Error en datos ingresados");
                        aviso.setHeaderText("Se detectó que hay errores en los datos.\n\n" + error_fila + "."
                                + "\n\nDebe corregir los datos para poder guardar.");
                        aviso.setContentText("");
                        aviso.showAndWait();
                    }
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
    
    private void modificarDatosCentro(ObservableList<ReprobadosEspañolMatematicas> lista_rep){
        System.out.println("\n\nModificando centro: " + nombre_centro + " (" + codigo_sace + ")");
        for (ReprobadosEspañolMatematicas lista : lista_rep) {
            if(Modificar.modificarReprobados(conexion, lista.getCodigo(), lista.getGrado(),
                    lista.getMatIni_n(), lista.getMatIni_v(), lista.getMatIni_t(), 
                    lista.getMatFin_n(), lista.getMatFin_v(), lista.getMatFin_t(), 
                    lista.getEvalEsp_n(), lista.getEvalEsp_v(), lista.getEvalEsp_t(), 
                    lista.getReproEsp_n(), lista.getReproEsp_v(), lista.getReproEsp_t(), 
                    lista.getTasaEsp_n(), lista.getTasaEsp_v(), lista.getTasaEsp_t(), 
                    lista.getEvalMat_n(), lista.getEvalMat_v(), lista.getEvalMat_t(), 
                    lista.getReproMat_n(), lista.getReproMat_v(), lista.getReproMat_t(), 
                    lista.getTasaMat_n(), lista.getTasaMat_v(), lista.getTasaMat_t())){
                System.out.println("Modificados: " + lista.getGrado());
            }
        }        
    }
    
    private void guardarDatosCentro(ObservableList<ReprobadosEspañolMatematicas> lista_reprobados){
        System.out.println("\n\nGuardando centro: " + nombre_centro + " (" + codigo_sace + ")");
        for (ReprobadosEspañolMatematicas lista : lista_reprobados) {
            if(Insertar.guardarReprobados(conexion, lista.getGrado().toUpperCase(),
                    lista.getMatIni_n(), lista.getMatIni_v(), lista.getMatIni_t(), 
                    lista.getMatFin_n(), lista.getMatFin_v(), lista.getMatFin_t(), 
                    lista.getEvalEsp_n(), lista.getEvalEsp_v(), lista.getEvalEsp_t(), 
                    lista.getReproEsp_n(), lista.getReproEsp_v(), lista.getReproEsp_t(), 
                    lista.getTasaEsp_n(), lista.getTasaEsp_v(), lista.getTasaEsp_t(), 
                    lista.getEvalMat_n(), lista.getEvalMat_v(), lista.getEvalMat_t(), 
                    lista.getReproMat_n(), lista.getReproMat_v(), lista.getReproMat_t(), 
                    lista.getTasaMat_n(), lista.getTasaMat_v(), lista.getTasaMat_t(),
                    codigo)){
                System.out.println("Guardados: " + lista.getGrado());
            }
        }        
    }
    
    private boolean validarDatos(ObservableList<ReprobadosEspañolMatematicas> lista){
        boolean valido = false;
        int contador = 0;
        for(int i=0; i<lista.size(); i++){
            String grado = lista.get(i).getGrado();
            int mat_final = lista.get(i).getMatFin_t();
            int eval_esp = lista.get(i).getEvalEsp_t();
            int rep_esp = lista.get(i).getReproEsp_t();
            int eval_mat = lista.get(i).getEvalMat_t();
            int rep_mat = lista.get(i).getReproMat_t();
            if(eval_esp > mat_final){
                error_fila = "Error en " + grado + " -> Los Evaluados En Español no deben ser mayor que la Matrícula Final";
                contador = 0;
                break;
            }
            if(rep_esp > mat_final){
                error_fila = "Error en " + grado + " -> Los Reprobados En Español no deben ser mayor que la Matrícula Final";
                contador = 0;
                break;
            }
            if(eval_mat > mat_final){
                error_fila = "Error en " + grado + " -> Los Evaluados En Matemáticas no deben ser mayor que la Matrícula Final";
                contador = 0;
                break;
            }
            if(rep_mat > mat_final){
                error_fila = "Error en " + grado + " -> Los Reprobados En Matemáticas no deben ser mayor que la Matrícula Final";
                contador = 0;
                break;
            }
            if(rep_esp > eval_esp){
                error_fila = "Error en " + grado + " -> Los Reprobados en Español no deben ser mayor que los Evaluados en Español";
                contador = 0;
                break;
            }
            if(rep_mat > eval_mat){
                error_fila = "Error en " + grado + " -> Los Reprobados en Matemáticas no deben ser mayor que los Evaluados en Matemáticas";
                contador = 0;
                break;
            }
            contador++;
        }
        if(contador > 0){
            valido = true;
        }else{
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Hay errores en los datos ingresados");
            mensaje.setHeaderText(error_fila);
            mensaje.setContentText("");
            mensaje.showAndWait();
        }
        return valido;
    }
    
    @FXML private void cancelar(){
        lblTitulo.setText("");
        txtCentro.setText("");
        lblEstado.setText("");
        if(lista_reprobados.size() > 0){
            lista_reprobados.clear();
        }
        nuevo = false;
        modificar = false;
        tblReprobados.setDisable(true);
        btnCancelar.setDisable(true);
    }
    
    @FXML
    public void salir(){
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
