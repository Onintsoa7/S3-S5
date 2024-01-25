/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Client;
import Model.ConnectionPs;
import Model.Cout;
import Model.Mere;
import Model.Vente;
import Model.Vente_detail;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Date;
import java.util.Vector;

/**
 *
 * @author user
 */
public class ServletFacturation extends HttpServlet {

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
        String liste = request.getParameter("liste");
        if (liste != null) {
            RequestDispatcher dispatcher = null;
            dispatcher = request.getRequestDispatcher("facture_liste.jsp");
            dispatcher.forward(request, response);
        } else {
            Client client = new Client();
            Vector<Client> liste_c = new Vector();
            Client[] clients = null;
            try {
                liste_c = client.select(null);
                clients = liste_c.toArray(new Client[liste_c.size()]);
                Mere[] meubles = Mere.meuble(null);
                request.setAttribute("meres", meubles);
                request.setAttribute("clients", clients);
                RequestDispatcher dispatcher = null;
                dispatcher = request.getRequestDispatcher("formFacture.jsp");
                dispatcher.forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
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
        String client = request.getParameter("client");
        Date date = Date.valueOf(request.getParameter("date"));
        Connection connection = ConnectionPs.connexionPostgreSQL();
        try {
            Mere[] meubles = Mere.meuble(null);
            float montant = 0;
            for (int i = 0; i < meubles.length; i++) {
                if (!request.getParameter(meubles[i].getIdmere()).equalsIgnoreCase("")) {
                    Cout cout = new Cout();
                    float prix_vente = cout.prix_vente(meubles[i].getIdstyle().getIdStyle(), meubles[i].getIdtaille().getIdTaille(), connection);
                    System.out.println(prix_vente);
                    float quantite = Float.valueOf(request.getParameter(meubles[i].getIdmere()));
                    montant = montant + (prix_vente*quantite);
                }
            }
            Vente vente = new Vente();
            vente.setId_client(client);
            vente.setMontant(montant);
            vente.setDate_vente(date);
            vente.insert(null);
            String id_vente = Vente.id_vente(connection);
            for (int i = 0; i < meubles.length; i++) {
                if (!request.getParameter(meubles[i].getIdmere()).equalsIgnoreCase("")) {
                    Vente_detail vente_detail = new Vente_detail();
                    Cout cout = new Cout();
                    float prix_vente = cout.prix_vente(meubles[i].getIdstyle().getIdStyle(), meubles[i].getIdtaille().getIdTaille(), connection);
                    float quantite = Float.valueOf(request.getParameter(meubles[i].getIdmere()));
                    vente_detail.setId_mere(meubles[i].getIdmere());
                    vente_detail.setId_vente(id_vente);
                    vente_detail.setPrix_unitaire(prix_vente);
                    vente_detail.setQuantite(quantite);
                    vente_detail.insert(null);
                }
            }
            RequestDispatcher dispatcher = null;
            dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
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
