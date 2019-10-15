/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esentialreportufps.dao;

import com.esentialreportufps.bd.ConnectionBD;
import com.esentialreportufps.valueobjects.UsuarioVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author victor
 */
public class UsuarioDAO {

    public UsuarioVO validarUsuario(String codigo, String clave) {

        UsuarioVO usuarioVO = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT * FROM USUARIOS AS USER ");
            sql.append(" WHERE USER.USU_COD = ? ");
            sql.append(" AND USER.USU_CLA = MD5(?) ");
            ps = ConnectionBD.getConnection().prepareStatement(sql.toString());
            ps.setString(1, codigo);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuarioVO = new UsuarioVO();
                usuarioVO.setcodigo(rs.getString("USU_COD"));
                usuarioVO.setProgramaAcademico(rs.getString("USU_PRO_ACA"));
                usuarioVO.setNombre(rs.getString("USU_NOM"));
                usuarioVO.setTipoUsuario(rs.getInt("USU_TIP"));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //ConnectionBD.cerrarConexion();
        }
        return usuarioVO;
    }

    public ArrayList<UsuarioVO> listarUsuarios() {
        ArrayList<UsuarioVO> listaUsuarios = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT * FROM USUARIOS AS USER ");
            ps = ConnectionBD.getConnection().prepareStatement(sql.toString());
            rs = ps.executeQuery();
            if (rs.isBeforeFirst()) {
                listaUsuarios = new ArrayList<UsuarioVO>();
                while (rs.next()) {
                    UsuarioVO usuarioVO = new UsuarioVO();
                    usuarioVO.setcodigo(rs.getString("USER.usu_cod"));
                    usuarioVO.setNombre(rs.getString("USER.USU_NOM"));
                    usuarioVO.setProgramaAcademico(rs.getString("USER.USU_PRO_ACA"));
                    usuarioVO.setTipoUsuario(Integer.parseInt(rs.getString("USER.USU_TIP")));
                    usuarioVO.setClave(rs.getString("USER.USU_CLA"));
                    listaUsuarios.add(usuarioVO);
                }
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //ConnectionBD.cerrarConexion();
        }
        return listaUsuarios;
    }

    public boolean registrar(UsuarioVO usuarioVO) throws SQLException {
        PreparedStatement ps = null;
        boolean operacion = false;
        try {
            System.out.println("registro");
            StringBuilder sql = new StringBuilder();
            sql.append(" INSERT INTO USUARIOS ");
            sql.append(" VALUES ( ?, MD5(?), ?, ?, ?) ");
            ps =ConnectionBD.getConnection().prepareStatement(sql.toString());
            ps.setString(1, usuarioVO.getcodigo());
            ps.setString(2, usuarioVO.getClave());
            ps.setString(3, usuarioVO.getProgramaAcademico());
            ps.setString(4, usuarioVO.getNombre());
            ps.setInt(5, usuarioVO.getTipoUsuario());
            operacion = ps.executeUpdate() > 0;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return operacion;
    }
    
    public boolean eliminar(UsuarioVO usuarioVO) throws SQLException {
        PreparedStatement ps = null;
        boolean operacion = false;
        try {
            System.out.println("registro");
            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE FROM USUARIOS  ");
            sql.append(" WHERE  usu_cod = ? ");
            ps =ConnectionBD.getConnection().prepareStatement(sql.toString());
            System.out.println(sql.toString() + usuarioVO.getcodigo());
            ps.setString(1, usuarioVO.getcodigo());
            operacion = ps.executeUpdate() > 0;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return operacion;
    }

}
