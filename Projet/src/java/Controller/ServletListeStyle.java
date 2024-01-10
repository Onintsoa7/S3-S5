/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionPs;
import Model.Materiel;
import Model.Style;
import Model.StyleMateriel;
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
public class ServletListeStyle extends HttpServlet {
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
        if (request.getParameter("Style") != null && !request.getParameter("Style").isEmpty()) {
            String style = request.getParameter("Style");
            request.setAttribute("nom",request.getParameter("nom"));
            Style styles = new Style();
            styles.setIdStyle(style);
            Connection connection = ConnectionPs.connexionPostgreSQL();
            try {
                StyleMateriel[] stylemateriels = StyleMateriel.styleMateriels(style, connection);
                request.setAttribute("stylemateriels", stylemateriels);
            } catch (Exception ex) {
                Logger.getLogger(ServletListeStyle.class.getName()).log(Level.SEVERE, null, ex);
            }

            RequestDispatcher dispatcher = null;
            dispatcher = request.getRequestDispatcher("listeStyleMateriel.jsp");
            dispatcher.forward(request, response);
        } else {
            Connection connection = ConnectionPs.connexionPostgreSQL();
            try {
                Style[] styles = Style.style(connection);
                request.setAttribute("styles", styles);
                RequestDispatcher dispatcher = null;
                dispatcher = request.getRequestDispatcher("listeStyle.jsp");
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
