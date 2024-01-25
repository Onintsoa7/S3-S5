<%-- 
    Document   : InsertionFournissuer
    Created on : 11 janv. 2024, 14:44:08
    Author     : Chan Kenny
--%>


<%@page import="Model.Ouvrier"%>
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
<%
    Ouvrier[] ouvriers = (Ouvrier[]) request.getAttribute("ouvriers");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<main role="main" class="main-content">

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Formulaire Taux horaire Ouvrier</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletTauxHoraire">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Ouvrier</label>
                    <div class="col-sm-9">
                        <select name="ouvrier"  id="ouvrier" class="form-control">
                            <% for (int i = 0; i < ouvriers.length; i++) {%>
                            <option value="<%=ouvriers[i].getId_ouvrier() %>">
                                <%=ouvriers[i].getNom()%>
                            </option>
                            <%}%>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Valeur</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="valeur" name="valeur" placeholder="0">
                    </div>
                </div>
                <div class="form-group mb-2">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
            </form>
        </div>
    </div>

</main> <!-- main -->
<%@ include file="footer.jsp" %>
