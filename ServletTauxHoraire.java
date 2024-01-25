/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionPs;
import Model.Ouvrier;
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
public class ServletTauxHoraire extends HttpServlet {
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
                String idTaille = request.getParameter("taille");
        Connection connection = ConnectionPs.connexionPostgreSQL();
        try {
            request.setAttribute("idTaille",idTaille);
            
            Style[] styles = Style.style(connection);
            
            request.setAttribute("style", styles);
            RequestDispatcher dispatcher = null;
            dispatcher = request.getRequestDispatcher("FormTauxHoraire.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletFormStyleMateriel.class.getName()).log(Level.SEVERE, null, ex);
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
        
        String idOuvrier = request.getParameter("ouvrier");
        Ouvrier jean = new Ouvrier(idOuvrier, idOuvrier);
        float valeur =  Float.valueOf(request.getParameter("valeur"));
        Connection connection = ConnectionPs.connexionPostgreSQL();
        try {
            Salaire_Ouvrier salOuv= new Salaire_Ouvrier(idOuvrier,valeur);
            salOuv.setOuvrier(jean);
            salOuv.insert(connection);
            
            RequestDispatcher dispatcher = null;
            dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(ServletFormStyleMateriel.class.getName()).log(Level.SEVERE, null, e);
        }
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
