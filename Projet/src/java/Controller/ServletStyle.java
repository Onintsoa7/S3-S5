/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionPs;
import Model.Materiel;
import Model.Style;
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
public class ServletStyle extends HttpServlet {
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
        if (request.getParameter("style") != null) {
            if (request.getParameter("nom") != null) {
                //modification
            } else {
                String idstyle = request.getParameter("style");
                Connection connection = ConnectionPs.connexionPostgreSQL();
                try {
                    Style.changeStatstyle(idstyle,0, connection);
                } catch (Exception ex) {
                    Logger.getLogger(ServletListeStyle.class.getName()).log(Level.SEVERE, null, ex);
                }

                RequestDispatcher dispatcher = null;
                dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            Connection connection = ConnectionPs.connexionPostgreSQL();
            try {
                Style[] styles = Style.style(connection);
                request.setAttribute("style", styles);
                RequestDispatcher dispatcher = null;
                dispatcher = request.getRequestDispatcher("formStyle.jsp");
                dispatcher.forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ServletFormStyleMateriel.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        //processRequest(request, response);
        String nom = request.getParameter("nom");
        Style style = new Style();
        style.setNom(nom);
        style.setEtat(1);

        Connection connection = ConnectionPs.connexionPostgreSQL();

        try {
            Style.insertStyle(style, connection);
        } catch (Exception ex) {
            Logger.getLogger(ServletCategorie.class.getName()).log(Level.SEVERE, null, ex);
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
