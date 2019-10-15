/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esentialreportufps.fachada;

import com.esentialreportufps.bd.ConnectionBD;
import com.esentialreportufps.mediador.MediadorEsentialReport;
import com.esentialreportufps.valueobjects.ColumnaVO;
import com.esentialreportufps.valueobjects.UsuarioVO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victor
 */
public class FachadaEsentialReport {

    private static MediadorEsentialReport mediadorEsentialReport;

    public static MediadorEsentialReport getFachada() throws SQLException {
        if (mediadorEsentialReport == null) {
            mediadorEsentialReport = new MediadorEsentialReport();
        }
        return mediadorEsentialReport;
    }

    public static ArrayList<String> validarUsuario(String codigo, String clave) throws SQLException {
        getFachada();
        return mediadorEsentialReport.validarUsuario(codigo, clave);
    }

    public static boolean validarToken(String token) throws SQLException {
        getFachada();
        return mediadorEsentialReport.validarToken(token);
    }

    public static ArrayList<UsuarioVO> listarUsuarios() {
        return mediadorEsentialReport.listarUsuarios();
    }

    public static boolean registraUsuario(JsonObject jsonRegistro) throws SQLException {
        return mediadorEsentialReport.registrarUsuario(jsonRegistro);
    }

    public static boolean eliminarUsuario(String codigoUsuario) throws SQLException {
        return mediadorEsentialReport.eliminarUsuario(codigoUsuario);
    }
    
    public static boolean validarTabla(List<ArrayList<ColumnaVO>> columnasTabla, String codigoCreacion, String nombreArchivo) throws SQLException {
         getFachada();
        return mediadorEsentialReport.validarTabla(columnasTabla, codigoCreacion, nombreArchivo);
    }
    
    public static JsonArray extraerInformacionFile(String ruta) throws SQLException, IOException {
         getFachada();
        return mediadorEsentialReport.extraerInformacionFile(ruta);
    }
    
    public static boolean registrarInformacionFile(JsonArray jsonArray, String tabla) throws SQLException{
         getFachada();
        return mediadorEsentialReport.registrarDatosFile(jsonArray, tabla);
    }
    
    
     
}
