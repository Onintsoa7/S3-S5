<%-- 
    Document   : FormulaireTarification
    Created on : 9 janv. 2024, 15:19:29
    Author     : Chan Kenny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<main role="main" class="main-content">

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Formulaire Tarifaire</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletTarification">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Prix Minimum</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="min" name="min" placeholder="0..." style="width: 50pc">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Prix Maximum</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="max" name="max" placeholder="10000..."  style="width: 50pc">
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
            <strong class="card-title">Benefice</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletTarification">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">MIN</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="min" name="min" placeholder="0">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">MAX</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="max" name="max" placeholder="0">
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
<%@ include file="footer.jsp" %>
