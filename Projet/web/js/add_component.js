function add_component(){
    document.addEventListener("DOMContentLoaded", function () {
        // Initialize counter for unique input names
        let counter = 1;

        // Function to add new input elements
        function addInput() {
            const dynamicInputs = document.getElementById("dynamicInputs");

            const newInputs = document.createElement("div");
            newInputs.className = "form-group row";

            const labelNumero = document.createElement("label");
            labelNumero.className = "col-sm-8 col-form-label";
            labelNumero.textContent = "Meuble";

            const inputNumero = document.createElement("div");
            inputNumero.className = "col-sm-8";
            const numeroInput = document.createElement("input");
            numeroInput.type = "text";
            numeroInput.className = "form-control";
            numeroInput.name = "numero[]";
            numeroInput.placeholder = "0";
            numeroInput.style.width = "30pc";

            const labelEtat = document.createElement("label");
            labelEtat.className = "col-sm-8 col-form-label";
            labelEtat.textContent = "Etat de la dent";

            const inputEtat = document.createElement("div");
            inputEtat.className = "col-sm-8";
            const etatInput = document.createElement("input");
            etatInput.type = "text";
            etatInput.className = "form-control";
            etatInput.name = "etat[]";
            etatInput.placeholder = "0";
            etatInput.style.width = "30pc";

            // Append elements to newInputs div
            inputNumero.appendChild(numeroInput);
            inputEtat.appendChild(etatInput);
            newInputs.appendChild(labelNumero);
            newInputs.appendChild(inputNumero);
            newInputs.appendChild(labelEtat);
            newInputs.appendChild(inputEtat);

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
            if (dynamicInputs.childElementCount > 1) {
                dynamicInputs.removeChild(lastInputs);
            }
        }

        // Event listeners for buttons
        document.getElementById("addButton").addEventListener("click", addInput);
        document.getElementById("removeButton").addEventListener("click", removeInput);
    });
}


