/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cout;
import Model.Materiel;
import java.io.IOException;
import static java.lang.Float.min;
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
public class ServletTarification extends HttpServlet {

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
        if (type.equalsIgnoreCase("1")) {
            String mini = request.getParameter("min");
            String maxi = request.getParameter("max");
            RequestDispatcher dispatcher = null;
            if (mini.isEmpty() && maxi.isEmpty()) {
                dispatcher = request.getRequestDispatcher("FormulaireTarification.jsp");
                dispatcher.forward(request, response);
            } else if (mini.isEmpty() && !maxi.isEmpty()) {
                mini = "0";
            } else if (!mini.isEmpty() && maxi.isEmpty()) {
                maxi = "50000000000000000";
            }
            float min = Float.valueOf(mini);
            float max = Float.valueOf(maxi);

            try {
                Cout[] tab = Cout.tarification(max, min, null);
                request.setAttribute("Couts", tab);
                dispatcher = request.getRequestDispatcher("TableauResultatTarifaire.jsp");
                dispatcher.forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ServletTarification.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String mini = request.getParameter("min");
            String maxi = request.getParameter("max");
            RequestDispatcher dispatcher = null;
            try {
                Cout cout = new Cout();
                Cout[] tab = cout.benefice_entre_2(mini, maxi, null);
                request.setAttribute("Couts", tab);
                dispatcher = request.getRequestDispatcher("TableauBenefice.jsp");
                dispatcher.forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ServletTarification.class.getName()).log(Level.SEVERE, null, ex);
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
