<%-- 
    Document   : formulebyMateriel
    Created on : 19 déc. 2023, 20:26:28
    Author     : Chan Kenny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<%@page import="Model.*" %>
<% Formule[] formules = (Formule[]) request.getAttribute("formule");
    String nomMateriel = (String) request.getAttribute("nom");
%>

<main role="main" class="main-content">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-12">
                <h2 class="mb-2 page-title">LISTE MEUBLE POUR LE MATERIEL <%= nomMateriel%></h2>
                <div class="row my-4">
                    <!-- Small table -->
                    <div class="col-md-12">
                        <div class="card shadow">
                            <div class="card-body">
                                <!-- table -->
                                <table class="table datatables" id="dataTable-1">
                                    <thead>
                                        <tr>
                                            <th><strong>StYLE</strong></th>
                                            <th><strong>TAILLE</strong></th>
                                            <th><strong>CATEGORIE</strong></th>
                                            <th><strong>QUANTITE</strong></th>                                            

                                        </tr>
                                    </thead>
                                    <tbody>             
                                        <%
                                                for (int i = 0; i < formules.length; i++) {%>
                                        <tr>

                                            <td><strong><%=formules[i].getStyle()%></strong></td>
                                            <td><strong><%=formules[i].getIdtaille()%></strong></td>
                                            <td><strong><%=formules[i].getIdcategorie()%></strong></td>
                                            <td><strong><%=formules[i].getQuantite()%></strong></td>

                                        </tr>
                                        <%    }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                            <a href="index.jsp"><button class="btn btn-primary">Retour</button></a>
                        </div>
                    </div> <!-- simple table -->
                </div> <!-- end section -->
            </div> <!-- .col-12 -->
        </div> <!-- .row -->
    </div> <!-- .container-fluid -->

</main> <!-- main -->




<%@ include file="footer.jsp" %>
