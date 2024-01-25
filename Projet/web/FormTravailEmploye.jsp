<%-- 
    Document   : FormTravailEmploye
    Created on : 23 janv. 2024, 14:33:54
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

<%
    Employer[] employes = (Employer[])request.getAttribute("employe");
    TypeOuvrier[] typeOuvrier = (TypeOuvrier[]) request.getAttribute("typeOuvrier");
%>

<main role="main" class="main-content">

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Formulaire travail employe</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletFournisseurs">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Employe</label>
                    <select name="style"  id="style" class="form-control">
                        <% for (int i = 0; i < employes.length; i++) {%>
                        <option value="<%=employes[i].getIdStyle()%>">
                            <%=employes[i].getNom()%>
                        </option>
                        <%}%>
                    </select>
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Type Ouvrier</label>
                    <select name="style"  id="style" class="form-control">
                        <% for (int i = 0; i < typeOuvrier.length; i++) {%>
                        <option value="<%=typeOuvrier[i].getIdStyle()%>">
                            <%=typeOuvrier[i].getNom()%>
                        </option>
                        <%}%>
                    </select>
                    <label for="inputEmail3" class="col-sm-3 col-form-label">date</label>
                    <div class="col-sm-9">
                        <input type="date" class="form-control" id="date" name="date">
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
