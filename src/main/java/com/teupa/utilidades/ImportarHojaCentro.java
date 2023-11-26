/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teupa.utilidades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;


public class ImportarHojaCentro {
    
    public static ArrayList obtenerListaDeHoja(File rutaArchivo){        
        boolean centro_correcto = false;        
        boolean codigo_correcto = false;
        boolean administracion_correcto = false;
        boolean zona_correcto = false;
        boolean nivel_correcto = false;
        boolean ciclo_correcto = false;
        boolean jornada_correcto = false;
        boolean cobertura_correcto = false;
        boolean matricula_correcto = false;
        int centro_incorrecto = 0;
        int codigo_incorrecto = 0;
        int nivel_incorrecto = 0;
        int matricula_incorrecto = 0;
        int administracion_incorrecto = 0;
        int zona_incorrecto = 0;
        int ciclo_incorrecto = 0;
        int jornada_incorrecto = 0;
        int cobertura_incorrecto = 0;
        String fila_error = "";
        //Un contador para saber la cantidad de aldeas correctas agregados a la fila
        int contador = 0;
        //Saber el tamaño de la lista de la hoja de cálculo
        int lista_hoja;
        ArrayList<ArrLstCentro> lista_centros = new ArrayList();
        InputStream excelStream = null;
        try {
            excelStream = new FileInputStream(rutaArchivo);
            String archivo = rutaArchivo.toString();
            if(archivo.substring(archivo.length() - ".xls".length()).equals(".xls")){
                // Representación del más alto nivel de la hoja excel.
                HSSFWorkbook libro_de_trabajo = new HSSFWorkbook(excelStream);
                // Elegimos la hoja que se pasa por parámetro.
                HSSFSheet hoja_de_trabajo = libro_de_trabajo.getSheetAt(0);
                // Objeto que nos permite leer una fila de la hoja excel, y de aquí extraer el contenido de las celdas.
                HSSFRow fila;
                // Obtiene el número de la ultima fila que contienen datos en la hoja
                int ultima_fila = hoja_de_trabajo.getLastRowNum();
                //Le indicamos al ArrayList el numero de filas que va a contener (tamaño)
                lista_centros.ensureCapacity(ultima_fila);
                //Variables para  los valores de cada celda
                String centro = "";
                String codigo = "";
                String administracion = "";
                String zona = "";
                String nivel = "";
                String ciclo = "";
                String jornada = "";
                String cobertura = "";
                int matricula_final = 0;
                // Cadena que usamos para almacenar la lectura de la celda
                String valor_celda_centro = "";
                String valor_celda_codigo = "";
                String valor_celda_administracion ="";
                String valor_celda_zona = "";
                String valor_celda_nivel = "";
                String valor_celda_ciclo = "";
                String valor_celda_jornada = "";
                String valor_celda_cobertura = "";
                int valor_celda_matricula = 0;
                lista_hoja = ultima_fila;
                // Se recorre cada fila del documento obteniendo los datos
                for (int r=0; r<=ultima_fila; r++) {
                    //Se obtiene la fila
                    fila = hoja_de_trabajo.getRow(r);
                    if (fila == null){
                        break;
                    }else{
                        //Repasa las celdas para buscar datos
                        for (int c = 0; c < (fila.getLastCellNum()); c++){
                            if(fila.getCell(c) != null){
                                if(fila.getCell(c).getCellType().STRING == CellType.STRING){
                                    valor_celda_centro = fila.getCell(c).getStringCellValue();
                                    if(es_centro(valor_celda_centro)){
                                        centro = valor_celda_centro;

                                        centro_correcto = true;
                                    }
                                }
                            }
                            if(fila.getCell(c+1) != null){
                                if(fila.getCell(c+1).getCellType().STRING == CellType.STRING){
                                    valor_celda_codigo = fila.getCell(c+1).getStringCellValue();
                                    if(es_codigo(valor_celda_codigo)){
                                        codigo = valor_celda_codigo;
                                        codigo_correcto = true;
                                    }
                                }
                            }
                            if(fila.getCell(c+2) != null){
                                if(fila.getCell(c+2).getCellType().STRING == CellType.STRING){
                                    valor_celda_administracion = fila.getCell(c+2).getStringCellValue();
                                    if(es_administracion(valor_celda_administracion)){
                                        administracion = valor_celda_administracion;
                                        administracion_correcto = true;
                                    }
                                }
                            }
                            if(fila.getCell(c+3) != null){
                                if(fila.getCell(c+3).getCellType().STRING == CellType.STRING){
                                    valor_celda_zona = fila.getCell(c+3).getStringCellValue();
                                    if(es_zona(valor_celda_zona)){
                                        zona = valor_celda_zona;
                                        zona_correcto = true;
                                    }
                                }
                            }
                            if(fila.getCell(c+4) != null){
                                if(fila.getCell(c+4).getCellType().STRING == CellType.STRING){
                                    valor_celda_nivel = fila.getCell(c+4).getStringCellValue();
                                    if(es_nivel(valor_celda_nivel)){
                                        nivel = valor_celda_nivel;
                                        nivel_correcto = true;
                                    }
                                }
                            }
                            if(fila.getCell(c+5) != null){
                                if(fila.getCell(c+5).getCellType().STRING == CellType.STRING){
                                    valor_celda_ciclo = fila.getCell(c+5).getStringCellValue();
                                    if(es_ciclo(valor_celda_ciclo)){
                                        ciclo = valor_celda_ciclo;
                                        ciclo_correcto = true;
                                    }
                                }
                            }
                            if(fila.getCell(c+6) != null){
                                if(fila.getCell(c+6).getCellType().STRING == CellType.STRING){
                                    valor_celda_jornada = fila.getCell(c+6).getStringCellValue();
                                    if(es_jornada(valor_celda_jornada)){
                                        jornada = valor_celda_jornada;
                                        jornada_correcto = true;
                                    }
                                }
                            }
                            if(fila.getCell(c+7) != null){
                                if(fila.getCell(c+7).getCellType().STRING == CellType.STRING){
                                    valor_celda_cobertura = fila.getCell(c+7).getStringCellValue();
                                    if(es_cobertura(valor_celda_cobertura)){
                                        cobertura = valor_celda_cobertura;
                                        cobertura_correcto = true;
                                    }
                                }
                            }
                            if(fila.getCell(c+8) != null){
                                if(fila.getCell(c+8).getCellType().NUMERIC == CellType.NUMERIC){
                                    valor_celda_matricula = (int)(fila.getCell(c+8).getNumericCellValue());
                                    if(es_matricula(valor_celda_matricula)){
                                        matricula_final = valor_celda_matricula;
                                        matricula_correcto = true;
                                        break;
                                    }
                                }
                            }
                        }

                        if(centro_correcto){
                            if(codigo_correcto){
                                if(administracion_correcto){
                                    if(zona_correcto){
                                        if(nivel_correcto){
                                            if(ciclo_correcto){
                                                if(jornada_correcto){
                                                    if(cobertura_correcto){
                                                        if(matricula_correcto){
                                                            contador++;
                                                            lista_centros.add(new ArrLstCentro(centro.toUpperCase(), codigo.toUpperCase(), administracion.toUpperCase(), zona.toUpperCase(), nivel.toUpperCase(), ciclo.toUpperCase(), jornada.toUpperCase(), cobertura.toUpperCase(), matricula_final));
                                                        }else{
                                                            fila_error = fila_error + String.valueOf(r+1) + "\n";
                                                            matricula_incorrecto++;
                                                            System.out.println("Dato incorrecto en celda matricula.  Dato: " + valor_celda_matricula);
                                                        }
                                                    }else{
                                                        fila_error = fila_error + String.valueOf(r+1) + "\n";
                                                        cobertura_incorrecto++;
                                                        System.out.println("Dato incorrecto en celda cobertura.  Dato: " + valor_celda_cobertura);
                                                    }
                                                }else{
                                                    fila_error = fila_error + String.valueOf(r+1) + "\n";
                                                    jornada_incorrecto++;
                                                    System.out.println("Dato incorrecto en celda jornada.  Dato: " + valor_celda_jornada);
                                                }
                                            }else{
                                                fila_error = fila_error + String.valueOf(r+1) + "\n";
                                                ciclo_incorrecto++;
                                                System.out.println("Dato incorrecto en celda ciclo.  Dato: " + valor_celda_ciclo);
                                            }
                                        }else{
                                            fila_error = fila_error + String.valueOf(r+1) + "\n";
                                            nivel_incorrecto++;
                                            System.out.println("Dato incorrecto en celda nivel.  Dato: " + valor_celda_nivel);
                                        }
                                    }else{
                                        fila_error = fila_error + String.valueOf(r+1) + "\n";
                                        zona_incorrecto++;
                                        System.out.println("Dato incorrecto en celda zona.  Dato: " + valor_celda_zona);
                                    }
                                }else{
                                    fila_error = fila_error + String.valueOf(r+1) + "\n";
                                    administracion_incorrecto++;
                                    System.out.println("Dato incorrecto en celda administración.  Dato: " + valor_celda_administracion);
                                }
                            }else{
                                fila_error = fila_error + String.valueOf(r+1) + "\n";
                                codigo_incorrecto++;
                                System.out.println("Dato incorrecto en celda codigo.  Dato: " + valor_celda_codigo);
                            }
                        }else{
                            fila_error = fila_error + String.valueOf(r+1) + "\n";
                            centro_incorrecto++;
                            System.out.println("Dato incorrecto en celda centro.  Dato: " + valor_celda_centro);
                        }
                        centro = "";
                        codigo = "";
                        administracion ="";
                        zona = "";
                        nivel = "";
                        ciclo = "";
                        jornada = "";
                        cobertura = "";
                        matricula_final = 0;
                        centro_correcto = false;
                        codigo_correcto = false;
                        administracion_correcto = false;
                        zona_correcto = false;
                        nivel_correcto = false;
                        ciclo_correcto = false;
                        jornada_correcto = false;
                    }
                }
                if(contador == (lista_hoja + 1)){
                    mensajeTodoCorrecto(contador,lista_hoja);
                }
                if(contador < (lista_hoja + 1)){
                    mensajeHayErrores(contador, lista_hoja);
                }
                if((centro_incorrecto > 0) || (codigo_incorrecto > 0) || (administracion_incorrecto > 0) || (zona_incorrecto > 0) || (nivel_incorrecto > 0) || (ciclo_incorrecto > 0) || (jornada_incorrecto > 0) || (cobertura_incorrecto > 0) || (matricula_incorrecto > 0)){
                    datosErroneos(centro_incorrecto, codigo_incorrecto, administracion_incorrecto, zona_incorrecto, nivel_incorrecto, ciclo_incorrecto, jornada_incorrecto, cobertura_incorrecto, matricula_incorrecto);
                }
                if(fila_error.length() > 0){
                    errorFilas(fila_error);
                }
            }
            if(archivo.substring(archivo.length() - ".xlsx".length()).equals(".xlsx")){
                archivo = null;
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Archivo no soportado");
                mensaje.setContentText("");
                mensaje.setHeaderText("El formato de archivo \".xlsx\" aún no está soportado.\n"
                        + "Utilice el formato \".xls\" al guardar el archivo de hoja de cálculo.");
                mensaje.showAndWait();
                lista_centros.clear();
                System.out.println("Formato de archivo XLSX no soportado ");
            }
        } catch (FileNotFoundException fnex) {
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("No se pudo encontrar el archivo");
            mensaje.setContentText("");
            mensaje.setHeaderText("No se ha encontrado el archivo fuente de los datos");
            mensaje.showAndWait();
            System.err.println("El archivo no se ha encontrado: " + fnex.getMessage());
        } catch (IOException ex) {
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("El archivo no funciona");
            mensaje.setContentText("");
            mensaje.setHeaderText("Ocurrió un error al tratar de procesar el archivo fuente de los datos");
            mensaje.showAndWait();
            System.err.println("Error al procesar el archivo: " + ex.getMessage());
        } finally {
            try {
                excelStream.close();
            } catch (IOException ex) {
                System.err.println("Error al procesar el archivo cuando este se cerraba: " + ex.getMessage());
            }
        }
        return lista_centros;
    }
    
    private static boolean es_centro(String p_centro){
        if((p_centro.length() > 2) && (p_centro.length() < 254)){
            char[] nombre = p_centro.toCharArray();
            for(int i=0; i<nombre.length; i++){
                if(nombre.length > 0){
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean es_codigo(String p_codigo){
        if(p_codigo.length() == 12){
            byte cont_num = 0;
            char[] dni = p_codigo.toCharArray();
            for(int i=0; i<dni.length; i++){
                if(Character.isDigit(dni[i])){
                    cont_num++;
                }else{
                    if(i==8){
                        if(Character.isAlphabetic(dni[i])){
                            cont_num++;
                        }
                    }
                }
            }
            if((cont_num + 1) == 12){
                return true;
            }
        }
        return false;
    }
    
    private static boolean es_administracion(String p_administracion){
        if(p_administracion.length() > 0){
            if(p_administracion.toUpperCase().equals("GUBERNAMENTAL")){
                return true;
            }
            if((p_administracion.toUpperCase().equals("PRIVADO")) || (p_administracion.toUpperCase().equals("PRIVADA"))){
                return true;
            }
        }
        return false;
    }
    
    private static boolean es_zona(String p_zona){
        if(p_zona.length() > 0){
            if(p_zona.toUpperCase().equals("URBANA")){
                return true;
            }
            if(p_zona.toUpperCase().equals("RURAL")){
                return true;
            }
        }
        return false;
    }
    
    private static boolean es_nivel(String p_nivel){
        if(p_nivel.length() > 0){
            if((p_nivel.toUpperCase().equals("PREBASICA")) || (p_nivel.toUpperCase().equals("PREBÁSICA"))){
                return true;
            }
            if((p_nivel.toUpperCase().equals("BASICA")) || (p_nivel.toUpperCase().equals("BÁSICA"))){
                return true;
            }
            if(p_nivel.toUpperCase().equals("MEDIA")){
                return true;
            }
        }
        return false;
    }
    
    private static boolean es_ciclo(String p_ciclo){
        if(p_ciclo.length() > 0){            
            if(p_ciclo.toUpperCase().equals("I Y II CICLO")){
                return true;
            }
            if(p_ciclo.toUpperCase().equals("I, II Y III CICLO")){
                return true;
            }
            if(p_ciclo.toUpperCase().equals("III CICLO")){
                return true;
            }
            if(p_ciclo.toUpperCase().equals("III CICLO Y BTP")){
                return true;
            }
            if(p_ciclo.toUpperCase().equals("NO APLICA")){
                return true;
            }
            if(p_ciclo.toUpperCase().equals("CBT Y BTP")){
                return true;
            }
            if(p_ciclo.toUpperCase().equals("BTP")){
                return true;
            }
            if(p_ciclo.toUpperCase().equals("CBT, BTP Y BCH")){
                return true;
            }
        }
        return false;
    }
    
    private static boolean es_jornada(String p_jornada){
        if(p_jornada.length() > 0){
            if((p_jornada.toUpperCase().equals("MATUTINA"))){
                return true;
            }
            if(p_jornada.toUpperCase().equals("MATUTINA Y VESPERTINA")){
                return true;
            }
            if(p_jornada.toUpperCase().equals("VESPERTINA")){
                return true;
            }
            if(p_jornada.toUpperCase().equals("NOCTURNA")){
                return true;
            }
            if(p_jornada.toUpperCase().equals("TRES JORNADAS")){
                return true;
            }
            if(p_jornada.toUpperCase().equals("DISTANCIA")){
                return true;
            }
        }
        return false;
    }
    
    private static boolean es_cobertura(String p_cobertura){
        if(p_cobertura.length() > 0){
            if(p_cobertura.toUpperCase().equals("OFICIAL")){
                return true;
            }   
            if(p_cobertura.toUpperCase().equals("PROHECO")){
                return true;
            }
            if(p_cobertura.toUpperCase().equals("ALCALDÍA")){
                return true;
            }
            if(p_cobertura.toUpperCase().equals("OTRO")){
                return true;
            }
        }
        return false;
    }
    
    private static boolean es_matricula(int matricula){
        if(matricula >= 0){
            return true;
        }
        return false;
    }
    
    private static void mensajeTodoCorrecto(int contador, int lista_hoja){
        System.out.println("\n\n\t\tTodos los datos de los centros del archivo fueron leidos correctamente.");
        Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
        mensaje.setTitle("Centros importados");
        mensaje.setContentText("");
        mensaje.setHeaderText("Se leyeron " + contador + " filas de " + (lista_hoja + 1) + " centros encontrados en el archivo.\n\n"
                + "Todos los datos de los centros se leyeron correctamente.");
        mensaje.show();
    }
    
    private static void mensajeHayErrores(int contador, int lista_hoja){        
        System.out.println("\n\n\t\tAviso: " + (lista_hoja - contador) + " filas de datos no se han podido importar.");
        Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
        mensaje.setTitle("Algunos datos de centros no se han importado.");
        mensaje.setContentText("");
        mensaje.setHeaderText(((lista_hoja + 1) - contador) + " de " + (lista_hoja + 1) + " filas no se han podido importar porque se detectaron datos incorrectos.\n\n"
                + "Verifique que los datos del centro estén escritos correctamente.\n"
                + "");
        mensaje.show();
    }
    
    private static void datosErroneos(int centro, int codigo, int administracion, int zona, int nivel, int ciclo, int jornada, int cobertura, int matricula){
        System.out.println("\n\tSe encontro " + centro + " errores en la celda centro."
                + "\n\tSe encontro " + codigo + " errores en la celda codigo."
                + "\n\tSe encontro " + administracion + " errores en la celda administración."
                + "\n\tSe encontro " + zona + " errores en la celda zona."
                + "\n\tSe encontro " + nivel + " errores en la celda nivel."
                + "\n\tSe encontro " + ciclo + " errores en la celda ciclo."
                + "\n\tSe encontro " + jornada + " errores en la celda jornada."
                + "\n\tSe encontro " + cobertura + " errores en la celda cobertura."
                + "\n\tSe encontro " + matricula + " errores en la celda matrícula."
                + "");
        Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
        mensaje.setTitle("Celdas con datos erroneos");
        mensaje.setContentText("");
        mensaje.setHeaderText("Se encontraron errores en los datos al leer las celdas de la hoja de cálculo."
                        + "\n\n" + centro + " errores en la celda de Centro."
                        + "\n" + codigo + " errores en la celda de Código."
                        + "\n" + administracion + " errores en la celdas de Administración."
                        + "\n" + zona + " errores en la celdas de Zona."
                        + "\n" + nivel + " errores en la celdas de Nivel."
                        + "\n" + ciclo + " errores en la celdas de Ciclo."
                        + "\n" + jornada + " errores en la celdas de Jornada."
                        + "\n" + cobertura + " errores en la celdas de Cobertura."
                        + "\n" + matricula + " errores en la celda de Matrícula.");
        mensaje.show();
    }
    
    private static void errorFilas(String error_fila){
        Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
        mensaje.setTitle("Filas con datos erroneos.");
        mensaje.setContentText("");
        mensaje.setHeaderText("Se encontro errores en los datos de la hoja de cálculo en las filas número:\n"+ error_fila);
        mensaje.show();
    }
}
