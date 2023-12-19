/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Categorie;
import Model.ConnectionPs;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chan Kenny
 */
public class ServletCategorie extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletCategorie</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletCategorie at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        /*if (request.getParameter("categorie") != null && !request.getParameter("categorie").isEmpty()) {
            if (request.getParameter("nom") != null) {
                //modification
            } else {
                String idcategorie = request.getParameter("categorie");
                Connection connection = ConnectionPs.connexionPostgreSQL();
                try {
                    Categorie.changeStatCategorie(idcategorie, 0, connection);
                } catch (Exception ex) {
                    Logger.getLogger(ServletListeStyle.class.getName()).log(Level.SEVERE, null, ex);
                }
                RequestDispatcher dispatcher = null;
                dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
            }else { */
            Connection connection = ConnectionPs.connexionPostgreSQL();
            try {
                Categorie[] categories = Categorie.categorie(connection);
                request.setAttribute("categories", categories);
                RequestDispatcher dispatcher = null;
                dispatcher = request.getRequestDispatcher("formCategorie.jsp");
                dispatcher.forward(request, response);
            } catch (Exception ex) {

            }
        /*}*/
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            //processRequest(request, response);
            String nom = request.getParameter("nom");
            Categorie catego = new Categorie();
            catego.setNom(nom);
            catego.setEtat(1);

            Connection connection = ConnectionPs.connexionPostgreSQL();
            try {
                Categorie.insertCategorie(catego, connection);
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
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
