/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.teupa.controlador;

import com.teupa.basedatos.ConexionMySql;
import com.teupa.basedatos.Insertar;
import com.teupa.basedatos.Seleccionar;
import com.teupa.modelo.ImportarCentro;
import com.teupa.utilidades.ArrLstCentro;
import com.teupa.utilidades.ImportarHojaCentro;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hector
 */
public class ImportarCentroCtrl implements Initializable {

    //Conexion a la BD
    ConexionMySql conn = new ConexionMySql();
    Connection conexion = conn.establecerConexion();

    //Elementos GUI
    @FXML private TextField txtRuta;
    @FXML private Button btnBuscar;
    @FXML private TableView<ImportarCentro> tblCentro;
    @FXML private TableColumn<ImportarCentro, String> clmCentro;
    @FXML private TableColumn<ImportarCentro, String> clmCodigo;
    @FXML private TableColumn<ImportarCentro, String> clmAdministracion;
    @FXML private TableColumn<ImportarCentro, String> clmZona;
    @FXML private TableColumn<ImportarCentro, String> clmNivel;
    @FXML private TableColumn<ImportarCentro, String> clmCiclo;
    @FXML private TableColumn<ImportarCentro, String> clmJornada;
    @FXML private TableColumn<ImportarCentro, String> clmCobertura;
    @FXML private TableColumn<ImportarCentro, Integer> clmMatriculaFinal;
            
    @FXML private Button btnAceptar;
    @FXML private Button btnEliminarFila;
    @FXML private Button btnCancelar;
    @FXML private Button btnSalir;

    //ArrayList para llenar el ObservableList
    private ArrayList<ArrLstCentro> lista = new ArrayList<ArrLstCentro>();
    //ObservableList para los llenar el TableView
    private ObservableList<ImportarCentro> lista_centros = FXCollections.observableArrayList();

    int contador_guardados = 0;
    int contador_ya_existen = 0;
    String codigo_existe = "";
    String url_imagen;
    String separador = System.getProperty("file.separator");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtRuta.setEditable(false);
        //Hace editable el TableView
        tblCentro.setEditable(true);
        clmCentro.setCellFactory(TextFieldTableCell.<ImportarCentro>forTableColumn());
        clmCodigo.setCellFactory(TextFieldTableCell.<ImportarCentro>forTableColumn());
        clmAdministracion.setCellFactory(TextFieldTableCell.<ImportarCentro>forTableColumn());
        clmZona.setCellFactory(TextFieldTableCell.<ImportarCentro>forTableColumn());
        clmNivel.setCellFactory(TextFieldTableCell.<ImportarCentro>forTableColumn());
        clmCiclo.setCellFactory(TextFieldTableCell.<ImportarCentro>forTableColumn());
        clmJornada.setCellFactory(TextFieldTableCell.<ImportarCentro>forTableColumn());
        clmCobertura.setCellFactory(TextFieldTableCell.<ImportarCentro>forTableColumn());
        clmMatriculaFinal.setCellValueFactory(dato -> dato.getValue().MatriculaFinalProperty().asObject());
        //Hace editable la columna
        clmCentro.setCellFactory(TextFieldTableCell.forTableColumn());
        clmCodigo.setCellFactory(TextFieldTableCell.forTableColumn());        
        clmAdministracion.setCellFactory(TextFieldTableCell.forTableColumn());
        clmZona.setCellFactory(TextFieldTableCell.forTableColumn());
        clmNivel.setCellFactory(TextFieldTableCell.forTableColumn());
        clmCiclo.setCellFactory(TextFieldTableCell.forTableColumn());
        clmJornada.setCellFactory(TextFieldTableCell.forTableColumn());
        clmCobertura.setCellFactory(TextFieldTableCell.forTableColumn());
        clmCentro.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ImportarCentro, String>>() {
            public void handle(TableColumn.CellEditEvent<ImportarCentro, String> t) {
                ((ImportarCentro) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCentro(t.getNewValue());
            }
        });
        clmCodigo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ImportarCentro, String>>() {
            public void handle(TableColumn.CellEditEvent<ImportarCentro, String> t) {
                ((ImportarCentro) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCodigo(t.getNewValue());
            }
        });
        clmAdministracion.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ImportarCentro, String>>() {
            public void handle(TableColumn.CellEditEvent<ImportarCentro, String> t) {
                ((ImportarCentro) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAdministracion(t.getNewValue());
            }
        });
        clmZona.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ImportarCentro, String>>() {
            public void handle(TableColumn.CellEditEvent<ImportarCentro, String> t) {
                ((ImportarCentro) t.getTableView().getItems().get(t.getTablePosition().getRow())).setZona(t.getNewValue());
            }
        });
        clmNivel.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ImportarCentro, String>>() {
            public void handle(TableColumn.CellEditEvent<ImportarCentro, String> t) {
                ((ImportarCentro) t.getTableView().getItems().get(t.getTablePosition().getRow())).setNivel(t.getNewValue());
            }
        });
        clmCiclo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ImportarCentro, String>>() {
            public void handle(TableColumn.CellEditEvent<ImportarCentro, String> t) {
                ((ImportarCentro) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCiclo(t.getNewValue());
            }
        });
        clmJornada.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ImportarCentro, String>>() {
            public void handle(TableColumn.CellEditEvent<ImportarCentro, String> t) {
                ((ImportarCentro) t.getTableView().getItems().get(t.getTablePosition().getRow())).setJonada(t.getNewValue());
            }
        });
        clmCobertura.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ImportarCentro, String>>() {
            public void handle(TableColumn.CellEditEvent<ImportarCentro, String> t) {
                ((ImportarCentro) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCobertura(t.getNewValue());
            }
        });
        inhabilitarLimpiarElementos();
        gestionarEventos();
        btnBuscar.requestFocus();
    }

    public void inicializarDatos() {

    }

    public void gestionarEventos() {
        //Ocurre cada vez que se selecciona un item del TableView
        tblCentro.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ImportarCentro>() {
            @Override
            public void changed(ObservableValue<? extends ImportarCentro> ov,
                    ImportarCentro valorAnterior, ImportarCentro valorSeleccionado) {
                if (valorSeleccionado != null) {
                    btnEliminarFila.setDisable(false);
                }
            }
        });
    }

    @FXML
    private void buscarArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar archivo de Hoja de Cálculo");
        //Muestra por defecto el directorio de usuario (Home)
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos de Hoja de Cálculo", "*.xls", "*.xlsx")
        );
        // Mostrar el FileChooser
        File archivo = fileChooser.showOpenDialog(null);
        // Mostar la ruta del archivo
        if (archivo != null) {
            txtRuta.setText(archivo.getAbsolutePath());
            llenarTablaCentros(archivo);
        } else {
            txtRuta.setText("No ha seleccionado un archivo");
        }
    }

    private void llenarTablaCentros(File archivo) {
        tblCentro.getItems().clear();
        //Limpia el ArrayList
        lista.clear();
        //Limpiar el ObservableList
        lista_centros.clear();
        //Trae los datos del ArrayList
        lista = ImportarHojaCentro.obtenerListaDeHoja(archivo);
        if (!lista.isEmpty()) {
            //Agrega los elementos del ArrayList al modelo del ObservableList
            for (ArrLstCentro lst : lista) {
                lista_centros.add(new ImportarCentro(lst.getCentro().toUpperCase(), lst.getCodigo().toUpperCase(), lst.getAdministracion().toUpperCase(), lst.getZona().toUpperCase(), lst.getNivel().toUpperCase(), lst.getCiclo().toUpperCase(), lst.getJornada().toUpperCase(), lst.getCobertura().toUpperCase(), lst.getMatriculaFinal()));
            }
            //Agrega el ObservableList al TableView
            tblCentro.setItems(lista_centros);
            //Muestra los datos en las columnas del TableView
            clmCentro.setCellValueFactory(dato -> dato.getValue().CentroProperty());
            clmCodigo.setCellValueFactory(dato -> dato.getValue().CodigoProperty());
            clmAdministracion.setCellValueFactory(dato -> dato.getValue().AdministracionProperty());
            clmZona.setCellValueFactory(dato -> dato.getValue().ZonaProperty());
            clmNivel.setCellValueFactory(dato -> dato.getValue().NivelProperty());
            clmCiclo.setCellValueFactory(dato -> dato.getValue().CicloProperty());
            clmJornada.setCellValueFactory(dato -> dato.getValue().JornadaProperty());
            clmCobertura.setCellValueFactory(dato -> dato.getValue().CoberturaProperty());
            clmMatriculaFinal.setCellValueFactory(dato -> dato.getValue().MatriculaFinalProperty().asObject());
            if (!tblCentro.getItems().isEmpty()) {
                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            } else {
                inhabilitarLimpiarElementos();
            }
        } else {
            System.out.println("\n\n\t\tNo hay centros en la lista");
        }
    }

    @FXML
    private void eliminarFila() {
        lista_centros.remove(tblCentro.getSelectionModel().getSelectedIndex());
        if (tblCentro.getItems().isEmpty()) {
            inhabilitarLimpiarElementos();
        }
    }

    @FXML
    private void cambiaZona() {
        btnAceptar.setDisable(false);
    }

    @FXML
    private void aceptar(ActionEvent event) {
        if (lista_centros.size() > 0) {
            Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
            confirmar.setTitle("Agregar centro");
            confirmar.setHeaderText("Va a agregar " + lista_centros.size() + " nuevos centros al sistema."
                    + "\n\n¿Desea continuar?");
            confirmar.setContentText("");
            Optional<ButtonType> result = confirmar.showAndWait();
            if (result.get() == ButtonType.OK) {
                lista_centros.forEach((registro) -> {
                    if (Seleccionar.yaExisteCentroCodigo(conexion, registro.getCodigo())) {
                        contador_ya_existen++;
                        codigo_existe = codigo_existe + "\n" + registro.getCodigo().toString() + " " + registro.getCentro().toString();
                    } else {
                        if (Insertar.guardarCentro(conexion, registro.getCentro().toUpperCase(), registro.getCodigo().toUpperCase(), registro.getAdministracion(), registro.getZona(), registro.getNivel().toUpperCase(), registro.getCiclo().toUpperCase(), registro.getJornada().toUpperCase(), registro.getCobertura().toUpperCase(), registro.getMatriculaFinal())) {
                            contador_guardados++;
                            System.out.println("\t\t" + contador_guardados + " ->" + registro.getCentro().toUpperCase());
                        }
                    }
                });
                if (contador_guardados > 0) {
                    Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                    mensaje.setTitle("Centros guardados");
                    mensaje.setContentText("");
                    mensaje.setHeaderText("Se agregaron " + contador_guardados + " nuevos centros al sistema.");
                    mensaje.show();
                    tblCentro.setPlaceholder(new javafx.scene.control.Label("Centros guardados"));
                }
                if (contador_ya_existen > 0) {
                    Alert mensaje_ya_existen = new Alert(Alert.AlertType.INFORMATION);
                    mensaje_ya_existen.setTitle("Centros ya existen");
                    mensaje_ya_existen.setContentText("");
                    mensaje_ya_existen.setHeaderText("No se guardaron " + contador_ya_existen + " centros."
                            + "\n\nSe encontró centros con códigos SACE repetidos en la lista."
                            + "\nPosiblemente se trata de centros que comparten código SACE con otro centro,"
                            + "\ndichos centros debe agregarlos individualmente al sistema."
                            + "\n\nCentro no guardados: " + codigo_existe);
                    mensaje_ya_existen.show();
                }
                limpiarListas();
                inhabilitarLimpiarElementos();
            }
        } else {
            System.err.println("No hay centros en la Lista");
        }
    }


    private void limpiarListas() {
        lista_centros.clear();
        lista.clear();
        tblCentro.getItems().clear();
    }

    private void inhabilitarLimpiarElementos() {
        txtRuta.setText(null);
        txtRuta.setDisable(false);
        btnBuscar.setDisable(false);
        btnAceptar.setDisable(true);
        btnCancelar.setDisable(true);
        btnEliminarFila.setDisable(true);
    }

    @FXML
    private void cancelar() {
        txtRuta.setText(null);
        lista_centros.clear();
        lista.clear();
        tblCentro.getItems().clear();
        lista_centros.clear();
        btnAceptar.setDisable(true);
        btnEliminarFila.setDisable(true);
        btnSalir.requestFocus();
    }

    @FXML
    private void salir() {
        // Obtener el escenario (Stage)
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        // Cerrar el formulario.
        stage.close();
    }

}
