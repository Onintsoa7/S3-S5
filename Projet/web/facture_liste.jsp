<%-- 
    Document   : facture_liste
    Created on : 25 janv. 2024, 11:02:10
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@page import="Model.*" %>
<%
Facture[] liste_f_tab = (Facture[])request.getAttribute("facture");
V_detail_vente_client[] liste_v_tab = (V_detail_vente_client[])request.getAttribute("vente");
%>
<main role="main" class="main-content">

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Liste des factures</strong>
        </div>
        <div class="card-body">
            <table class="table datatables" id="dataTable-1">
                <thead>
                    <tr>
                        <th><strong>FACTURE N°</strong></th>
                        <th><strong>CLIENT</strong></th>
                        <th><strong>Montant total</strong></th>
                        <td><strong>Montant restant</strong></td>
                        <td><strong>Payer reste</strong></td>
                        <td><strong>Voir facture</strong></td>
                        <td><strong>Voir paiement</strong></td>  
                    </tr>
                </thead>
                <tbody>             
                    <% for (int i = 0; i < liste_f_tab.length; i++) {
                    Float payer  = (liste_v_tab[i].getMontant()-liste_f_tab[i].getMontant_restant());
                    String modalId = "defaultModal_" + i;
                    %>
                    <tr>
                        <td><strong><%=liste_f_tab[i].getId_facture()%> </strong></td>
                        <td><strong><%=liste_v_tab[i].getClient_name()%></strong></td>
                        <td><strong><%=liste_v_tab[i].getMontant()%> AR</strong></td>
                        <td><strong><%=liste_f_tab[i].getMontant_restant()%> AR</strong></td>
                        <td>
                            <a>
                                <button class="btn mb-2 btn-outline-danger btn mb-2" data-toggle="modal" data-target="#<%= modalId %>">
                                    <span class="fe fe-24">$</span>
                                </button>
                            </a>
                        </td>
                        <td><a href="ServletListeFacture?detail=<%=liste_f_tab[i].getId_vente() %>"><button class="btn mb-2 btn-outline-info"><span class="fe fe-24 fe-more-vertical"></span></button></a></td>
                        <td><a href="ServletListeFacture?vente=<%=liste_f_tab[i].getId_vente() %>"><button class="btn mb-2 btn-outline-warning"><span class="fe fe-24 fe-more-vertical"></span></button></a></td>
                <div class="modal fade"id="<%= modalId %>" tabindex="-1" role="dialog" aria-labelledby="<%= modalId %>Label" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="defaultModalLabel">Paiement</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">×</span>
                                </button>
                            </div>
                            <div class="modal-body">Le reste à payer est de <%=liste_f_tab[i].getMontant_restant() %>AR</div>
                            <form method="post" action="ServletFacturation">
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="montant_paye" name="montant_paye" placeholder="0">
                                </div>
                                <hr>
                                <div class="col-sm-3">
                                    <input class="form-control" type="date" name="date" id="date">
                                </div>
                                <input type="hidden" value="<%=liste_f_tab[i].getId_facture() %>" name="id_facture" id="id_facture"> 
                                <hr>
                                <div class="form-group mb-2" style="margin-left: 1pc">
                                    <button type="submit" class="btn btn-primary">Payer</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                </tr>
                <%    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</main>
<%@ include file="footer.jsp" %>
