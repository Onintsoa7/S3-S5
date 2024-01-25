/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Categorie;
import Model.ConnectionPs;
import Model.Fournisseur;
import Model.Materiel;
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
public class ServletMateriel extends HttpServlet {
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
        if (request.getParameter("materiel") != null) {
            if (request.getParameter("nom") != null) {
                   //modification
            } else {
                String idmateriel = request.getParameter("materiel");
                Connection connection = ConnectionPs.connexionPostgreSQL();
                try {
                    Materiel.changeStatMateriel(idmateriel, 0, connection);
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
                Materiel[] materiels = Materiel.materiel(connection);
                Fournisseur[] fournisseurs = Fournisseur.fournisseurs(connection);
                request.setAttribute("Materiel", materiels);
                request.setAttribute("Fournisseur", fournisseurs);
                RequestDispatcher dispatcher = null;
                dispatcher = request.getRequestDispatcher("formMateriel.jsp");
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
        Materiel materiel = new Materiel();
        materiel.setNom(nom);
        materiel.setEtat(1);

        Connection connection = ConnectionPs.connexionPostgreSQL();

        try {
            Materiel.insertMateriel(materiel, connection);
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
