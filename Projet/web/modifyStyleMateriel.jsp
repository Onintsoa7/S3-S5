<%-- 
    Document   : modifyStyleMateriel
    Created on : 18 déc. 2023, 17:28:46
    Author     : Chan Kenny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<%@page import="Model.*" %>
<% Materiel[] materiels = (Materiel[]) request.getAttribute("materiels");
    String stylemateriels = (String) request.getAttribute("style");
    String[] selected = (String[]) request.getAttribute("selected");
    String nomStyle = (String) request.getAttribute("nom");
%>
<main role="main" class="main-content">

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Formulaire matériels pour le style <%= nomStyle%></strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletModifyStyleMateriel">
                <input type="hidden" name="style" value="<%= stylemateriels%>">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Matériels</label>
                    <div class="col-sm-9">
                        <% for (int i = 0; i < materiels.length; i++) {%> 
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="custom-control-input" id="customCheck<%= i + 1%>" name="materiel" value="<%= materiels[i].getIdMateriel()%>" <%= selected[i]%>>
                            <label class="custom-control-label" for="customCheck<%= i + 1%>"><strong><%= materiels[i].getNom()%></strong></label>
                        </div>
                        <br>
                        <%}%>
                    </div>
                </div>
                <div class="form-group mb-2">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
            </form>
                    <a href="ServletListeStyle"><button class="btn btn-primary">Retour</button></a>   
        </div>
    </div>
</main> <!-- main -->
<%@ include file="footer.jsp" %>




