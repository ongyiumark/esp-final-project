let num_data = 0;

// Get the URL from the input
function grabURL() {
    let res = document.getElementById("url-input").value;
    return res;
}

function grabData() {
    let res = {};
    for (let i = 1; i <= num_data; i++) {
        let dataKey = document.getElementById(`data-key-${i}`);
        let dataVal = document.getElementById(`data-value-${i}`);
        if (!dataKey.value.trim()) continue;
        res[dataKey.value.trim()] = dataVal.value.trim();
    }
    return res;
}

// Add a data row to the table
function addDataRow() {
    num_data += 1;
    let dataTable = document.getElementById("data-table");
    let dataRow = document.createElement("tr");

    let keyRow = document.createElement("td");
    let keyInput = document.createElement("input");
    keyInput.setAttribute("type", "text");
    keyInput.setAttribute("class", "data-input");
    keyInput.setAttribute("id", `data-key-${num_data}`);
    keyRow.appendChild(keyInput);

    let valueRow = document.createElement("td");
    let valueInput = document.createElement("input");
    valueInput.setAttribute("type", "text");
    valueInput.setAttribute("class", "data-input");
    valueInput.setAttribute("id", `data-value-${num_data}`);
    valueRow.appendChild(valueInput);

    dataRow.appendChild(keyRow);
    dataRow.appendChild(valueRow);

    dataTable.appendChild(dataRow);
}

// Display to output
function parseResponse(res) {
    let output = document.getElementById("output-text");
    if ((typeof res) == 'string') output.innerText = res;
    else output.innerText = JSON.stringify(res, null, 2);
}


function init() {
    // Set url prefix based on helper.js
    document.getElementById("url-input-prefix").innerHTML = BASE_URL

    // Set up data table
    num_data = 0;
    let dataTable = document.getElementById("data-table");
    while (dataTable.childNodes.length > 2) {
        dataTable.removeChild(dataTable.lastChild);
    }
    addDataRow();

    // Set up add data button
    document.getElementById("add-data-btn").addEventListener("click", function() {
        addDataRow();
        console.log(`There are now ${num_data} rows of data.`)
    });

    
    // Set up get button
    document.getElementById("get-btn").addEventListener("click", function() {
        if (!grabURL().trim()) return;        

        let url = `${BASE_URL}${grabURL()}`;
        let data = grabData();

        // Display URL
        document.getElementById("output-url").innerText = `URL: ${url}?${new URLSearchParams(data)}`;
        
        // Display response
        getData(url, data)
            .then((data) => {
                parseResponse(data);
            })
            .catch((error) => {
                parseResponse(error.message);
            })
    });


    // Set up post button
    document.getElementById("post-btn").addEventListener("click", function() {
        if (!grabURL().trim()) return;

        let url = `${BASE_URL}${grabURL()}`;
        let data = grabData();

        // Display URL
        document.getElementById("output-url").innerText =  `URL: ${url}`;

        // Display response
        postData(url, data)
            .then((data) => {
                parseResponse(data);
            })
            .catch((error) => {
                console.log(error);
                parseResponse(error.message);
            })
    });
}


init();
