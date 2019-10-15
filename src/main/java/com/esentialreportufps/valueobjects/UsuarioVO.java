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
public class UsuarioVO {
    
    private String codigo; 
    private String clave;
    private String nombre;
    private String programaAcademico;
    private int tipoUsuario;

    
    public UsuarioVO () {
        
    }

    public UsuarioVO(String codigo, String clave, String nombre, String programaAcademico, int tipoUsuario) {
        this.codigo = codigo;
        this.clave = clave;
        this.nombre = nombre;
        this.programaAcademico = programaAcademico;
        this.tipoUsuario = tipoUsuario;
    }

    public String getcodigo() {
        return codigo;
    }

    public void setcodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProgramaAcademico() {
        return programaAcademico;
    }

    public void setProgramaAcademico(String programaAcademico) {
        this.programaAcademico = programaAcademico;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    
 
       

    
}
