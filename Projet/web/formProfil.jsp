<%-- 
    Document   : profil.jsp
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
<% Profil[] profil = (Profil[]) request.getAttribute("profils");
Employer[] employers = (Employer[]) request.getAttribute("employers");
Poste[] postes = (Poste[]) request.getAttribute("postes");
%>
<main role="main" class="main-content">

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Formulaire profil</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletProfil">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">profil description</label>
                    <div class="col-sm-3">
                        <input type="text" name="nom" id="nom" class="form-control">
                    </div>
                </div>
                <input type="hidden" value="1" name="type" id="type">
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
                    <th><strong>VOIR</strong></th>
                </tr>
            </thead>
            <tbody>             
                <% for (int i = 0; i < profil.length; i++) {%>
                <tr>
                    <td><strong><%=profil[i].getNom()%></strong></td>
                    <td><a href="#" ><button class="btn mb-2 btn-outline-warning"><span class="fe fe-24 fe-edit-3"></span></button></a></td>
                    <td><a><button class="btn mb-2 btn-outline-danger btn mb-2" data-toggle="modal" data-target="#defaultModal"><span class="fe fe-24 fe-x-circle"></span></button></a></td>
                    <td><a href="ServletProfil?profil=<%=profil[i].getId_profil()%>"><button class="btn mb-2 btn-outline-success"><span class="fe fe-24 fe-more-vertical"></span></button></a></td>
            <div class="modal fade" id="defaultModal" tabindex="-1" role="dialog" aria-labelledby="defaultModalLabel" style="display: none;" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="defaultModalLabel">Validation</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">Voulez vous supprimer le materiel <%=profil[i].getNom()%></div>
                        <a href="ServletMateriel?materiel=<%=profil[i].getId_profil()%>"><button type="button" class="btn mb-2 btn-primary">OUI</button></a>
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
            <strong class="card-title">Taux horaire par profil</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletProfil">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Taux horaire</label>
                    <div class="col-sm-3">
                        <input type="text" name="salaire" id="salaire" class="form-control">
                    </div>
                </div>
                <input type="hidden" value="2" name="type" id="type">
                <div class="form-group mb-2">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
        </div>
        </form>
    </div>
    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Embauche</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletProfil">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-2 col-form-label">Employer</label>
                    <div class="col-sm-3">
                        <select name="employer"  id="employer" class="form-control">
                            <% for (int i = 0; i < employers.length; i++) {%>
                            <option value="<%=employers[i].getId_employer()%>">
                                <%= employers[i].getNom() %>
                            </option>
                            <%}%>
                        </select>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-2 col-form-label">Poste</label>
                    <div class="col-sm-3">
                        <select name="poste"  id="poste" class="form-control">
                            <% for (int i = 0; i < postes.length; i++) {%>
                            <option value="<%=postes[i].getId_poste()%>">
                                <%= postes[i].getNom() %>
                            </option>
                            <%}%>
                        </select>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Karama</label>
                    <div class="col-sm-3">
                        <input type="text" name="karama" id="karama" class="form-control">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-2 col-form-label">Date d'embauche</label>
                    <div class="col-sm-3">
                        <input class="form-control" type="date" name="date" id="date">
                    </div>
                </div>
                <input type="hidden" value="3" name="type" id="type">
                <div class="form-group mb-2">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
        </div>
        </form>
    </div>

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Changement de grade</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletProfil">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-2 col-form-label">Type</label>
                    <div class="col-sm-3">
                        <select name="profil"  id="profil" class="form-control">
                            <% for (int i = 0; i < profil.length; i++) {%>
                            <option value="<%=profil[i].getId_profil()%>">
                                <%= profil[i].getNom() %>
                            </option>
                            <%}%>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Année minimum</label>
                    <div class="col-sm-3">
                        <input type="text" name="niveau" id="niveau" class="form-control">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-2 col-form-label">Date de modification</label>
                    <div class="col-sm-3">
                        <input class="form-control" type="date" name="date" id="date">
                    </div>
                </div>
                <input type="hidden" value="4" name="type" id="type">
                <div class="form-group mb-2">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
        </div>
        </form>
    </div>

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Paramètrage salaire par rapport à celui d' un ouvrier</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletProfil">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-2 col-form-label">Type</label>
                    <div class="col-sm-3">
                        <select name="profil"  id="profil" class="form-control">
                            <% for (int i = 0; i < profil.length; i++) {%>
                            <option value="<%=profil[i].getId_profil()%>">
                                <%= profil[i].getNom() %>
                            </option>
                            <%}%>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Paramètre</label>
                    <div class="col-sm-3">
                        <input type="text" name="niveau" id="niveau" class="form-control">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-2 col-form-label">Date de modification</label>
                    <div class="col-sm-3">
                        <input class="form-control" type="date" name="date" id="date">
                    </div>
                </div>
                <input type="hidden" value="5" name="type" id="type">
                <div class="form-group mb-2">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
        </div>
        </form>
    </div>
</main> <!-- main -->
<%@ include file="footer.jsp" %>
