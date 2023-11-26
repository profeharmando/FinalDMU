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
 * @author dmu2
 */
public class ConsolidadoResultadoFinal {
    
    private StringProperty grado;
    private IntegerProperty mat_inicial_n;
    private IntegerProperty mat_inicial_v;
    private IntegerProperty mat_inicial_t;
    private IntegerProperty mat_final_n;
    private IntegerProperty mat_final_v;
    private IntegerProperty mat_final_t;
    private IntegerProperty ingre_n;
    private IntegerProperty ingre_v;
    private IntegerProperty ingre_t;
    private IntegerProperty trasla_n;
    private IntegerProperty trasla_v;
    private IntegerProperty trasla_t;
    private IntegerProperty deser_n;
    private IntegerProperty deser_v;
    private IntegerProperty deser_t;
    private IntegerProperty evalu_n;
    private IntegerProperty evalu_v;
    private IntegerProperty evalu_t;
    private IntegerProperty inic_n;
    private IntegerProperty inic_v;
    private IntegerProperty inic_t;
    private IntegerProperty basi_n;
    private IntegerProperty basi_v;
    private IntegerProperty basi_t;
    private IntegerProperty avan_n;
    private IntegerProperty avan_v;
    private IntegerProperty avan_t;
    private IntegerProperty exce_n;
    private IntegerProperty exce_v;
    private IntegerProperty exce_t;
    
    public ConsolidadoResultadoFinal(String grado, 
            int mat_inicial_n, int mat_inicial_v, int mat_inicial_t, 
            int mat_final_n, int mat_final_v, int mat_final_t, 
            int ingre_n, int ingre_v, int ingre_t, 
            int trasla_n, int trasla_v, int trasla_t, 
            int deser_n, int deser_v, int deser_t, 
            int evalu_n, int evalu_v, int evalu_t, 
            int inic_n, int inic_v, int inic_t, 
            int basi_n, int basi_v, int basi_t, 
            int avan_n, int avan_v, int avan_t, 
            int exce_n, int exce_v, int exce_t) {
        this.grado = new SimpleStringProperty(grado);
        this.mat_inicial_n = new SimpleIntegerProperty(mat_inicial_n);
        this.mat_inicial_v = new SimpleIntegerProperty(mat_inicial_v);
        this.mat_inicial_t = new SimpleIntegerProperty(mat_inicial_t);
        this.mat_final_n = new SimpleIntegerProperty(mat_final_n);
        this.mat_final_v = new SimpleIntegerProperty(mat_final_v);
        this.mat_final_t = new SimpleIntegerProperty(mat_final_t);
        this.ingre_n = new SimpleIntegerProperty(ingre_n);
        this.ingre_v = new SimpleIntegerProperty(ingre_v);
        this.ingre_t = new SimpleIntegerProperty(ingre_t);
        this.trasla_n = new SimpleIntegerProperty(trasla_n);
        this.trasla_v = new SimpleIntegerProperty(trasla_v);
        this.trasla_t = new SimpleIntegerProperty(trasla_t);
        this.deser_n = new SimpleIntegerProperty(deser_n);
        this.deser_v = new SimpleIntegerProperty(deser_v);
        this.deser_t = new SimpleIntegerProperty(deser_t);
        this.evalu_n = new SimpleIntegerProperty(evalu_n);
        this.evalu_v = new SimpleIntegerProperty(evalu_v);
        this.evalu_t = new SimpleIntegerProperty(evalu_t);
        this.inic_n = new SimpleIntegerProperty(inic_n);
        this.inic_v = new SimpleIntegerProperty(inic_v);
        this.inic_t = new SimpleIntegerProperty(inic_t);
        this.basi_n = new SimpleIntegerProperty(basi_n);
        this.basi_v = new SimpleIntegerProperty(basi_v);
        this.basi_t = new SimpleIntegerProperty(basi_t);
        this.avan_n = new SimpleIntegerProperty(avan_n);
        this.avan_v = new SimpleIntegerProperty(avan_v);
        this.avan_t = new SimpleIntegerProperty(avan_t);
        this.exce_n = new SimpleIntegerProperty(exce_n);
        this.exce_v = new SimpleIntegerProperty(exce_v);
        this.exce_t = new SimpleIntegerProperty(exce_t);
    }
    
    //Metodos atributo: grado
    public String getGrado() {
        return grado.get();
    }
    public void setGrado(String grado) {
        this.grado = new SimpleStringProperty(grado);
    }
    public StringProperty GradoProperty() {
        return grado;
    }
    
    //Metodos atributo: mat_inicial_n
    public int getMatInicial_n() {
        return mat_inicial_n.get();
    }
    public void setMatInicial_n(int mat_inicial_n) {
        this.mat_inicial_n = new SimpleIntegerProperty(mat_inicial_n);
    }
    public IntegerProperty MatInicial_nProperty() {
        return mat_inicial_n;
    }
    //Metodos atributo: mat_inicial_v
    public int getMatInicial_v() {
        return mat_inicial_v.get();
    }
    public void setMatInicial_v(int mat_inicial_v) {
        this.mat_inicial_v = new SimpleIntegerProperty(mat_inicial_v);
    }
    public IntegerProperty MatInicial_vProperty() {
        return mat_inicial_v;
    }
    //Metodos atributo: mat_inicial_t
    public int getMatInicial_t() {
        return mat_inicial_t.get();
    }
    public void setMatInicial_t(int mat_inicial_t) {
        this.mat_inicial_t = new SimpleIntegerProperty(mat_inicial_t);
    }
    public IntegerProperty MatInicial_tProperty() {
        return mat_inicial_t;
    }
    
    //Metodos atributo: mat_final_n
    public int getMatFinal_n() {
        return mat_final_n.get();
    }
    public void setMatFinal_n(int mat_final_n) {
        this.mat_final_n = new SimpleIntegerProperty(mat_final_n);
    }
    public IntegerProperty MatFinal_nProperty() {
        return mat_final_n;
    }
    //Metodos atributo: mat_final_v
    public int getMatFinal_v() {
        return mat_final_v.get();
    }
    public void setMatFinal_v(int mat_final_v) {
        this.mat_final_v = new SimpleIntegerProperty(mat_final_v);
    }
    public IntegerProperty MatFinal_vProperty() {
        return mat_final_v;
    }
    //Metodos atributo: mat_final_t
    public int getMatFinal_t() {
        return mat_final_t.get();
    }
    public void setMatFinal_t(int mat_final_t) {
        this.mat_final_t = new SimpleIntegerProperty(mat_final_t);
    }
    public IntegerProperty MatFinal_tProperty() {
        return mat_final_t;
    }
    
    //Metodos atributo: ingre_n
    public int getIngre_n() {
        return ingre_n.get();
    }
    public void setIngre_n(int ingre_n) {
        this.ingre_n = new SimpleIntegerProperty(ingre_n);
    }
    public IntegerProperty Ingre_nProperty() {
        return ingre_n;
    }
    //Metodos atributo: ingre_v
    public int getIngre_v() {
        return ingre_v.get();
    }
    public void setIngre_v(int ingre_v) {
        this.ingre_v = new SimpleIntegerProperty(ingre_v);
    }
    public IntegerProperty Ingre_vProperty() {
        return ingre_v;
    }
    //Metodos atributo: ingre_t
    public int getIngre_t() {
        return ingre_t.get();
    }
    public void setIngre_t(int ingre_t) {
        this.ingre_t = new SimpleIntegerProperty(ingre_t);
    }
    public IntegerProperty Ingre_tProperty() {
        return ingre_t;
    }
    
    //Metodos atributo: trasla_n
    public int getTrasla_n() {
        return trasla_n.get();
    }
    public void setTrasla_n(int trasla_n) {
        this.trasla_n = new SimpleIntegerProperty(trasla_n);
    }
    public IntegerProperty Trasla_nProperty() {
        return trasla_n;
    }
    //Metodos atributo: trasla_v
    public int getTrasla_v() {
        return trasla_v.get();
    }
    public void setTrasla_v(int trasla_v) {
        this.trasla_v = new SimpleIntegerProperty(trasla_v);
    }
    public IntegerProperty Trasla_vProperty() {
        return trasla_v;
    }
    //Metodos atributo: trasla_t
    public int getTrasla_t() {
        return trasla_t.get();
    }
    public void setTrasla_t(int trasla_t) {
        this.trasla_t = new SimpleIntegerProperty(trasla_t);
    }
    public IntegerProperty Trasla_tProperty() {
        return trasla_t;
    }
    
    //Metodos atributo: deser_n
    public int getDeser_n() {
        return deser_n.get();
    }
    public void setDeser_n(int deser_n) {
        this.deser_n = new SimpleIntegerProperty(deser_n);
    }
    public IntegerProperty Deser_nProperty() {
        return deser_n;
    }
    //Metodos atributo: deser_v
    public int getDeser_v() {
        return deser_v.get();
    }
    public void setDeser_v(int deser_v) {
        this.deser_v = new SimpleIntegerProperty(deser_v);
    }
    public IntegerProperty Deser_vProperty() {
        return deser_v;
    }
    //Metodos atributo: deser_t
    public int getDeser_t() {
        return deser_t.get();
    }
    public void setDeser_t(int deser_t) {
        this.deser_t = new SimpleIntegerProperty(deser_t);
    }
    public IntegerProperty Deser_tProperty() {
        return deser_t;
    }
    
    //Metodos atributo: evalu_n
    public int getEvalu_n() {
        return evalu_n.get();
    }
    public void setEvalu_n(int evalu_n) {
        this.evalu_n = new SimpleIntegerProperty(evalu_n);
    }
    public IntegerProperty Evalu_nProperty() {
        return evalu_n;
    }
    //Metodos atributo: evalu_v
    public int getEvalu_v() {
        return evalu_v.get();
    }
    public void setEvalu_v(int evalu_v) {
        this.evalu_v = new SimpleIntegerProperty(evalu_v);
    }
    public IntegerProperty Evalu_vProperty() {
        return evalu_v;
    }
    //Metodos atributo: evalu_t
    public int getEvalu_t() {
        return evalu_t.get();
    }
    public void setEvalu_t(int evalu_t) {
        this.evalu_t = new SimpleIntegerProperty(evalu_t);
    }
    public IntegerProperty Evalu_tProperty() {
        return evalu_t;
    }
    
    //Metodos atributo: inic_n
    public int getInic_n() {
        return inic_n.get();
    }
    public void setInic_n(int inic_n) {
        this.inic_n = new SimpleIntegerProperty(inic_n);
    }
    public IntegerProperty Inic_nProperty() {
        return inic_n;
    }
    //Metodos atributo: inic_v
    public int getInic_v() {
        return inic_v.get();
    }
    public void setInic_v(int inic_v) {
        this.inic_v = new SimpleIntegerProperty(inic_v);
    }
    public IntegerProperty Inic_vProperty() {
        return inic_v;
    }
    //Metodos atributo: inic_t
    public int getInic_t() {
        return inic_t.get();
    }
    public void setInic_t(int inic_t) {
        this.inic_t = new SimpleIntegerProperty(inic_t);
    }
    public IntegerProperty Inic_tProperty() {
        return inic_t;
    }
    
    //Metodos atributo: basi_n
    public int getBasi_n() {
        return basi_n.get();
    }
    public void setBasi_n(int basi_n) {
        this.basi_n = new SimpleIntegerProperty(basi_n);
    }
    public IntegerProperty Basi_nProperty() {
        return basi_n;
    }
    //Metodos atributo: basi_v
    public int getBasi_v() {
        return basi_v.get();
    }
    public void setBasi_v(int basi_v) {
        this.basi_v = new SimpleIntegerProperty(basi_v);
    }
    public IntegerProperty Basi_vProperty() {
        return basi_v;
    }
    //Metodos atributo: basi_t
    public int getBasi_t() {
        return basi_t.get();
    }
    public void setBasi_t(int basi_t) {
        this.basi_t = new SimpleIntegerProperty(basi_t);
    }
    public IntegerProperty Basi_tProperty() {
        return basi_t;
    }
    
    //Metodos atributo: avan_n
    public int getAvan_n() {
        return avan_n.get();
    }
    public void setAvan_n(int avan_n) {
        this.avan_n = new SimpleIntegerProperty(avan_n);
    }
    public IntegerProperty Avan_nProperty() {
        return avan_n;
    }
    //Metodos atributo: avan_v
    public int getAvan_v() {
        return avan_v.get();
    }
    public void setAvan_v(int avan_v) {
        this.avan_v = new SimpleIntegerProperty(avan_v);
    }
    public IntegerProperty Avan_vProperty() {
        return avan_v;
    }
    //Metodos atributo: avan_t
    public int getAvan_t() {
        return avan_t.get();
    }
    public void setAvan_t(int avan_t) {
        this.avan_t = new SimpleIntegerProperty(avan_t);
    }
    public IntegerProperty Avan_tProperty() {
        return avan_t;
    }
    
    //Metodos atributo: exce_n
    public int getExce_n() {
        return exce_n.get();
    }
    public void setExce_n(int exce_n) {
        this.exce_n = new SimpleIntegerProperty(exce_n);
    }
    public IntegerProperty Exce_nProperty() {
        return exce_n;
    }
    //Metodos atributo: exce_v
    public int getExce_v() {
        return exce_v.get();
    }
    public void setExce_v(int exce_v) {
        this.exce_v = new SimpleIntegerProperty(exce_v);
    }
    public IntegerProperty Exce_vProperty() {
        return exce_v;
    }
    //Metodos atributo: exce_t
    public int getExce_t() {
        return exce_t.get();
    }
    public void setExce_t(int exce_t) {
        this.exce_t = new SimpleIntegerProperty(exce_t);
    }
    public IntegerProperty Exce_tProperty() {
        return exce_t;
    }
    
    //para mostrar en los controles tipo ComboBox
    @Override
    public String toString(){
        return grado.get();
    }
    
}
