/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionPs;
import Model.Cout;
import Model.Fabrication;
import Model.Formule;
import Model.Materiel;
import Model.Mere;
import Model.Mouvement;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ServletFabrication extends HttpServlet {

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
            Mere[] meubles = Mere.meuble(connection);
            Cout cout = new Cout();
            Float[] floats = new Float[meubles.length];
            for (int i = 0; i < meubles.length; i++) {
                floats[i] = cout.prix_vente(meubles[i].getIdstyle().getIdStyle() ,meubles[i].getIdtaille().getIdTaille(), connection);
            }
            request.setAttribute("meubles", meubles);
            request.setAttribute("prix", floats);
        } catch (Exception ex) {
            Logger.getLogger(ServletFabrication.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher dispatcher = null;
        dispatcher = request.getRequestDispatcher("fabrication.jsp");
        dispatcher.forward(request, response);
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
        try (PrintWriter out = response.getWriter()) {
            if (type.equalsIgnoreCase("1")) {
                String id_mere = request.getParameter("id_mere");
                String quantite = request.getParameter("quantite");
                 Connection co = ConnectionPs.connexionPostgreSQL();
                try {
                co.setAutoCommit(false);
                Fabrication fabrication = new Fabrication();
                fabrication.setIdmere(id_mere);
                fabrication.setQuantite(quantite);
                System.out.println(fabrication.getIdmere());
                Materiel[] reste = fabrication.insertion_fabrication(fabrication, co);
                co.commit();
                co.close();
                System.out.println(reste.length);
                if (reste.length >= 0) {
                    out.println("-----------------------------------------------");
                    out.println("----les quantites manquantes----");
                    for (int i = 0; i < reste.length; i++) {
                        out.println(reste[i].getIdMateriel() + " : " + reste[i].getReste());
                    }
                } else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                    dispatcher.forward(request, response);
                }
                } catch (Exception e) {
                    out.print(e.getMessage());
                    e.printStackTrace();
                }finally{
                    try {
                        co.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ServletFabrication.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            } else {
                String id_mere = request.getParameter("id_mere");
                String pourcentage = request.getParameter("pourcentage");
                try {
                    ConnectionPs.isNotEmpty(pourcentage);
                    ConnectionPs.isFloat(pourcentage);
                    ConnectionPs.isNotNegative(pourcentage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    Mere mere = new Mere();
                    mere.setIdmere(id_mere);
                    mere.setPourcentage(Float.valueOf(pourcentage));
                    mere.insert_pourcentage_vente(connection);
                    connection.close();
                    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                    dispatcher.forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
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
