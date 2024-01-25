<%-- 
    Document   : fabrication
    Created on : 11 janv. 2024, 14:49:57
    Author     : Chan Kenny
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.*" %>
<%
    Mere[] description = (Mere[])request.getAttribute("meubles");
%>
<%@include file="header.jsp" %>
<main role="main" class="main-content">
    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Formulaire de fabrication</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletFabrication">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-2 col-form-label">Fournisseur</label>
                    <div class="col-sm-3">
                        <select name="id_mere"  id="description" class="form-control">
                            <% for (int i = 0; i < description.length; i++) {%>
                            <option value="<%=description[i].getIdmere()%>">
                                <%= description[i].getIdcategorie().getNom() + "-" + description[i].getIdstyle().getNom() + "(" + description[i].getIdtaille().getNom() + ")" %>
                            </option>
                            <%}%>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-1 col-form-label">Quantite</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="quantite" name="quantite" placeholder="0" style="width: 31pc">
                    </div>   
                </div>
                <input type="hidden" value="1" name="type" id="type">

                <div class="form-group mb-2">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
            </form>
        </div>
    </div>

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Prix de vente</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletFabrication">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-2 col-form-label">Fournisseur</label>
                    <div class="col-sm-3">
                        <select name="id_mere"  id="id_mere" class="form-control">
                            <% for (int i = 0; i < description.length; i++) {%>
                            <option value="<%=description[i].getIdmere()%>">
                                <%= description[i].getIdcategorie().getNom() + "-" + description[i].getIdstyle().getNom() + "(" + description[i].getIdtaille().getNom() + ")" %>
                            </option>
                            <%}%>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-1 col-form-label">Pourcentage du prix de revient</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="pourcentage" name="pourcentage" placeholder="%" style="width: 31pc">
                    </div>   
                </div>
                <input type="hidden" value="2" name="type" id="type">
                <div class="form-group mb-2">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
            </form>
        </div>
    </div>
</main> 
<%@include file="footer.jsp" %>

