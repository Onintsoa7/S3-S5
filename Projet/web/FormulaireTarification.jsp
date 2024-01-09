<%-- 
    Document   : FormulaireTarification
    Created on : 9 janv. 2024, 15:19:29
    Author     : Chan Kenny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <main role="main" class="main-content">

            <div class="card shadow mb-4">
                <div class="card-header">
                    <strong class="card-title">Formulaire Tarifaire</strong>
                </div>
                <div class="card-body">
                    <form method="post" action="ServletTarification">
                        <h2>Choisissez le prix</h2>
                        <div class="form-group row">
                            <label for="inputEmail3" class="col-sm-3 col-form-label">Prix Minimum</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="min" name="min" placeholder="0">
                            </div>
                            <label for="inputEmail3" class="col-sm-3 col-form-label">Prix Maximum</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="max" name="max" placeholder="10000">
                            </div>
                        </div>
                        <div class="form-group mb-2">
                            <button type="submit" class="btn btn-primary">Valider</button>
                        </div>
                    </form>
                </div>
            </div>
    </body>
</html>
<%@ include file="footer.jsp" %>
