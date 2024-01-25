<%-- 
    Document   : taille.jsp
    Created on : 19 déc. 2023, 14:58:05
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
<% Taille[] taille = (Taille[]) request.getAttribute("tailles");
%>
<main role="main" class="main-content">

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Formulaire Taille</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletTaille">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Taille description</label>
                    <div class="col-sm-9">
                        <input type="text" name="nom" id="nom" class="form-control">
                    </div>
                </div>
                <div class="form-group mb-2">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
                <input type="hidden" value="1" name="type" id="type">

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
                    <th><strong>VOIR</strong></th>
                </tr>
            </thead>
            <tbody>             
                <% for (int i = 0; i < taille.length; i++) {%>
                <tr>
                    <td><strong><%=taille[i].getNom()%></strong></td>
                    <td><a href="ServletMateriel?materiel=<%=taille[i].getIdTaille()%>&nom=<%=taille[i].getNom()%>" ><button class="btn mb-2 btn-outline-warning"><span class="fe fe-24 fe-edit-3"></span></button></a></td>
                    <td><a><button class="btn mb-2 btn-outline-danger btn mb-2" data-toggle="modal" data-target="#defaultModal"><span class="fe fe-24 fe-x-circle"></span></button></a></td>
                    <td><a href="ServletTravailOuvrier?taille=<%=taille[i].getIdTaille()%>"><button class="btn mb-2 btn-outline-success"><span class="fe fe-24 fe-more-vertical"></span></button></a></td>
            <div class="modal fade" id="defaultModal" tabindex="-1" role="dialog" aria-labelledby="defaultModalLabel" style="display: none;" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="defaultModalLabel">Validation</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">Voulez vous supprimer le materiel <%=taille[i].getNom()%></div>
                        <a href="ServletMateriel?materiel=<%=taille[i].getIdTaille()%>"><button type="button" class="btn mb-2 btn-primary">OUI</button></a>
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

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Nombre d'ouvrier(Taille standard)</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletTaille">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Nombre d'ouvriers</label>
                    <div class="col-sm-9">
                        <input type="text" name="nombre_ouvrier" id="nombre_ouvrier" class="form-control">
                    </div>
                </div>
                <div class="form-group mb-2">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
                <input type="hidden" value="2" name="type" id="type">
                </div>
            </form>
        </div>
    </div>
</main> <!-- main -->
<%@ include file="footer.jsp" %>
