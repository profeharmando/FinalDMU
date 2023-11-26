/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teupa.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hector
 */
public class ImportarCentro {
    
    private StringProperty centro;
    private StringProperty codigo;
    private StringProperty administracion;
    private StringProperty zona;
    private StringProperty nivel;
    private StringProperty ciclo;
    private StringProperty jornada;
    private StringProperty cobertura;
    private IntegerProperty matricula_final;
    
    public ImportarCentro(String centro, String codigo, String administracion, String zona, String nivel, String ciclo, String jornada, String cobertura, int matricula_final) {
        this.centro = new SimpleStringProperty(centro);
        this.codigo = new SimpleStringProperty(codigo);
        this.administracion = new SimpleStringProperty(administracion);
        this.zona = new SimpleStringProperty(zona);
        this.nivel = new SimpleStringProperty(nivel);
        this.ciclo = new SimpleStringProperty(ciclo);
        this.jornada = new SimpleStringProperty(jornada);
        this.cobertura = new SimpleStringProperty(cobertura);
        this.matricula_final = new SimpleIntegerProperty(matricula_final);
    }
    
    //Metodos atributo: centro
    public String getCentro() {
        return centro.get();
    }
    public void setCentro(String centro) {
        this.centro = new SimpleStringProperty(centro);
    }
    public StringProperty CentroProperty() {
        return centro;
    }
    
    //Metodos atributo: codigo
    public String getCodigo() {
        return codigo.get();
    }
    public void setCodigo(String codigo) {
        this.codigo = new SimpleStringProperty(codigo);
    }
    public StringProperty CodigoProperty() {
        return codigo;
    }
    
    //Metodos atributo: administracion
    public String getAdministracion() {
        return administracion.get();
    }
    public void setAdministracion(String administracion) {
        this.administracion = new SimpleStringProperty(administracion);
    }
    public StringProperty AdministracionProperty() {
        return administracion;
    }
    
    //Metodos atributo: zona
    public String getZona() {
        return zona.get();
    }
    public void setZona(String zona) {
        this.zona = new SimpleStringProperty(zona);
    }
    public StringProperty ZonaProperty() {
        return zona;
    }
    
    //Metodos atributo: nivel
    public String getNivel() {
        return nivel.get();
    }
    public void setNivel(String nivel) {
        this.nivel = new SimpleStringProperty(nivel);
    }
    public StringProperty NivelProperty() {
        return nivel;
    }
    
    //Metodos atributo: ciclo
    public String getCiclo() {
        return ciclo.get();
    }
    public void setCiclo(String ciclo) {
        this.ciclo = new SimpleStringProperty(ciclo);
    }
    public StringProperty CicloProperty() {
        return ciclo;
    }
    
    //Metodos atributo: jornada
    public String getJornada() {
        return jornada.get();
    }
    public void setJonada(String jornada) {
        this.jornada = new SimpleStringProperty(jornada);
    }
    public StringProperty JornadaProperty() {
        return jornada;
    }
    
    //Metodos atributo: cobertura
    public String getCobertura() {
        return cobertura.get();
    }
    public void setCobertura(String cobertura) {
        this.cobertura = new SimpleStringProperty(cobertura);
    }
    public StringProperty CoberturaProperty() {
        return cobertura;
    }
    
    //Metodos atributo: matricula_final
    public int getMatriculaFinal() {
        return matricula_final.get();
    }
    public void setMatriculaFinal(int matricula_final) {
        this.matricula_final = new SimpleIntegerProperty(matricula_final);
    }
    public IntegerProperty MatriculaFinalProperty() {
        return matricula_final;
    }
    
    
    //para mostrar en los controles nivel ComboBox
    @Override
    public String toString(){
        return centro.get();
    }
    
}
