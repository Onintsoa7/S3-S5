/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Client;
import Model.ConnectionPs;
import Model.Facture;
import Model.V_detail_facture;
import Model.V_detail_paiement_facture;
import Model.V_detail_vente_client;
import Model.V_facture_remise;
import Model.Vente;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ServletListeFacture extends HttpServlet {

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
        if (request.getParameter("vente") != null) {
            V_detail_vente_client vente = new V_detail_vente_client();
            Facture facture = new Facture();
            facture.setId_vente(request.getParameter("vente"));
            vente.setId_vente(request.getParameter("vente"));
            Vector<Facture> factures = null;
            Vector<V_detail_vente_client> v_detail_vente_clients = null;
            Facture[] to_get = null;
            V_detail_vente_client v_to_get = new V_detail_vente_client();
            try {
                factures = facture.select(null);
                v_detail_vente_clients = vente.select(null);
            } catch (Exception ex) {
                Logger.getLogger(ServletListeFacture.class.getName()).log(Level.SEVERE, null, ex);
            }
            to_get = factures.toArray(new Facture[factures.size()]);
            v_to_get = v_detail_vente_clients.toArray(new V_detail_vente_client[v_detail_vente_clients.size()])[0];
            request.setAttribute("facture", to_get);
            request.setAttribute("vente", v_to_get);
            RequestDispatcher dispatcher = null;
            dispatcher = request.getRequestDispatcher("paiement_liste.jsp");
            dispatcher.forward(request, response);
        } else {
            if (request.getParameter("detail") != null) {
                Connection connection = ConnectionPs.connexionPostgreSQL();
                try {
                    String id_vente = request.getParameter("detail");
                    /*-------------------------------------------------------------------------------------------*/
                    V_detail_facture v_detail_facture = new V_detail_facture();
                    v_detail_facture.setId_vente(id_vente);
                    Vector<V_detail_facture> v_detail_facture_vect = v_detail_facture.select(connection);
                    V_detail_facture[] v_detail_facture_tab = v_detail_facture_vect.toArray(new V_detail_facture[v_detail_facture_vect.size()]);
                    /*-------------------------------------------------------------------------------------------*/
                    V_detail_paiement_facture v_detail_paiement_facture = new V_detail_paiement_facture();
                    v_detail_paiement_facture.setId_vente(id_vente);
                    Vector<V_detail_paiement_facture> v_detail_paiement_facture_vect = v_detail_paiement_facture.select(connection);
                    V_detail_paiement_facture[] v_detail_paiement_facture_tab = v_detail_paiement_facture_vect.toArray(new V_detail_paiement_facture[v_detail_paiement_facture_vect.size()]);
                    /*-------------------------------------------------------------------------------------------*/
                    V_facture_remise v_facture_remise = new V_facture_remise();
                    v_facture_remise.setId_vente(id_vente);
                    Vector<V_facture_remise> v_facture_remise_vect = v_facture_remise.select(connection);
                    V_facture_remise[] v_facture_remise_tab = v_facture_remise_vect.toArray(new V_facture_remise[v_facture_remise_vect.size()]);
                    /*-------------------------------------------------------------------------------------------*/

                    Client client = new Client();
                    client.setId_client(v_detail_paiement_facture_tab[0].getId_client());
                    Vector<Client> liste_c = new Vector();
                    Client clients = null;
                    liste_c = client.select(null);
                    clients = liste_c.toArray(new Client[liste_c.size()])[0];
                    request.setAttribute("client", clients);
                    request.setAttribute("remise", v_facture_remise_tab);
                    request.setAttribute("paiement", v_detail_paiement_facture_tab);
                    request.setAttribute("facture", v_detail_facture_tab);
                    RequestDispatcher dispatcher = null;
                    dispatcher = request.getRequestDispatcher("DetailsFacture.jsp");
                    dispatcher.forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Facture fact = new Facture();
                V_detail_vente_client vente = new V_detail_vente_client();
                if (request.getParameter("id_client") != null) {
                    vente.setId_client(request.getParameter("id_client"));
                }
                Facture[] liste_f_tab = null;
                V_detail_vente_client[] liste_v_tab = null;
                V_detail_vente_client vente2 = new V_detail_vente_client();
                try {
                    Vector<V_detail_vente_client> v_detail_vente_client = new Vector<>();
                    Vector<Facture> facture = new Vector<>();
                    Vector<V_detail_vente_client> vente_l = vente.select(null);
                    for (int i = 0; i < vente_l.size(); i++) {
                        fact.setId_vente(vente_l.get(i).getId_vente());
                        String id_facture = fact.max_id_facture(null);
                        Facture facture_izy = new Facture();
                        facture_izy.setId_facture(id_facture);
                        Vector<Facture> facture_det = facture_izy.select(null);
                        Facture to_get = facture_det.toArray(new Facture[facture_det.size()])[0];
                        vente2.setId_vente(to_get.getId_vente());
                        Vector<V_detail_vente_client> vente_det = vente2.select(null);
                        V_detail_vente_client vente_to_get = vente_det.toArray(new V_detail_vente_client[vente_det.size()])[0];
                        v_detail_vente_client.add(vente_to_get);
                        facture.add(to_get);
                    }
                    liste_f_tab = facture.toArray(new Facture[facture.size()]);
                    liste_v_tab = v_detail_vente_client.toArray(new V_detail_vente_client[v_detail_vente_client.size()]);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println(liste_f_tab.length + " - " + liste_v_tab.length);
                request.setAttribute("facture", liste_f_tab);
                request.setAttribute("vente", liste_v_tab);
                RequestDispatcher dispatcher = null;
                dispatcher = request.getRequestDispatcher("facture_liste.jsp");
                dispatcher.forward(request, response);
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
