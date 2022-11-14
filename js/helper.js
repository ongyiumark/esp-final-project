const BASE_URL = 'http://localhost:9999/'

// Helper function for GET method requests
async function getData(url='', params={}) {
    // Build query URL
    let n = Object.keys(params).length;
    let get_url = (n > 0 ? `${url}?${new URLSearchParams(params)}` : url);

    // Fetch URL
    const response = await fetch(get_url);

    // Try to parse as JSON, otherwise return as text
    if (response.ok) {
        let isJSON = response.headers.get('content-type').includes('application/json');
        return (isJSON ? response.json() : response.text());
    }

    throw new Error(await response.text());
}

// Helper function for POST method requests
async function postData(url='', data={}) {
    // Build form body from JSON
    let formBody = [];
    for (let property in data) {
        let encodedKey = encodeURIComponent(property);
        let encodedValue = encodeURIComponent(data[property]);
        formBody.push(encodedKey + "=" + encodedValue);
    }
    formBody = formBody.join("&");

    // Fetch URL
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        body: formBody
    });

    // Try to parse as JSON, otherwise return as text
    if (response.ok) {
        let isJSON = response.headers.get('content-type').includes('application/json');
        return (isJSON ? response.json() : response.text());
    }

    throw new Error(await response.text());
}