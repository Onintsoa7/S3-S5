<%-- 
    Document   : DetailsFacture
    Created on : 27 janv. 2024, 14:41:14
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@page import="Model.*" %>
<%
    V_facture_remise[] v_facture_remise_tab = (V_facture_remise[]) request.getAttribute("remise");
    V_detail_paiement_facture[] v_detail_paiement_facture_tab = (V_detail_paiement_facture[]) request.getAttribute("paiement");
    V_detail_facture[] v_detail_facture_tab = (V_detail_facture[]) request.getAttribute("facture");
    Client client = (Client)request.getAttribute("client");
%>

<div>
    <div class="form-group mb-2" style="margin-left: 317px;">
        <button type="submit" name="plus" id="plus" class="btn btn-primary">Exporter PDF</button>
    </div>
    <main role="main" id="main" class="main-content">
        <div class="card-header">
            <h1 class="card-title">FACTURE N° <%=v_detail_paiement_facture_tab[0].getDate_paiement().toString().replace("-", "")+"-"+v_detail_paiement_facture_tab[0].getId_vente().replace("vente", "") %></h1>
        </div>
        <div class="card-body">
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-3 col-form-label">E-Mobilier</label>
            </div>
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-3 col-form-label">Anoharanofotsy TANA 101</label>
            </div>
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-3 col-form-label">1767</label>
            </div>
        </div>
        <div class="card-header">
            <strong class="card-title">FACTURé à :</strong>
        </div>
        <div class="card-body">
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-3 col-form-label"><%=client.getNom()%></label>
            </div>
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-3 col-form-label"><%=client.getAdresse()%></label>
            </div>
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-3 col-form-label"><%=client.getContact()%></label>
            </div>

            <div class="card-body">
                <table class="table">
                    <thead>
                        <tr>
                            <th><strong>Produit</strong></th>
                            <th><strong>Quantite</strong></th>
                            <th><strong>Prix unitaire</strong></th>
                            <th><strong>Montant</strong></th>
                        </tr>
                    </thead>
                    <tbody>             
                        <% for (int i = 0; i < v_detail_facture_tab.length; i++) {
                        %>
                        <tr>
                            <td><%=v_detail_facture_tab[i].getDescription()%> </td>
                            <td><%=v_detail_facture_tab[i].getQuantite()%></td>
                            <td><%=v_detail_facture_tab[i].getPrix_unitaire()%> AR</td>
                            <td><%=v_detail_facture_tab[i].getMontant()%> AR</td>
                        </tr>
                        <%    }
                        %>
                        <tr>
                            <td><strong>TOTAL</strong></td>
                            <td></td>
                            <td></td>
                            <td><%=v_detail_paiement_facture_tab[0].getMontant_vente()%> AR</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
</div>


<script>
    document.getElementById('plus').addEventListener('click', function () {

        // Get the HTML content of the section
        var sectionHTML = document.getElementById('main').innerHTML;

        // Create a new window
        var printWindow = window.open('', '', 'height=1000,width=1000');

        // Write the HTML content to the new window
        printWindow.document.write(sectionHTML);

        // Include CSS files from online links in the new window
        var cssLinks = [
            'https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css',
            'https://cdn.jsdelivr.net/npm/bootstrap-icons@1.17.0/dist/css/bootstrap-icons.css',
            'https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css',
            'https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css',
            'https://cdn.jsdelivr.net/npm/templatemo@1.0.0/dist/css/templatemo-pod-talk.min.css',
            'https://yourdomain.com/css/login.css' // Replace with your actual online link
        ];

        cssLinks.forEach(function (link) {
            printWindow.document.write('<link rel="stylesheet" href="' + link + '">');
        });

        // Additional styling for the new window
        printWindow.document.write('<style>');
        printWindow.document.write('body { padding: 20px; }');
        printWindow.document.write('.footer-text { position: absolute; left: 20px; bottom: 20px; }');
        // Add more custom styles if needed
        printWindow.document.write('</style>');

        printWindow.document.close();

        // Print the new window
        printWindow.print();
    });

</script>

<%@ include file="footer.jsp" %>

