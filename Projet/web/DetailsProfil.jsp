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
    Employer[] emps = (Employer[]) request.getAttribute("emp");
%>
<main role="main" class="main-content">
    <div class="card-body">
        <h2>Details</h2>
        <table class="table datatables" id="dataTable-1">
            <thead>
                <tr>
                    <th><strong>Nom</strong></th>
                    <th><strong>Prenom</strong></th>
                    <th><strong>Ancienneté</strong></th>
                    <th><strong>Salaire</strong></th>
                </tr>
            </thead>
            <tbody>             
                <% for (int i = 0; i < emps.length; i++) {%>
                <tr>
                    <td><strong><%=emps[i].getNom()%></strong></td>
                    <td><strong><%=emps[i].getPrenom()%></strong></td>
                    <td><strong><%=emps[i].getAnciennete()%></strong></td>
                    <td><strong><%=emps[i].getSalaire()%> AR</strong></td>
                </tr>
                <%    }
                %>
            </tbody>
        </table>
    </div>
</main> <!-- main -->

<%@ include file="footer.jsp" %>
