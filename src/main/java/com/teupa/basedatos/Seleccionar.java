/*
 *  Eleciones IT5N por Hector Armando Herrera
 *
 * Este programa es software libre: usted puede distribuir y/o modificarlo
 * bajo los términos de la GNU General Public License publicada por
 * la Free Software Foundation, ya sea la versión 3 de la Licencia, o
 * versiones posteriores.
 *
 * Este programa se distribuye con la esperanza de que sea útil.,
 * pero SIN NINGUNA GARANTÍA; sin ni siquiera la garantía implícita de
 * COMERCIABILIDAD o APTITUD PARA UN PROPÓSITO PARTICULAR. Ver la
 * GNU General Public License para mas detalles.
 *
 * Debería haber recibido una copia de la GNU General Public License
 * junto con este programa. Si no, visite <http://www.gnu.org/licenses/>.
 */

package com.teupa.basedatos;

import com.teupa.modelo.BuscarCentro;
import com.teupa.modelo.Centro;
import com.teupa.modelo.ConsolidadoReprobadosEspMat;
import com.teupa.modelo.IngresarResultadoFinal;
import com.teupa.modelo.ConsolidadoResultadoFinal;
import com.teupa.modelo.Progreso;
import com.teupa.modelo.ReprobadosEspañolMatematicas;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;
import static com.teupa.utilidades.ObtenerNombreClase.*;

/**
 *
 * @author Héctor Armando Herrera (profeharmando@gmail.com)
 */
public class Seleccionar {
    
    //////Obtener el total de registros de cada tabla
    
    public static int optenerTotalAldeas(Connection conn){
        String sentenciaSQL = "SELECT SUM(codigo) AS total FROM tbl_aldea";
        int total_aldeas = 0;
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                total_aldeas = resultado.getInt("total");
            }
            resultado.close();
            return total_aldeas;
        }catch(SQLException ex){
            System.err.println(
                        "Error de Base de Datos tratando de obtener el total de aldeas"+
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
        return total_aldeas;
    }
    
    
    public static boolean hayCentros(Connection conn){
        String sentenciaSQL = "SELECT centro FROM tbl_centro";
        boolean hay = false;
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            if(resultado.next()){
                hay = true;
            }
            resultado.close();
        }catch(SQLException ex){
            System.err.println(
                        "Error de Base de Datos tratando de saber si hay o no centros registrados.\n"+
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
        return hay;
    }
    
    public static boolean hayResultadoFinal(Connection conn){
        String sentenciaSQL = "SELECT codigo_centro FROM tbl_resultado_final";
        boolean hay = false;
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            if(resultado.next()){
                hay = true;
            }
            resultado.close();
        }catch(SQLException ex){
            System.err.println(
                        "Error de Base de Datos tratando de saber si hay o no resultados finale registrados.\n"+
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
        return hay;
    }
    
    public static boolean hayTasasReprobados(Connection conn){
        String sentenciaSQL = "SELECT codigo_centro FROM tbl_reprobados";
        boolean hay = false;
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            if(resultado.next()){
                hay = true;
            }
            resultado.close();
        }catch(SQLException ex){
            System.err.println(
                        "Error de Base de Datos tratando de saber si hay o no tasas de reprobados registrados.\n"+
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
        return hay;
    }
    
    public static boolean hayResultadoFinalCentro(Connection conn, String codigo_centro){
        String sentenciaSQL = "SELECT codigo_centro"
                + " FROM tbl_resultado_final"
                + " WHERE codigo_centro = " + codigo_centro;
        boolean hay = false;
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            if(resultado.next()){
                hay = true;
            }
            resultado.close();
        }catch(SQLException ex){
            System.err.println(
                        "Error de Base de Datos tratando de saber si hay o no resultados finale registrados.\n"+
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
        return hay;
    }
    
    public static boolean yaExisteCentro(Connection conn, String centro, String codigo_sace){
        boolean existe = false;
        String sentenciaSQL = "SELECT codigo, centro, codigo_sace FROM tbl_centro " + 
                              "WHERE centro = '" + centro + "' AND codigo_sace = '" + codigo_sace + "'";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                existe = true;
            }
            resultado.close();
        }catch(SQLException ex){
            System.err.println(
                        "Error de Base de Datos tratando de saber si el centro ya existe en la BD.\n"+
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
        return existe;
    }
    
    public static boolean yaExisteCentroCodigo(Connection conn, String codigo_sace){
        boolean existe = false;
        String sentenciaSQL = "SELECT centro FROM tbl_centro " + 
                              "WHERE codigo_sace = '" + codigo_sace + "'";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                existe = true;
            }
            resultado.close();
        }catch(SQLException ex){
            System.err.println(
                        "Error de Base de Datos tratando de saber si el centro ya existe en la BD.\n"+
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
        return existe;
    }
    
    
    //Obtener datos en cero    
    public static void obtenerResultadosFinalCeros(Connection conn, ObservableList<IngresarResultadoFinal> lista){
        String sentenciaSQL = "SELECT"
                                + " codigo,"
                                + " grado,"
                                + " inicial_n,"
                                + " inicial_v,"
                                + " inicial_t,"
                                + " final_n,"
                                + " final_v,"
                                + " final_t,"
                                + " ingre_n,"
                                + " ingre_v,"
                                + " ingre_t,"
                                + " trasla_n,"
                                + " trasla_v,"
                                + " trasla_t,"
                                + " deser_n,"
                                + " deser_v,"
                                + " deser_t,"
                                + " evalu_n,"
                                + " evalu_v,"
                                + " evalu_t,"
                                + " inic_n,"
                                + " inic_v,"
                                + " inic_t,"
                                + " basi_n,"
                                + " basi_v,"
                                + " basi_t,"
                                + " avan_n,"
                                + " avan_v,"
                                + " avan_t,"
                                + " exce_n,"
                                + " exce_v,"
                                + " exce_t"
                            + " FROM tbl_resultado_final_ceros";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new IngresarResultadoFinal(
                                    resultado.getInt("codigo"),
                                    resultado.getString("grado"),
                                    resultado.getInt("inicial_n"),
                                    resultado.getInt("inicial_v"),
                                    resultado.getInt("inicial_t"),
                                    resultado.getInt("final_n"),
                                    resultado.getInt("final_v"),
                                    resultado.getInt("final_t"),
                                    resultado.getInt("ingre_n"),
                                    resultado.getInt("ingre_v"),
                                    resultado.getInt("ingre_t"),
                                    resultado.getInt("trasla_n"),
                                    resultado.getInt("trasla_v"),
                                    resultado.getInt("trasla_t"),
                                    resultado.getInt("deser_n"),
                                    resultado.getInt("deser_v"),
                                    resultado.getInt("deser_t"),
                                    resultado.getInt("evalu_n"),
                                    resultado.getInt("evalu_v"),
                                    resultado.getInt("evalu_t"),
                                    resultado.getInt("inic_n"),
                                    resultado.getInt("inic_v"),
                                    resultado.getInt("inic_t"),
                                    resultado.getInt("basi_n"),
                                    resultado.getInt("basi_v"),
                                    resultado.getInt("basi_t"),
                                    resultado.getInt("avan_n"),
                                    resultado.getInt("avan_v"),
                                    resultado.getInt("avan_t"),
                                    resultado.getInt("exce_n"),
                                    resultado.getInt("exce_v"),
                                    resultado.getInt("exce_t"),
                                    resultado.getInt("codigo_centro")
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener todos los datos de la tabla tbl_resultado_final_ceros\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
    }
    
    //Obtener datos en cero    
    public static void obtenerConsolidadoResultadosFinalCeros(Connection conn, ObservableList<ConsolidadoResultadoFinal> lista){
        String sentenciaSQL = "SELECT"
                                + " codigo,"
                                + " grado,"
                                + " inicial_n,"
                                + " inicial_v,"
                                + " inicial_t,"
                                + " final_n,"
                                + " final_v,"
                                + " final_t,"
                                + " ingre_n,"
                                + " ingre_v,"
                                + " ingre_t,"
                                + " trasla_n,"
                                + " trasla_v,"
                                + " trasla_t,"
                                + " deser_n,"
                                + " deser_v,"
                                + " deser_t,"
                                + " evalu_n,"
                                + " evalu_v,"
                                + " evalu_t,"
                                + " inic_n,"
                                + " inic_v,"
                                + " inic_t,"
                                + " basi_n,"
                                + " basi_v,"
                                + " basi_t,"
                                + " avan_n,"
                                + " avan_v,"
                                + " avan_t,"
                                + " exce_n,"
                                + " exce_v,"
                                + " exce_t"
                            + " FROM tbl_resultado_final_ceros";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new ConsolidadoResultadoFinal(
                                    resultado.getString("grado"),
                                    resultado.getInt("inicial_n"),
                                    resultado.getInt("inicial_v"),
                                    resultado.getInt("inicial_t"),
                                    resultado.getInt("final_n"),
                                    resultado.getInt("final_v"),
                                    resultado.getInt("final_t"),
                                    resultado.getInt("ingre_n"),
                                    resultado.getInt("ingre_v"),
                                    resultado.getInt("ingre_t"),
                                    resultado.getInt("trasla_n"),
                                    resultado.getInt("trasla_v"),
                                    resultado.getInt("trasla_t"),
                                    resultado.getInt("deser_n"),
                                    resultado.getInt("deser_v"),
                                    resultado.getInt("deser_t"),
                                    resultado.getInt("evalu_n"),
                                    resultado.getInt("evalu_v"),
                                    resultado.getInt("evalu_t"),
                                    resultado.getInt("inic_n"),
                                    resultado.getInt("inic_v"),
                                    resultado.getInt("inic_t"),
                                    resultado.getInt("basi_n"),
                                    resultado.getInt("basi_v"),
                                    resultado.getInt("basi_t"),
                                    resultado.getInt("avan_n"),
                                    resultado.getInt("avan_v"),
                                    resultado.getInt("avan_t"),
                                    resultado.getInt("exce_n"),
                                    resultado.getInt("exce_v"),
                                    resultado.getInt("exce_t")
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener todos los datos de la tabla tbl_resultado_final_ceros\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
    }
    
    public static void obtenerResultadosFinalCentro(Connection conn, ObservableList<IngresarResultadoFinal> lista, int codigo_centro){
        String sentenciaSQL = "SELECT"
                                + " codigo,"
                                + " grado,"
                                + " inicial_n,"
                                + " inicial_v,"
                                + " inicial_t,"
                                + " final_n,"
                                + " final_v,"
                                + " final_t,"
                                + " ingre_n,"
                                + " ingre_v,"
                                + " ingre_t,"
                                + " trasla_n,"
                                + " trasla_v,"
                                + " trasla_t,"
                                + " deser_n,"
                                + " deser_v,"
                                + " deser_t,"
                                + " evalu_n,"
                                + " evalu_v,"
                                + " evalu_t,"
                                + " inic_n,"
                                + " inic_v,"
                                + " inic_t,"
                                + " basi_n,"
                                + " basi_v,"
                                + " basi_t,"
                                + " avan_n,"
                                + " avan_v,"
                                + " avan_t,"
                                + " exce_n,"
                                + " exce_v,"
                                + " exce_t,"
                                + " codigo_centro"
                            + " FROM tbl_resultado_final"
                            + " WHERE codigo_centro = " + codigo_centro + "";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new IngresarResultadoFinal(
                                    resultado.getInt("codigo"),
                                    resultado.getString("grado"),
                                    resultado.getInt("inicial_n"),
                                    resultado.getInt("inicial_v"),
                                    resultado.getInt("inicial_t"),
                                    resultado.getInt("final_n"),
                                    resultado.getInt("final_v"),
                                    resultado.getInt("final_t"),
                                    resultado.getInt("ingre_n"),
                                    resultado.getInt("ingre_v"),
                                    resultado.getInt("ingre_t"),
                                    resultado.getInt("trasla_n"),
                                    resultado.getInt("trasla_v"),
                                    resultado.getInt("trasla_t"),
                                    resultado.getInt("deser_n"),
                                    resultado.getInt("deser_v"),
                                    resultado.getInt("deser_t"),
                                    resultado.getInt("evalu_n"),
                                    resultado.getInt("evalu_v"),
                                    resultado.getInt("evalu_t"),
                                    resultado.getInt("inic_n"),
                                    resultado.getInt("inic_v"),
                                    resultado.getInt("inic_t"),
                                    resultado.getInt("basi_n"),
                                    resultado.getInt("basi_v"),
                                    resultado.getInt("basi_t"),
                                    resultado.getInt("avan_n"),
                                    resultado.getInt("avan_v"),
                                    resultado.getInt("avan_t"),
                                    resultado.getInt("exce_n"),
                                    resultado.getInt("exce_v"),
                                    resultado.getInt("exce_t"),
                               resultado.getInt("codigo_centro")
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener todos los datos de la tabla tbl_resultado_final_centros\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
    }
    
    public static void obtenerReprobadosCentro(Connection conn, ObservableList<ReprobadosEspañolMatematicas> lista, int codigo_centro){
        String sentenciaSQL = "SELECT"
                                + " codigo,"
                                + " grado,"
                                + " inicial_n,"
                                + " inicial_v,"
                                + " inicial_t,"
                                + " final_n,"
                                + " final_v,"
                                + " final_t,"
                                + " eval_esp_n,"
                                + " eval_esp_v,"
                                + " eval_esp_t,"
                                + " rep_esp_n,"
                                + " rep_esp_v,"
                                + " rep_esp_t,"
                                + " tasa_esp_n,"
                                + " tasa_esp_v,"
                                + " tasa_esp_t,"
                                + " eval_mat_n,"
                                + " eval_mat_v,"
                                + " eval_mat_t,"
                                + " rep_mat_n,"
                                + " rep_mat_v,"
                                + " rep_mat_t,"
                                + " tasa_mat_n,"
                                + " tasa_mat_v,"
                                + " tasa_mat_t,"
                                + " codigo_centro"
                            + " FROM tbl_reprobados"
                            + " WHERE codigo_centro = " + codigo_centro + "";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new ReprobadosEspañolMatematicas(
                                    resultado.getInt("codigo"),
                                    resultado.getString("grado"),
                                    resultado.getInt("inicial_n"),
                                    resultado.getInt("inicial_v"),
                                    resultado.getInt("inicial_t"),
                                    resultado.getInt("final_n"),
                                    resultado.getInt("final_v"),
                                    resultado.getInt("final_t"),
                                    resultado.getInt("eval_esp_n"),
                                    resultado.getInt("eval_esp_v"),
                                    resultado.getInt("eval_esp_t"),
                                    resultado.getInt("rep_esp_n"),
                                    resultado.getInt("rep_esp_v"),
                                    resultado.getInt("rep_esp_t"),
                                    resultado.getDouble("tasa_esp_n"),
                                    resultado.getDouble("tasa_esp_v"),
                                    resultado.getDouble("tasa_esp_t"),
                                    resultado.getInt("eval_mat_n"),
                                    resultado.getInt("eval_mat_v"),
                                    resultado.getInt("eval_mat_t"),
                                    resultado.getInt("rep_mat_n"),
                                    resultado.getInt("rep_mat_v"),
                                    resultado.getInt("rep_mat_t"),
                                    resultado.getDouble("tasa_mat_n"),
                                    resultado.getDouble("tasa_mat_v"),
                                    resultado.getDouble("tasa_mat_t"),
                                    resultado.getInt("codigo_centro")
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener todos los datos de la tabla tbl_reprobados\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
    }
    
    public static void obtenerReprobados(Connection conn, ObservableList<ReprobadosEspañolMatematicas> lista, int codigo_centro){
        String sentenciaSQL = "SELECT"
                                + " codigo,"
                                + " grado,"
                                + " inicial_n,"
                                + " inicial_v,"
                                + " inicial_t,"
                                + " final_n,"
                                + " final_v,"
                                + " final_t,"
                                + " eval_esp_n,"
                                + " eval_esp_v,"
                                + " eval_esp_t,"
                                + " rep_esp_n,"
                                + " rep_esp_v,"
                                + " rep_esp_t,"
                                + " tasa_esp_n,"
                                + " tasa_esp_v,"
                                + " tasa_esp_t,"
                                + " eval_mat_n,"
                                + " eval_mat_v,"
                                + " eval_mat_t,"
                                + " rep_mat_n,"
                                + " rep_mat_v,"
                                + " rep_mat_t,"
                                + " tasa_mat_n,"
                                + " tasa_mat_v,"
                                + " tasa_mat_t,"
                                + " codigo_centro"
                            + " FROM tbl_reprobados"
                            + " WHERE codigo_centro = " + codigo_centro + "";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new ReprobadosEspañolMatematicas(
                                    resultado.getInt("codigo"),
                                    resultado.getString("grado"),
                                    resultado.getInt("inicial_n"),
                                    resultado.getInt("inicial_v"),
                                    resultado.getInt("inicial_t"),
                                    resultado.getInt("final_n"),
                                    resultado.getInt("final_v"),
                                    resultado.getInt("final_t"),
                                    resultado.getInt("eval_esp_n"),
                                    resultado.getInt("eval_esp_v"),
                                    resultado.getInt("eval_esp_t"),
                                    resultado.getInt("rep_esp_n"),
                                    resultado.getInt("rep_esp_v"),
                                    resultado.getInt("rep_esp_t"),
                                    resultado.getDouble("tasa_esp_n"),
                                    resultado.getDouble("tasa_esp_v"),
                                    resultado.getDouble("tasa_esp_t"),
                                    resultado.getInt("eval_mat_n"),
                                    resultado.getInt("eval_mat_v"),
                                    resultado.getInt("eval_mat_t"),
                                    resultado.getInt("rep_mat_n"),
                                    resultado.getInt("rep_mat_v"),
                                    resultado.getInt("rep_mat_t"),
                                    resultado.getDouble("tasa_mat_n"),
                                    resultado.getDouble("tasa_mat_v"),
                                    resultado.getDouble("tasa_mat_t"),
                                    resultado.getInt("codigo_centro")
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener todos los datos de la tabla tbl_reprobados\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
    }
    
    public static void obtenerMatriculaInicialFinalCentro(Connection conn, ObservableList<ReprobadosEspañolMatematicas> lista, int codigo_centro){
        String sentenciaSQL = "SELECT"
                                + " codigo,"
                                + " grado,"
                                + " inicial_n,"
                                + " inicial_v,"
                                + " inicial_t,"
                                + " final_n,"
                                + " final_v,"
                                + " final_t,"
                                + " codigo_centro"
                            + " FROM tbl_resultado_final"
                            + " WHERE codigo_centro = " + codigo_centro + "";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new ReprobadosEspañolMatematicas(
                                    resultado.getInt("codigo"),
                                    resultado.getString("grado"),
                                    resultado.getInt("inicial_n"),
                                    resultado.getInt("inicial_v"),
                                    resultado.getInt("inicial_t"),
                                    resultado.getInt("final_n"),
                                    resultado.getInt("final_v"),
                                    resultado.getInt("final_t"),
                                    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                                    resultado.getInt("codigo_centro")
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener la matricula y nicial y final de la tabla tbl_reprobados\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
    }
    
    public static void obtenerResultadosFinalConsolidado(Connection conn, ObservableList<ConsolidadoResultadoFinal> lista, String admon, String zona, String nivel, String ciclo, String jornada, String cobertura){
        String sentenciaSQL = "SELECT "
                                + " B.grado,"
                                + " SUM(B.inicial_n) AS inicial_n,"
                                + " SUM(B.inicial_v) AS inicial_v,"
                                + " SUM(B.inicial_t) AS inicial_t,"
                                + " SUM(B.final_n) AS final_n,"
                                + " SUM(B.final_v) AS final_v,"
                                + " SUM(B.final_t) AS final_t,"
                                + " SUM(B.ingre_n) AS ingre_n,"
                                + " SUM(B.ingre_v) AS ingre_v,"
                                + " SUM(B.ingre_t) AS ingre_t,"
                                + " SUM(B.trasla_n) AS trasla_n,"
                                + " SUM(B.trasla_v) AS trasla_v,"
                                + " SUM(B.trasla_t) AS trasla_t,"
                                + " SUM(B.deser_n) AS deser_n,"
                                + " SUM(B.deser_v) AS deser_v,"
                                + " SUM(B.deser_t) AS deser_t,"
                                + " SUM(B.evalu_n) AS evalu_n,"
                                + " SUM(B.evalu_v) AS evalu_v,"
                                + " SUM(B.evalu_t) AS evalu_t,"
                                + " SUM(B.inic_n) AS inic_n,"
                                + " SUM(B.inic_v) AS inic_v,"
                                + " SUM(B.inic_t) AS inic_t,"
                                + " SUM(B.basi_n) AS basi_n,"
                                + " SUM(B.basi_v) AS basi_v,"
                                + " SUM(B.basi_t) AS basi_t,"
                                + " SUM(B.avan_n) AS avan_n,"
                                + " SUM(B.avan_v) AS avan_v,"
                                + " SUM(B.avan_t) AS avan_t,"
                                + " SUM(B.exce_n) AS exce_n,"
                                + " SUM(B.exce_v) AS exce_v,"
                                + " SUM(B.exce_t) AS exce_t"
                            + " FROM tbl_resultado_final B"
                            + " INNER JOIN tbl_centro A"
                            + " ON B.codigo_centro = A.codigo"
                            + " AND A.administracion = '" + admon + "'"
                            + " AND A.zona = '" + zona + "'"
                            + " AND A.nivel = '" + nivel + "'"
                            + " AND A.ciclo = '" + ciclo + "'"
                            + " AND A.jornada = '" + jornada + "'"
                            + " AND A.cobertura = '" + cobertura + "'"
                            + " GROUP BY B.Grado"
                            + " ORDER BY B.codigo";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new ConsolidadoResultadoFinal(
                                    resultado.getString("grado"),
                                    resultado.getInt("inicial_n"),
                                    resultado.getInt("inicial_v"),
                                    resultado.getInt("inicial_t"),
                                    resultado.getInt("final_n"),
                                    resultado.getInt("final_v"),
                                    resultado.getInt("final_t"),
                                    resultado.getInt("ingre_n"),
                                    resultado.getInt("ingre_v"),
                                    resultado.getInt("ingre_t"),
                                    resultado.getInt("trasla_n"),
                                    resultado.getInt("trasla_v"),
                                    resultado.getInt("trasla_t"),
                                    resultado.getInt("deser_n"),
                                    resultado.getInt("deser_v"),
                                    resultado.getInt("deser_t"),
                                    resultado.getInt("evalu_n"),
                                    resultado.getInt("evalu_v"),
                                    resultado.getInt("evalu_t"),
                                    resultado.getInt("inic_n"),
                                    resultado.getInt("inic_v"),
                                    resultado.getInt("inic_t"),
                                    resultado.getInt("basi_n"),
                                    resultado.getInt("basi_v"),
                                    resultado.getInt("basi_t"),
                                    resultado.getInt("avan_n"),
                                    resultado.getInt("avan_v"),
                                    resultado.getInt("avan_t"),
                                    resultado.getInt("exce_n"),
                                    resultado.getInt("exce_v"),
                                    resultado.getInt("exce_t")
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener todos los datos de la tabla tbl_resultado_final_centros\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
    }
    
    public static void obtenerReprobadosEspMatConsolidado(Connection conn, ObservableList<ConsolidadoReprobadosEspMat> lista, String admon, String zona, String nivel, String ciclo, String jornada, String cobertura){
        String sentenciaSQL = "SELECT "
                                + " B.grado,"
                                + " SUM(B.inicial_n) AS inicial_n,"
                                + " SUM(B.inicial_v) AS inicial_v,"
                                + " SUM(B.inicial_t) AS inicial_t,"
                                + " SUM(B.final_n) AS final_n,"
                                + " SUM(B.final_v) AS final_v,"
                                + " SUM(B.final_t) AS final_t,"
                                + " SUM(B.eval_esp_n) AS eval_esp_n,"
                                + " SUM(B.eval_esp_v) AS eval_esp_v,"
                                + " SUM(B.eval_esp_t) AS eval_esp_t,"
                                + " SUM(B.rep_esp_n) AS rep_esp_n,"
                                + " SUM(B.rep_esp_v) AS rep_esp_v,"
                                + " SUM(B.rep_esp_t) AS rep_esp_t,"
                                + " SUM(B.tasa_esp_n) AS tasa_esp_n,"
                                + " SUM(B.tasa_esp_v) AS tasa_esp_v,"
                                + " SUM(B.tasa_esp_t) AS tasa_esp_t,"
                                + " SUM(B.eval_mat_n) AS eval_mat_n,"
                                + " SUM(B.eval_mat_v) AS eval_mat_v,"
                                + " SUM(B.eval_mat_t) AS eval_mat_t,"
                                + " SUM(B.rep_mat_n) AS rep_mat_n,"
                                + " SUM(B.rep_mat_v) AS rep_mat_v,"
                                + " SUM(B.rep_mat_t) AS rep_mat_t,"
                                + " SUM(B.tasa_mat_n) AS tasa_mat_n,"
                                + " SUM(B.tasa_mat_v) AS tasa_mat_v,"
                                + " SUM(B.tasa_mat_t) AS tasa_mat_t"
                            + " FROM tbl_reprobados B"
                            + " INNER JOIN tbl_centro A"
                            + " ON B.codigo_centro = A.codigo"
                            + " AND A.administracion = '" + admon + "'"
                            + " AND A.zona = '" + zona + "'"
                            + " AND A.nivel = '" + nivel + "'"
                            + " AND A.ciclo = '" + ciclo + "'"
                            + " AND A.jornada = '" + jornada + "'"
                            + " AND A.cobertura = '" + cobertura + "'"
                            + " GROUP BY B.Grado"
                            + " ORDER BY B.codigo";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new ConsolidadoReprobadosEspMat(
                                    resultado.getString("grado"),
                                    resultado.getInt("inicial_n"),
                                    resultado.getInt("inicial_v"),
                                    resultado.getInt("inicial_t"),
                                    resultado.getInt("final_n"),
                                    resultado.getInt("final_v"),
                                    resultado.getInt("final_t"),
                                    resultado.getInt("eval_esp_n"),
                                    resultado.getInt("eval_esp_v"),
                                    resultado.getInt("eval_esp_t"),
                                    resultado.getInt("rep_esp_n"),
                                    resultado.getInt("rep_esp_v"),
                                    resultado.getInt("rep_esp_t"),
                                    resultado.getInt("tasa_esp_n"),
                                    resultado.getInt("tasa_esp_v"),
                                    resultado.getInt("tasa_esp_t"),
                                    resultado.getInt("eval_mat_n"),
                                    resultado.getInt("eval_mat_v"),
                                    resultado.getInt("eval_mat_t"),
                                    resultado.getInt("rep_mat_n"),
                                    resultado.getInt("rep_mat_v"),
                                    resultado.getInt("rep_mat_t"),
                                    resultado.getInt("tasa_mat_n"),
                                    resultado.getInt("tasa_mat_v"),
                                    resultado.getInt("tasa_mat_t")
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener todos los datos de la tabla tbl_resultado_final_centros\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
    }
    
    public static int obtenerTotalConsolidadoCentro(Connection conn, String administracion, String zona, String nivel, String ciclo, String jornada, String cobertura){
        int centros = 0;
        String sentenciaSQL = "SELECT Count(*) AS total FROM ("
                                    + " SELECT DISTINCT R.codigo_centro"
                                    + " FROM tbl_resultado_final R"
                                    + " INNER JOIN tbl_centro C"
                                    + " ON R.codigo_centro = C.codigo"
                                    + " AND C.administracion = '" + administracion + "'"
                                    + " AND C.zona = '" + zona + "'"
                                    + " AND C.nivel = '" + nivel + "'"
                                    + " AND C.ciclo = '" + ciclo + "'"
                                    + " AND C.jornada = '" + jornada + "'"
                                    + " AND C.cobertura = '" + cobertura + "')"
                                + " tabla";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                centros = resultado.getInt("total");
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener el total de la tabla tbl_resultado_final\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
        return centros;
    }
    
    public static int obtenerCentrosTipo(Connection conn, String administracion, String zona, String nivel, String ciclo, String jornada, String cobertura){
        int centros = 0;
        String sentenciaSQL = "SELECT Count(*) AS total FROM"
                                    + " tbl_centro C"
                                    + " WHERE C.administracion = '" + administracion + "'"
                                    + " AND C.zona = '" + zona + "'"
                                    + " AND C.nivel = '" + nivel + "'"
                                    + " AND C.ciclo = '" + ciclo + "'"
                                    + " AND C.jornada = '" + jornada + "'"
                                    + " AND C.cobertura = '" + cobertura + "'";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                centros = resultado.getInt("total");
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener el conteo de los centros seleccionados de la tabla tbl_resultado_final_centros\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
        return centros;
    }
    
    public static boolean hayDatosResultadoFinalCentros(Connection conn, int codigo_centro){
        String sentenciaSQL = "SELECT codigo"
                + " FROM tbl_resultado_final"
                + " WHERE codigo_centro = " + codigo_centro + "";
        boolean hay = false;
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            if(resultado.next()){
                hay = true;
            }
            resultado.close();
        }catch(SQLException ex){
            System.err.println(
                        "Error de Base de Datos tratando de saber si hay o no datos de centros en la BD.\n"+
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
        return hay;
    }
    
    public static boolean hayReprobadosCentro(Connection conn, int codigo_centro){
        String sentenciaSQL = "SELECT codigo"
                + " FROM tbl_reprobados"
                + " WHERE codigo_centro = " + codigo_centro + "";
        boolean hay = false;
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            if(resultado.next()){
                hay = true;
            }
            resultado.close();
        }catch(SQLException ex){
            System.err.println(
                        "Error de Base de Datos tratando de saber si hay o no datos en la tabla tbl_reprobados.\n"+
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
        return hay;
    }
    
    public static boolean hayMatriculaInicialCentro(Connection conn, int codigo_centro){
        String sentenciaSQL = "SELECT codigo"
                + " FROM tbl_resultado_final"
                + " WHERE codigo_centro = " + codigo_centro + "";
        boolean hay = false;
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            if(resultado.next()){
                hay = true;
            }
            resultado.close();
        }catch(SQLException ex){
            System.err.println(
                        "Error de Base de Datos tratando de saber si hay o no matricula Inicial y Final para el centro en la tabla tbl_resultado_final.\n"+
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
        return hay;
    }
    
    public static boolean hayDatosMatriculaFinalEdad(Connection conn, int codigo_centro){
        String sentenciaSQL = "SELECT codigo"
                + " FROM tbl_matricula_final_edad"
                + " WHERE codigo_centro = '" + codigo_centro + "'";
        boolean hay = false;
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            if(resultado.next()){
                hay = true;
            }
            resultado.close();
        }catch(SQLException ex){
            System.err.println(
                        "Error de Base de Datos tratando de saber si hay o no datos de centros en la BD.\n"+
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
        return hay;
    }
    
    public static boolean hayDatosResultadoFinalCeros(Connection conn){
        String sentenciaSQL = "SELECT grado"
                + " FROM tbl_resultado_final_ceros";
        boolean hay = false;
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            if(resultado.next()){
                hay = true;
            }
            resultado.close();
        }catch(SQLException ex){
            System.err.println(
                        "Error de Base de Datos tratando de saber si hay o no datos en cero en la tabla tbl_resultado_final_ceros.\n"+
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
        return hay;
    }
    
    public static void obtenerDatosCentroCodigo(Connection conn, ObservableList<BuscarCentro> lista, String codigo_sace){
            String sentenciaSQL = "SELECT"
                                + " A.codigo,"
                                + " A.centro,"
                                + " A.codigo_sace,"
                                + " A.administracion,"
                                + " A.zona,"
                                + " A.nivel,"
                                + " A.ciclo,"
                                + " A.jornada,"
                                + " A.cobertura,"
                                + " A.matricula"
                            + " FROM tbl_centro A"
                            + " WHERE A.codigo_sace LIKE '%" + codigo_sace + "%'";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new BuscarCentro(
                                    resultado.getInt("codigo"),
                                    resultado.getString("centro"),
                                    resultado.getString("codigo_sace"),
                                    resultado.getString("administracion"),
                                    resultado.getString("zona"),
                                    resultado.getString("nivel"),
                                    resultado.getString("ciclo"),
                                    resultado.getString("jornada"),
                                    resultado.getString("cobertura"),
                                    resultado.getInt("matricula")
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener todos los datos del centro codigo " + codigo_sace + " tabla tbl_centro\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
    }
    
    public static int obtenerMatriculaFinalCentroCodigo(Connection conn, String codigo_sace){
        int matricula_final = 0;    
        String sentenciaSQL = "SELECT"
                                + " A.matricula"
                            + " FROM tbl_centro A"
                            + " WHERE A.codigo_sace = '" + codigo_sace + "'";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                matricula_final = resultado.getInt("matricula");
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener la matricula final del centro codigo " + codigo_sace + " tabla tbl_centro\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
        return matricula_final;
    }
    
    public static void obtenerDatosCentro(Connection conn, ObservableList<BuscarCentro> lista){
            String sentenciaSQL = "SELECT"
                                + " A.codigo,"
                                + " A.centro,"
                                + " A.codigo_sace,"
                                + " A.administracion,"
                                + " A.zona,"
                                + " A.nivel,"
                                + " A.ciclo,"
                                + " A.jornada,"
                                + " A.cobertura,"
                                + " A.matricula"
                            + " FROM tbl_centro A"
                            + " ORDER BY A.centro";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new BuscarCentro(
                                    resultado.getInt("codigo"),
                                    resultado.getString("centro"),
                                    resultado.getString("codigo_sace"),
                                    resultado.getString("administracion"),
                                    resultado.getString("zona"),
                                    resultado.getString("nivel"),
                                    resultado.getString("ciclo"),
                                    resultado.getString("jornada"),
                                    resultado.getString("cobertura"),
                                    resultado.getInt("matricula")
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener todos los datos de la tabla tbl_centro\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
    }
    
    public static void obtenerDatosCentros(Connection conn, ObservableList<Centro> lista){
            String sentenciaSQL = "SELECT"
                                + " A.codigo,"
                                + " A.centro,"
                                + " A.codigo_sace,"
                                + " A.administracion,"
                                + " A.zona,"
                                + " A.nivel,"
                                + " A.ciclo,"
                                + " A.jornada,"
                                + " A.cobertura,"
                                + " A.matricula"
                            + " FROM tbl_centro A"
                            + " ORDER BY A.centro";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new Centro(
                                    resultado.getInt("codigo"),
                                    resultado.getString("centro"),
                                    resultado.getString("codigo_sace"),
                                    resultado.getString("administracion"),
                                    resultado.getString("zona"),
                                    resultado.getString("nivel"),
                                    resultado.getString("ciclo"),
                                    resultado.getString("jornada"),
                                    resultado.getString("cobertura"),
                                    resultado.getInt("matricula")
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener todos los datos de la tabla tbl_centro\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
    }
    
    public static void obtenerDatosCentrosConReprobados(Connection conn, ObservableList<BuscarCentro> lista){
        String sentenciaSQL = "SELECT "
                                + " A.codigo,"
                                + " A.centro,"
                                + " A.codigo_sace,"
                                + " A.administracion,"
                                + " A.zona,"
                                + " A.nivel,"
                                + " A.ciclo,"
                                + " A.jornada,"
                                + " A.cobertura,"
                                + " A.matricula"
                            + " FROM tbl_centro A"
                            + " WHERE A.codigo IN ("
                                + " SELECT B.codigo_centro"
                                + " FROM tbl_reprobados B)"
                            + " GROUP BY A.codigo_sace"
                            + " ORDER BY A.centro";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new BuscarCentro(
                                    resultado.getInt("codigo"),
                                    resultado.getString("centro"),
                                    resultado.getString("codigo_sace"),
                                    resultado.getString("administracion"),
                                    resultado.getString("zona"),
                                    resultado.getString("nivel"),
                                    resultado.getString("ciclo"),
                                    resultado.getString("jornada"),
                                    resultado.getString("cobertura"),
                                    resultado.getInt("matricula")
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener los datos de los centros con reprobados de la tabla tbl_reprobados\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }    
    }
    
    public static void obtenerDatosCentrosConMatriculaInicialFinal(Connection conn, ObservableList<BuscarCentro> lista){
        String sentenciaSQL = "SELECT "
                                + " A.codigo,"
                                + " A.centro,"
                                + " A.codigo_sace,"
                                + " A.administracion,"
                                + " A.zona,"
                                + " A.nivel,"
                                + " A.ciclo,"
                                + " A.jornada,"
                                + " A.cobertura,"
                                + " A.matricula"
                            + " FROM tbl_centro A"
                            + " WHERE A.codigo IN ("
                                + " SELECT B.codigo_centro"
                                + " FROM tbl_resultado_final B)"
                            + " GROUP BY A.codigo_sace"
                            + " ORDER BY A.centro";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new BuscarCentro(
                                    resultado.getInt("codigo"),
                                    resultado.getString("centro"),
                                    resultado.getString("codigo_sace"),
                                    resultado.getString("administracion"),
                                    resultado.getString("zona"),
                                    resultado.getString("nivel"),
                                    resultado.getString("ciclo"),
                                    resultado.getString("jornada"),
                                    resultado.getString("cobertura"),
                                    resultado.getInt("matricula")
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener los datos de los centros con reprobados de la tabla tbl_reprobados\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }    
    }
    
    public static void obtenerDatosCentrosEstadoLlenos(Connection conn, ObservableList<Progreso> lista){
        String sentenciaSQL = "SELECT "
                                + " A.codigo,"
                                + " A.centro,"
                                + " A.codigo_sace,"
                                + " A.administracion,"
                                + " A.zona,"
                                + " A.nivel,"
                                + " A.ciclo,"
                                + " A.jornada,"
                                + " A.cobertura,"
                                + " A.matricula"
                            + " FROM tbl_centro A"
                            + " WHERE A.codigo IN ("
                                + " SELECT B.codigo_centro"
                                + " FROM tbl_resultado_final B)"
                            + " GROUP BY A.codigo_sace"
                            + " ORDER BY A.centro";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new Progreso(
                                    resultado.getInt("codigo"),
                                    resultado.getString("centro"),
                                    resultado.getString("codigo_sace"),
                                    resultado.getString("administracion"),
                                    resultado.getString("zona"),
                                    resultado.getString("nivel"),
                                    resultado.getString("ciclo"),
                                    resultado.getString("jornada"),
                                    resultado.getString("cobertura"),
                                    resultado.getInt("matricula"),
                                    "LLENO"
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener los datos de los centros llenos de la tabla tbl_resultado_final_centros\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
    }
    
    public static void obtenerDatosCentrosResultadoFinalLlenos(Connection conn, ObservableList<BuscarCentro> lista){
            String sentenciaSQL = "SELECT "
                                + " A.codigo,"
                                + " A.centro,"
                                + " A.codigo_sace,"
                                + " A.administracion,"
                                + " A.zona,"
                                + " A.nivel,"
                                + " A.ciclo,"
                                + " A.jornada,"
                                + " A.cobertura,"
                                + " A.matricula"
                            + " FROM tbl_centro A"
                            + " WHERE A.codigo IN ("
                                + " SELECT B.codigo_centro"
                                + " FROM tbl_resultado_final B)"
                            + " GROUP BY A.codigo_sace"
                            + " ORDER BY A.centro";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new BuscarCentro(
                                    resultado.getInt("codigo"),
                                    resultado.getString("centro"),
                                    resultado.getString("codigo_sace"),
                                    resultado.getString("administracion"),
                                    resultado.getString("zona"),
                                    resultado.getString("nivel"),
                                    resultado.getString("ciclo"),
                                    resultado.getString("jornada"),
                                    resultado.getString("cobertura"),
                                    resultado.getInt("matricula")
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener los datos de los centros llenos de la tabla tbl_resultado_final_centros\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
    }
    
    public static void obtenerDatosCentrosLlenosTipo(Connection conn, ObservableList<Progreso> lista, String administracion, String zona, String nivel, String ciclo, String jornada, String cobertura){
            String sentenciaSQL = "SELECT "
                                + " A.codigo,"
                                + " A.centro,"
                                + " A.codigo_sace,"
                                + " A.administracion,"
                                + " A.zona,"
                                + " A.nivel,"
                                + " A.ciclo,"
                                + " A.jornada,"
                                + " A.cobertura,"
                                + " A.matricula"
                            + " FROM tbl_centro A"
                            + " WHERE A.codigo IN ("
                                + " SELECT B.codigo_centro"
                                + " FROM tbl_resultado_final B"
                                + " WHERE B.codigo_centro = A.codigo"
                                + " AND A.administracion = '" + administracion + "'"
                                + " AND A.zona = '" + zona + "'"
                                + " AND A.nivel = '" + nivel + "'"
                                + " AND A.ciclo = '" + ciclo + "'"
                                + " AND A.jornada = '" + jornada + "'"
                                + " AND A.cobertura = '" + cobertura +"'"
                                + ")"
                            + " GROUP BY A.codigo_sace"
                            + " ORDER BY A.centro";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new Progreso(
                                    resultado.getInt("codigo"),
                                    resultado.getString("centro"),
                                    resultado.getString("codigo_sace"),
                                    resultado.getString("administracion"),
                                    resultado.getString("zona"),
                                    resultado.getString("nivel"),
                                    resultado.getString("ciclo"),
                                    resultado.getString("jornada"),
                                    resultado.getString("cobertura"),
                                    resultado.getInt("matricula"),
                                    "INGRESADO"
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener los datos de los centros llenos por tipo de las tablas tbl_centro y tbl_resultado_final\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
    }
    
    public static void obtenerDatosCentrosPendientesTipo(Connection conn, ObservableList<Progreso> lista, String administracion, String zona, String nivel, String ciclo, String jornada, String cobertura){
            String sentenciaSQL = "SELECT "
                                + " A.codigo,"
                                + " A.centro,"
                                + " A.codigo_sace,"
                                + " A.administracion,"
                                + " A.zona,"
                                + " A.nivel,"
                                + " A.ciclo,"
                                + " A.jornada,"
                                + " A.cobertura,"
                                + " A.matricula"
                            + " FROM tbl_centro A"
                            + " WHERE A.codigo NOT IN("
                                + " SELECT B.codigo_centro"
                                + " FROM tbl_resultado_final B"
                                + " WHERE B.codigo_centro = A.codigo"
                                + " and A.administracion = '" + administracion + "'"
                                + " AND A.zona = '" + zona + "'"
                                + " AND A.nivel = '" + nivel + "'"
                                + " AND A.ciclo = '" + ciclo + "'"
                                + " AND A.jornada = '" + jornada + "'"
                                + " AND A.cobertura = '" + cobertura +"'"
                                + ")"
                            + " GROUP BY A.codigo_sace"
                            + " ORDER BY A.centro";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new Progreso(
                                    resultado.getInt("codigo"),
                                    resultado.getString("centro"),
                                    resultado.getString("codigo_sace"),
                                    resultado.getString("administracion"),
                                    resultado.getString("zona"),
                                    resultado.getString("nivel"),
                                    resultado.getString("ciclo"),
                                    resultado.getString("jornada"),
                                    resultado.getString("cobertura"),
                                    resultado.getInt("matricula"),
                                    "PENDIENTE"
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener los datos de los centros pendientes por tipo de las tablas tbl_centro y tbl_resultado_final\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
    }
    
    public static void obtenerDatosCentrosEstadoPendientes(Connection conn, ObservableList<Progreso> lista, String administracion, String zona, String nivel, String ciclo, String jornada, String cobertura){
        System.out.println("\nObteniendo Centros Pendientes");    
        String sentenciaSQL = "SELECT "
                                + " A.codigo,"
                                + " A.centro,"
                                + " A.codigo_sace,"
                                + " A.administracion,"
                                + " A.zona,"
                                + " A.nivel,"
                                + " A.ciclo,"
                                + " A.jornada,"
                                + " A.cobertura,"
                                + " A.matricula"
                            + " FROM tbl_centro A"
                            + " WHERE NOT A.codigo IN ("
                                + " SELECT B.codigo_centro"
                                + " FROM tbl_resultado_final B)"
                            + " AND A.administracion = '" + administracion + "'"
                            + " AND A.zona = '" + zona + "'"
                            + " AND A.nivel = '" + nivel + "'"
                            + " AND A.ciclo = '" + ciclo + "'"
                            + " AND A.jornada = '" + jornada + "'"
                            + " AND A.cobertura = '" + cobertura + "'"
                            + " GROUP BY A.codigo_sace"
                            + " ORDER BY A.centro";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new Progreso(
                                    resultado.getInt("codigo"),
                                    resultado.getString("centro"),
                                    resultado.getString("codigo_sace"),
                                    resultado.getString("administracion"),
                                    resultado.getString("zona"),
                                    resultado.getString("nivel"),
                                    resultado.getString("ciclo"),
                                    resultado.getString("jornada"),
                                    resultado.getString("cobertura"),
                                    resultado.getInt("matricula"),
                                    "PENDIENTE"
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener todos los datos de la tabla tbl_centro\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
    }
    
    public static int obtenerTotalCentrosTipo(Connection conn, String administracion, String zona, String nivel, String ciclo, String jornada, String cobertura){
        int total = 0;
        String sentenciaSQL = "SELECT COUNT(A.codigo) AS total"
                            + " FROM tbl_centro A"
                            + " WHERE A.administracion = '" + administracion + "'"
                            + " AND A.zona = '" + zona + "'"
                            + " AND A.nivel = '" + nivel + "'"
                            + " AND A.ciclo = '" + ciclo + "'"
                            + " AND A.jornada = '" + jornada + "'"
                            + " AND A.cobertura = '" + cobertura + "'";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                total = resultado.getInt("total");
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener el total de centros del mismo tipo de la tabla tbl_centro\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
        return total;
    }
    
    public static int optenerTotalCentros(Connection conn){
        String sentenciaSQL = "SELECT COUNT(codigo_sace) AS total"
                                + " FROM tbl_centro";
        int total_centros = 0;
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                total_centros = resultado.getInt("total");
            }
            resultado.close();
            return total_centros;
        }catch(SQLException ex){
            System.err.println(
                        "Error de Base de Datos tratando de obtener el total de centros"+
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
        return total_centros;
    }
    
    public static void obtenerDatosTasaReprobadosLlenosTipo(Connection conn, ObservableList<Progreso> lista, String administracion, String zona, String nivel, String ciclo, String jornada, String cobertura){
            String sentenciaSQL = "SELECT "
                                + " A.codigo,"
                                + " A.centro,"
                                + " A.codigo_sace,"
                                + " A.administracion,"
                                + " A.zona,"
                                + " A.nivel,"
                                + " A.ciclo,"
                                + " A.jornada,"
                                + " A.cobertura,"
                                + " A.matricula"
                            + " FROM tbl_centro A"
                            + " WHERE A.codigo IN ("
                                + " SELECT B.codigo_centro"
                                + " FROM tbl_reprobados B"
                                + " WHERE B.codigo_centro = A.codigo"
                                + " AND A.administracion = '" + administracion + "'"
                                + " AND A.zona = '" + zona + "'"
                                + " AND A.nivel = '" + nivel + "'"
                                + " AND A.ciclo = '" + ciclo + "'"
                                + " AND A.jornada = '" + jornada + "'"
                                + " AND A.cobertura = '" + cobertura +"'"
                                + ")"
                            + " GROUP BY A.codigo_sace"
                            + " ORDER BY A.centro";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new Progreso(
                                    resultado.getInt("codigo"),
                                    resultado.getString("centro"),
                                    resultado.getString("codigo_sace"),
                                    resultado.getString("administracion"),
                                    resultado.getString("zona"),
                                    resultado.getString("nivel"),
                                    resultado.getString("ciclo"),
                                    resultado.getString("jornada"),
                                    resultado.getString("cobertura"),
                                    resultado.getInt("matricula"),
                                    "INGRESADO"
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener los datos de los centros llenos por tipo de las tablas tbl_centro y tbl_reprobados\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
    }
    
    public static void obtenerDatosTasaReprobadosPendientes(Connection conn, ObservableList<Progreso> lista, String administracion, String zona, String nivel, String ciclo, String jornada, String cobertura){
        String sentenciaSQL = "SELECT "
                                + " A.codigo,"
                                + " A.centro,"
                                + " A.codigo_sace,"
                                + " A.administracion,"
                                + " A.zona,"
                                + " A.nivel,"
                                + " A.ciclo,"
                                + " A.jornada,"
                                + " A.cobertura,"
                                + " A.matricula"
                            + " FROM tbl_centro A"
                            + " WHERE NOT A.codigo IN ("
                                + " SELECT B.codigo_centro"
                                + " FROM tbl_reprobados B)"
                            + " AND A.administracion = '" + administracion + "'"
                            + " AND A.zona = '" + zona + "'"
                            + " AND A.nivel = '" + nivel + "'"
                            + " AND A.ciclo = '" + ciclo + "'"
                            + " AND A.jornada = '" + jornada + "'"
                            + " AND A.cobertura = '" + cobertura + "'"
                            + " GROUP BY A.codigo_sace"
                            + " ORDER BY A.centro";
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                lista.add(new Progreso(
                                    resultado.getInt("codigo"),
                                    resultado.getString("centro"),
                                    resultado.getString("codigo_sace"),
                                    resultado.getString("administracion"),
                                    resultado.getString("zona"),
                                    resultado.getString("nivel"),
                                    resultado.getString("ciclo"),
                                    resultado.getString("jornada"),
                                    resultado.getString("cobertura"),
                                    resultado.getInt("matricula"),
                                    "PENDIENTE"
                                )
                         );
            }
        }catch(SQLException ex){
            System.err.println("Error al tratar de obtener todos los datos de la tabla tbl_centro y tbl_reprobados\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
    }
    
    public static int contarCentrosEstadoLleno(Connection conn){
            String sentenciaSQL = "SELECT COUNT(codigo_sace) AS total"
                            + " FROM tbl_resultado_final"
                            + " GROUP BY codigo_sace";
            int total_filas = 0;
        try{
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while(resultado.next()){
                total_filas = resultado.getInt("total");
            }
            resultado.close();
            return total_filas;
        }catch(SQLException ex){
            System.err.println("Error al tratar de contar las filas de la tabla tbl_resultado_final_centros\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
        return total_filas;
    }
    
    

    
    // ESTE METODO ME SIRVIO PARA SI LAS TABLAS DE LA BASE DE DATOS ESTABAN DESBLOQUEADAS (lo use en debuggin)
    // Muestra el estado de las tablas, aprendi a que debo cerrar los ResulSet siempre
    public static void encontrarBloqueo(Connection conn){
	String sentenciaSQL = "SELECT * FROM SYSCS_DIAG.LOCK_TABLE";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            while (resultado.next()){
                System.out.println("\t\t\t" + resultado.getString(1) + "\t" + resultado.getString(2)+ "\t" + resultado.getString(3) + "\t" + resultado.getString(4) + "\t" + resultado.getString(5) + "\t" + resultado.getString(6) + "\t" + resultado.getString(7) + "\t" + resultado.getString(8));
            }
            resultado.close();
        } catch (SQLException ex) {
            System.err.println(
                        "\nError al tratar de obtener el estado de bloqueo de las tablas.\n" +
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
        }
    }
    
}
