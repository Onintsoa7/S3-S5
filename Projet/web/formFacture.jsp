<%-- 
    Document   : formFacture
    Created on : 25 janv. 2024, 06:52:21
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.*" %>
<%@ include file="header.jsp" %>

<% Client[] clients = (Client[]) request.getAttribute("clients");
Mere[] meres = (Mere[]) request.getAttribute("meres");
Remise[] remises = (Remise[]) request.getAttribute("remises");
%>
<main role="main" class="main-content">
    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">VENTE</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletFacturation">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Client</label>
                    <div class="col-sm-9">
                        <select name="client"  id="client" class="form-control">
                            <% for (int i = 0; i < clients.length; i++) {%>
                            <option value="<%=clients[i].getId_client()%>">
                                <%= clients[i].getNom()%>
                            </option>
                            <%}%>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-2 col-form-label">Date de vente</label>
                    <div class="col-sm-3">
                        <input class="form-control" type="date" name="date" id="date">
                    </div>
                </div>
                <hr>
                <strong class="card-title">PRODUITS</strong>
                <hr>
                <div class="form-group row">
                    <div id="dynamicInputs" style="margin-left: 3pc">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-9">
                        <button type="button" id="addButton" class="btn btn-primary">
                            <i class="fe fe-plus-circle"></i>
                        </button>
                        <button type="button" id="removeButton" class="btn btn-primary">
                            <i class="fe fe-minus-circle"></i>
                        </button>
                    </div>
                </div>
                <hr>
                <strong class="card-title">REMISES</strong>
                <hr>

                <div class="form-group row">
                    <div id="dynamicRemises" style="margin-left: 3pc">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-9">
                        <button type="button" id="addRemise" class="btn btn-primary">
                            <i class="fe fe-plus-circle"></i>
                        </button>
                        <button type="button" id="removeRemise" class="btn btn-primary">
                            <i class="fe fe-minus-circle"></i>
                        </button>
                    </div>
                </div>
                <hr>
                <strong class="card-title">PAIMENT</strong>
                <hr>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Montant payé</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="montant_paye" name="montant_paye" value="0" placeholder="Montant">
                    </div>
                </div>
                <div class="form-group mb-2">
                    <button type="submit" class="btn btn-primary">Ajouter au panier</button>
                </div>
            </form>
        </div>
    </div>
</main>
<script>
    document.addEventListener("DOMContentLoaded", function () {
// Initialize counter for unique input names
        let counter = 1;
        let counter1 = 1;

        function addRemise() {
            const dynamicInputs = document.getElementById("dynamicRemises");

            const newInputs = document.createElement("div");
            newInputs.className = "form-group row";


            const inputSymptome = document.createElement("div");
            inputSymptome.className = "col-sm-8";
            const symptomeInput = document.createElement("select");
            symptomeInput.type = "text";
            symptomeInput.className = "form-control";
            symptomeInput.name = "remise[]";  // Corrected name here
            symptomeInput.style.width = "30pc";


    <% for (int i = 0; i < remises.length; i++) {%>
            const optionMeuble<%= i %> = document.createElement("option");
            optionMeuble<%= i %>.value = "<%= remises[i].getId_remise()%>";
            optionMeuble<%= i %>.textContent = "<%= remises[i].getNom()%>";
            symptomeInput.appendChild(optionMeuble<%= i %>);
    <%} %>
            inputSymptome.appendChild(symptomeInput);
            dynamicInputs.appendChild(newInputs);
            newInputs.appendChild(inputSymptome);

// Append newInputs div to dynamicInputs container
            dynamicInputs.appendChild(newInputs);
            counter1 ++;
        }

        function addInput() {
            const dynamicInputs = document.getElementById("dynamicInputs");

            const newInputs = document.createElement("div");
            newInputs.className = "form-group row";

// Ajouter le premier ensemble d'éléments (symptome)
            const labelSymptome = document.createElement("label");
            labelSymptome.className = "col-sm-8 col-form-label";
            labelSymptome.textContent = "Mere";

            const inputSymptome = document.createElement("div");
            inputSymptome.className = "col-sm-8";
            const symptomeInput = document.createElement("select");
            symptomeInput.type = "text";
            symptomeInput.className = "form-control";
            symptomeInput.name = "mere[]";  // Corrected name here
            symptomeInput.style.width = "30pc";

// Populate the select with options
    <% for (int i = 0; i < meres.length; i++) {%>
            const optionMeuble<%= i %> = document.createElement("option");
            optionMeuble<%= i %>.value = "<%= meres[i].getIdmere()%>";
            optionMeuble<%= i %>.textContent = "<%= meres[i].getIdcategorie().getNom() + "-" + meres[i].getIdstyle().getNom() + "(" + meres[i].getIdtaille().getNom() + ")" %>";
            symptomeInput.appendChild(optionMeuble<%= i %>);
    <%} %>

// Ajouter le deuxième ensemble d'éléments (Quantite)
            const labelQuantite = document.createElement("label");
            labelQuantite.className = "col-sm-8 col-form-label";
            labelQuantite.textContent = "Quantite";

            const inputQuantite = document.createElement("div");
            inputQuantite.className = "col-sm-8";
            const QuantiteInput = document.createElement("input");
            QuantiteInput.type = "text";
            QuantiteInput.className = "form-control";
            QuantiteInput.name = "quantite[]";
            QuantiteInput.placeholder = "0";
            QuantiteInput.style.width = "30pc";

// Append elements to newInputs div
            inputSymptome.appendChild(symptomeInput);
            inputQuantite.appendChild(QuantiteInput);
            newInputs.appendChild(labelSymptome);
            newInputs.appendChild(inputSymptome);
            newInputs.appendChild(labelQuantite);
            newInputs.appendChild(inputQuantite);

// Append newInputs div to dynamicInputs container
            dynamicInputs.appendChild(newInputs);

// Increment the counter for unique names
            counter++;
        }
// Function to remove the last set of input elements
        function removeInput() {
            const dynamicInputs = document.getElementById("dynamicInputs");
            const lastInputs = dynamicInputs.lastElementChild;

// Make sure at least one set of input elements is present
            if (dynamicInputs.childElementCount > 0) {
                dynamicInputs.removeChild(lastInputs);
            }
        }

// Function to remove the last set of input elements
        function removeRemise() {
            const dynamicInputs = document.getElementById("dynamicRemises");
            const lastInputs = dynamicInputs.lastElementChild;

// Make sure at least one set of input elements is present
            if (dynamicInputs.childElementCount > 0) {
                dynamicInputs.removeChild(lastInputs);
            }
        }

// Event listeners for buttons
        document.getElementById("addButton").addEventListener("click", addInput);
        document.getElementById("removeButton").addEventListener("click", removeInput);

        document.getElementById("addRemise").addEventListener("click", addRemise);
        document.getElementById("removeRemise").addEventListener("click", removeRemise);
    });
</script>
<%@ include file="footer.jsp" %>
