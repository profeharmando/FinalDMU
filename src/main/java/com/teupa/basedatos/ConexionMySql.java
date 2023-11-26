/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teupa.basedatos;

import com.teupa.modelo.Configuracion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hector
 */
public class ConexionMySql {
    
    private Connection conexion = null;    
   
    private String usuario;
    private String password;
    private String servidor;
    private String puerto;
    private String nombreBD; 
    
    private boolean hay_conexion = false;
    
    Configuracion cnf = new Configuracion();
    
    public Connection establecerConexion() {
        cnf.obtenerConfiguracion();
        usuario = cnf.getUsuario();
        password = cnf.getContraseña();
        servidor = cnf.getServidor();
        puerto = cnf.getPuerto();
        nombreBD = cnf.getNombreBD();    
    
        String url = "jdbc:mariadb://" + servidor + ":" + puerto + "/" + nombreBD + "?serverTimezone=UTC";
        String driver = "org.mariadb.jdbc.Driver";
        try{
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, usuario, password);
            if(conexion != null){
                System.out.println("\nConexión a BD realizada correctamente");
                hay_conexion = true;
            }
        }catch(SQLException ex) {
            hay_conexion = false;
            System.err.println("\nError al tratar de conectar a la Base de Datos.\nError: " + ex.getMessage());
            System.err.println("\nDeatalle de error:");
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            hay_conexion = false;
            System.err.println("\nError al usar el Drivel \"org.mariadb.jdbc.Driver\" la BD.\nError: " + ex.getMessage());
            System.err.println("\nDeatalle de error:");
            ex.printStackTrace();
        } catch(Exception ex){
            hay_conexion = false;
            System.err.println("\nError en las credenciales de conexión al servidor de Base de Datos.\nError: " + ex.getMessage());
        }
        return conexion;
    }
    
    public boolean hayConexion(){
        if(hay_conexion){
            return true;
        }
        return false;
    }

}
