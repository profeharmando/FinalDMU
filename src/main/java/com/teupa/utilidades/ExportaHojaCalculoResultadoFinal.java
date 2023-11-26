
package com.teupa.utilidades;

import com.teupa.modelo.ConsolidadoResultadoFinal;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ExportaHojaCalculoResultadoFinal {   
        
    public void obtieneDatos(String ruta_archivo, String administracion, String area, String nivel, String tipo, ObservableList<ConsolidadoResultadoFinal> lista_consolidado){
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet hoja = wb.createSheet("CONSOLIDADO_RESULTADO_FINAL");
        //Configura el ancho de las columnas
        hoja.setColumnWidth(0, 5000);
        hoja.setColumnWidth(1, 1300);
        hoja.setColumnWidth(2, 1300);
        hoja.setColumnWidth(3, 1300);
        hoja.setColumnWidth(4, 1300);
        hoja.setColumnWidth(5, 1300);
        hoja.setColumnWidth(6, 1300);
        hoja.setColumnWidth(7, 1300);
        hoja.setColumnWidth(8, 1300);
        hoja.setColumnWidth(9, 1300);
        hoja.setColumnWidth(10, 1300);
        hoja.setColumnWidth(11, 1300);
        hoja.setColumnWidth(12, 1300);
        hoja.setColumnWidth(13, 1300);
        hoja.setColumnWidth(14, 1300);
        hoja.setColumnWidth(15, 1300);
        hoja.setColumnWidth(16, 1300);
        hoja.setColumnWidth(17, 1300);
        hoja.setColumnWidth(18, 1300);
        hoja.setColumnWidth(19, 1300);
        hoja.setColumnWidth(20, 1300);
        hoja.setColumnWidth(21, 1300);
        hoja.setColumnWidth(22, 1300);
        hoja.setColumnWidth(23, 1300);
        hoja.setColumnWidth(24, 1300);
        hoja.setColumnWidth(25, 1300);
        hoja.setColumnWidth(26, 1300);
        hoja.setColumnWidth(27, 1300);
        hoja.setColumnWidth(28, 1300);
        hoja.setColumnWidth(29, 1300);
        hoja.setColumnWidth(30, 1300);
        //Crea las celdas con los datos del la Seccion de Clases
        HSSFRow nombre_cuadro = hoja.createRow(0);
        nombre_cuadro.createCell(0).setCellValue("CONSOLIDADO DE RESULTADOS FINALES");
        HSSFRow vacio1 = hoja.createRow(1);
        vacio1.createCell(0).setCellValue("");
        vacio1.createCell(0).setCellValue("Administración: " + administracion + "\tZona: " + area + "\tNivel:" + nivel + "\tTipo:" + tipo);
        HSSFRow titulo = hoja.createRow(2);
        titulo.createCell(0).setCellValue("");
        //Crea el encabezado de las columnas
        HSSFRow cabecera = hoja.createRow(3);
        cabecera.createCell(0).setCellValue("GRADO");
        //MAT INICIAL
        cabecera.createCell(1).setCellValue("N");
        cabecera.createCell(2).setCellValue("V");
        cabecera.createCell(3).setCellValue("T");
        //MAT FINAL
        cabecera.createCell(4).setCellValue("N");
        cabecera.createCell(5).setCellValue("V");
        cabecera.createCell(6).setCellValue("T");
        //INGRESOS
        cabecera.createCell(7).setCellValue("N");
        cabecera.createCell(8).setCellValue("V");
        cabecera.createCell(9).setCellValue("T");
        //TRASLADOS
        cabecera.createCell(10).setCellValue("N");
        cabecera.createCell(11).setCellValue("V");
        cabecera.createCell(12).setCellValue("T");
        //DESERTORES
        cabecera.createCell(13).setCellValue("N");
        cabecera.createCell(14).setCellValue("V");
        cabecera.createCell(15).setCellValue("T");
        //EVALUADOS
        cabecera.createCell(16).setCellValue("N");
        cabecera.createCell(17).setCellValue("V");
        cabecera.createCell(18).setCellValue("T");
        //INICIAL
        cabecera.createCell(19).setCellValue("N");
        cabecera.createCell(20).setCellValue("V");
        cabecera.createCell(21).setCellValue("T");
        //BASICO
        cabecera.createCell(22).setCellValue("N");
        cabecera.createCell(23).setCellValue("V");
        cabecera.createCell(24).setCellValue("T");
        //AVANZADO
        cabecera.createCell(25).setCellValue("N");
        cabecera.createCell(26).setCellValue("V");
        cabecera.createCell(27).setCellValue("T");
        //EXCELENTE
        cabecera.createCell(28).setCellValue("N");
        cabecera.createCell(29).setCellValue("V");
        cabecera.createCell(30).setCellValue("T");
        
        //crea las filas necesarias y llena cada celdas
        int indice = 4;
        for(int i=0; i<lista_consolidado.size(); i++){
            HSSFRow fila = hoja.createRow(indice);
            //Muestra el grado
            fila.createCell(0).setCellValue(lista_consolidado.get(i).GradoProperty().getValue());
            //MAT INICIAL
            fila.createCell(1).setCellValue(lista_consolidado.get(i).getMatInicial_n());
            fila.createCell(2).setCellValue(lista_consolidado.get(i).getMatInicial_v());
            fila.createCell(3).setCellValue(lista_consolidado.get(i).getMatInicial_t());
            //MAT FINAL
            fila.createCell(4).setCellValue(lista_consolidado.get(i).getMatFinal_n());
            fila.createCell(5).setCellValue(lista_consolidado.get(i).getMatFinal_v());
            fila.createCell(6).setCellValue(lista_consolidado.get(i).getMatFinal_t());
            //INGRESOS
            fila.createCell(7).setCellValue(lista_consolidado.get(i).getIngre_n());
            fila.createCell(8).setCellValue(lista_consolidado.get(i).getIngre_v());
            fila.createCell(9).setCellValue(lista_consolidado.get(i).getIngre_t());
            //TRASLADOS
            fila.createCell(10).setCellValue(lista_consolidado.get(i).getTrasla_n());
            fila.createCell(11).setCellValue(lista_consolidado.get(i).getTrasla_v());
            fila.createCell(12).setCellValue(lista_consolidado.get(i).getTrasla_t());
            //DESERTORES
            fila.createCell(13).setCellValue(lista_consolidado.get(i).getDeser_n());
            fila.createCell(14).setCellValue(lista_consolidado.get(i).getDeser_v());
            fila.createCell(15).setCellValue(lista_consolidado.get(i).getDeser_t());
            //EVALUADOS
            fila.createCell(16).setCellValue(lista_consolidado.get(i).getEvalu_n());
            fila.createCell(17).setCellValue(lista_consolidado.get(i).getEvalu_v());
            fila.createCell(18).setCellValue(lista_consolidado.get(i).getEvalu_t());
            //INICIAL
            fila.createCell(19).setCellValue(lista_consolidado.get(i).getInic_n());
            fila.createCell(20).setCellValue(lista_consolidado.get(i).getInic_v());
            fila.createCell(21).setCellValue(lista_consolidado.get(i).getInic_t());
            //BASICO
            fila.createCell(22).setCellValue(lista_consolidado.get(i).getBasi_n());
            fila.createCell(23).setCellValue(lista_consolidado.get(i).getBasi_v());
            fila.createCell(24).setCellValue(lista_consolidado.get(i).getBasi_t());
            //AVANZADO
            fila.createCell(25).setCellValue(lista_consolidado.get(i).getAvan_n());
            fila.createCell(26).setCellValue(lista_consolidado.get(i).getAvan_v());
            fila.createCell(27).setCellValue(lista_consolidado.get(i).getAvan_t());
            //INICIAL
            fila.createCell(28).setCellValue(lista_consolidado.get(i).getExce_n());
            fila.createCell(29).setCellValue(lista_consolidado.get(i).getExce_v());
            fila.createCell(30).setCellValue(lista_consolidado.get(i).getExce_t());            
            indice++;
        }
        try {
            FileOutputStream archivo = new FileOutputStream(ruta_archivo);
            wb.write(archivo);
            archivo.close();
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Archivo guardado");
            mensaje.setHeaderText(null);
            mensaje.setContentText("Se a creado el archivo en " + ruta_archivo);
            mensaje.showAndWait();
        } catch (FileNotFoundException ex) {
            System.err.println("\n\tNo se pudo generar el archivo de Excell.\n\t"
                    + "Error: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("\n\tNo se pudo guardar el archivo de Excell.\n\t"
                    + "Error: " + ex.getMessage());
        }
    }
    

}
