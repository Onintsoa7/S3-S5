<%-- 
    Document   : formule.jsp
    Created on : 19 déc. 2023, 15:43:04
    Author     : Chan Kenny
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@page import="Model.*" %>
<% Categorie[] categories = (Categorie[]) request.getAttribute("categories");
    String nomStyle = (String) request.getAttribute("nom");
    Taille[] tailles = (Taille[]) request.getAttribute("tailles");
    StyleMateriel[] stylemateriels = (StyleMateriel[]) request.getAttribute("stylemateriels");
    out.println(request.getAttribute("idstyle"));
%>

<main role="main" class="main-content">
    <form action="Servletformule" method="post">
        <div class="container-fluid">
            <div class="row justify-content-center">
                <div class="col-12">
                    <div class="form-group row">
                        <label for="inputEmail3" class="col-sm-3 col-form-label">Style</label>
                        <div class="col-sm-9">
                            <select name="categorie"  id="categorie" class="form-control">
                                <% for (int i = 0; i < categories.length; i++) {%>
                                <option value="<%=categories[i].getIdCategorie()%>">
                                    <%=categories[i].getNom()%>
                                </option>
                                <%} %>
                            </select>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputEmail3" class="col-sm-3 col-form-label">Style</label>
                        <div class="col-sm-9">
                            <select name="taille"  id="taille" class="form-control">
                                <% for (int i = 0; i < tailles.length; i++) {%>
                                <option value="<%=tailles[i].getIdTaille()%>">
                                    <%=tailles[i].getNom()%>
                                </option>
                                <%}%>
                            </select>
                        </div>
                    </div>
                    <h2 class="mb-2 page-title">LISTE MATERIEL POUR LE STYLE <%= nomStyle%></h2>
                    <div class="row my-4">
                        <!-- Small table -->
                        <div class="col-md-12">
                            <div class="card shadow">
                                <div class="card-body">
                                    <!-- table -->
                                    <table class="table datatables" id="dataTable-1">
                                        <thead>
                                            <tr>
                                                <th><strong>Materiels</strong></th>
                                                <th><strong>Quantite</strong></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (int i = 0; i < stylemateriels.length; i++) {%>
                                            <tr>

                                                <td><strong><%=stylemateriels[i].getMateriel().getNom()%></strong></td>
                                                <td>
                                                    <input type="number" class="form-control" id="<%=stylemateriels[i].getIdStyleMateriel()%>" name="<%=stylemateriels[i].getIdStyleMateriel()%>" placeholder="quantite">
                                                </td>

                                            </tr>
                                            <%    }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div> <!-- simple table -->
                    </div> <!-- end section -->
                </div> <!-- .col-12 -->
                <input type="hidden" class="form-control" id="style" name="style" value="<%=request.getAttribute("idstyle") %>" placeholder="quantite">

                <button type="submit"  class="btn btn-primary">Valider</button>
            </div> <!-- .row -->
        </div> <!-- .container-fluid -->
    </form> 
    <a href="ServletListeStyle"><button class="btn btn-primary">Retour</button></a>
</main> <!-- main -->
<%@ include file="footer.jsp" %>