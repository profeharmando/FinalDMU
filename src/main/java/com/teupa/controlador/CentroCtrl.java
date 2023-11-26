/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.teupa.controlador;

import com.teupa.basedatos.ConexionMySql;
import com.teupa.basedatos.Eliminar;
import com.teupa.basedatos.Insertar;
import com.teupa.basedatos.Modificar;
import com.teupa.basedatos.Seleccionar;
import com.teupa.modelo.Centro;
import java.net.URL;
import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
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
import javafx.stage.Stage;

public class CentroCtrl implements Initializable {

    @FXML private Button btnAgregar;
    @FXML private Button btnNuevo;
    @FXML private Button btnModificar;
    @FXML private Button btnEliminar;
    @FXML private Button btnAceptar;
    @FXML private Button btnCancelar;
    @FXML private Button btnSalir;
    @FXML private TextField txtCentro;
    @FXML private TextField txtCodigo;
    @FXML private ComboBox<String> cmbAdministracion;
    @FXML private ComboBox<String> cmbZona;
    @FXML private ComboBox<String> cmbNivel;
    @FXML private ComboBox<String> cmbCiclo;
    @FXML private ComboBox<String> cmbJornada;
    @FXML private ComboBox<String> cmbCobertura;
    @FXML private TextField txtMatriculaFinal;
    @FXML private TableView <Centro> tblCentro;
    @FXML private TableColumn <Centro, String> clmCentro;
    @FXML private TableColumn <Centro, String> clmCodigoCentro;
    @FXML private TableColumn <Centro, String> clmAdministracion;
    @FXML private TableColumn <Centro, String> clmZona;
    @FXML private TableColumn <Centro, String> clmNivel;
    @FXML private TableColumn <Centro, String> clmCiclo;
    @FXML private TableColumn <Centro, String> clmJornada;
    @FXML private TableColumn <Centro, String> clmCobertura;
    @FXML private TableColumn <Centro, Integer> clmMatriculaFinal;
    
    //Conexion a la BD
    ConexionMySql conn = new ConexionMySql();
    Connection conexion = conn.establecerConexion();
    
    //Variables banderas para saber
    boolean nuevo = false;
    boolean modificar = false;
    boolean eliminar = false;
    //Variable para saber si se ha guardado los docentes agregados al TableView
    boolean guardado = true;
    
    //Listas de listas para Combobox fijos
    ObservableList<String> lista_administracion = FXCollections.observableArrayList("GUBERNAMENTAL", "PRIVADO");
    ObservableList<String> lista_zonas = FXCollections.observableArrayList("RURAL", "URBANA");
    ObservableList<String> lista_niveles = FXCollections.observableArrayList("BÁSICA", "PREBÁSICA", "MEDIA");
    ObservableList<String> lista_ciclos = FXCollections.observableArrayList("I Y II CICLO", "I, II Y III CICLO", "CBT Y BTP", "NO APLICA");
    ObservableList<String> lista_jornadas = FXCollections.observableArrayList("MATUTINA", "VESPERTINA", "NOCTURNA", "MATUTINA Y VESPERTINA", "TRES JORNADAS", "DISTANCIA");
    ObservableList<String> lista_cobertura = FXCollections.observableArrayList("OFICIAL", "PROHECO", "ALCALDÍA", "OTRO");
    
    //Lista para la tabla tblCentro
    private ObservableList<Centro> lista_centros = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        deshabilitarControles();
        clmCentro.setCellValueFactory(dato -> dato.getValue().CentroProperty());
        clmCodigoCentro.setCellValueFactory(dato -> dato.getValue().CodigoCentroProperty());
        clmAdministracion.setCellValueFactory(dato -> dato.getValue().AdministracionProperty());
        clmZona.setCellValueFactory(dato -> dato.getValue().ZonaProperty());
        clmNivel.setCellValueFactory(dato -> dato.getValue().NivelProperty());
        clmCiclo.setCellValueFactory(dato -> dato.getValue().CicloProperty());
        clmJornada.setCellValueFactory(dato -> dato.getValue().JornadaProperty());
        clmCobertura.setCellValueFactory(dato -> dato.getValue().CoberturaProperty());
        clmMatriculaFinal.setCellValueFactory(dato -> dato.getValue().MatriculaFinalProperty().asObject());
        cmbAdministracion.setItems(lista_administracion);
        cmbZona.setItems(lista_zonas);
        cmbNivel.setItems(lista_niveles);
        cmbCiclo.setItems(lista_ciclos);
        cmbJornada.setItems(lista_jornadas);
        cmbCobertura.setItems(lista_cobertura);
        txtMatriculaFinal.setDisable(true);
        if(Seleccionar.hayCentros(conexion)){
            Seleccionar.obtenerDatosCentros(conexion, lista_centros);
            tblCentro.setItems(lista_centros);
            tblCentro.setDisable(false);
        }else{
            System.out.println("\n\tNo hay centros registradas en la BD");
            tblCentro.setPlaceholder(new javafx.scene.control.Label("No hay centros para mostrar"));
            tblCentro.setDisable(false);
        }
        btnSalir.setDisable(false);
        btnNuevo.setDisable(false);
        gestionarEventos();
        Platform.runLater(() -> btnSalir.requestFocus());
    }
    
    public void inicializarDatos(){
        
    }
    
    private void deshabilitarControles(){
        txtCentro.setDisable(true);
        txtCodigo.setDisable(true);
        cmbAdministracion.setDisable(true);
        cmbZona.setDisable(true);
        cmbNivel.setDisable(true);
        cmbCiclo.setDisable(true);
        cmbJornada.setDisable(true);
        cmbCobertura.setDisable(true);
        txtMatriculaFinal.setDisable(true);
        btnAgregar.setDisable(true);
        tblCentro.setDisable(true);
        btnAceptar.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnNuevo.setDisable(true);
        btnCancelar.setDisable(true);
        btnSalir.setDisable(true);
    } 
    
    private void gestionarEventos(){
        //Ocurre cuando se edita el TextField
        txtCentro.textProperty().addListener((obs, oldText, newText) -> {
            datosLlenos();
        });
        txtCodigo.textProperty().addListener((obs, oldText, newText) -> {
            datosLlenos();
        });
        //Ocurre cuando se cambia el valor del ComboBox
        cmbAdministracion.valueProperty().addListener((obs, oldValor, newValor) -> {
            datosLlenos();
        });
        cmbZona.valueProperty().addListener((obs, oldValor, newValor) -> {
            datosLlenos();
        });
        cmbNivel.valueProperty().addListener((obs, oldValor, newValor) -> {
            datosLlenos();
        });
        cmbCiclo.valueProperty().addListener((obs, oldValor, newValor) -> {
            datosLlenos();
        });
        cmbJornada.valueProperty().addListener((obs, oldValor, newValor) -> {
            datosLlenos();
        });
        cmbCobertura.valueProperty().addListener((obs, oldValor, newValor) -> {
            datosLlenos();
        });
        //Ocurre cuando se edita el TextField
        txtMatriculaFinal.textProperty().addListener((obs, oldText, newText) -> {
            datosLlenos();
        });
        //Ocurre cada vez que se selecciona un item de la tabla
        tblCentro.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Centro> ov, Centro valorAnterior, Centro valorSeleccionado) -> {
            if(!nuevo){
                if(valorSeleccionado != null){
                    txtCentro.setText(valorSeleccionado.getCentro());
                    txtCodigo.setText(valorSeleccionado.getCodigoCentro());
                    cmbAdministracion.setValue(valorSeleccionado.getAdministracion());
                    cmbZona.setValue(valorSeleccionado.getZona());
                    cmbNivel.setValue(valorSeleccionado.getNivel());
                    cmbCiclo.setValue(valorSeleccionado.getCiclo());
                    cmbJornada.setValue(valorSeleccionado.getJornada());
                    cmbCobertura.setValue(valorSeleccionado.getCobertura());
                    txtMatriculaFinal.setText(String.valueOf(valorSeleccionado.getMatriculaFinal()));
                    btnAceptar.setDisable(true);
                    btnAgregar.setDisable(true);
                    btnNuevo.setDisable(true);
                    btnModificar.setDisable(false);
                    btnEliminar.setDisable(false);
                    btnCancelar.setDisable(false);
                }
            }
        });
    }
    
    private boolean datosLlenos(){
        if(txtCentro.getText().isEmpty()){
            btnAgregar.setDisable(true);
            return false;
        }
        if(txtCodigo.getText().isEmpty()){
            btnAgregar.setDisable(true);
            return false;
        }
        if(cmbAdministracion.getValue() == null){
            btnAgregar.setDisable(true);
            return false;
        }
        if(cmbZona.getValue() == null){
            btnAgregar.setDisable(true);
            return false;
        }   
        if(cmbNivel.getValue() == null){
            btnAgregar.setDisable(true);
            return false;
        }
        if(cmbCiclo.getValue() == null){
            btnAgregar.setDisable(true);
            return false;
        }
        if(cmbJornada.getValue() == null){
            btnAgregar.setDisable(true);
            return false;
        }
        if(cmbCobertura.getValue() == null){
            btnAgregar.setDisable(true);
            return false;
        }
        if(txtMatriculaFinal.getText().isEmpty()){
            btnAgregar.setDisable(true);
            return false;
        }
        if(nuevo){
            btnAgregar.setDisable(false);
            return true;
        }
        if(modificar){
            btnAgregar.setDisable(true);
            btnAceptar.setDisable(false);
            return true;
        }
        return true;
    }
    
    @FXML
    private void agregar(){
        guardado = false;
        String centro = txtCentro.getText().toUpperCase();
        String codigo = txtCodigo.getText().toUpperCase();
        String administracion = cmbAdministracion.getValue();
        String zona = cmbZona.getValue();
        String nivel = cmbNivel.getValue();
        String ciclo = cmbCiclo.getValue();
        String jornada = cmbJornada.getValue();
        String cobertura = cmbCobertura.getValue();
        int matricula_final = Integer.parseInt(txtMatriculaFinal.getText());
        
        Centro a = new Centro(0, centro, codigo, administracion, zona, nivel, ciclo, jornada, cobertura, matricula_final);
        lista_centros.add(a);
        tblCentro.setItems(lista_centros);
        
        txtCentro.clear();
        txtCodigo.clear();
        txtCentro.setDisable(false);
        txtCodigo.setDisable(false);
        cmbAdministracion.getSelectionModel().select(null);
        cmbZona.getSelectionModel().select(null);
        cmbNivel.getSelectionModel().select(null);
        cmbCiclo.getSelectionModel().select(null);
        cmbJornada.getSelectionModel().select(null);
        cmbCobertura.getSelectionModel().select(null);
        tblCentro.setDisable(false);        
        btnAceptar.setDisable(false);
        btnAgregar.setDisable(true);
        btnNuevo.setDisable(true);
        btnCancelar.setDisable(false);
        Platform.runLater(() -> txtCentro.requestFocus());
    }
    
 
    @FXML
    private void nuevo(){
        nuevo = true;
        tblCentro.getItems().clear();
        tblCentro.setDisable(true);
        txtCentro.setDisable(false);
        txtCodigo.setDisable(false);
        cmbAdministracion.setDisable(false);
        cmbZona.setDisable(false);
        cmbNivel.setDisable(false);
        cmbCiclo.setDisable(false);
        cmbJornada.setDisable(false);
        cmbCobertura.setDisable(false);
        txtMatriculaFinal.setDisable(false);
        txtCentro.requestFocus();
        btnNuevo.setDisable(true);
        btnCancelar.setDisable(false);
    }
    
    @FXML
    private void modificar(){
        modificar = true;
        txtCentro.setDisable(false);
        txtCodigo.setDisable(false);
        cmbAdministracion.setDisable(false);
        cmbZona.setDisable(false);
        cmbNivel.setDisable(false);
        cmbCiclo.setDisable(false);
        cmbJornada.setDisable(false);
        cmbCobertura.setDisable(false);
        txtMatriculaFinal.setDisable(false);
        tblCentro.setDisable(true);
        btnAgregar.setDisable(true);
        btnAceptar.setDisable(true);
        btnEliminar.setDisable(true);
        btnModificar.setDisable(true);
        txtCentro.requestFocus();
    }
    
    @FXML
    private void eliminar(){
        eliminar = true;
        int codigo = tblCentro.getSelectionModel().getSelectedItem().getCodigo();
        String centro = tblCentro.getSelectionModel().getSelectedItem().getCentro();
        String codigo_centro = tblCentro.getSelectionModel().getSelectedItem().getCodigoCentro();
        if(codigo >= 0){
            Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
            confirmar.setTitle("Eliminar centro");
            confirmar.setHeaderText("¿Realmente desea eliminar el centro \n" + centro + " código " + codigo_centro + "?");
            confirmar.setContentText("");
            Optional<ButtonType> result = confirmar.showAndWait();
            if(result.get() == ButtonType.OK){
                if(Eliminar.eliminarCentro(conexion, codigo)){
                    Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                    mensaje.setTitle("Centro eliminado");
                    mensaje.setHeaderText("Se ha eliminado el centro " + centro + ".\n código " + codigo_centro);
                    mensaje.setContentText("");
                    mensaje.showAndWait();
                    lista_centros.clear();                    
                    Seleccionar.obtenerDatosCentros(conexion, lista_centros);
                    if(!lista_centros.isEmpty()){
                        tblCentro.setItems(lista_centros);
                    }else{
                        tblCentro.setPlaceholder(new Label("No hay centros para mostrar"));
                    }
                    txtCentro.clear();
                    txtCodigo.clear();
                    cmbAdministracion.getSelectionModel().select(null);
                    cmbZona.getSelectionModel().select(null);
                    cmbNivel.getSelectionModel().select(null);
                    cmbCiclo.getSelectionModel().select(null);
                    cmbJornada.getSelectionModel().select(null);
                    cmbCobertura.getSelectionModel().select(null);
                    txtMatriculaFinal.clear();
                    btnModificar.setDisable(true);
                    btnEliminar.setDisable(true);
                    btnCancelar.setDisable(true);
                    btnNuevo.setDisable(false);
                    btnNuevo.requestFocus();
                    eliminar = false;
                }else{
                    Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                    mensaje.setTitle("Centro no eliminado");
                    mensaje.setHeaderText("No se pudo eliminar el centro.");
                    mensaje.setContentText("");
                    mensaje.showAndWait();
                }
            }
        }
    }
    
    @FXML
    private void aceptar(){
        if(nuevo){
            if(lista_centros.size() > 0){
                for (Centro fila : lista_centros) {
                    int codigo = fila.getCodigo();
                    String centro = fila.getCentro().toUpperCase();
                    String codigo_centro = fila.getCodigoCentro().toUpperCase();
                    String administracion = fila.getAdministracion();
                    String zona = fila.getZona();
                    String nivel = fila.getNivel();
                    String ciclo = fila.getCiclo();
                    String jornada = fila.getJornada();
                    String cobertura = fila.getCobertura();
                    int matricula_final = fila.getMatriculaFinal();
                    if(!Seleccionar.yaExisteCentroCodigo(conexion, codigo_centro)){
                        if(Insertar.guardarCentro(conexion, centro, codigo_centro, administracion, zona, nivel, ciclo, jornada, cobertura, matricula_final)){
                            guardado = true;
                            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                            mensaje.setTitle("Centro guardado");
                            mensaje.setHeaderText("Se ha guardado el centro " + centro + ". \nCódigo "+ codigo_centro);
                            mensaje.setContentText("");
                            mensaje.showAndWait();
                            btnAceptar.setDisable(true);
                        }else{
                            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                            mensaje.setTitle("Centro no guardado");
                            mensaje.setHeaderText("No se pudo guardar el centro " + centro + ". \nCódigo "+ codigo_centro);
                            mensaje.setContentText("");
                            mensaje.showAndWait();
                        }
                    }else{
                        System.out.println("El centro " + centro + " con código " + fila.getCodigoCentro() + " ya existe.");
                        Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                            mensaje.setTitle("Centro ya existe");
                            mensaje.setHeaderText("No se pudo guardar el centro " + centro + ". \nCódigo "+ codigo_centro
                                                + "Ya existe un centro con  código " + codigo_centro);
                            mensaje.setContentText("");
                            mensaje.showAndWait();
                    }
                }
                lista_centros.clear();
                Seleccionar.obtenerDatosCentros(conexion, lista_centros);
                tblCentro.setItems(lista_centros);
                txtCentro.clear();
                txtCodigo.clear();
                cmbAdministracion.getSelectionModel().select(null);
                cmbZona.getSelectionModel().select(null);
                cmbNivel.getSelectionModel().select(null);
                cmbCiclo.getSelectionModel().select(null);
                cmbJornada.getSelectionModel().select(null);
                cmbCobertura.getSelectionModel().select(null);
                txtCentro.setDisable(true);
                txtCodigo.setDisable(true);
                cmbAdministracion.setDisable(true);
                cmbZona.setDisable(true);
                cmbNivel.setDisable(true);
                cmbCiclo.setDisable(true);
                cmbJornada.setDisable(true);
                cmbCobertura.setDisable(true);
                txtMatriculaFinal.clear();
                txtMatriculaFinal.setDisable(true);
                btnModificar.setDisable(true);
                btnEliminar.setDisable(true);
                btnCancelar.setDisable(true);
                btnNuevo.setDisable(false);
                btnNuevo.requestFocus();
                nuevo = false;
            }else{
                System.out.println("No hay centros en la TableView para guardar en la BD");
            }
        }        
        if(modificar){
            if(datosLlenos()){
                String centro = txtCentro.getText().toUpperCase();
                String codigo_centro = txtCodigo.getText().toUpperCase();
                String administracion = cmbAdministracion.getValue();
                String zona = cmbZona.getValue();
                String nivel = cmbNivel.getValue();
                String ciclo = cmbCiclo.getValue();
                String jornada = cmbJornada.getValue();
                String cobertura = cmbCobertura.getValue();
                int matricula = Integer.parseInt(txtMatriculaFinal.getText());
                int codigo = tblCentro.getSelectionModel().getSelectedItem().getCodigo();
                if(Modificar.modificarCentro(conexion, codigo, centro, codigo_centro, administracion, zona, nivel, ciclo, jornada, cobertura, matricula)){
                    Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                    mensaje.setTitle("Centro modificado");
                    mensaje.setHeaderText("Se ha modificado el centro " + centro + ".");
                    mensaje.setContentText("");
                    mensaje.showAndWait();
                    lista_centros.clear();
                    Seleccionar.obtenerDatosCentros(conexion, lista_centros);
                    tblCentro.setItems(lista_centros);
                    tblCentro.setDisable(false);
                    txtCentro.clear();
                    txtCodigo.clear();
                    cmbAdministracion.getSelectionModel().select(null);
                    cmbZona.getSelectionModel().select(null);
                    cmbNivel.getSelectionModel().select(null);
                    cmbCiclo.getSelectionModel().select(null);
                    cmbJornada.getSelectionModel().select(null);
                    cmbCobertura.getSelectionModel().select(null);
                    txtCentro.setDisable(true);
                    txtCodigo.setDisable(true);
                    cmbAdministracion.setDisable(true);
                    cmbZona.setDisable(true);
                    cmbNivel.setDisable(true);
                    cmbCiclo.setDisable(true);
                    cmbJornada.setDisable(true);
                    cmbCobertura.setDisable(true);
                    txtMatriculaFinal.clear();
                    txtMatriculaFinal.setDisable(true);
                    btnModificar.setDisable(true);
                    btnEliminar.setDisable(true);
                    btnAceptar.setDisable(true);
                    btnCancelar.setDisable(true);
                    btnNuevo.setDisable(false);
                    btnNuevo.requestFocus();
                    modificar = false;
                }else{
                    Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                    mensaje.setTitle("Centro no modificado");
                    mensaje.setHeaderText("No se pudo modificar el centro " + centro + ".");
                    mensaje.setContentText("");
                    mensaje.showAndWait();
                }
            }else{
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                    mensaje.setTitle("Datos vacios");
                    mensaje.setHeaderText("Hay datos vacios que debe llenar.");
                    mensaje.setContentText("");
                    mensaje.showAndWait();
            }
        }
    }
    
    @FXML
    private void cancelar(){
        nuevo = false;
        modificar = false;
        eliminar = false;
        txtCentro.clear();
        txtCodigo.clear();
        cmbAdministracion.getSelectionModel().select(null);
        cmbZona.getSelectionModel().select(null);
        cmbNivel.getSelectionModel().select(null);
        txtCentro.setDisable(true);
        txtCodigo.setDisable(true);
        cmbAdministracion.setDisable(true);
        cmbZona.setDisable(true);
        cmbNivel.setDisable(true);
        cmbCiclo.setDisable(true);
        cmbJornada.setDisable(true);
        cmbCobertura.setDisable(true);
        txtMatriculaFinal.setDisable(true);
        btnAgregar.setDisable(true);
        tblCentro.getItems().clear();
        Seleccionar.obtenerDatosCentros(conexion, lista_centros); 
        tblCentro.setItems(lista_centros);
        tblCentro.setDisable(false);
        btnAceptar.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnNuevo.setDisable(false);
        btnCancelar.setDisable(true);
    }
    
    @FXML
    private void salir(){
        if(guardado){
            Stage stage = (Stage) btnSalir.getScene().getWindow();
            stage.close();
        }else{
            Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
            confirmar.setTitle("Aviso: Datos sin guardar");
            confirmar.setHeaderText("No ha guardado los datos agregados a la tabla Centros\n\nPresione Cancelar para volver\no seleccione Aceptar si desea salir sin guardar?");
            confirmar.setContentText("");
            Optional<ButtonType> result = confirmar.showAndWait();
            if(result.get() == ButtonType.OK){
                Stage stage = (Stage) btnSalir.getScene().getWindow();
                stage.close();
            }
        }
    }    
    
}
