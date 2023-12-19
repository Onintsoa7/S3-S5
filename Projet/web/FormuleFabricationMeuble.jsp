<%-- 
    Document   : FormuleFabricationMeuble
    Created on : 19 déc. 2023, 14:42:32
    Author     : Chan Kenny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
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
                            <%}%>
                        </select>
                    </div>
                    <div class="form-group mb-3">
                        <label for="example-time">Time</label>
                        <input class="form-control" id="example-time" type="time" name="time">
                    </div>
                    <div class="form-group mb-3">
                        <label for="example-week">Week</label>
                        <input class="form-control" id="example-week" type="week" name="week">
                    </div>
                    <div class="form-group mb-3">
                        <label for="example-number">Number</label>
                        <input class="form-control" id="example-number" type="number" name="number">
                    </div>
                    <div class="form-group mb-3">
                        <label for="example-color">Color</label>
                        <input class="form-control" id="example-color" type="color" name="color" value="#28a745">
                    </div>
                </div>
                <div class="form-group mb-2">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
            </form>
        </div>
    </body>
</html>

