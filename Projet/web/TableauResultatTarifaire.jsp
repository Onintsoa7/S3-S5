<%-- 
    Document   : TableauResultatTarifaire
    Created on : 9 janv. 2024, 14:23:24
    Author     : Chan Kenny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<%@page import="Model.*" %>
<%
    Cout[] cout = (Cout[]) request.getAttribute("Couts");
    
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
                                            <th><strong>Meuble</strong></th>
                                            <th><strong>Taille</strong></th>
                                            <th><strong>Style</strong></th>
                                            <th><strong>Coutde fabrication</strong></th>
                                        </tr>
                                    </thead>
                                    <tbody>             
                                        <%
                                            for (int i = 0; i < cout.length; i++) {%>
                                        <tr>
                                            <td><strong><%= cout[i].getCategorie_nom() %></strong></td>
                                            <td><strong><%= cout[i].getTaille_nom()%></strong></td>
                                            <td><strong><%= cout[i].getStyle_nom()%></strong></td>
                                            <td><strong><%= cout[i].getPrix_unitaire() + " AR"%></strong></td>
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