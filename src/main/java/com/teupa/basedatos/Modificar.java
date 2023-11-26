/*
 * Copyright (C) 2021 Hector Armando Herrera
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static com.teupa.utilidades.ObtenerNombreClase.*;

/**
 * 
 * @author Héctor Armando Herrera <profeharmando@gmail.com>
 */
public class Modificar {
    
    public static boolean modificarResultadoFinal(Connection conn, int codigo, String grado,
                                            int mat_ini_n, int mat_ini_v, int mat_ini_t,
                                            int mat_fin_n, int mat_fin_v, int mat_fin_t,
                                            int ingre_n, int ingre_v, int ingre_t,
                                            int trasla_n, int trasla_v, int trasla_t,
                                            int deser_n, int deser_v, int deser_t,
                                            int evalu_n, int evalu_v, int evalu_t,
                                            int inic_n, int inic_v, int inic_t,
                                            int basi_n, int basi_v, int basi_t,
                                            int avan_n, int avan_v, int avan_t,
                                            int exce_n, int exce_v, int exce_t){
        boolean modificado = false;
        String sentenciaSQL = "UPDATE tbl_resultado_final SET"
                                    + " grado = ?,"
                                    + " inicial_n = ?,"
                                    + " inicial_v = ?,"
                                    + " inicial_t = ?,"
                                    + " final_n = ?,"
                                    + " final_v = ?,"
                                    + " final_t = ?,"
                                    + " ingre_n = ?,"
                                    + " ingre_v = ?,"
                                    + " ingre_t = ?,"
                                    + " trasla_n = ?,"
                                    + " trasla_v = ?,"
                                    + " trasla_t = ?,"
                                    + " deser_n = ?,"
                                    + " deser_v = ?,"
                                    + " deser_t = ?,"
                                    + " evalu_n = ?,"
                                    + " evalu_v = ?,"
                                    + " evalu_t = ?,"
                                    + " inic_n = ?,"
                                    + " inic_v = ?,"
                                    + " inic_t = ?,"
                                    + " basi_n = ?,"
                                    + " basi_v = ?,"
                                    + " basi_t = ?,"
                                    + " avan_n = ?,"
                                    + " avan_v = ?,"
                                    + " avan_t = ?,"
                                    + " exce_n = ?,"
                                    + " exce_v = ?,"
                                    + " exce_t = ?"
                              + " WHERE codigo = " + codigo;
        try {
            PreparedStatement instruccion = conn.prepareStatement(sentenciaSQL);
            instruccion.setString(1, grado);
            instruccion.setInt(2, mat_ini_n);
            instruccion.setInt(3, mat_ini_v);
            instruccion.setInt(4, mat_ini_t);
            instruccion.setInt(5, mat_fin_n);
            instruccion.setInt(6, mat_fin_v);
            instruccion.setInt(7, mat_fin_t);
            instruccion.setInt(8, ingre_n);
            instruccion.setInt(9, ingre_v);
            instruccion.setInt(10, ingre_t);
            instruccion.setInt(11, trasla_n);
            instruccion.setInt(12, trasla_v);
            instruccion.setInt(13, trasla_t);
            instruccion.setInt(14, deser_n);
            instruccion.setInt(15, deser_v);
            instruccion.setInt(16, deser_t);
            instruccion.setInt(17, evalu_n);
            instruccion.setInt(18, evalu_v);
            instruccion.setInt(19, evalu_t);
            instruccion.setInt(20, inic_n);
            instruccion.setInt(21, inic_v);
            instruccion.setInt(22, inic_t);
            instruccion.setInt(23, basi_n);
            instruccion.setInt(24, basi_v);
            instruccion.setInt(25, basi_t);
            instruccion.setInt(26, avan_n);
            instruccion.setInt(27, avan_v);
            instruccion.setInt(28, avan_t);
            instruccion.setInt(29, exce_n);
            instruccion.setInt(30, exce_v);
            instruccion.setInt(31, exce_t);
            if(instruccion.executeUpdate() > 0){
                modificado = true;
            }
        } catch (SQLException ex) {
            System.err.println(
                        "Error de Base de Datos tratando de modificar la tbl_resultado_final.\n"+
                        "Clase: " + getNombreClase() + "\n" +
                        "Método: " + getNombreMetodo() + "\n" +
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
            modificado = false;
        }
        return modificado;
    }
    
    public static boolean modificarReprobados(Connection conn, int codigo, String grado,
                                            int mat_ini_n, int mat_ini_v, int mat_ini_t,
                                            int mat_fin_n, int mat_fin_v, int mat_fin_t,
                                            int eval_esp_n, int eval_esp_v, int eval_esp_t,
                                            int rep_esp_n, int rep_esp_v, int rep_esp_t,
                                            double tasa_esp_n, double tasa_esp_v, double tasa_esp_t,
                                            int eval_mat_n, int eval_mat_v, int eval_mat_t,
                                            int rep_mat_n, int rep_mat_v, int rep_mat_t,
                                            double tasa_mat_n, double tasa_mat_v, double tasa_mat_t){
        boolean modificado = false;
        String sentenciaSQL = "UPDATE tbl_reprobados SET"
                                    + " grado = ?,"
                                    + " inicial_n = ?,"
                                    + " inicial_v = ?,"
                                    + " inicial_t = ?,"
                                    + " final_n = ?,"
                                    + " final_v = ?,"
                                    + " final_t = ?,"
                                    + " eval_esp_n = ?,"
                                    + " eval_esp_v = ?,"
                                    + " eval_esp_t = ?,"
                                    + " rep_esp_n = ?,"
                                    + " rep_esp_v = ?,"
                                    + " rep_esp_t = ?,"
                                    + " tasa_esp_n = ?,"
                                    + " tasa_esp_v = ?,"
                                    + " tasa_esp_t = ?,"
                                    + " eval_mat_n = ?,"
                                    + " eval_mat_v = ?,"
                                    + " eval_mat_t = ?,"
                                    + " rep_mat_n = ?,"
                                    + " rep_mat_v = ?,"
                                    + " rep_mat_t = ?,"
                                    + " tasa_mat_n = ?,"
                                    + " tasa_mat_v = ?,"
                                    + " tasa_mat_t = ?"
                              + " WHERE codigo = " + codigo;
        try {
            PreparedStatement instruccion = conn.prepareStatement(sentenciaSQL);
            instruccion.setString(1, grado);
            instruccion.setInt(2, mat_ini_n);
            instruccion.setInt(3, mat_ini_v);
            instruccion.setInt(4, mat_ini_t);
            instruccion.setInt(5, mat_fin_n);
            instruccion.setInt(6, mat_fin_v);
            instruccion.setInt(7, mat_fin_t);
            instruccion.setInt(8, eval_esp_n);
            instruccion.setInt(9, eval_esp_v);
            instruccion.setInt(10, eval_esp_t);
            instruccion.setInt(11, rep_esp_n);
            instruccion.setInt(12, rep_esp_v);
            instruccion.setInt(13, rep_esp_t);
            instruccion.setDouble(14, tasa_esp_n);
            instruccion.setDouble(15, tasa_esp_v);
            instruccion.setDouble(16, tasa_esp_t);
            instruccion.setInt(17, eval_mat_n);
            instruccion.setInt(18, eval_mat_v);
            instruccion.setInt(19, eval_mat_t);
            instruccion.setInt(20, rep_mat_n);
            instruccion.setInt(21, rep_mat_v);
            instruccion.setInt(22, rep_mat_t);
            instruccion.setDouble(23, tasa_mat_n);
            instruccion.setDouble(24, tasa_mat_v);
            instruccion.setDouble(25, tasa_mat_t);
            if(instruccion.executeUpdate() > 0){
                modificado = true;
            }
        } catch (SQLException ex) {
            System.err.println(
                        "\nError de Base de Datos tratando de modificar los datos de la tbl_reprobados.\n"+
                        "Clase: " + getNombreClase() + "\n" +
                        "Método: " + getNombreMetodo() + "\n" +
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
            modificado = false;
        }
        return modificado;
    }
    
    public static boolean modificarMatriculaEdadGradoSexo(Connection conn, int codigo, String grado,
                                            int edad_menos_6_n, int edad_menos_6_v, int edad_menos_6_t,
                                            int edad_6_n, int edad_6_v, int edad_6_t,
                                            int edad_7_n, int edad_7_v, int edad_7_t,
                                            int edad_8_n, int edad_8_v, int edad_8_t,
                                            int edad_9_n, int edad_9_v, int edad_9_t,
                                            int edad_10_n, int edad_10_v, int edad_10_t,
                                            int edad_11_n, int edad_11_v, int edad_11_t,
                                            int edad_12_n, int edad_12_v, int edad_12_t,
                                            int edad_13_n, int edad_13_v, int edad_13_t,
                                            int edad_14_n, int edad_14_v, int edad_14_t,
                                            int edad_15_mas_n, int edad_15_mas_v, int edad_15_mas_t){
        boolean modificado = false;
        String sentenciaSQL = "UPDATE tbl_matricula_final_edad SET"
                                    + " grado = ?,"
                                    + " edad_menos_6_n = ?,"
                                    + " edad_menos_6_v = ?,"
                                    + " edad_menos_6_t = ?,"
                                    + " edad_6_n = ?,"
                                    + " edad_6_v = ?,"
                                    + " edad_6_t = ?,"
                                    + " edad_7_n = ?,"
                                    + " edad_7_v = ?,"
                                    + " edad_7_t = ?,"
                                    + " edad_8_n = ?,"
                                    + " edad_8_v = ?,"
                                    + " edad_8_t = ?,"
                                    + " edad_9_n = ?,"
                                    + " edad_9_v = ?,"
                                    + " edad_9_t = ?,"
                                    + " edad_10_n = ?,"
                                    + " edad_10_v = ?,"
                                    + " edad_10_t = ?,"
                                    + " edad_11_n = ?,"
                                    + " edad_11_v = ?,"
                                    + " edad_11_t = ?,"
                                    + " edad_12_n = ?,"
                                    + " edad_12_v = ?,"
                                    + " edad_12_t = ?,"
                                    + " edad_13_n = ?,"
                                    + " edad_13_v = ?,"
                                    + " edad_13_t = ?,"
                                    + " edad_14_n = ?,"
                                    + " edad_14_v = ?,"
                                    + " edad_14_t = ?,"
                                    + " edad_15_mas_n = ?,"
                                    + " edad_15_mas_v = ?,"
                                    + " edad_15_mas_t = ?"
                              + " WHERE codigo = " + codigo;
        try {
            PreparedStatement instruccion = conn.prepareStatement(sentenciaSQL);
            instruccion.setString(1, grado);
            instruccion.setInt(2, edad_menos_6_n);
            instruccion.setInt(3, edad_menos_6_v);
            instruccion.setInt(4, edad_menos_6_t);
            instruccion.setInt(5, edad_6_n);
            instruccion.setInt(6, edad_6_v);
            instruccion.setInt(7, edad_6_t);
            instruccion.setInt(8, edad_7_n);
            instruccion.setInt(9, edad_7_v);
            instruccion.setInt(10, edad_7_t);
            instruccion.setInt(11, edad_8_n);
            instruccion.setInt(12, edad_8_v);
            instruccion.setInt(13, edad_8_t);
            instruccion.setInt(14, edad_9_n);
            instruccion.setInt(15, edad_9_v);
            instruccion.setInt(16, edad_9_t);
            instruccion.setInt(17, edad_10_n);
            instruccion.setInt(18, edad_10_v);
            instruccion.setInt(19, edad_10_t);
            instruccion.setInt(20, edad_11_n);
            instruccion.setInt(21, edad_11_v);
            instruccion.setInt(22, edad_11_t);
            instruccion.setInt(23, edad_12_n);
            instruccion.setInt(24, edad_12_v);
            instruccion.setInt(25, edad_12_t);
            instruccion.setInt(26, edad_13_n);
            instruccion.setInt(27, edad_13_v);
            instruccion.setInt(28, edad_13_t);
            instruccion.setInt(29, edad_14_n);
            instruccion.setInt(30, edad_14_v);
            instruccion.setInt(31, edad_14_t);
            instruccion.setInt(32, edad_15_mas_n);
            instruccion.setInt(33, edad_15_mas_v);
            instruccion.setInt(34, edad_15_mas_t);
            if(instruccion.executeUpdate() > 0){
                modificado = true;
            }
        } catch (SQLException ex) {
            System.err.println(
                        "Error de Base de Datos tratando de modificar la tbl_matricula_final_edad.\n"+
                        "Clase: " + getNombreClase() + "\n" +
                        "Método: " + getNombreMetodo() + "\n" +
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
            modificado = false;
        }
        return modificado;
    }
    
    public static boolean modificarCentro(Connection conn, int codigo, String centro, String codigo_sace, String administracion, String zona, String nivel, String ciclo, String jornada, String cobertura, int matricula){
        boolean modificado = false;
        String sentenciaSQL = "UPDATE tbl_centro SET"
                                    + " centro = ?,"
                                    + " codigo_sace = ?,"
                                    + " administracion = ?,"
                                    + " zona = ?,"
                                    + " nivel = ?,"
                                    + " ciclo = ?,"
                                    + " jornada = ?,"
                                    + " cobertura = ?,"
                                    + " matricula = ?"
                              + " WHERE codigo = " + codigo;
        try {
            PreparedStatement instruccion = conn.prepareStatement(sentenciaSQL);
            instruccion.setString(1, centro);
            instruccion.setString(2, codigo_sace);
            instruccion.setString(3, administracion);
            instruccion.setString(4, zona);
            instruccion.setString(5, nivel);
            instruccion.setString(6, ciclo);
            instruccion.setString(7, jornada);
            instruccion.setString(8, cobertura);
            instruccion.setInt(9, matricula);
            if(instruccion.executeUpdate() > 0){
                modificado = true;
            }
        } catch (SQLException ex) {
            System.err.println(
                        "Error de Base de Datos tratando de modificar el centro.\n"+
                        "Clase: " + getNombreClase() + "\n" +
                        "Método: " + getNombreMetodo() + "\n" +
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
            modificado = false;
        }
        return modificado;
    }
    
    
}
