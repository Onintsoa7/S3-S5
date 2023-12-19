<%-- 
    Document   : formStyle
    Created on : 12 déc. 2023, 16:21:07
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
<% Style[] styles = (Style[]) request.getAttribute("style");
%>
<main role="main" class="main-content">

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Formulaire Style</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletStyle">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">nom</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="nom" name="nom" placeholder="nom">
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
                    <th><strong>MODIFIER</strong></th>
                    <th><strong>SUPPRIMER</strong></th>
                    <td><strong>Formule</strong></td>
                </tr>
            </thead>
            <tbody>             
                <% for (int i = 0; i < styles.length; i++) {%>
                <tr>
                    <td><strong><%=styles[i].getNom()%></strong></td>
                    <td><a href="ServletStyle?style=<%=styles[i].getIdStyle()%>&nom=<%=styles[i].getNom()%>" ><button class="btn mb-2 btn-outline-warning"><span class="fe fe-24 fe-edit-3"></span></button></a></td>
                    <td><a><button class="btn mb-2 btn-outline-danger btn mb-2" data-toggle="modal" data-target="#defaultModal"><span class="fe fe-24 fe-x-circle"></span></button></a></td>
                    <td><a href="Servletformule?style=<%=styles[i].getIdStyle()%>&nom=<%=styles[i].getNom()%>"><button class="btn mb-2 btn-outline-success"><span class="fe fe-24 fe-more-vertical"></span></button></a></td>
            <div class="modal fade" id="defaultModal" tabindex="-1" role="dialog" aria-labelledby="defaultModalLabel" style="display: none;" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="defaultModalLabel">Validation</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">Voulez vous supprimer le style <%=styles[i].getNom()%></div>
                        <a href="ServletStyle?style=<%=styles[i].getIdStyle()%>"><button type="button" class="btn mb-2 btn-primary">OUI</button></a>
                        <a><button type="button" class="btn mb-2 btn-secondary" data-dismiss="modal">NON</button></a>
                    </div>
                </div>
            </div>
            </tr>
            <%    }
            %>
            </tbody>
        </table>
    </div>
</main> <!-- main -->
<%@ include file="footer.jsp" %>
