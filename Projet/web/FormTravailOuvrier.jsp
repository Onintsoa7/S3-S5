<%-- 
    Document   : FormTravailOuvrier
    Created on : 16 janv. 2024, 15:08:18
    Author     : Chan Kenny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.*" %>
<%
    Profil[] ouvriers = (Profil[]) request.getAttribute("profils");
    Style[] styles = (Style[]) request.getAttribute("styles");
    String idTaille = (String) request.getAttribute("idTaille");
    float repetition = (float)request.getAttribute("repetition");
%>
<%@ include file="header.jsp" %>



<main role="main" class="main-content">

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Dure de travail par Ouvrier</strong>
        </div>
        <div class="card-header">
            <strong class="card-title">Nombre d'ouvriers au total = <%=repetition %> ouvriers</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletTravailOuvrier">
                <input type="hidden" id="id_taille" name="id_taille" value="<%=idTaille%>">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-4 col-form-label">Ouvrier</label>
                    <% for(int j = 0; j < ouvriers.length; j++){ %>
                    <div class="col-sm-9">
                        <input type="text" readonly value="<%=ouvriers[j].getNom()%>" class="form-control" placeholder="">
                        <hr>
                        <input type="text" class="form-control" id="<%=ouvriers[j].getId_profil()%>" name="<%=ouvriers[j].getId_profil()%>" placeholder="Nombre <%=ouvriers[j].getNom()%>">
                        <hr>
                    </div>
                    <% }%>
                </div>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Style</label>
                    <div class="col-sm-9">
                        <select name="id_style"  id="id_style" class="form-control">
                            <% for (int i = 0; i < styles.length; i++) {%>
                            <option value="<%=styles[i].getIdStyle()%>">
                                <%=styles[i].getNom()%>
                            </option>
                            <%}%>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Duree</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="duree" name="duree" placeholder="">
                    </div>
                </div>
                <input type="hidden" value="<%=repetition %>" name="repetition" id="repetition">
                <div class="form-group mb-2">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
            </form>
        </div>
    </div>

</main> <!-- main -->
<%@ include file="footer.jsp" %>
