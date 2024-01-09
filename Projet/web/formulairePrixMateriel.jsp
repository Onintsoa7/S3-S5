<%-- 
    Document   : formStyleMateriel
    Created on : 12 déc. 2023, 16:33:51
    Author     : Chan Kenny
--%>
<%-- Document : formCategorie Created on : 12 déc. 2023, 15:54:58 Author : Chan Kenny --%>

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

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<%@page import="Model.*" %>
<% Materiel[] materiels = (Materiel[]) request.getAttribute("materiels");
    Style[] styles = (Style[]) request.getAttribute("styles"); %>
<main role="main" class="main-content">

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Formulaire matériels par style</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletPrixMateriel                                  ">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Style</label>
                    <div class="col-sm-9">
                        <select name="style"  id="style" class="form-control">
                            <% for (int i = 0; i < styles.length; i++) {%>
                            <option value="<%=styles[i].getIdStyle()%>">
                                <%=styles[i].getNom()%>
                            </option>
                            <%}%>
                        </select>
                        <label for="inputEmail3" class="col-sm-3 col-form-label">Prix</label>
                        <div class="col-sm-9">
                            <input type="number" class="form-control" id="max" name="prix" placeholder="0">
                        </div>
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

