async function addStall() {
    let stallName = document.getElementById("sname").value;
    let locationName = document.getElementById("lname").value;
    let description = document.getElementById("description").value;

    if (stallName == "") {
        throw "Please provide a stall name."
    } 

    if (locationName == "") {
        throw "Please provide a location."
    }

    let locationData = await getData(`${BASE_URL}location/list`)
    let stall = {
        "stallName": stallName,
        "description": description,
        "locationName": locationName
    };

    // Upload image
    let image = document.getElementById('file').files[0];
    stall['imageName'] = null
    //console.log(image)
    if (image) {
        let imageRes = await postDataMultiform(`${BASE_URL}image/upload`, {'file' : image});
        stall['imageName'] = imageRes.fileName;
        console.log("WHY")
        console.log(image)
    }

    for (let location of locationData) {
        if (location.locationName == locationName) {
            stall['latitude'] = location.latitude;
            stall['longitude'] = location.longitude; 
        }
    }
    
    let stallRes = await postData(`${BASE_URL}stall/new`, stall)
    return stallRes
}

function init() {
    const selectImage = document.getElementsByClassName('select-image')[0];
    const inputFile = document.getElementById('file');
    const imgArea = document.getElementsByClassName('img-area')[0];
    const saveBtn = document.getElementById("save-button")

    saveBtn.addEventListener("click", async () => {
        addStall()
            .then((msg) => {
                console.log(msg)
                alert("Food stall added successfully.")
            })
            .catch((error) => console.log(error));
    })

    selectImage.addEventListener('click', () => {
        console.log("here")
        inputFile.click();

    })

    inputFile.addEventListener('change', function () {
        const image = this.files[0]
        console.log("here")
        if(image.size < 2000000) {
            const reader = new FileReader();
            reader.onload = ()=> {
                const allImg = imgArea.querySelectorAll('img');
                allImg.forEach(item=> item.remove());
                const imgUrl = reader.result;
                const img = document.createElement('img');
                img.src = imgUrl;
                imgArea.appendChild(img);
                imgArea.classList.add('active');
                imgArea.dataset.img = image.name;
            }
            reader.readAsDataURL(image);
        } else {
            alert("Image size more than 2MB");
        }
    })


}

init() 