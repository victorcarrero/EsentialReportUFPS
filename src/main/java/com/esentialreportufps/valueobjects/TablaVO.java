/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esentialreportufps.valueobjects;

import java.util.ArrayList;

/**
 *
 * @author victor
 */
public class TablaVO {

    private String nombre;
    private ArrayList<String> columnas;
    private ArrayList<String> tiposColumna;
    private String codigoCreacion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getColumnas() {
        return columnas;
    }

    public void setColumnas(ArrayList<String> columnas) {
        this.columnas = columnas;
    }

    public ArrayList<String> getTiposColumna() {
        return tiposColumna;
    }

    public void setTiposColumna(ArrayList<String> tiposColumna) {
        this.tiposColumna = tiposColumna;
    }

    public String getcodigoCreacion() {
        return codigoCreacion;
    }

    public void setcodigoCreacion(String codigoCreacion) {
        this.codigoCreacion = codigoCreacion;
    }

}
