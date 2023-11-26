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
public class BuscarCentro {
        
    private IntegerProperty codigo;
    private StringProperty centro;
    private StringProperty codigo_sace;
    private StringProperty administracion;
    private StringProperty area;
    private StringProperty nivel;
    private StringProperty ciclo;
    private StringProperty jornada;
    private StringProperty cobertura;
    private IntegerProperty matricula;
    
    public BuscarCentro(int codigo, String centro, String codigo_sace, String administracion, String area, String nivel, String ciclo, String jornada, String cobertura, int matricula) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.centro = new SimpleStringProperty(centro);
        this.codigo_sace = new SimpleStringProperty(codigo_sace);
        this.administracion = new SimpleStringProperty(administracion);
        this.area = new SimpleStringProperty(area);
        this.nivel = new SimpleStringProperty(nivel);
        this.ciclo = new SimpleStringProperty(ciclo);
        this.jornada = new SimpleStringProperty(jornada);
        this.cobertura = new SimpleStringProperty(cobertura);
        this.matricula = new SimpleIntegerProperty(matricula);
    }
    
    //Metodos atributo: codigo
    public int getCodigo() {
        return codigo.get();
    }
    public void setCodigo(int codigo) {
        this.codigo = new SimpleIntegerProperty(codigo);
    }
    public IntegerProperty CodigoProperty() {
        return codigo;
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
    
    //Metodos atributo: codigo_sace
    public String getCodigoSace() {
        return codigo_sace.get();
    }
    public void setCodigoSace(String codigo_sace) {
        this.codigo_sace = new SimpleStringProperty(codigo_sace);
    }
    public StringProperty CodigoSaceProperty() {
        return codigo_sace;
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
    
    //Metodos atributo: area
    public String getArea() {
        return area.get();
    }
    public void setArea(String area) {
        this.area = new SimpleStringProperty(area);
    }
    public StringProperty AreaProperty() {
        return area;
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
    public void setJornada(String jornada) {
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
    
    //Metodos atributo: matricula
    public int getMatricula() {
        return matricula.get();
    }
    public void setMatricula(int matricula) {
        this.matricula = new SimpleIntegerProperty(matricula);
    }
    public IntegerProperty MatriculaProperty() {
        return matricula;
    }
    
    
    //para mostrar en los controles nivel ComboBox
    @Override
    public String toString(){
        return centro.get();
    }
}
