<%-- 
    Document   : paiement_liste
    Created on : 28 janv. 2024, 10:33:31
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.*" %>
<%@ include file="header.jsp" %>
<%
Facture[] liste_f_tab = (Facture[])request.getAttribute("facture");
V_detail_vente_client vente = (V_detail_vente_client)request.getAttribute("vente");    
%>
<main role="main" id="main" class="main-content">

    <div class="card-header">
        <h1 class="card-title">FACTURE N° <%=vente.getDate_de_naissance().toString().replace("-", "")+"-"+vente.getId_vente().replace("vente", "") %></h1>
    </div>
    <div class="card-header">
        <h1 class="card-title">MONTANT TOTAL : <%=vente.getMontant() %> AR</h1>
    </div>
    <div class="card-body">
        <table class="table">
            <thead>
                <tr>
                    <th><strong>Date</strong></th>
                    <th><strong>Payé</strong></th>
                    <th><strong>Restant</strong></th>
                </tr>
            </thead>
            <tbody>             
                <% for (int i = 0; i < liste_f_tab.length; i++) {
                %>
                <tr>
                    <td><%=liste_f_tab[i].getDate_paiement()%> </td>
                    <td><%=liste_f_tab[i].getMontant_paye()%> AR</td>
                    <td><%=liste_f_tab[i].getMontant_restant()%> AR</td>
                </tr>
                <%    }
                %>
            </tbody>
        </table>
    </div>
</main>
<%@ include file="footer.jsp" %>
