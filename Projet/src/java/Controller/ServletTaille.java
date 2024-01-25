/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Categorie;
import Model.ConnectionPs;
import Model.Materiel;
import Model.Taille;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Chan Kenny
 */
public class ServletTaille extends HttpServlet {

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
        Connection connection = ConnectionPs.connexionPostgreSQL();
        try {
            Taille[] tailles = Taille.Taille(connection);
            request.setAttribute("tailles", tailles);
            RequestDispatcher dispatcher = null;
            dispatcher = request.getRequestDispatcher("formTaille.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletTaille.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        String type = request.getParameter("type");
        Connection connection = ConnectionPs.connexionPostgreSQL();
        if (type.equalsIgnoreCase("1")) {
            String nom = request.getParameter("nom");
            Taille taille = new Taille();
            taille.setNom(nom);
            try {
                Taille.insertTaille(taille, connection);
                connection.close();
            } catch (Exception ex) {

            }
        } else {
            String nombre_ouvrier_str = request.getParameter("nombre_ouvrier");
            try {
                Taille.insert_taille_ouvrier_nombre(nombre_ouvrier_str, connection);
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        RequestDispatcher dispatcher = null;
        dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
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
