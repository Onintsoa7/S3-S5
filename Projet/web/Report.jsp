<%-- 
    Document   : Report
    Created on : 28 janv. 2024, 11:51:06
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.*" %>
<%@ include file="header.jsp" %>
<main role="main" id="main" class="main-content">
    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">REPORT</strong>
        </div>
        <div class="form-group row">
            <label for="inputEmail3" class="col-sm-2 col-form-label">Date de report</label>
            <div class="col-sm-3">
                <input class="form-control" type="date" name="date" id="date">
            </div>
        </div>
        <div class="form-group row">
            <label for="inputEmail3" class="col-sm-2 col-form-label">Valeur</label>
            <div class="col-sm-3">
                <input class="form-control" type="text" name="valeur" id="valeur">
            </div>
        </div>
    </div>
</main>
<%@ include file="footer.jsp" %>
