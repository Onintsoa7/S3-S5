<%-- 
    Document   : FormInsertionPersonnel
    Created on : 23 janv. 2024, 14:29:11
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
    Employer[] lesEmp = (Employer[]) request.getAttribute("lesemps");
%>
<main role="main" class="main-content">

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Formulaire personnel</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletEmployer">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Nom</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="nom" name="nom" placeholder="nom">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Prenom</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="prenom" name="prenom" placeholder="prenom">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">date</label>
                    <div class="col-sm-9">
                        <input type="date" class="form-control" id="date" name="date">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Adresse</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="adresse" name="adresse" placeholder="adresse">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Contact</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="contact" name="contact" placeholder="contact">
                    </div>
                </div>
                <div class="form-group mb-2">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
            </form>
        </div>
    </div>
    <div class="card-body">
        <!-- table -->
        <table class="table datatables" id="dataTable-1">
            <thead>
                <tr>
                    <th><strong>NOM</strong></th>
                    <th><strong>PRENOM</strong></th>
                    <th><strong>VOIR</strong></th>
                </tr>
            </thead>
            <tbody>             
                <% for (int i = 0; i < lesEmp.length; i++) {%>
                <tr>
                    <td><strong><%=lesEmp[i].getNom()%></strong></td>
                    <td><strong><%=lesEmp[i].getPrenom()%></strong></td>
                    <td><a href="ServletEmployer?idEmp=<%=lesEmp[i].getId_employer() %>"><button class="btn mb-2 btn-outline-success"><span class="fe fe-24 fe-more-vertical"></span></button></a></td>
                </tr>
                <%    }
                %>
            </tbody>
        </table>
    </div>
</main> <!-- main -->
<%@ include file="footer.jsp" %>
