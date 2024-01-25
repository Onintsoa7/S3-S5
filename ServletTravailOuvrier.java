/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionPs;
import Model.Materiel;
import Model.Ouvrier;
import Model.Style;
import Model.Taille_Ouvrier;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Chan Kenny
 */
public class ServletTravailOuvrier extends HttpServlet {
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
            Style[] styles = Style.style(connection);
            Ouvrier[] ouvriers = Ouvrier.ouvrier(connection);
            Taille_Ouvrier taille_ouvrier = Taille_Ouvrier.taille_ouvriers(idTaille, connection)[0];
            request.setAttribute("repetition", taille_ouvrier.getNombre());
            request.setAttribute("ouvriers", ouvriers);
            request.setAttribute("styles", styles);
            request.setAttribute("idTaille",idTaille);
            RequestDispatcher dispatcher = null;
            dispatcher = request.getRequestDispatcher("FormTravailOuvrier.jsp");
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
        String 
        String id_style = request.getParameter("style");
        String duree = request.getParameter("duree");
        
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
