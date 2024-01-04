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
public class ServletModifyStyleMateriel extends HttpServlet {

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
            out.println("<title>Servlet ServletModifyStyleMateriel</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletModifyStyleMateriel at " + request.getContextPath() + "</h1>");
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
        Connection connection = ConnectionPs.connexionPostgreSQL();
        try {
            String style = request.getParameter("Style");
            StyleMateriel[] stylemateriels = StyleMateriel.styleMateriels(style, connection);

            String nomStyle = request.getParameter("nom");
            request.setAttribute("nom", nomStyle);

            Materiel[] materiels = Materiel.materiel(connection);

            String[] selectionner = StyleMateriel.selectedMateriel(materiels, stylemateriels);

            request.setAttribute("selected", selectionner);
            request.setAttribute("style", style);
            request.setAttribute("materiels", materiels);

        } catch (Exception e) {
        }

        RequestDispatcher dispatcher = null;
        dispatcher = request.getRequestDispatcher("modifyStyleMateriel.jsp");
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
        Connection connection = ConnectionPs.connexionPostgreSQL();
        String style = request.getParameter("style");
        Style styles = new Style();
        styles.setIdStyle(style);

        String[] materiels = request.getParameterValues("materiel");
        Materiel materiel = new Materiel();

        StyleMateriel stylemateriel = new StyleMateriel();
        stylemateriel.setStyle(styles);
        try {
            // supprimer les style puis reinserer
            StyleMateriel.deleteStyleMateriels(style, connection);
            if (materiels != null) {
                for (int i = 0; i < materiels.length; i++) {
                    materiel.setIdMateriel(materiels[i]);
                    stylemateriel.setMateriel(materiel);
                    StyleMateriel.insertStyleMateriel(stylemateriel, connection);
                }
            }
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
