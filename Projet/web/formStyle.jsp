<%-- 
    Document   : formStyle
    Created on : 12 déc. 2023, 16:21:07
    Author     : Chan Kenny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
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
      </main> <!-- main -->
<%@ include file="footer.jsp" %>
