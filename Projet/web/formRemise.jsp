<%-- 
    Document   : formRemise
    Created on : 26 janv. 2024, 13:15:37
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<main role="main" class="main-content">

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Formulaire Remise</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletRemise">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Nom du Genre</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="nom" name="nom" placeholder="Nom">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Valeur</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" value="0" id="valeur" name="valeur" placeholder="Valeur">
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
