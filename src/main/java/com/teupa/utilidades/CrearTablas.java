/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teupa.utilidades;

import com.teupa.basedatos.ConexionMySql;
import com.teupa.basedatos.Crear;
import java.sql.Connection;

/**
 *
 * @author hector
 */
public class CrearTablas {
    public static void main(String[] args) {
        Crear crear = new Crear();        
        //Conexion a la BD
        ConexionMySql conn = new ConexionMySql();
        Connection conexion = conn.establecerConexion();
        //Crea las tablas a continuacion        
        crear.tbl_centro(conexion);
        crear.tbl_resultado_final(conexion);
        crear.tbl_reprobados(conexion);
    }
}
