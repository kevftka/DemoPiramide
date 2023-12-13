const pyramidContainer = document.getElementById("pyramid");
let pyramidData = [];

document
    .getElementById("generate-pyramid")
    .addEventListener("click", generatePyramid);
document.getElementById("find-max-path").addEventListener("click", findMaxPath);
document
    .getElementById("reset-pyramid")
    .addEventListener("click", resetPyramid);

document.getElementById("save-pyramid").addEventListener("click", savePyramid);


function generatePyramid() {
    const pyramidHeight = parseInt(
        document.getElementById("pyramid-height").value
    );
    pyramidData = [];

    for (let i = 1; i <= pyramidHeight; i++) {
        let temp = [];
        for (let j = 1; j <= i; j++) {
            const randomNumber = Math.floor(Math.random() * 98) + 1;
            pyramidData.push(randomNumber);
        }
        
    }
    showUI();
}

function showUI(){
    const pyramidHeight = parseInt(
        document.getElementById("pyramid-height").value
    );
    pyramidContainer.innerHTML = "";

    for (let i = 1; i <= pyramidHeight; i++) {
        const row = document.createElement("div");
        row.className = "row";
        for (let j = 1; j <= i; j++) {
            const brick = document.createElement("div");
            brick.className = "brick";
            const randomNumber = pyramidData[i+j-2];
            brick.innerText = randomNumber;
            row.appendChild(brick);
        }

        pyramidContainer.appendChild(row);
    }
}

function findMaxPath() {
    if (pyramidData.length === 0) {
        alert("Primero genera una pirámide.");
        return;
    }

    const path = document.querySelectorAll(".path");
    path.forEach((brick) => brick.classList.remove("path"));

    let currentIndex = 0;
    let maxPathSum = 0;
    let maxPath = [];

    maxPathSum += pyramidData[0];
    maxPath.push(pyramidData[0]);
    pyramidContainer.children[0].children[currentIndex].classList.add("path");
    let Leftcount = 0;
    let rightcount = 0;
    for (let i = 1; i < pyramidData.length; i++) {
        if (i + pyramidContainer.children.length < pyramidData.length) {
            const leftValue = pyramidData[i + currentIndex];
            const rightValue = pyramidData[i + currentIndex + 1];

            if (leftValue > rightValue) {
                maxPathSum += leftValue;
                maxPath.push(pyramidData[i + currentIndex]);

                pyramidContainer.children[i].children[
                    i - i + rightcount
                ].classList.add("path");
                currentIndex += i;
                Leftcount += 1;
            } else if (leftValue < rightValue) {
                maxPathSum += rightValue;
                maxPath.push(pyramidData[i + currentIndex + 1]);

                pyramidContainer.children[i].children[
                    i - Leftcount
                ].classList.add("path");
                currentIndex += i + 1;
                rightcount += 1;
            } else if (leftValue == rightValue) {
                maxPathSum += rightValue;
                maxPath.push(pyramidData[i + currentIndex + 1]);

                pyramidContainer.children[i].children[
                    i - Leftcount
                ].classList.add("path");
                currentIndex += i + 1;
                rightcount += 1;
            }
        } else {
            break;
        }
        document.getElementById(
            "max-path-sum"
        ).innerText = `Suma de la Ruta con Mayor Peso: ${maxPathSum}`;
    }
}

function resetPyramid() {
    pyramidData = [];
    pyramidContainer.innerHTML = "";
    document.getElementById("max-path-sum").innerText =
        "Suma de la Ruta con Mayor Peso:";
    document.getElementById("pyramid-height").value = "5";
}

function savePyramid() {
    // Check if a pyramid has been generated
    if (pyramidData.length === 0) {
      alert("Primero genera una pirámide.");
      return;
    }
  
    // Make an AJAX request to save the pyramid with the path
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/save-pyramid", true);
    xhr.setRequestHeader("Content-Type", "application/json");
  
    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status === 200) {
        alert("Pyramid saved successfully!");
      }
    };
  
    const requestBody = {
      pyramidData: pyramidData,
      height: parseInt(document.getElementById("pyramid-height").value),
      maxPathSum: parseInt(document.getElementById("max-path-sum").innerText.split(":")[1].trim())
    };
  
    xhr.send(JSON.stringify(requestBody));
  }
  

// Function to open the history modal
document.getElementById("view-history").addEventListener("click", openHistoryModal);
function openHistoryModal() {
    const historyModal = document.getElementById("history-modal");
    const historyContent = document.getElementById("history-content");

    // Make an AJAX request to get the stored pyramids
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "/stored-pyramids", true);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const storedPyramids = JSON.parse(xhr.responseText);

            // Generate HTML content for the history modal
            let historyHTML = "<h2>Historial de Pirámides</h2><ul style='list-style-type: none;'>";

            storedPyramids.forEach((pyramid, index) => {
                historyHTML += `<li style="margin-top: 10px;"><button onclick="viewPyramid(${index})">Ver Pirámide ${index + 1}</button></li>`;
            });

            historyHTML += "</ul>";

            // Set the content and display the modal
            historyContent.innerHTML = historyHTML;
            historyModal.style.display = "block";
        }
    };

    xhr.send();
}

function viewPyramid(index) {
    // Make an AJAX request to get the stored pyramids
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "/stored-pyramids", true);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const storedPyramids = JSON.parse(xhr.responseText);

            // Get the pyramid data for the selected index
            const selectedPyramid = storedPyramids[index];

            document.getElementById("pyramid-height").value = String(selectedPyramid.height);
            pyramidData = selectedPyramid.pyramidData;
            document.getElementById("history-modal").style.display = "none";
            showUI();
            findMaxPath();
        }
    };

    xhr.send();
}


document.querySelector(".modal-content .close").addEventListener("click", function() {
    document.getElementById("history-modal").style.display = "none";
});

// Close the history modal if the user clicks outside the modal
window.addEventListener("click", function(event) {
    const historyModal = document.getElementById("history-modal");
    if (event.target === historyModal) {
        historyModal.style.display = "none";
    }
});


// Generar la pirámide inicial al cargar la página
generatePyramid();
