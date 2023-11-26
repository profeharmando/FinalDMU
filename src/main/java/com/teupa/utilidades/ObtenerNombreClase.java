/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teupa.utilidades;

/**
 *
 * @author hector
 */
public class ObtenerNombreClase {
    
    //Retorna el nombre de la clase en la cual se genera el error.
    public static String getNombreClase(){
        return new Exception().getStackTrace()[1].getClassName();
    }
    
    //Retorna el nombre del método en el cual se genera el error.
    public static String getNombreMetodo(){
        return new Exception().getStackTrace()[1].getMethodName();
    }
    
}
