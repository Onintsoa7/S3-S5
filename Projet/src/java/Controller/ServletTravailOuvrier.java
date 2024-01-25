/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionPs;
import Model.Materiel;
import Model.Profil;
import Model.Style;
import Model.Taille;
import Model.Taille_Profil;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

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
            Profil[] profils = Profil.profils(connection);
            Taille taille = new Taille();
            taille.setIdTaille(idTaille);
            Taille_Profil taille_ouvrier = Taille_Profil.taille_profils(taille, connection)[0];
            request.setAttribute("repetition", taille_ouvrier.getNombre());
            request.setAttribute("profils", profils);
            request.setAttribute("styles", styles);
            request.setAttribute("idTaille", idTaille);
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
        try (PrintWriter out = response.getWriter()) {
            Connection connection = ConnectionPs.connexionPostgreSQL();
            float repetition = Float.valueOf(request.getParameter("repetition"));
            String id_style = request.getParameter("id_style");
            String id_taille = request.getParameter("id_taille");
            String duree = request.getParameter("duree");
            try {
                Profil[] profils = Profil.profils(connection);
                int count = 0;
                for (int i = 0; i < profils.length; i++) {
                    int isa = 0;
                    if (request.getParameter(profils[i].getId_profil()).isEmpty()
                            || request.getParameter(profils[i].getId_profil()) == null
                            || request.getParameter(profils[i].getId_profil()).equalsIgnoreCase("")) {
                        System.out.println(profils[i].getNom());
                        isa = 0;    
                    } else {
                        String value = request.getParameter(profils[i].getId_profil());
                        ConnectionPs.isFloat(value);
                        ConnectionPs.isNotNegative(value);
                        isa = Integer.valueOf(value);
                    }
                    count = count + isa;
                }
                if (count > repetition) {
                    out.println("Nombre d' profils superieur a " + repetition);
                } else if (count < repetition) {
                    out.println("Nombre d' profils inferieur a " + repetition);
                } else {
                    ConnectionPs.isNotEmpty(duree);
                    ConnectionPs.isFloat(duree);
                    ConnectionPs.isNotNegative(duree);
                    for (int i = 0; i < profils.length; i++) {
                        float isa = 0;
                        if (request.getParameter(profils[i].getId_profil()).isEmpty()
                                || request.getParameter(profils[i].getId_profil()) == null
                                || request.getParameter(profils[i].getId_profil()).equalsIgnoreCase("")) {
                            isa = 0;
                        } else {
                            String value = request.getParameter(profils[i].getId_profil());
                            ConnectionPs.isFloat(value);
                            ConnectionPs.isNotNegative(value);
                            isa = Integer.valueOf(value);
                        }
                        if (isa != 0) {
                            Profil o = new Profil();
                            o.setId_profil(profils[i].getId_profil());
                            o.setDuree_travail(Float.valueOf(duree));
                            o.insert_duree_travail(isa, id_taille, id_style, connection);
                        }
                    }
                    RequestDispatcher dispatcher = null;
                    dispatcher = request.getRequestDispatcher("index.jsp");
                    dispatcher.forward(request, response);
                }
            } catch (Exception ex) {
                Logger.getLogger(ServletTravailOuvrier.class.getName()).log(Level.SEVERE, null, ex);
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
