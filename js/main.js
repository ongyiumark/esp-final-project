// Helper function for GET method requests
async function getData(url='', params={}) {
    const response = await fetch(url+'?'+new URLSearchParams(params));
    const text = await response.text();

    // Try to parse as JSON, otherwise return the raw text.
    if (response.ok) {
        try {
            return JSON.parse(text);
        } catch(err) {
            return text;
        }
    }

    throw new Error(text);
}

// Helper function for POST method requests
async function postData(url='', data={}) {
    // Building form body from JSON
    let formBody = [];
    for (let property in data) {
        let encodedKey = encodeURIComponent(property);
        let encodedValue = encodeURIComponent(data[property]);
        formBody.push(encodedKey + "=" + encodedValue);
    }
    formBody = formBody.join("&");

    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        body: formBody
    });
    const text = await response.text();

    // Try to parse as JSON, otherwise return the raw text.
    if (response.ok) {
        try {
            return JSON.parse(text);
        } catch(err) {
            return text;
        }
    }

    throw new Error(text);
}

function test() {
    // Add listener to test button
    document.getElementById("testBtn").addEventListener("click", function() {
        // Sample Data
        data = {
            stallName: "Good Taste",
            itemName: "Go Party Porkie",
            price: 110
        }

        const output = document.getElementById("testOut");

        getData('http://localhost:9999/food/list', data)
            .then((data) => {
                output.innerHTML = JSON.stringify(data);
            })
            .catch((error) => {
                output.innerHTML = error;
            });
    });
}

function init() {
    test();
}


init();
