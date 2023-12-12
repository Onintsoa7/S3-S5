<%-- 
    Document   : formStyleMateriel
    Created on : 12 déc. 2023, 16:33:51
    Author     : Chan Kenny
--%>
<%-- Document : formCategorie Created on : 12 déc. 2023, 15:54:58 Author : Chan Kenny --%>

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
            <form method="post" action="ServletFormStyleMateriel">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Style</label>
                    <div class="col-sm-9">
                        <select name="style"  id="style" class="form-control">
                            <% for (int i = 0; i < styles.length; i++) {%>
                            <option value="<%=styles[i].getIdStyle()%>">
                                <%=styles[i].getNom()%>
                            </option>
                            <%} %>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Matériels</label>
                    <div class="col-sm-9">
                        <% for (int i = 0; i < materiels.length; i++) {%> 
                        <input type="text" class="form-control" readonly="" value="<%= materiels[i].getNom() %>" style="width: 100px">
                        <input type="checkbox" id="materiel" name="materiel" value="<%= materiels[i].getIdMateriel()%>">
                        <%}%>
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
