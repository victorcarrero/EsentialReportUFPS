/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esentialreportufps.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author victor
 */
public class ConnectionBD {

    private static Connection con = null;

    public static Connection getConnection() throws SQLException {

        if (con == null) {
            try {
                Class.forName(AtributosBd.driver);
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ESENTIALREPORT","admin", "admin");
                System.out.println("Conexion a BD exitosa");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectionBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return con;
    }

    public static void cerrarConexion() {
        try {
            if (con != null) {
            con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
