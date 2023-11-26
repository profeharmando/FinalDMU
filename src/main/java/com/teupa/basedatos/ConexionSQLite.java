/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teupa.basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author hector
 */
public class ConexionSQLite {
    
    private static Connection conn = null;
    
    public Connection crearBaseDatos(){
        try {
            System.setProperty("sqlite.system.home", System.getProperty("user.dir") + System.getProperty("file.separator") + "basedatos");
            Class.forName("org.sqlite.JDBC");
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:" + "basedatos" + System.getProperty("file.separator") + "FinalDMU");
                if(conn != null){
                    System.out.println("\nLa base de datos FinalDMU ha sido creada en " + System.getProperty("user.dir") + System.getProperty("file.separator") + "basedatos");
                }
            } catch (SQLException ex) {
                System.err.println("\t\t...Error: al crear la BD.\n" + ex.getMessage());
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Base de Datos no creada");
                mensaje.setHeaderText("No se pudo crear la Base de Datos en el sistema.\n"
                        + "Intente ejecutar la aplicación como Administrador."
                        + "Error: " + ex.getMessage());
                mensaje.setContentText("");
                mensaje.showAndWait();
            }
        } catch (ClassNotFoundException ex) {
            System.err.println("\t\t...Error: al cargar el Driver conector de la BD.\n" + ex.getMessage());
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Error con el driver conector de la Base de Datos");
            mensaje.setHeaderText("No se pudo cargar el driver conectos de la Base de Datos.\n" + ex.getMessage());
            mensaje.setContentText("");
            mensaje.showAndWait();
        }
        return conn;
    }
    
    public boolean hayConexion(){
        try {
            System.setProperty("sqlite.system.home", System.getProperty("user.dir") + System.getProperty("file.separator") + "basedatos");
            Class.forName("org.sqlite.JDBC");
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:" + "basedatos" + System.getProperty("file.separator") + "FinalDMU");
                if(conn != null){
                    System.out.println("\t\t...Aviso: hay conexion a la BD");
                    return true;                    
                }
            } catch (SQLException ex) {
                System.err.println("No hay conexion con la BD.\n" + ex.getMessage());
            }
        } catch (ClassNotFoundException ex) {
            System.err.println("\t\t...Error: al cargar el Driver conector de la BD.\n" + ex.getMessage());
        }
        return false;
    }
    
    public static Connection establecerConexion(){
        String pathDB = System.getProperty("user.dir") + System.getProperty("file.separator") + "basedatos" + System.getProperty("file.separator") + "FinalDMU";
        System.out.println("\n...conectando a BD: " + pathDB);
        String url = "jdbc:sqlite:" + pathDB;
        if(conn == null){            
            try {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection(url);
                if(!conn.isClosed()){
                    System.out.println("\n\n\tAVISO: Conexion con BD establecida correctamente\n\n");
                }
            } catch (ClassNotFoundException ex) {
                System.out.println("Error, la clase org.sqlite.JDBC no funciona.\n"
                        + "Error: " + ex.getMessage());
            } catch (SQLException ex){
                System.out.println("Error, al intentar conectar la base de datos"
                        + "Error: " + ex.getMessage());
            } 
        }        
        return conn;
    }    
    
    public void cerrarConexion(){
        if(conn != null){
            try {
                conn.close();
                System.out.println("\n\t\t...Aviso: la conexion con la Base de Datos ha sido cerrada.");
            } catch (SQLException ex) {
                System.err.println("\n\t\t...Error: no se pudo cerrar la conexion con la Base de Datos.\n" + ex.getMessage());
            }                      
        }
    }
 
}
