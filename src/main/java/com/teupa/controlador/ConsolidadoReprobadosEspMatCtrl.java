/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.teupa.controlador;

import com.teupa.basedatos.ConexionMySql;
import com.teupa.basedatos.Seleccionar;
import com.teupa.modelo.ConsolidadoReprobadosEspMat;
import com.teupa.utilidades.ExportaHojaCalculoResultadoFinal;
import com.teupa.utilidades.ExportarHojaCalculoReprobados;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;


public class ConsolidadoReprobadosEspMatCtrl implements Initializable {
    
    @FXML private ComboBox <String> cmbAdministracion;
    @FXML private ComboBox <String> cmbZona;
    @FXML private ComboBox <String> cmbNivel;
    @FXML private ComboBox <String> cmbCiclo;
    @FXML private ComboBox <String> cmbJornada;
    @FXML private ComboBox <String> cmbCobertura;
    @FXML private Button btnMostrarConsolidado;
    @FXML private Label lblTitulo;
    @FXML private Label lblDescripcion;
    @FXML private TableView <ConsolidadoReprobadosEspMat> tblReprobados;
    @FXML private Label lblEstado;
    @FXML private TextField txtNombreArchivoExcell;
    @FXML private Button btnExportar;
    @FXML private Button btnAceptar;
    @FXML private Button btnCancelar;
    @FXML private Button btnSalir;
    
    //Conexion a la BD
    ConexionMySql conn = new ConexionMySql();
    Connection conexion = conn.establecerConexion();
    
    //Para rutas para exportar a Excel
    String ruta_archivo_excell = null;
    String nombre_archivo_excell = null;
    
    //Variable para saber si se ha guardado los datos agregados al TableView
    boolean guardado = true;
    
    //Variables banderas para saber
    boolean nuevo = false;
    boolean modificar = false;
    boolean eliminar = false;
    
    //Son llenadas desde otro formulario
    int codigo = 0;
    public String nombre_centro;
    public String codigo_sace;
    public String nivel_centro;
    
    //Fecha actual
    Date fecha = new Date();
    
    //Listas de listas para Combobox fijos
    ObservableList<String> lista_administracion = FXCollections.observableArrayList("GUBERNAMENTAL", "PRIVADO");
    ObservableList<String> lista_zonas = FXCollections.observableArrayList("RURAL", "URBANO");
    ObservableList<String> lista_niveles = FXCollections.observableArrayList("BÁSICA", "PREBÁSICA", "MEDIA");
    ObservableList<String> lista_ciclos = FXCollections.observableArrayList("");
    ObservableList<String> lista_jornadas = FXCollections.observableArrayList("MATUTINA", "VESPERTINA", "NOCTURNA", "MATUTINA Y VESPERTINA", "TRES JORNADAS", "DISTANCIA");
    ObservableList<String> lista_coberturas = FXCollections.observableArrayList("OFICIAL", "PROHECO", "ALCALDÍA", "OTRO");
    
    //Lista para la tabla
    private ObservableList<ConsolidadoReprobadosEspMat> lista_reprobados = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbAdministracion.setItems(lista_administracion);
        cmbZona.setItems(lista_zonas);
        cmbNivel.setItems(lista_niveles);
        cmbCiclo.setItems(lista_ciclos);
        cmbJornada.setItems(lista_jornadas);
        cmbCobertura.setItems(lista_coberturas);
        cmbAdministracion.setDisable(false);
        cmbZona.setDisable(true);
        cmbNivel.setDisable(true);
        cmbCiclo.setDisable(true);
        cmbJornada.setDisable(true);
        cmbCobertura.setDisable(true);
        lblTitulo.setText("");
        lblDescripcion.setText("");
        lblEstado.setText("");
        btnMostrarConsolidado.setDisable(true);
        // GRADO
        TableColumn<ConsolidadoReprobadosEspMat, String> clmGrado = new TableColumn("GRADO");
	clmGrado.setMinWidth(120);
        clmGrado.setMaxWidth(120);
        clmGrado.setCellValueFactory(dato -> dato.getValue().GradoProperty());
        
        // MAT INICIAL
        TableColumn<ConsolidadoReprobadosEspMat, Integer> clmMatInicialN = new TableColumn("N");
        clmMatInicialN.setMinWidth(45);
        clmMatInicialN.setMaxWidth(45);
        clmMatInicialN.setCellValueFactory(dato -> dato.getValue().MatIni_nProperty().asObject());
        clmMatInicialN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ConsolidadoReprobadosEspMat, Integer> clmMatInicialV = new TableColumn("V");
        clmMatInicialV.setMinWidth(45);
        clmMatInicialV.setMaxWidth(45);
        clmMatInicialV.setCellValueFactory(dato -> dato.getValue().MatIni_vProperty().asObject());
        clmMatInicialV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ConsolidadoReprobadosEspMat, Integer> clmMatInicialT = new TableColumn("T");
        clmMatInicialT.setMinWidth(55);
        clmMatInicialT.setMaxWidth(55);
        clmMatInicialT.setCellValueFactory(dato -> dato.getValue().MatIni_tProperty().asObject());
        TableColumn clmMatInicial = new TableColumn("MAT INICIAL");	
        clmMatInicial.setMinWidth(145);
        clmMatInicial.setMaxWidth(145);
        clmMatInicial.getColumns().addAll(clmMatInicialN, clmMatInicialV, clmMatInicialT);
        
        // MAT FINAL
        TableColumn<ConsolidadoReprobadosEspMat, Integer> clmMatFinalN = new TableColumn("N");
        clmMatFinalN.setMinWidth(45);
        clmMatFinalN.setMaxWidth(45);
        clmMatFinalN.setCellValueFactory(dato -> dato.getValue().MatFin_nProperty().asObject());
        clmMatFinalN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ConsolidadoReprobadosEspMat, Integer> clmMatFinalV = new TableColumn("V");
        clmMatFinalV.setMinWidth(45);
        clmMatFinalV.setMaxWidth(45);
        clmMatFinalV.setCellValueFactory(dato -> dato.getValue().MatFin_vProperty().asObject());
        clmMatFinalV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ConsolidadoReprobadosEspMat, Integer> clmMatFinalT = new TableColumn("T");
        clmMatFinalT.setMinWidth(55);
        clmMatFinalT.setMaxWidth(55);
        clmMatFinalT.setCellValueFactory(dato -> dato.getValue().MatFin_tProperty().asObject());
        TableColumn clmMatFinal = new TableColumn("MAT FINAL");
        clmMatFinal.setMinWidth(145);
        clmMatFinal.setMaxWidth(145);
        clmMatFinal.getColumns().addAll(clmMatFinalN, clmMatFinalV, clmMatFinalT);
        
        // EVALUADOS EN ESPAÑOL
        TableColumn<ConsolidadoReprobadosEspMat, Integer> clmEvalEspN = new TableColumn("N");
        clmEvalEspN.setMinWidth(45);
        clmEvalEspN.setMaxWidth(45);
        clmEvalEspN.setCellValueFactory(dato -> dato.getValue().EvalEsp_nProperty().asObject());
        clmEvalEspN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ConsolidadoReprobadosEspMat, Integer> clmEvalEspV = new TableColumn("V");
        clmEvalEspV.setMinWidth(45);
        clmEvalEspV.setMaxWidth(45);
        clmEvalEspV.setCellValueFactory(dato -> dato.getValue().EvalEsp_vProperty().asObject());
        clmEvalEspV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ConsolidadoReprobadosEspMat, Integer> clmEvalEspT = new TableColumn("T");
        clmEvalEspT.setMinWidth(55);
        clmEvalEspT.setMaxWidth(55);
        clmEvalEspT.setCellValueFactory(dato -> dato.getValue().EvalEsp_tProperty().asObject());
        TableColumn clmEvalEsp = new TableColumn("EVAL. ESPAÑOL");
	clmEvalEsp.setMinWidth(145);
        clmEvalEsp.setMaxWidth(145);
        clmEvalEsp.getColumns().addAll(clmEvalEspN, clmEvalEspV, clmEvalEspT);
        
        // REPROBADOS ESPAÑOL
        TableColumn<ConsolidadoReprobadosEspMat, Integer> clmRepEspN = new TableColumn("N");
        clmRepEspN.setMinWidth(45);
        clmRepEspN.setMaxWidth(45);
        clmRepEspN.setCellValueFactory(dato -> dato.getValue().ReproEsp_nProperty().asObject());
        clmRepEspN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ConsolidadoReprobadosEspMat, Integer> clmRepEspV = new TableColumn("V");
        clmRepEspV.setMinWidth(45);
        clmRepEspV.setMaxWidth(45);
        clmRepEspV.setCellValueFactory(dato -> dato.getValue().ReproEsp_vProperty().asObject());
        clmRepEspV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ConsolidadoReprobadosEspMat, Integer> clmRepEspT = new TableColumn("T");
        clmRepEspT.setMinWidth(55);
        clmRepEspT.setMaxWidth(55);
        clmRepEspT.setCellValueFactory(dato -> dato.getValue().ReproEsp_tProperty().asObject());
        TableColumn clmReproEsp = new TableColumn("REPRO. ESPOAÑOL");
	clmReproEsp.setMinWidth(145);
        clmReproEsp.setMaxWidth(145);
        clmReproEsp.getColumns().addAll(clmRepEspN, clmRepEspV, clmRepEspT);
        
        ////TASA DE REPROBACIÓN ESPAÑOL
        TableColumn<ConsolidadoReprobadosEspMat, Double> clmTasaEspN = new TableColumn("N");
        clmTasaEspN.setMinWidth(45);
        clmTasaEspN.setMaxWidth(45);
        clmTasaEspN.setCellValueFactory(dato -> dato.getValue().TasaEsp_nProperty().asObject());
        clmTasaEspN.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        TableColumn<ConsolidadoReprobadosEspMat, Double> clmTasaEspV = new TableColumn("V");
        clmTasaEspV.setMinWidth(45);
        clmTasaEspV.setMaxWidth(45);
        clmTasaEspV.setCellValueFactory(dato -> dato.getValue().TasaEsp_vProperty().asObject());
        clmTasaEspV.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        TableColumn<ConsolidadoReprobadosEspMat, Double> clmTasaEspT = new TableColumn("T");
        clmTasaEspT.setMinWidth(55);
        clmTasaEspT.setMaxWidth(55);
        clmTasaEspT.setCellValueFactory(dato -> dato.getValue().TasaEsp_tProperty().asObject());
        TableColumn clmTasaEsp = new TableColumn("TASA REP ESP");
	clmTasaEsp.setMinWidth(145);
        clmTasaEsp.setMaxWidth(145);
        clmTasaEsp.getColumns().addAll(clmTasaEspN, clmTasaEspV, clmTasaEspT);
        
        // EVALUADOS EN MATEMATICAS
        TableColumn<ConsolidadoReprobadosEspMat, Integer> clmEvalMatN = new TableColumn("N");
        clmEvalMatN.setMinWidth(45);
        clmEvalMatN.setMaxWidth(45);
        clmEvalMatN.setCellValueFactory(dato -> dato.getValue().EvalMat_nProperty().asObject());
        clmEvalMatN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ConsolidadoReprobadosEspMat, Integer> clmEvalMatV = new TableColumn("V");
        clmEvalMatV.setMinWidth(45);
        clmEvalMatV.setMaxWidth(45);
        clmEvalMatV.setCellValueFactory(dato -> dato.getValue().EvalMat_vProperty().asObject());
        clmEvalMatV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ConsolidadoReprobadosEspMat, Integer> clmEvalMatT = new TableColumn("T");
        clmEvalMatT.setMinWidth(55);
        clmEvalMatT.setMaxWidth(55);
        clmEvalMatT.setCellValueFactory(dato -> dato.getValue().EvalMat_tProperty().asObject());
        TableColumn clmEvalMat = new TableColumn("EVAL. MATEMÁTICAS");
	clmEvalMat.setMinWidth(145);
        clmEvalMat.setMaxWidth(145);
        clmEvalMat.getColumns().addAll(clmEvalMatN, clmEvalMatV, clmEvalMatT);
        
        // REPROBADOS MATEMÁTICAS
        TableColumn<ConsolidadoReprobadosEspMat, Integer> clmRepMatN = new TableColumn("N");
        clmRepMatN.setMinWidth(45);
        clmRepMatN.setMaxWidth(45);
        clmRepMatN.setCellValueFactory(dato -> dato.getValue().ReproMat_nProperty().asObject());
        clmRepMatN.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ConsolidadoReprobadosEspMat, Integer> clmRepMatV = new TableColumn("V");
        clmRepMatV.setMinWidth(45);
        clmRepMatV.setMaxWidth(45);
        clmRepMatV.setCellValueFactory(dato -> dato.getValue().ReproMat_vProperty().asObject());
        clmRepMatV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableColumn<ConsolidadoReprobadosEspMat, Integer> clmRepMatT = new TableColumn("T");
        clmRepMatT.setMinWidth(55);
        clmRepMatT.setMaxWidth(55);
        clmRepMatT.setCellValueFactory(dato -> dato.getValue().ReproMat_tProperty().asObject());
        TableColumn clmReproMat = new TableColumn("REPRO. MATEMÁTICAS");
	clmReproMat.setMinWidth(145);
        clmReproMat.setMaxWidth(145);
        clmReproMat.getColumns().addAll(clmRepMatN, clmRepMatV, clmRepMatT);
        
        ////TASA DE REPROBACIÓN MATEMATICAS
        TableColumn<ConsolidadoReprobadosEspMat, Double> clmTasaMatN = new TableColumn("N");
        clmTasaMatN.setMinWidth(45);
        clmTasaMatN.setMaxWidth(45);
        clmTasaMatN.setCellValueFactory(dato -> dato.getValue().TasaMat_nProperty().asObject());
        clmTasaMatN.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        TableColumn<ConsolidadoReprobadosEspMat, Double> clmTasaMatV = new TableColumn("V");
        clmTasaMatV.setMinWidth(45);
        clmTasaMatV.setMaxWidth(45);
        clmTasaMatV.setCellValueFactory(dato -> dato.getValue().TasaMat_vProperty().asObject());
        clmTasaMatV.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        TableColumn<ConsolidadoReprobadosEspMat, Double> clmTasaMatT = new TableColumn("T");
        clmTasaMatT.setMinWidth(55);
        clmTasaMatT.setMaxWidth(55);
        clmTasaMatT.setCellValueFactory(dato -> dato.getValue().TasaMat_tProperty().asObject());
        TableColumn clmTasaMat = new TableColumn("TASA REP MAT");
	clmTasaMat.setMinWidth(145);
        clmTasaMat.setMaxWidth(145);
        clmTasaMat.getColumns().addAll(clmTasaMatN, clmTasaMatV, clmTasaMatT);
        
        //Asigna las columnas a la tabla
        tblReprobados.getColumns().addAll(clmGrado, clmMatInicial, clmMatFinal, clmEvalEsp, clmReproEsp, clmTasaEsp, clmEvalMat, clmReproMat, clmTasaMat);
        deshabilitarControlesIniciales();
    }
    
    public void inicializarDatos(){
        
    }
    
    private void deshabilitarControlesIniciales(){
        btnExportar.setDisable(true);
        txtNombreArchivoExcell.setDisable(true);
        btnAceptar.setDisable(true);
        btnCancelar.setDisable(true);
        btnSalir.setDisable(false);
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
            ObservableList<String> lista_ciclos = FXCollections.observableArrayList( "CBT Y BTP");
            cmbCiclo.setItems(lista_ciclos);
        }
        
    }
        
    @FXML private void habilitaZona(){
        cmbZona.setDisable(false);
    }
    
    @FXML private void habilitaNivel(){
        cmbNivel.setDisable(false);
    }
    
    private void habilitarCiclo(){
        cmbCiclo.setDisable(false);
    }
    
    @FXML private void habilitarJornada(){
        cmbJornada.setDisable(false);
    }
    
    @FXML private void habilitarCobertura(){
        cmbCobertura.setDisable(false);
    }
    
    @FXML private void activarMostrarDatos(){
        btnMostrarConsolidado.setDisable(false);
    }
    
    @FXML private void mostrarConsolidado(){
        if(cmbCiclo.getValue().length() > 0){
            lista_reprobados.clear();
            String adminstracion = cmbAdministracion.getValue();
            String zona = cmbZona.getValue();
            String nivel = cmbNivel.getValue();
            String ciclo = cmbCiclo.getValue();
            String jornada = cmbJornada.getValue();
            String cobertura = cmbCobertura.getValue();
            System.out.println("\nParametros que se mandan  adminstracion: " + adminstracion + "\t zona: " + zona + "\t nivel: " + nivel + "\t ciclo: " + ciclo + "\n");            
            Seleccionar.obtenerReprobadosEspMatConsolidado(conexion, lista_reprobados, adminstracion, zona, nivel, ciclo, jornada, cobertura);
            tblReprobados.setItems(lista_reprobados);
            String descripcion = "Administración: " + cmbAdministracion.getValue() + "  Zona: " + cmbZona.getValue() + "  Nivel: " + cmbNivel.getValue() + "  Ciclo: " + cmbCiclo.getValue() + "  Cobertura: " + cmbCobertura.getValue();
            int centros_consolidados = Seleccionar.obtenerTotalConsolidadoCentro(conexion, adminstracion, zona, nivel, ciclo, jornada, cobertura);
            int centros_tipo = Seleccionar.obtenerCentrosTipo(conexion, adminstracion, zona, nivel, ciclo, jornada, cobertura);
            if(lista_reprobados.size() != 0){                
                lblTitulo.setText("CONSOLIDADO DE RESULTADO FINAL");
                lblDescripcion.setText(descripcion);                
                lblEstado.setText("Centros consolidados " + centros_consolidados + " de " + centros_tipo);
                txtNombreArchivoExcell.setText("Consolidado_resulatado_final_" + fecha.getTime());
                txtNombreArchivoExcell.setDisable(false);
                btnExportar.setDisable(false);
            }else{
                tblReprobados.setPlaceholder(new javafx.scene.control.Label("NO HAY DATOS PARA LOS CENTROS EDUCATIVOS"
                        + "\n" + descripcion));
                lblEstado.setText("Centros consolidados 0 de " + centros_tipo);
                txtNombreArchivoExcell.setText("");
                txtNombreArchivoExcell.setDisable(true);
                btnExportar.setDisable(true);
            }            
        }
    }

    @FXML private void exportar(){
        if(!txtNombreArchivoExcell.getText().equals("")){
            DirectoryChooser directorio = new DirectoryChooser();
            directorio.setTitle("Seleccionar ruta para archivo a exportar");
            directorio.setInitialDirectory(new File(System.getProperty("user.home")));
            File ruta_directorio = directorio.showDialog(null);
            if(ruta_directorio != null){
                ruta_archivo_excell = ruta_directorio.getAbsolutePath() + System.getProperty("file.separator");
                nombre_archivo_excell = txtNombreArchivoExcell.getText();
                System.out.println("\n\tLa ruta el archivo a exportar es " + ruta_archivo_excell);
                String administracion = cmbAdministracion.getValue();
                String area = cmbZona.getValue();            ;
                String nivel = cmbNivel.getValue();
                String tipo = cmbCiclo.getValue();
                ExportarHojaCalculoReprobados exportar = new ExportarHojaCalculoReprobados();
                exportar.obtieneDatos(ruta_archivo_excell + nombre_archivo_excell + ".xls", administracion, area, nivel, tipo, lista_reprobados);
            }
        }else{
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Nombre de archivo vacio");
            mensaje.setHeaderText("Escriba un nombre para el archivo que desea guardar.");
            mensaje.setContentText("");
            mensaje.showAndWait();
            txtNombreArchivoExcell.requestFocus();
        }
    }
    
    @FXML private void modificar(){
        System.out.println("\nModificar");
    }
    
    @FXML private void eliminar(){
        System.out.println("\nEliminar");
    }
    
    @FXML private void cancelar(){
        System.out.println("\nCancelar");
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
