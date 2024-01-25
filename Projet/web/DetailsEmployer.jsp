<%-- 
    Document   : formMateriel
    Created on : 12 déc. 2023, 15:54:38
    Author     : Chan Kenny
--%>

<style>
    /* Style pour masquer la fenêtre modale par défaut */
    .modal {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0,0,0,0.7);
        justify-content: center;
        align-items: center;
    }

    /* Style pour la boîte de dialogue */
    .modal-content {
        background-color: #fefefe;
        padding: 20px;
        border: 1px solid #888;
        width: 80%;
        max-width: 400px;
        text-align: center;
    }
</style>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@page import="Model.*" %>
<%
    Employer emp = (Employer) request.getAttribute("emp");
%>
<main role="main" class="main-content">

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Profil employer</strong>
        </div>
        <div class="card-body">
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-3 col-form-label">Nom: <%=emp.getNom() %></label>
            </div>
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-3 col-form-label">Prenom: <%=emp.getPrenom() %></label>
            </div>
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-3 col-form-label">Date-Naissance: <%=emp.getDate_de_naissance() %></label>
            </div>
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-3 col-form-label">Anne de travail:</label>
            </div>
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-3 col-form-label">Statut:</label>
            </div>
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-3 col-form-label">Taux:</label>
            </div>
        </div>
    </div>
</main> <!-- main -->

<%@ include file="footer.jsp" %>
