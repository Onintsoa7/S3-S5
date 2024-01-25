<%-- 
    Document   : sortie.jsp
    Created on : 11 janv. 2024, 15:51:22
    Author     : Chan Kenny
--%>


<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<%@page import="Model.*" %>
<%
    Sortie[] sortie = (Sortie[]) request.getAttribute("sortie");
    
%>

<main role="main" class="main-content">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-12">
                <h2 class="mb-2 page-title">Liste des meubles dans la fourchette de prix</h2>
                <div class="row my-4">
                    <!-- Small table -->
                    <div class="col-md-12">
                        <div class="card shadow">
                            <div class="card-body">
                                <!-- table -->
                                <table class="table datatables" id="dataTable-1">
                                    <thead>
                                        <tr>
                                            <th><strong>Materiel</strong></th>
                                            <th><strong>Quantite Sortent</strong></th>
                                            <th><strong>Quantite restente</strong></th>
                                        </tr>
                                    </thead>
                                    <tbody>             
                                        <%
                                            for (int i = 0; i < sortie.length; i++) {%>
                                        <tr>
                                            <td><strong><%= sortie[i].get() %></strong></td>
                                            <td><strong><%= sortie[i].getCategorie_nom() %></strong></td>
                                            <td><strong><%= sortie[i].getTaille_nom()%></strong></td>
                                        </tr>
                                        <%    }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                            <a href="ServletListeStyle"><button class="btn btn-primary">Retour</button></a>
                        </div>
                    </div> <!-- simple table -->
                </div> <!-- end section -->
            </div> <!-- .col-12 -->
        </div> <!-- .row -->
    </div> <!-- .container-fluid -->

</main> <!-- main -->

<%@ include file="footer.jsp" %>