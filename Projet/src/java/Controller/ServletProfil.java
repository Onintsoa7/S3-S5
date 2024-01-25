/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionPs;
import Model.Employer;
import Model.Poste;
import Model.Profil;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ServletProfil extends HttpServlet {

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
        if (request.getParameter("profil") == null) {
            try {
                Profil[] profils = Profil.profils(connection);
                Employer[] emplyoers = Employer.employers(connection);
                Poste[] poste = Poste.postes(connection);
                connection.close();
                request.setAttribute("postes", poste);
                request.setAttribute("profils", profils);
                request.setAttribute("employers", emplyoers);
                RequestDispatcher dispatcher = null;
                dispatcher = request.getRequestDispatcher("formProfil.jsp");
                dispatcher.forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ServletTaille.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String idemp = request.getParameter("profil");
            System.out.println(idemp);
            Employer[] emps = null;
            try {
                emps = Employer.employer_detail(idemp, connection);
                connection.close();
                request.setAttribute("emp", emps);
            } catch (Exception ex) {
                Logger.getLogger(ServletEmployer.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestDispatcher dispatcher = null;
            dispatcher = request.getRequestDispatcher("DetailsProfil.jsp");
            dispatcher.forward(request, response);
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
        Connection connection = ConnectionPs.connexionPostgreSQL();
        String type = request.getParameter("type");
        if (type.equalsIgnoreCase("1")) {
            String nom = request.getParameter("nom");
            Profil profil = new Profil(nom);
            try {
                profil.insert(connection);
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (type.equalsIgnoreCase("2")) {
            //String id_profil = request.getParameter("profil");
            Float salaire = Float.valueOf(request.getParameter("salaire"));
            try {
                //profil profil = new Profil(id_profil, salaire);
                Profil profil = new Profil();
                profil.setSalaire(salaire);
                profil.insert_taux_profil(connection);
                connection.close();
            } catch (Exception ex) {
                Logger.getLogger(ServletProfil.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (type.equalsIgnoreCase("3")) {
            String employer = request.getParameter("employer");
            String poste = request.getParameter("poste");
            String date = request.getParameter("date");
            String karama = request.getParameter("karama");
            try {
                Employer emp = new Employer();
                emp.setDate_embauche(Date.valueOf(date));
                emp.setId_employer(employer);
                emp.setId_poste(poste);
                emp.setKarama(Float.valueOf(karama));
                emp.insert_embauche_employer(connection);
                connection.close();
            } catch (Exception ex) {
                Logger.getLogger(ServletProfil.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(type.equalsIgnoreCase("4")){
            String id_profil = request.getParameter("profil");
            int niveau = Integer.valueOf(request.getParameter("niveau"));
            Date date = Date.valueOf(request.getParameter("date"));
            Profil profil = new Profil();
            profil.setNiveau(niveau);
            profil.setId_profil(id_profil);
            try {
                profil.insert_grade(date, connection);
                connection.close();
            } catch (Exception ex) {
                Logger.getLogger(ServletProfil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            String id_profil = request.getParameter("profil");
            int niveau = Integer.valueOf(request.getParameter("niveau"));
            String date = request.getParameter("date");
            Date dated = Date.valueOf(date);
            Profil profil = new Profil();
            profil.setId_profil(id_profil);
            profil.setNiveau(niveau);
            try {
                profil.insert_profil_taux_salaire(dated, connection);
                connection.close();
            } catch (Exception ex) {
                Logger.getLogger(ServletProfil.class.getName()).log(Level.SEVERE, null, ex);
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
