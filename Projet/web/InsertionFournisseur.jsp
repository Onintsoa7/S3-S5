<%-- 
    Document   : InsertionFournissuer
    Created on : 11 janv. 2024, 14:44:08
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

<main role="main" class="main-content">

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Formulaire Fournisseur</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletFournisseurs">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Nom</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="nom" name="nom" placeholder="nom">
                    </div>
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Adresse</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="adresse" name="adresse" placeholder="adresse">
                    </div>
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

</main> <!-- main -->
<%@ include file="footer.jsp" %>
