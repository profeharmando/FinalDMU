/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teupa.utilidades;

import com.teupa.basedatos.ConexionSQLite;
import com.teupa.basedatos.Insertar;
import java.sql.Connection;

/**
 *
 * @author hector
 */
public class InsertarDatos {
    static Insertar insertar = new Insertar();
    //Conexion a la BD
    static ConexionSQLite conn = new ConexionSQLite();
    static Connection conex = conn.establecerConexion();
    
    public static void main(String[] args) {        
        //Guarda datos iniciales en cero en la tabla tbl_resultado_final_ceros
        System.out.println("\nInsertando datos en la tabla especificada");
        insertar.guardarResultadoFinalDatosEnCero(conex, "tbl_resultado_final_ceros", "Primero");
        insertar.guardarResultadoFinalDatosEnCero(conex, "tbl_resultado_final_ceros", "Segundo");
        insertar.guardarResultadoFinalDatosEnCero(conex, "tbl_resultado_final_ceros", "Tercero");
        insertar.guardarResultadoFinalDatosEnCero(conex, "tbl_resultado_final_ceros", "Cuarto");
        insertar.guardarResultadoFinalDatosEnCero(conex, "tbl_resultado_final_ceros", "Quinto");
        insertar.guardarResultadoFinalDatosEnCero(conex, "tbl_resultado_final_ceros", "Sexto");
        insertar.guardarResultadoFinalDatosEnCero(conex, "tbl_resultado_final_ceros", "Septimo");
        insertar.guardarResultadoFinalDatosEnCero(conex, "tbl_resultado_final_ceros", "Octavo");
        insertar.guardarResultadoFinalDatosEnCero(conex, "tbl_resultado_final_ceros", "Noveno");
    }
}
