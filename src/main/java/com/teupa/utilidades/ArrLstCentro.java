/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teupa.utilidades;

/**
 *
 * @author hector
 */
public class ArrLstCentro {
    private String centro;
    private String codigo;
    private String administracion;
    private String zona;
    private String nivel;
    private String ciclo;
    private String jornada;
    private String cobertura;
    private int matricula_final;
    
    public ArrLstCentro(String centro, String codigo, String administracion, String zona, String nivel, String ciclo, String jornada, String cobertura, int matricula_final) {
        this.centro = centro;
        this.codigo = codigo;
        this.administracion = administracion;
        this.zona = zona;
        this.nivel = nivel;
        this.ciclo = ciclo;
        this.jornada = jornada;
        this.cobertura = cobertura;
        this.matricula_final = matricula_final;
    }
    
    public String getCentro() {
        return this.centro;
    }
    public void setCentro(String centro) {
            this.centro = centro;
    }
    
    public String getCodigo() {
        return this.codigo;
    }
    public void setCodigo(String codigo) {
            this.codigo = codigo;
    }
    
    public String getAdministracion() {
        return this.administracion;
    }
    public void setAdministracion(String administracion) {
            this.administracion = administracion;
    }
    
    public String getZona() {
        return this.zona;
    }
    public void setZona(String zona) {
            this.zona = zona;
    }
    
    public String getNivel() {
        return this.nivel;
    }
    public void setNivel(String nivel) {
            this.nivel = nivel;
    }
    
    public String getCiclo() {
        return this.ciclo;
    }
    public void setCiclo(String ciclo) {
            this.ciclo = ciclo;
    }
    
    public String getJornada() {
        return this.jornada;
    }
    public void setJornada(String jornada) {
            this.jornada = jornada;
    }
    
    public String getCobertura() {
        return this.cobertura;
    }
    public void setCobertura(String cobertura) {
            this.cobertura = cobertura;
    }
    
    public int getMatriculaFinal(){
        return this.matricula_final;
    }
    public void setMatriculaFinal(int matricula_final){
        this.matricula_final = matricula_final;
    }
       
    
    public String mostrarDatos(){
        return "El centro es " + getCentro();
    }
}
