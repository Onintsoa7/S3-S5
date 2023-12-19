/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Categorie;
import Model.ConnectionPs;
import Model.Formule;
import Model.Materiel;
import Model.StyleMateriel;
import Model.Taille;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chan Kenny
 */
public class Servletformule extends HttpServlet {

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
            out.println("<title>Servlet Servletformule</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servletformule at " + request.getContextPath() + "</h1>");
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
        String idStyle = request.getParameter("style");
        Connection connection = ConnectionPs.connexionPostgreSQL();
        try {
            request.setAttribute("idstyle", idStyle);
            request.setAttribute("nom", request.getParameter("nom"));

            StyleMateriel[] stylemateriels = StyleMateriel.styleMateriels(idStyle, connection);
            request.setAttribute("stylemateriels", stylemateriels);

            Categorie[] categories = Categorie.categorie(connection);
            request.setAttribute("categories", categories);
            Taille[] tailles = Taille.Taille(connection);
            request.setAttribute("tailles", tailles);

            RequestDispatcher dispatcher = null;
            dispatcher = request.getRequestDispatcher("formule.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
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
        PrintWriter out = response.getWriter();
        String idStyle = request.getParameter("style");

        String categorie = request.getParameter("categorie");

        String taille = request.getParameter("taille");

        Connection connection = ConnectionPs.connexionPostgreSQL();
        try {
            Formule formule = new Formule();
            formule.setIdcategorie(categorie);
            formule.setIdtaille(taille);
            out.print(categorie);
            out.print(taille);
            out.print(idStyle);
            StyleMateriel[] stylemateriels = StyleMateriel.styleMateriels(idStyle, connection);
            for (int i = 0; i < stylemateriels.length; i++) {
                if (request.getParameter(stylemateriels[i].getIdStyleMateriel()) != null) {
                    formule.setIdstylemateriel(stylemateriels[i].getIdStyleMateriel());
                    formule.setQuantite(request.getParameter(stylemateriels[i].getIdStyleMateriel()));
                    Formule.insertFormule(formule, connection);
                }
            }
            RequestDispatcher dispatcher = null;
            dispatcher = request.getRequestDispatcher("formule.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
