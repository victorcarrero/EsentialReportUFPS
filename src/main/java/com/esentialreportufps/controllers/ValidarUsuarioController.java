/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esentialreportufps.controllers;

import com.esentialreportufps.fachada.FachadaEsentialReport;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author victor
 */
public class ValidarUsuarioController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ValidarUsuarioController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ValidarUsuarioController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("usercodigo");
        String clave = request.getParameter("userClave");
        String token = "";
        String tipoUsuario;
        ArrayList<String> resultValidacion; 
        try {
            resultValidacion = FachadaEsentialReport.validarUsuario(codigo, clave);
            token = resultValidacion.get(0);
            if (!token.isEmpty()) {
            tipoUsuario = resultValidacion.get(1);
            HttpSession session = request.getSession();
            session.setAttribute("token", token);
            session.setAttribute("codigo", codigo);
            session.setAttribute("tipoUsuario", Integer.parseInt(tipoUsuario));
            response.sendRedirect("vistas/men_prin.jsp");
            } else {
            response.sendRedirect("login.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ValidarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(token);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
