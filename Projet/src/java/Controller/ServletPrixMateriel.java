/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionPs;
import Model.Materiel;
import Model.Mouvement;
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
public class ServletPrixMateriel extends HttpServlet {
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
        Connection connection = ConnectionPs.connexionPostgreSQL();
        String price = request.getParameter("prix");
        String id = request.getParameter("materiel");
        String idfournisseur = request.getParameter("fournissuer");
        String quantite = request.getParameter("quantite");
        Materiel materiel = new Materiel();
        materiel.setIdMateriel(id);
        materiel.setIdFournisseurs(idfournisseur);
        try {
            
            materiel.setQuantite(quantite);
            materiel.setPrix(price);
        } catch (Exception ex) {
            Logger.getLogger(ServletPrixMateriel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Mouvement mouvement = new Mouvement();
            mouvement.setId_materiel(materiel.getIdMateriel());
            mouvement.setQuantite_entree(materiel.getQuantite());
            mouvement.setQuantite_sortie(0);
            mouvement.insererMouvement(connection);
            
            Materiel.insertPrixMateriel(materiel, connection);
            connection.commit();
            connection.close();
        } catch (Exception e) {
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
