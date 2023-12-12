<%-- 
    Document   : listeStyleMateriel
    Created on : 12 déc. 2023, 17:22:51
    Author     : Chan Kenny
--%>


<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<%@page import="Model.*" %>
<% StyleMateriel[] stylemateriels = (StyleMateriel[]) request.getAttribute("stylemateriels");%>
<main role="main" class="main-content">

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Formulaire matériels par style</strong>
        </div>
        <div class="card-body">
            <table border="1">
                <tr>
                    <th>nomMateriel</th>
                </tr>
                <%
                                        for (int i = 0; i < stylemateriels.length; i++) {%>
                <tr>

                    <td><%= stylemateriels[i].getMateriel().getNom()%></td>

                </tr>
                <%    }
                %>
            </table>
        </div>
    </div>
</main> 







<%@ include file="footer.jsp" %>
