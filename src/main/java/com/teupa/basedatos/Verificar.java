/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teupa.basedatos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author hector
 */
public class Verificar {
    
    public void verificarExisteTablaResultadoFinal(Connection conn){    
        //Verifica si existe, sino crea la tabla tbl_resultado_final_centros
        try {
            DatabaseMetaData dmd = conn.getMetaData();
            ResultSet rs = dmd.getTables(null, "FinalDMU", "tbl_resultado_final",null);
            if(rs.next()){
                System.out.println("\t\t...la tabla tbl_resultado_final existe.");
//                if(Crear.tbl_resultado_final_centros(conn)){
//                    System.out.println("\t\t...la tabla tbl_resultado_final_centros ha sido creda.");
//                }
            } else{
                System.out.println("\t\t...la tabla tbl_resultado_final NO existe.");
            }
        } catch (SQLException ex) {
            System.err.println(
                        "Error de Base de Datos tratando de verificar si existe y sino crear la tabla tbl_resultado_final_centros.\n"+
                        "Mensaje de error(SQL): " + ex.getMessage()+"\n"
            );
        }
    }
    
    public void verificarExisteTablaResultadoFinalCeros(Connection conn){    
        //Verifica si existe, sino crea la tabla tbl_resultado_final_ceros
        try {
            DatabaseMetaData dmd = conn.getMetaData();
            ResultSet rs = dmd.getTables(null, "FinalDMU", "tbl_resultado_final_ceros",null);
            if(rs.next()){
                System.out.println("\t\t...la tabla tbl_resultado_final_ceros existe");
//                if(Crear.tbl_resultado_final_ceros(conn)){
//                    System.out.println("\t\t...la tabla tbl_resultado_final_ceros ha sido creda.");
//                }
            } else{
                System.out.println("\t\t...la tabla tbl_resultado_final_ceros NO existe.");
            }
        } catch (SQLException ex) {
            System.err.println(
                        "Error de Base de Datos tratando de verificar si existe y sino crear la tbl_resultado_final_ceros.\n"+
                        "Mensaje de error(SQL): " + ex.getMessage()+"\n"
            );
        }
    }
    
    public void verificarExisteTablaCentro(Connection conn){    
        //Verifica si existe, sino crea la tabla tbl_centro
        try {
            DatabaseMetaData dmd = conn.getMetaData();
            ResultSet rs = dmd.getTables(null, "FinalDMU", "tbl_centro",null);
            if(rs.next()){
                System.out.println("\t\t...la tabla tbl_centros existe.");
//                if(Crear.tbl_centro(conn)){
//                    System.out.println("\t\t...la tabla tbl_centro ha sido creda.");
//                }
            } else{
                System.out.println("\t\t...la tabla tbl_centros NO existe.");
            }
        } catch (SQLException ex) {
            System.err.println(
                        "Error de Base de Datos tratando de verificar si existe y sino crear la tbl_centros.\n"+
                        "Mensaje de error(SQL): " + ex.getMessage()+"\n"
            );
        }
    }
    

    
    public static boolean existeTablaResultadoFinalCentros(Connection conn){    
        //Verifica si existe la tabla tbl_resultado_final_centros
        try {
            DatabaseMetaData dmd = conn.getMetaData();
            ResultSet rs = dmd.getTables(null, "FinalDMU", "tbl_resultado_final",null);
            if(rs.next()){
                System.out.println("\t\t...la tabla tbl_resultado_final existe.");
                return true;
            }
        } catch (SQLException ex) {
            System.err.println(
                        "Error de Base de Datos tratando de verificar si existe la tabla tbl_resultado_final.\n"+
                        "Mensaje de error(SQL): " + ex.getMessage()+"\n"
            );
        }
        return false;
    }
    
    public static boolean existeTablaResultadoFinalCeros(Connection conn){    
        //Verifica si existe la tabla tbl_resultado_final_ceros
        try {
            DatabaseMetaData dmd = conn.getMetaData();
            ResultSet rs = dmd.getTables(null, "FinalDMU", "tbl_resultado_final_ceros",null);
            if(rs.next()){
                System.out.println("\t\t...la tabla tbl_resultado_final_ceros existe.");
                return true;
            }
        } catch (SQLException ex) {
            System.err.println(
                        "Error de Base de Datos tratando de verificar si existe la tabla tbl_resultado_final_ceros.\n"+
                        "Mensaje de error(SQL): " + ex.getMessage()+"\n"
            );
        }
        return false;
    }
    
    public static boolean existeTablaCentro(Connection conn){    
        //Verifica si existe la tabla tbl_centro
        try {
            DatabaseMetaData dmd = conn.getMetaData();
            ResultSet rs = dmd.getTables(null, "FinalDMU", "tbl_centro",null);
            if(rs.next()){
                System.out.println("\t\t...la tabla tbl_centro existe.");
                return true;
            }
        } catch (SQLException ex) {
            System.err.println(
                        "Error de Base de Datos tratando de verificar si existe la tabla tbl_centro.\n"+
                        "Mensaje de error(SQL): " + ex.getMessage()+"\n"
            );
        }
        return false;
    }
    
    private boolean existeTabla(Connection conn, String tabla){
        try {
            DatabaseMetaData dmd = conn.getMetaData();
            ResultSet rs = dmd.getTables(null,"APP", tabla, null);
            if(rs.next()){
                System.out.println("\t\t...la tabla " + tabla + " ya existe.");
                return true;                
            } else{
                System.out.println("\t\t...la tabla " + tabla + " no existe.");
                return false;
            }
        } catch (SQLException ex) {
            System.err.println(
                        "Error de Base de Datos tratando de verificar si existe la tabla " + tabla + ".\n"+
                        "Mensaje de error(SQL): " + ex.getMessage()+"\n"
            );
            return false;
        }
    }
    
}
