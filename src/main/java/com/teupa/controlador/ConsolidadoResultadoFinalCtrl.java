/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.teupa.controlador;

import com.teupa.basedatos.ConexionMySql;
import com.teupa.basedatos.Seleccionar;
import com.teupa.modelo.ConsolidadoResultadoFinal;
import com.teupa.utilidades.ExportaHojaCalculoResultadoFinal;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hector
 */
public class ConsolidadoResultadoFinalCtrl implements Initializable {

    @FXML private ComboBox <String> cmbAdministracion;
    @FXML private ComboBox <String> cmbZona;
    @FXML private ComboBox <String> cmbNivel;
    @FXML private ComboBox <String> cmbCiclo;
    @FXML private ComboBox <String> cmbJornada;
    @FXML private ComboBox <String> cmbCobertura;
    @FXML private Button btnMostrarConsolidado;
    @FXML private Label lblTitulo;
    @FXML private Label lblDescripcion;
    @FXML private TableView <ConsolidadoResultadoFinal> tblResultadoFinal;
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
    
    //Variable para el nombre de la tabla a guardar
    String nombre_tabla;
    
    //Listas de listas para Combobox fijos
    ObservableList<String> lista_administracion = FXCollections.observableArrayList("GUBERNAMENTAL", "PRIVADO");
    ObservableList<String> lista_zonas = FXCollections.observableArrayList("RURAL", "URBANA");
    ObservableList<String> lista_niveles = FXCollections.observableArrayList("BÁSICA", "PREBÁSICA", "MEDIA");
    ObservableList<String> lista_ciclos = FXCollections.observableArrayList("");
    ObservableList<String> lista_jornadas = FXCollections.observableArrayList("MATUTINA", "VESPERTINA", "NOCTURNA", "MATUTINA Y VESPERTINA", "TRES JORNADAS", "DISTANCIA");
    ObservableList<String> lista_coberturas = FXCollections.observableArrayList("OFICIAL", "PROHECO", "ALCALDÍA", "OTRO");
    
    //Lista para la tabla
    private ObservableList<ConsolidadoResultadoFinal> lista_consolidadoResultadosFinales = FXCollections.observableArrayList();
    
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
        TableColumn<ConsolidadoResultadoFinal, String> clmGrado = new TableColumn("GRADO");
	clmGrado.setMinWidth(100);
        clmGrado.setMaxWidth(100);
        clmGrado.setCellValueFactory(dato -> dato.getValue().GradoProperty());
        // MAT INICIAL
        TableColumn<ConsolidadoResultadoFinal, Integer> clmMatInicialN = new TableColumn("N");
        clmMatInicialN.setMinWidth(50);
        clmMatInicialN.setMaxWidth(50);
        clmMatInicialN.setCellValueFactory(dato -> dato.getValue().MatInicial_nProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmMatInicialV = new TableColumn("V");
        clmMatInicialV.setMinWidth(50);
        clmMatInicialV.setMaxWidth(50);
        clmMatInicialV.setCellValueFactory(dato -> dato.getValue().MatInicial_vProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmMatInicialT = new TableColumn("T");
        clmMatInicialT.setMinWidth(60);
        clmMatInicialT.setMaxWidth(60);
        clmMatInicialT.setCellValueFactory(dato -> dato.getValue().MatInicial_tProperty().asObject());
        TableColumn clmMatInicial = new TableColumn("MAT INICIAL");
	clmMatInicial.setMinWidth(160);
        clmMatInicial.setMaxWidth(160);
        clmMatInicial.getColumns().addAll(clmMatInicialN, clmMatInicialV, clmMatInicialT);
        // MAT FINAL
        TableColumn<ConsolidadoResultadoFinal, Integer> clmMatFinalN = new TableColumn("N");
        clmMatFinalN.setMinWidth(50);
        clmMatFinalN.setMaxWidth(50);
        clmMatFinalN.setCellValueFactory(dato -> dato.getValue().MatFinal_nProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmMatFinalV = new TableColumn("V");
        clmMatFinalV.setMinWidth(50);
        clmMatFinalV.setMaxWidth(50);
        clmMatFinalV.setCellValueFactory(dato -> dato.getValue().MatFinal_vProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmMatFinalT = new TableColumn("T");
        clmMatFinalT.setMinWidth(60);
        clmMatFinalT.setMaxWidth(60);
        clmMatFinalT.setCellValueFactory(dato -> dato.getValue().MatFinal_tProperty().asObject());
        TableColumn clmMatFinal = new TableColumn("MAT FINAL");
	clmMatFinal.setMinWidth(160);
        clmMatFinal.setMaxWidth(160);
        clmMatFinal.getColumns().addAll(clmMatFinalN, clmMatFinalV, clmMatFinalT);
        // INGRESOS
        TableColumn<ConsolidadoResultadoFinal, Integer> clmIngresoN = new TableColumn("N");
        clmIngresoN.setMinWidth(50);
        clmIngresoN.setMaxWidth(50);
        clmIngresoN.setCellValueFactory(dato -> dato.getValue().Ingre_nProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmIngresoV = new TableColumn("V");
        clmIngresoV.setMinWidth(50);
        clmIngresoV.setMaxWidth(50);
        clmIngresoV.setCellValueFactory(dato -> dato.getValue().Ingre_vProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmIngresoT = new TableColumn("T");
        clmIngresoT.setMinWidth(60);
        clmIngresoT.setMaxWidth(60);
        clmIngresoT.setCellValueFactory(dato -> dato.getValue().Ingre_tProperty().asObject());
        TableColumn clmIngresos = new TableColumn("INGRESOS");
	clmIngresos.setMinWidth(160);
        clmIngresos.setMaxWidth(160);
        clmIngresos.getColumns().addAll(clmIngresoN, clmIngresoV, clmIngresoT);
        // TRASLADOS
        TableColumn<ConsolidadoResultadoFinal, Integer> clmTrasladoN = new TableColumn("N");
        clmTrasladoN.setMinWidth(50);
        clmTrasladoN.setMaxWidth(50);
        clmTrasladoN.setCellValueFactory(dato -> dato.getValue().Trasla_nProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmTrasladoV = new TableColumn("V");
        clmTrasladoV.setMinWidth(50);
        clmTrasladoV.setMaxWidth(50);
        clmTrasladoV.setCellValueFactory(dato -> dato.getValue().Trasla_vProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmTrasladoT = new TableColumn("T");
        clmTrasladoT.setMinWidth(60);
        clmTrasladoT.setMaxWidth(60);
        clmTrasladoT.setCellValueFactory(dato -> dato.getValue().Trasla_tProperty().asObject());
        TableColumn clmTraslados = new TableColumn("TRASLADOS");
	clmTraslados.setMinWidth(160);
        clmTraslados.setMaxWidth(160);
        clmTraslados.getColumns().addAll(clmTrasladoN, clmTrasladoV, clmTrasladoT);
        // DESERTORES
        TableColumn<ConsolidadoResultadoFinal, Integer> clmDesertorN = new TableColumn("N");
        clmDesertorN.setMinWidth(50);
        clmDesertorN.setMaxWidth(50);
        clmDesertorN.setCellValueFactory(dato -> dato.getValue().Deser_nProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmDesertorV = new TableColumn("V");
        clmDesertorV.setMinWidth(50);
        clmDesertorV.setMaxWidth(50);
        clmDesertorV.setCellValueFactory(dato -> dato.getValue().Deser_vProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmDesertorT = new TableColumn("T");
        clmDesertorT.setMinWidth(60);
        clmDesertorT.setMaxWidth(60);
        clmDesertorT.setCellValueFactory(dato -> dato.getValue().Deser_tProperty().asObject());
        TableColumn clmDesertores = new TableColumn("DESERTORES");
	clmDesertores.setMinWidth(160);
        clmDesertores.setMaxWidth(160);
        clmDesertores.getColumns().addAll(clmDesertorN, clmDesertorV, clmDesertorT);
        // EVALUADOS
        TableColumn<ConsolidadoResultadoFinal, Integer> clmEvaluadosN = new TableColumn("N");
        clmEvaluadosN.setMinWidth(50);
        clmEvaluadosN.setMaxWidth(50);
        clmEvaluadosN.setCellValueFactory(dato -> dato.getValue().Evalu_nProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmEvaluadosV = new TableColumn("V");
        clmEvaluadosV.setMinWidth(50);
        clmEvaluadosV.setMaxWidth(50);
        clmEvaluadosV.setCellValueFactory(dato -> dato.getValue().Evalu_vProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmEvaluadosT = new TableColumn("T");
        clmEvaluadosT.setMinWidth(60);
        clmEvaluadosT.setMaxWidth(60);
        clmEvaluadosT.setCellValueFactory(dato -> dato.getValue().Evalu_tProperty().asObject());
        TableColumn clmEvaluados = new TableColumn("EVALUADOS");
	clmEvaluados.setMinWidth(160);
        clmEvaluados.setMaxWidth(160);
        clmEvaluados.getColumns().addAll(clmEvaluadosN, clmEvaluadosV, clmEvaluadosT);
        // INICIAL
        TableColumn<ConsolidadoResultadoFinal, Integer> clmInicialN = new TableColumn("N");
        clmInicialN.setMinWidth(50);
        clmInicialN.setMaxWidth(50);
        clmInicialN.setCellValueFactory(dato -> dato.getValue().Inic_nProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmInicialV = new TableColumn("V");
        clmInicialV.setMinWidth(50);
        clmInicialV.setMaxWidth(50);
        clmInicialV.setCellValueFactory(dato -> dato.getValue().Inic_vProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmInicialT = new TableColumn("T");
        clmInicialT.setMinWidth(60);
        clmInicialT.setMaxWidth(60);
        clmInicialT.setCellValueFactory(dato -> dato.getValue().Inic_tProperty().asObject());
        TableColumn clmInicial = new TableColumn("INICIAL");
	clmInicial.setMinWidth(160);
        clmInicial.setMaxWidth(160);
        clmInicial.getColumns().addAll(clmInicialN, clmInicialV, clmInicialT);
        // BASICO
        TableColumn<ConsolidadoResultadoFinal, Integer> clmBasicoN = new TableColumn("N");
        clmBasicoN.setMinWidth(50);
        clmBasicoN.setMaxWidth(50);
        clmBasicoN.setCellValueFactory(dato -> dato.getValue().Basi_nProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmBasicoV = new TableColumn("V");
        clmBasicoV.setMinWidth(50);
        clmBasicoV.setMaxWidth(50);
        clmBasicoV.setCellValueFactory(dato -> dato.getValue().Basi_vProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmBasicoT = new TableColumn("T");
        clmBasicoT.setMinWidth(60);
        clmBasicoT.setMaxWidth(60);
        clmBasicoT.setCellValueFactory(dato -> dato.getValue().Basi_tProperty().asObject());
        TableColumn clmBasico = new TableColumn("BÁSICO");
	clmBasico.setMinWidth(160);
        clmBasico.setMaxWidth(160);
        clmBasico.getColumns().addAll(clmBasicoN, clmBasicoV, clmBasicoT);
        // AVANZADO
        TableColumn<ConsolidadoResultadoFinal, Integer> clmAvanzadoN = new TableColumn("N");
        clmAvanzadoN.setMinWidth(50);
        clmAvanzadoN.setMaxWidth(50);
        clmAvanzadoN.setCellValueFactory(dato -> dato.getValue().Avan_nProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmAvanzadoV = new TableColumn("V");
        clmAvanzadoV.setMinWidth(50);
        clmAvanzadoV.setMaxWidth(50);
        clmAvanzadoV.setCellValueFactory(dato -> dato.getValue().Avan_vProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmAvanzadoT = new TableColumn("T");
        clmAvanzadoT.setMinWidth(60);
        clmAvanzadoT.setMaxWidth(60);
        clmAvanzadoT.setCellValueFactory(dato -> dato.getValue().Avan_tProperty().asObject());
        TableColumn clmAvanzado = new TableColumn("AVANZADO");
	clmAvanzado.setMinWidth(160);
        clmAvanzado.setMaxWidth(160);
        clmAvanzado.getColumns().addAll(clmAvanzadoN, clmAvanzadoV, clmAvanzadoT);
        // EXCELENTE
        TableColumn<ConsolidadoResultadoFinal, Integer> clmExcelenteN = new TableColumn("N");
        clmExcelenteN.setMinWidth(50);
        clmExcelenteN.setMaxWidth(50);
        clmExcelenteN.setCellValueFactory(dato -> dato.getValue().Exce_nProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmExcelenteV = new TableColumn("V");
        clmExcelenteV.setMinWidth(50);
        clmExcelenteV.setMaxWidth(50);
        clmExcelenteV.setCellValueFactory(dato -> dato.getValue().Exce_vProperty().asObject());
        TableColumn<ConsolidadoResultadoFinal, Integer> clmExcelenteT = new TableColumn("T");
        clmExcelenteT.setMinWidth(60);
        clmExcelenteT.setMaxWidth(60);
        clmExcelenteT.setCellValueFactory(dato -> dato.getValue().Exce_tProperty().asObject());
        TableColumn clmExcelente = new TableColumn("EXCELENTE");
	clmExcelente.setMinWidth(160);
        clmExcelente.setMaxWidth(160);
        clmExcelente.getColumns().addAll(clmExcelenteN, clmExcelenteV, clmExcelenteT);
        tblResultadoFinal.getColumns().addAll(clmGrado, clmMatInicial, clmMatFinal, clmIngresos, clmTraslados, clmDesertores, clmEvaluados, clmInicial, clmBasico, clmAvanzado, clmExcelente);        
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
            ObservableList<String> lista_ciclos = FXCollections.observableArrayList( "BTP", "BCH", "III CICLO Y BTP", "CBT, BTP Y BCH");
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
            lista_consolidadoResultadosFinales.clear();
            String adminstracion = cmbAdministracion.getValue();
            String zona = cmbZona.getValue();
            String nivel = cmbNivel.getValue();
            String ciclo = cmbCiclo.getValue();
            String jornada = cmbJornada.getValue();
            String cobertura = cmbCobertura.getValue();
            System.out.println("\nParametros que se mandan a la BD: adminstracion: " + adminstracion + "\tzona: " + zona + "\tnivel: " + nivel + "\tciclo: " + ciclo + "\tjornada: " + jornada + "\tcobertura: " + cobertura);            
            Seleccionar.obtenerResultadosFinalConsolidado(conexion, lista_consolidadoResultadosFinales, adminstracion, zona, nivel, ciclo, jornada, cobertura);
            tblResultadoFinal.setItems(lista_consolidadoResultadosFinales);
            String descripcion = "Administración: " + cmbAdministracion.getValue() + "  Zona: " + cmbZona.getValue() + "  Nivel: " + cmbNivel.getValue() + "  Ciclo: " + cmbCiclo.getValue() + "  Cobertura: " + cmbCobertura.getValue();
            int centros_consolidados = Seleccionar.obtenerTotalConsolidadoCentro(conexion, adminstracion, zona, nivel, ciclo, jornada, cobertura);
            int centros_tipo = Seleccionar.obtenerCentrosTipo(conexion, adminstracion, zona, nivel, ciclo, jornada, cobertura);
            if(lista_consolidadoResultadosFinales.size() > 0){                
                lblTitulo.setText("CONSOLIDADO DE RESULTADO FINAL");
                lblDescripcion.setText(descripcion);                
                lblEstado.setText("Consolidados " + centros_consolidados + " de " + centros_tipo + " centros de nivel " + nivel + " de zona " + zona);
                txtNombreArchivoExcell.setText("Consolidado_resulatado_final_" + fecha.getTime());
                txtNombreArchivoExcell.setDisable(false);
                btnExportar.setDisable(false);
            }else{
                tblResultadoFinal.setPlaceholder(new javafx.scene.control.Label("NO HAY DATOS PARA LOS CENTROS EDUCATIVOS"
                        + "\n" + descripcion));
                lblEstado.setText("Consolidados 0 / No consolidaos " + centros_tipo + "  de nivel " + nivel + " de zona " + zona);
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
                ExportaHojaCalculoResultadoFinal exportar = new ExportaHojaCalculoResultadoFinal();
                exportar.obtieneDatos(ruta_archivo_excell + nombre_archivo_excell + ".xls", administracion, area, nivel, tipo, lista_consolidadoResultadosFinales);
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
