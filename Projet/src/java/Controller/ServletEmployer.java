/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Employer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
public class ServletEmployer extends HttpServlet {

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
                 if (request.getParameter("idEmp") == null) {
            Employer[] lesEmp;
            try {
                lesEmp = Employer.employers(null);
                request.setAttribute("lesemps", lesEmp);
                RequestDispatcher dispatcher = null;
                dispatcher = request.getRequestDispatcher("FormInsertionPersonnel.jsp");
                dispatcher.forward(request, response);

            } catch (Exception ex) {
                Logger.getLogger(ServletEmployer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else {
            String idemp = request.getParameter("idEmp");
            Employer emp;
            try {
                emp = Employer.employerById(idemp, null);
                request.setAttribute("emp", emp);
            } catch (Exception ex) {
                Logger.getLogger(ServletEmployer.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestDispatcher dispatcher = null;
            dispatcher = request.getRequestDispatcher("DetailsEmployer.jsp");
            dispatcher.forward(request, response);

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *F
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        Date date = Date.valueOf(request.getParameter("date"));
        String adresse = request.getParameter("adresse");
        String contact = request.getParameter("contact");

        Employer emp = new Employer();
        emp.setNom(nom);
        emp.setPrenom(prenom);
        emp.setAdresse(adresse);
        emp.setContact(contact);
        emp.setDate_de_naissance(date);
        try {
            emp.insertEmployer(null);
        } catch (Exception ex) {
            Logger.getLogger(ServletEmployer.class.getName()).log(Level.SEVERE, null, ex);
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
