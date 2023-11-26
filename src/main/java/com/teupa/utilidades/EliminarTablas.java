/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teupa.utilidades;

import com.teupa.basedatos.ConexionMySql;
import com.teupa.basedatos.Eliminar;
import java.sql.Connection;
/**
 *
 * @author hector
 */
public class EliminarTablas {
    public static void main(String[] args) {
        //Conexion a la BD
        ConexionMySql conn = new ConexionMySql();
        Connection conex = conn.establecerConexion();
        //Se eliminan las tablas a continuacion
        Eliminar.eliminarTabla(conex, "tbl_resultado_final");
        Eliminar.eliminarTabla(conex, "tbl_reprobados");
        Eliminar.eliminarTabla(conex, "tbl_centro");
    }
}
