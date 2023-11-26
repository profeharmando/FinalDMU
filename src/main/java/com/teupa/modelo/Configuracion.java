/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teupa.modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author hector
 */
public class Configuracion {
    
    private static String servidor;
    private static String puerto;
    private static String nombreBD;
    private static String usuario;
    private static String contraseña;
    
    public void obtenerConfiguracion(){
        Properties propiedades = new Properties();
        InputStream entrada = null;
        try {
            entrada = new FileInputStream(System.getProperty("user.dir") + System.getProperty("file.separator") + "configuracion.properties");
            propiedades.load(entrada);
            servidor = propiedades.getProperty("servidor");
            puerto = propiedades.getProperty("puerto");
            nombreBD = propiedades.getProperty("nombreBD");
            usuario = propiedades.getProperty("usuario");
            contraseña = propiedades.getProperty("password");
        } catch (FileNotFoundException ex) {
            System.err.println("\nNo se encontró el archivo de configuración.\n"
                    + "Error: " + ex.getMessage());
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public String getServidor() {
        return servidor;
    }

    public String getPuerto() {
        return puerto;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }
    
}
