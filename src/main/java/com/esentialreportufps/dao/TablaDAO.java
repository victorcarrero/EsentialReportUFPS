/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esentialreportufps.dao;

import com.esentialreportufps.bd.ConnectionBD;
import com.esentialreportufps.valueobjects.TablaVO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author victor
 */
public class TablaDAO {

    public boolean insertar(TablaVO tablaVO, int numeroColumnas) {
        PreparedStatement ps = null;
        boolean operacion = false;
        try {
            StringBuilder sqlTemporal = new StringBuilder();
            sqlTemporal.append(" CREATE TABLE IF NOT EXISTS " + tablaVO.getNombre() + "( fechaRegistro BIGINT(20), ");
            for (int i = 0; i < tablaVO.getColumnas().size(); i++) {
                sqlTemporal.append(tablaVO.getColumnas().get(i).replace(" ", "_") + " VARCHAR(40), ");
            }
            int ultimaComa = sqlTemporal.lastIndexOf(",");
            StringBuilder sqlFinal = new StringBuilder(sqlTemporal.substring(0, ultimaComa));
            sqlFinal.append(" ) ");
            ps = ConnectionBD.getConnection().prepareStatement(sqlFinal.toString());
            operacion = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TablaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TablaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return operacion;
    }
}
