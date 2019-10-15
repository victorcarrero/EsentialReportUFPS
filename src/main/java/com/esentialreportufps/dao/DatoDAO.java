/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esentialreportufps.dao;

import com.esentialreportufps.bd.ConnectionBD;
import com.google.gson.JsonObject;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author victor
 */
public class DatoDAO {

    public boolean registrar(JsonObject jsonObject, String table) {
        PreparedStatement ps = null;
        boolean operacion = false;
        try {
            StringBuilder sqlTemporal = new StringBuilder();
            sqlTemporal.append(" INSERT INTO "+ table +" VALUES ( ");
            sqlTemporal.append(" ?, ");
            for (String json : jsonObject.keySet()) {
                sqlTemporal.append(" ?, ");
            }
            int ultimaComa = sqlTemporal.lastIndexOf(",");
            StringBuilder sqlFinal = new StringBuilder(sqlTemporal.substring(0, ultimaComa));
            sqlFinal.append(" ) ");
            ps = ConnectionBD.getConnection().prepareStatement(sqlFinal.toString());
            ps.setLong(1, new Date().getTime());
            int posSetear = 2;
            for (String json : jsonObject.keySet()) {
                System.out.println(jsonObject.get(json).toString());
                ps.setString(posSetear, jsonObject.get(json).toString().replace("\"", ""));
                posSetear++;
            }
            operacion = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return operacion;
    }

}
