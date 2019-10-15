/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esentialreportufps.valueobjects;

/**
 *
 * @author victor
 */
public class ColumnaVO {
    
    private int idColumna;
    private String nombreColumna; 
    private int tipoColumna;

    public ColumnaVO() {
        
    }
    public ColumnaVO(int idColumna, String nombreColumna) {
        this.idColumna = idColumna;
        this.nombreColumna = nombreColumna;
    }

    public int getIdColumna() {
        return idColumna;
    }

    public void setIdColumna(int idColumna) {
        this.idColumna = idColumna;
    }

    public String getNombreColumna() {
        return nombreColumna;
    }

    public void setNombreColumna(String nombreColumna) {
        this.nombreColumna = nombreColumna;
    }

    public int getTipoColumna() {
        return tipoColumna;
    }

    public void setTipoColumna(int tipoColumna) {
        this.tipoColumna = tipoColumna;
    }
    
    
    
}
