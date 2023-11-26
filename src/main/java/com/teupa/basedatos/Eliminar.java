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

import com.teupa.basedatos.ConexionMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import static com.teupa.utilidades.ObtenerNombreClase.*;

/**
 * 
 * @author Héctor Armando Herrera <profeharmando@gmail.com>
 */
public class Eliminar {
        
    public static boolean eliminarTabla(Connection conn, String tabla){
        String sentenciaSQL = "DROP TABLE " + tabla;
        try {
            Statement instruccion = conn.createStatement();
            instruccion.execute(sentenciaSQL);
            System.out.println("\n\tLa tabla " + tabla + " ha sido eliminada");
            return true;
        } catch (SQLException ex) {
            System.err.println("Error al tratar de Eliminar la Tabla " + tabla + ".\n"
                        + "Clase: " + getNombreClase() + "\n"
                        + "Método: " + getNombreMetodo() + "\n"
                        + "Sentencia SQL: \"" + sentenciaSQL + "\"\n"
                        + "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
            return false;
        }
    }
    
    //Elimina todo registro de acuerdo al nombre de la tabla    
    public static boolean limpiarTabla(Connection conn, String nombre_tabla){
        String sentenciaSQL = "DELETE"
                            + " FROM " + nombre_tabla;
        boolean limpiado = false;
        int borrado = 0;
        try {
            PreparedStatement instruccion = conn.prepareStatement(sentenciaSQL);
            borrado = instruccion.executeUpdate();
            if(borrado > 0){                
                System.out.println("\n\tSe han eliminado " + borrado + " registros de la tabla " + nombre_tabla);
                limpiado = true;
            }else{
                System.out.println("\n\tNo se eliminó ningun registro de la tabla " + nombre_tabla + ", la tabla está vacia.\n");
            }
        } catch (SQLException ex) {
            System.err.println(
                        "Error al tratar de limpiar la tabla " + nombre_tabla + ".\n" +
                        "Clase: " + getNombreClase() + "\n" +
                        "Método: " + getNombreMetodo() + "\n" +
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
            limpiado = false;
        }
        return limpiado;
    }
    
    public static boolean eliminarCentro(Connection conn, int codigo){
        String sentenciaSQL = "DELETE"
                            + " FROM tbl_centro"
                            + " WHERE codigo = ?";
        boolean eliminado = false;
        try {
            PreparedStatement instruccion = conn.prepareStatement(sentenciaSQL);
            instruccion.setInt(1, codigo);
            if(instruccion.executeUpdate() == 1){
                eliminado = true;
            }
        } catch (SQLException ex) {
            System.err.println(
                        "Error al tratar de eliminar el centro codigo " + codigo + ".\n" +
                        "Clase: " + getNombreClase() + "\n" +
                        "Método: " + getNombreMetodo() + "\n" +
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
            eliminado = false;
        }
        return eliminado;
    }
    
    //Eliminar todos los datos de la tabla
    public static boolean eliminarCentros(Connection conn){        
        String sentenciaSQL = "DELETE"
                            + " FROM tbl_centro";
        boolean eliminado = false;
        try {
            PreparedStatement instruccion = conn.prepareStatement(sentenciaSQL);
            if(instruccion.executeUpdate() > 0){
                eliminado = true;
            }
        } catch (SQLException ex) {
            System.err.println(
                        "Error al tratar de eliminar todos los centros de la tabla tbl_centro.\n" +
                        "Clase: " + getNombreClase() + "\n" +
                        "Método: " + getNombreMetodo() + "\n" +
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
            eliminado = false;
        }
        return eliminado;
    }
    
    
    //Eliminar todos los datos de la tabla
    public static boolean eliminarResultadoFinales(Connection conn){        
        String sentenciaSQL = "DELETE"
                            + " FROM tbl_resultado_final";
        boolean eliminado = false;
        try {
            PreparedStatement instruccion = conn.prepareStatement(sentenciaSQL);
            if(instruccion.executeUpdate() > 0){
                eliminado = true;
            }
        } catch (SQLException ex) {
            System.err.println(
                        "Error al tratar de eliminar todos los resultados finales de la tabla tbl_resultado_final.\n" +
                        "Clase: " + getNombreClase() + "\n" +
                        "Método: " + getNombreMetodo() + "\n" +
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
            eliminado = false;
        }
        return eliminado;
    }
    
    //Eliminar todos los datos de la tabla
    public static boolean eliminarTasas(Connection conn){        
        String sentenciaSQL = "DELETE"
                            + " FROM tbl_reprobados";
        boolean eliminado = false;
        try {
            PreparedStatement instruccion = conn.prepareStatement(sentenciaSQL);
            if(instruccion.executeUpdate() > 0){
                eliminado = true;
            }
        } catch (SQLException ex) {
            System.err.println(
                        "Error al tratar de eliminar todos las Tasas de Reprobados de la tabla tbl_reprobados.\n" +
                        "Clase: " + getNombreClase() + "\n" +
                        "Método: " + getNombreMetodo() + "\n" +
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
            eliminado = false;
        }
        return eliminado;
    }
    
    //Eliminar todos los datos de la tabla
    public static boolean eliminarResultadoFinalCentro(Connection conn, int codigo_centro){
        String sentenciaSQL = "DELETE"
                            + " FROM tbl_resultado_final"
                            + " WHERE codigo_centro = ?";
        boolean eliminado = false;
        try {
            PreparedStatement instruccion = conn.prepareStatement(sentenciaSQL);
            instruccion.setInt(1, codigo_centro);
            if(instruccion.executeUpdate() >= 1){
                eliminado = true;
            }
        } catch (SQLException ex) {
            System.err.println(
                        "Error al tratar de eliminar el resultado final para el centro codigo " + codigo_centro + ".\n" +
                        "Clase: " + getNombreClase() + "\n" +
                        "Método: " + getNombreMetodo() + "\n" +
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
            eliminado = false;
        }
        return eliminado;
    }
    
    //Eliminar todos los datos de la tabla
    public static boolean eliminarTasasCentro(Connection conn, int codigo_centro){
        String sentenciaSQL = "DELETE"
                            + " FROM tbl_reprobados"
                            + " WHERE codigo_centro = ?";
        boolean eliminado = false;
        try {
            PreparedStatement instruccion = conn.prepareStatement(sentenciaSQL);
            instruccion.setInt(1, codigo_centro);
            if(instruccion.executeUpdate() >= 1){
                eliminado = true;
            }
        } catch (SQLException ex) {
            System.err.println(
                        "Error al tratar de eliminar la Tasa de Reprobados para el centro codigo " + codigo_centro + ".\n" +
                        "Clase: " + getNombreClase() + "\n" +
                        "Método: " + getNombreMetodo() + "\n" +
                        "Sentencia SQL: \"" + sentenciaSQL + "\"\n" +
                        "Mensaje de error(SQL): " + ex.getMessage() + "\n"
            );
            eliminado = false;
        }
        return eliminado;
    }
    
}
