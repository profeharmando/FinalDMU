/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teupa.modelo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hector
 */
public class ConsolidadoReprobadosEspMat {
    private StringProperty grado;
    private IntegerProperty mat_ini_n;
    private IntegerProperty mat_ini_v;
    private IntegerProperty mat_ini_t;
    private IntegerProperty mat_fin_n;
    private IntegerProperty mat_fin_v;
    private IntegerProperty mat_fin_t;
    private IntegerProperty eval_esp_n;
    private IntegerProperty eval_esp_v;
    private IntegerProperty eval_esp_t;
    private IntegerProperty repro_esp_n;
    private IntegerProperty repro_esp_v;
    private IntegerProperty repro_esp_t;
    private DoubleProperty tasa_esp_n;
    private DoubleProperty tasa_esp_v;
    private DoubleProperty tasa_esp_t;
    private IntegerProperty eval_mat_n;
    private IntegerProperty eval_mat_v;
    private IntegerProperty eval_mat_t;
    private IntegerProperty repro_mat_n;
    private IntegerProperty repro_mat_v;
    private IntegerProperty repro_mat_t;
    private DoubleProperty tasa_mat_n;
    private DoubleProperty tasa_mat_v;
    private DoubleProperty tasa_mat_t;
    
    public ConsolidadoReprobadosEspMat(String grado, 
            int mat_ini_n, int mat_ini_v, int mat_ini_t,
            int mat_fin_n, int mat_fin_v, int mat_fin_t,
            int eval_esp_n, int eval_esp_v, int eval_esp_t,
            int repro_esp_n, int repro_esp_v, int repro_esp_t,
            double tasa_esp_n, double tasa_esp_v, double tasa_esp_t,
            int eval_mat_n, int eval_mat_v, int eval_mat_t,
            int repro_mat_n, int repro_mat_v, int repro_mat_t,
            double tasa_mat_n, double tasa_mat_v, double tasa_mat_t) {
        this.grado = new SimpleStringProperty(grado);
        this.mat_ini_n = new SimpleIntegerProperty(mat_ini_n);
        this.mat_ini_v = new SimpleIntegerProperty(mat_ini_v);
        this.mat_ini_t = new SimpleIntegerProperty(mat_ini_t);
        this.mat_fin_n = new SimpleIntegerProperty(mat_fin_n);
        this.mat_fin_v = new SimpleIntegerProperty(mat_fin_v);
        this.mat_fin_t = new SimpleIntegerProperty(mat_fin_t);
        this.eval_esp_n = new SimpleIntegerProperty(eval_esp_n);
        this.eval_esp_v = new SimpleIntegerProperty(eval_esp_v);
        this.eval_esp_t = new SimpleIntegerProperty(eval_esp_t);
        this.repro_esp_n = new SimpleIntegerProperty(repro_esp_n);
        this.repro_esp_v = new SimpleIntegerProperty(repro_esp_v);
        this.repro_esp_t = new SimpleIntegerProperty(repro_esp_t);
        this.tasa_esp_n = new SimpleDoubleProperty(tasa_esp_n);
        this.tasa_esp_v = new SimpleDoubleProperty(tasa_esp_v);
        this.tasa_esp_t = new SimpleDoubleProperty(tasa_esp_t);
        this.eval_mat_n = new SimpleIntegerProperty(eval_mat_n);
        this.eval_mat_v = new SimpleIntegerProperty(eval_mat_v);
        this.eval_mat_t = new SimpleIntegerProperty(eval_mat_t);
        this.repro_mat_n = new SimpleIntegerProperty(repro_mat_n);
        this.repro_mat_v = new SimpleIntegerProperty(repro_mat_v);
        this.repro_mat_t = new SimpleIntegerProperty(repro_mat_t);
        this.tasa_mat_n = new SimpleDoubleProperty(tasa_mat_n);
        this.tasa_mat_v = new SimpleDoubleProperty(tasa_mat_v);
        this.tasa_mat_t = new SimpleDoubleProperty(tasa_mat_t);
    }
    
    public ConsolidadoReprobadosEspMat(int codigo, String grado, 
            int mat_ini_n, int mat_ini_v, int mat_ini_t,
            int mat_fin_n, int mat_fin_v, int mat_fin_t,            
            int codigo_centro) {
        this.grado = new SimpleStringProperty(grado);
        this.mat_ini_n = new SimpleIntegerProperty(mat_ini_n);
        this.mat_ini_v = new SimpleIntegerProperty(mat_ini_v);
        this.mat_ini_t = new SimpleIntegerProperty(mat_ini_t);
        this.mat_fin_n = new SimpleIntegerProperty(mat_fin_n);
        this.mat_fin_v = new SimpleIntegerProperty(mat_fin_v);
        this.mat_fin_t = new SimpleIntegerProperty(mat_fin_t);
    }
    
    public ConsolidadoReprobadosEspMat(int codigo, String grado) {
        this.grado = new SimpleStringProperty(grado);
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
    
    //Metodos atributo: mat_ini_n
    public int getMatIni_n() {
        return mat_ini_n.get();
    }
    public void setMatIni_n(int mat_ini_n) {
        this.mat_ini_n = new SimpleIntegerProperty(mat_ini_n);
    }
    public IntegerProperty MatIni_nProperty() {
        return mat_ini_n;
    }
    //Metodos atributo: mat_ini_v
    public int getMatIni_v() {
        return mat_ini_v.get();
    }
    public void setMatIni_v(int mat_ini_v) {
        this.mat_ini_v = new SimpleIntegerProperty(mat_ini_v);
    }
    public IntegerProperty MatIni_vProperty() {
        return mat_ini_v;
    }
    //Metodos atributo: mat_ini_t
    public int getMatIni_t() {
        return mat_ini_t.get();
    }
    public void setMatIni_t(int mat_ini_t) {
        this.mat_ini_t = new SimpleIntegerProperty(mat_ini_t);
    }
    public IntegerProperty MatIni_tProperty() {
        return mat_ini_t;
    }
    
    //Metodos atributo: mat_fin_n
    public int getMatFin_n() {
        return mat_fin_n.get();
    }
    public void setMatFin_n(int mat_fin_n) {
        this.mat_fin_n = new SimpleIntegerProperty(mat_fin_n);
    }
    public IntegerProperty MatFin_nProperty() {
        return mat_fin_n;
    }
    //Metodos atributo: mat_fin_v
    public int getMatFin_v() {
        return mat_fin_v.get();
    }
    public void setMatFin_v(int mat_fin_v) {
        this.mat_fin_v = new SimpleIntegerProperty(mat_fin_v);
    }
    public IntegerProperty MatFin_vProperty() {
        return mat_fin_v;
    }
    //Metodos atributo: mat_fin_t
    public int getMatFin_t() {
        return mat_fin_t.get();
    }
    public void setMatFin_t(int mat_fin_t) {
        this.mat_fin_t = new SimpleIntegerProperty(mat_fin_t);
    }
    public IntegerProperty MatFin_tProperty() {
        return mat_fin_t;
    }
    
    //Metodos atributo: eval_esp_n
    public int getEvalEsp_n() {
        return eval_esp_n.get();
    }
    public void setEvalEsp_n(int eval_esp_n) {
        this.eval_esp_n = new SimpleIntegerProperty(eval_esp_n);
    }
    public IntegerProperty EvalEsp_nProperty() {
        return eval_esp_n;
    }
    //Metodos atributo: eval_esp_v
    public int getEvalEsp_v() {
        return eval_esp_v.get();
    }
    public void setEvalEsp_v(int eval_esp_v) {
        this.eval_esp_v = new SimpleIntegerProperty(eval_esp_v);
    }
    public IntegerProperty EvalEsp_vProperty() {
        return eval_esp_v;
    }
    //Metodos atributo: eval_esp_t
    public int getEvalEsp_t() {
        return eval_esp_t.get();
    }
    public void setEvalEsp_t(int eval_esp_t) {
        this.eval_esp_t = new SimpleIntegerProperty(eval_esp_t);
    }
    public IntegerProperty EvalEsp_tProperty() {
        return eval_esp_t;
    }
    
    //Metodos atributo: repro_esp_n
    public int getReproEsp_n() {
        return repro_esp_n.get();
    }
    public void setReproEsp_n(int repro_esp_n) {
        this.repro_esp_n = new SimpleIntegerProperty(repro_esp_n);
    }
    public IntegerProperty ReproEsp_nProperty() {
        return repro_esp_n;
    }
    //Metodos atributo: repro_esp_v
    public int getReproEsp_v() {
        return repro_esp_v.get();
    }
    public void setReproEsp_v(int repro_esp_v) {
        this.repro_esp_v = new SimpleIntegerProperty(repro_esp_v);
    }
    public IntegerProperty ReproEsp_vProperty() {
        return repro_esp_v;
    }
    //Metodos atributo: repro_esp_t
    public int getReproEsp_t() {
        return repro_esp_t.get();
    }
    public void setReproEsp_t(int repro_esp_t) {
        this.repro_esp_t = new SimpleIntegerProperty(repro_esp_t);
    }
    public IntegerProperty ReproEsp_tProperty() {
        return repro_esp_t;
    }
    
    //Metodos atributo: tasa_esp_n
    public double getTasaEsp_n() {
        return tasa_esp_n.get();
    }
    public void setTasaEsp_n(double tasa_esp_n) {
        this.tasa_esp_n = new SimpleDoubleProperty(tasa_esp_n);
    }
    public DoubleProperty TasaEsp_nProperty() {
        return tasa_esp_n;
    }
    //Metodos atributo: tasa_esp_v
    public double getTasaEsp_v() {
        return tasa_esp_v.get();
    }
    public void setTasaEsp_v(double tasa_esp_v) {
        this.tasa_esp_v = new SimpleDoubleProperty(tasa_esp_v);
    }
    public DoubleProperty TasaEsp_vProperty() {
        return tasa_esp_v;
    }
    //Metodos atributo: tasa_esp_t
    public double getTasaEsp_t() {
        return tasa_esp_t.get();
    }
    public void setTasaEsp_t(double tasa_esp_t) {
        this.tasa_esp_t = new SimpleDoubleProperty(tasa_esp_t);
    }
    public DoubleProperty TasaEsp_tProperty() {
        return tasa_esp_t;
    }
    
    //Metodos atributo: eval_mat_n
    public int getEvalMat_n() {
        return eval_mat_n.get();
    }
    public void setEvalMat_n(int eval_mat_n) {
        this.eval_mat_n = new SimpleIntegerProperty(eval_mat_n);
    }
    public IntegerProperty EvalMat_nProperty() {
        return eval_mat_n;
    }
    //Metodos atributo: eval_mat_v
    public int getEvalMat_v() {
        return eval_mat_v.get();
    }
    public void setEvalMat_v(int eval_mat_v) {
        this.eval_mat_v = new SimpleIntegerProperty(eval_mat_v);
    }
    public IntegerProperty EvalMat_vProperty() {
        return eval_mat_v;
    }
    //Metodos atributo: eval_mat_t
    public int getEvalMat_t() {
        return eval_mat_t.get();
    }
    public void setEvalMat_t(int eval_mat_t) {
        this.eval_mat_t = new SimpleIntegerProperty(eval_mat_t);
    }
    public IntegerProperty EvalMat_tProperty() {
        return eval_mat_t;
    }
    
    //Metodos atributo: repro_mat_n
    public int getReproMat_n() {
        return repro_mat_n.get();
    }
    public void setReproMat_n(int repro_mat_n) {
        this.repro_mat_n = new SimpleIntegerProperty(repro_mat_n);
    }
    public IntegerProperty ReproMat_nProperty() {
        return repro_mat_n;
    }
    //Metodos atributo: repro_mat_v
    public int getReproMat_v() {
        return repro_mat_v.get();
    }
    public void setReproMat_v(int repro_mat_v) {
        this.repro_mat_v = new SimpleIntegerProperty(repro_mat_v);
    }
    public IntegerProperty ReproMat_vProperty() {
        return repro_mat_v;
    }
    //Metodos atributo: repro_mat_t
    public int getReproMat_t() {
        return repro_mat_t.get();
    }
    public void setReproMat_t(int repro_mat_t) {
        this.repro_mat_t = new SimpleIntegerProperty(repro_mat_t);
    }
    public IntegerProperty ReproMat_tProperty() {
        return repro_mat_t;
    }
    
        //Metodos atributo: tasa_mat_n
    public double getTasaMat_n() {
        return tasa_mat_n.get();
    }
    public void setTasaMat_n(double tasa_mat_n) {
        this.tasa_mat_n = new SimpleDoubleProperty(tasa_mat_n);
    }
    public DoubleProperty TasaMat_nProperty() {
        return tasa_mat_n;
    }
    //Metodos atributo: tasa_mat_v
    public double getTasaMat_v() {
        return tasa_mat_v.get();
    }
    public void setTasaMat_v(double tasa_mat_v) {
        this.tasa_mat_v = new SimpleDoubleProperty(tasa_mat_v);
    }
    public DoubleProperty TasaMat_vProperty() {
        return tasa_mat_v;
    }
    //Metodos atributo: tasa_mat_t
    public double getTasaMat_t() {
        return tasa_mat_t.get();
    }
    public void setTasaMat_t(double tasa_mat_t) {
        this.tasa_mat_t = new SimpleDoubleProperty(tasa_mat_t);
    }
    public DoubleProperty TasaMat_tProperty() {
        return tasa_mat_t;
    }
    
    //para mostrar en los controles tipo ComboBox
    @Override
    public String toString(){
        return grado.get();
    }
}
