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

// Load URL presets
function loadPresets() {
    if (!presets.length) return;

    let select = document.createElement("select");
    select.setAttribute("name", "presets");
    select.setAttribute("id", "preset-select")

    let defaultOption = document.createElement("option");
    defaultOption.setAttribute("value", "");
    defaultOption.setAttribute("selected", "");
    defaultOption.setAttribute("disabled", "");
    defaultOption.setAttribute("hidden", "");
    defaultOption.innerText = "Choose preset"
    select.appendChild(defaultOption);

    for (const preset of presets) {
        let option = document.createElement("option");
        option.setAttribute("value", preset['name']);
        option.innerText = preset['name'];
        select.appendChild(option);
    } 

    document.getElementById("preset-container").appendChild(select);

    // Add change listener
    select = document.getElementById("preset-select");
    select.addEventListener("change", function() {
        for (const preset of presets) {
            if (select.value == preset["name"]) {
                document.getElementById("url-input").value = preset["url"];

                // Adjust data table length to fit number of parameters
                let n = Object.keys(preset['data']).length;
                while(num_data < n) {
                    addDataRow();
                }
                let dataTable = document.getElementById("data-table");
                while (dataTable.childNodes.length > 2+n) {
                    dataTable.removeChild(dataTable.lastChild);
                    num_data -= 1;
                }

                let i = 1;
                for (let key in preset['data']) {
                    let dataKey = document.getElementById(`data-key-${i}`);
                    let dataVal = document.getElementById(`data-value-${i}`);
                    dataKey.value = key;
                    dataVal.value = preset['data'][key];
                    i += 1;
                }
            }
        } 
    }); 
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
        
        if (grabURL() == "image/show") {
            let get_url = `${url}?${new URLSearchParams(data)}`;

            fetch(get_url)
                .then(response => response.blob())
                .then(imageBlob => {
                    const imageObjectURL = URL.createObjectURL(imageBlob);
                    let img = document.createElement("img")
                    img.setAttribute("width", "80%")
                    img.src = imageObjectURL
                    let output = document.getElementById("output-text")
                    output.innerHTML = ""
                    output.appendChild(img)
                })
            return
        }

        // Display response
        getData(url, data)
            .then((data) => {
                parseResponse(data);
            })
            .catch((error) => {
                console.log(error.message)
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
                console.log(error.message)
                parseResponse(error.message);
            })
    });

    // Load presets
    loadPresets();
}


init();
