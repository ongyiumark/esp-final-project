let allStallData;

const stallContainer = document.getElementById("stall-container")

async function loadData() {
    let itemData = await getData(`${BASE_URL}food/list`)

    // load location data
    let locationData = await getData(`${BASE_URL}location/list`)

    // load image data
    let imageData = await getData(`${BASE_URL}image/list`)

    // load stall data
    let stallData = await getData(`${BASE_URL}stall/list`)
    
    // attach location name and rating to each stall object
    let stallList = await Promise.all(stallData.map(async (stall) => {
        let reviewData = await getData(`${BASE_URL}review/stall`, {stallName: stall.stallName})

        // average ratings
        let totalRating = 0;
        let numRatings = reviewData.length
        for (let review of reviewData) {
            totalRating += review.rating
        }
        let avgRating = (numRatings == 0 ? 'N/A': totalRating/numRatings)
        stall['rating'] = avgRating

        // get location name
        for (let loc of locationData) {
            if (stall.locationId == loc.locationId) {
                stall['locationName'] = loc.locationName;
            }
        }

        stall['imagePath'] = 'images/stallpic.png'
        // get image path
        for (let img of imageData) {
            if (stall.imageId == img.imageId) {
                stall['imagePath'] = `../FinalProjectDemo/images/${img.fileName}`;
            }
        }

        stall['items'] = itemData.filter((item) => (item.stallId == stall.stallId));

        return stall
    }))

    return stallList
}

function updateStallModal(stall) {
    modalImg = document.getElementById("stall-modal--image")
    modalName = document.getElementById("stall-modal--name")
    modalLocation = document.getElementById("stall-modal--location")
    modalDescription = document.getElementById("stall-modal--description")
    modalRatings = document.getElementById("stall-modal--ratings")
    modalMenu = document.getElementById("menu-list")

    modalImg.src = stall.imagePath
    modalName.textContent = stall.stallName
    modalLocation.textContent = stall.locationName
    modalDescription.textContent = stall.description
    modalRatings.textContent = (stall.rating == 'N/A' ? stall.rating : `${stall.rating.toFixed(1)} / 5.0`)

    let menuList = ""
    for (let item of stall.items) {
        menuList += `${item.itemName} - ???${item.price}\n`
    }
    modalMenu.value = menuList
    stallmodal()
}

function createStallCards(stallList) {
    for (var stall of stallList) {
        var newCard = document.createElement("div")
        newCard.classList.add("stall-card")
        newCard.setAttribute("id", "stallCard#"+stall.stallId)
        stallContainer.appendChild(newCard)
    
        currentCard = document.getElementById("stallCard#"+stall.stallId)
        // img div
        var stallCardImgDiv = document.createElement("div")
        stallCardImgDiv.classList.add("stall-card-img")
        var stallCardImgDivImg = document.createElement("img")
        stallCardImgDivImg.setAttribute("src", stall.imagePath)
        stallCardImgDiv.appendChild(stallCardImgDivImg)
    
        // details div
        var stallCardDetailsDiv = document.createElement("div")
        stallCardDetailsDiv.classList.add("stall-card-details")
        var stallCardNameDiv = document.createElement("div")
        stallCardNameDiv.classList.add("stall-card-name")
        var stallCardRatingDiv = document.createElement("div")
        stallCardRatingDiv.classList.add("stall-card-rating")
    
        // name div
        var stallCardLocationNameDiv = document.createElement("div")
        stallCardLocationNameDiv.classList.add("stall-card-location-name")
        stallCardLocationNameDiv.textContent = stall.locationName
        var stallCardStallNameDiv = document.createElement("div")
        stallCardStallNameDiv.classList.add("stall-card-stall-name")
        stallCardStallNameDiv.textContent = stall.stallName
    
        stallCardNameDiv.appendChild(stallCardLocationNameDiv)
        stallCardNameDiv.appendChild(stallCardStallNameDiv)
    
    
        // rating div
        var stallCardStarContainerDiv = document.createElement("div")
        stallCardStarContainerDiv.classList.add("stall-card-star-container")
        var stallCardStarContainerDivImg = document.createElement("img")
        stallCardStarContainerDivImg.setAttribute("src", "images/star-solid.svg")
        stallCardStarContainerDiv.appendChild(stallCardStarContainerDivImg)
        var stallCardAverageRatingDiv = document.createElement("div")
        stallCardAverageRatingDiv.classList.add("stall-card-average-rating")
        stallCardAverageRatingDiv.textContent = (stall.rating == 'N/A' ? stall.rating : `${stall.rating.toFixed(1)}`)
    
        stallCardRatingDiv.appendChild(stallCardStarContainerDiv)
        stallCardRatingDiv.appendChild(stallCardAverageRatingDiv)
    
        stallCardDetailsDiv.appendChild(stallCardNameDiv)
        stallCardDetailsDiv.appendChild(stallCardRatingDiv)
    
        currentCard.appendChild(stallCardImgDiv)
        currentCard.appendChild(stallCardDetailsDiv)
    }

    // Set up cards to be clickable
    for (let stall of stallList) {
        currentCard = document.getElementById("stallCard#"+stall.stallId)
        currentCard.addEventListener("click", () => updateStallModal(stall), false);
    }
}

function updateStallCards() {
    // remove all children of container
    stallContainer.innerHTML = "";

    searchSelect = document.getElementById("search-select")
    searchInput = document.getElementById("search-input")

    // output all stalls if search input is empty or if no search type is selected
    if (searchInput.value == "" || searchSelect.value == "none") {
        createStallCards(allStallData)
        return;
    } 

    // regex the search
    let filteredStallList = allStallData.filter((stall) => {
        const re = new RegExp(searchInput.value.toLowerCase())
        if (searchSelect.value == "stall") {
            return re.test(stall.stallName.toLowerCase())
        }
        else if (searchSelect.value == "location") {
            return re.test(stall.locationName.toLowerCase())
        }
        else {
            console.log("This shouldn't be happening. Something is wrong")
            // In the off chance this happens, we just show all stalls
            return true
        }
    })

    createStallCards(filteredStallList)
}

async function addFoodToggle() {
    let SESSION_KEY = localStorage.getItem("SESSION_KEY")
    let user
    if (SESSION_KEY != null) {
        try {
            user = await getData(`${BASE_URL}user/session`, {'sessionKey': SESSION_KEY})
        }
        catch(error) {
            console.log(error)
        }
    }
    if (!user) {
        signin()
        closeModal()
        throw new Error("Sign in required.")
    }
    return "Add food form is displayed."
}

function cancelAddFood() {
    let inputName = document.getElementById("input-food-name")
    let inputPrice = document.getElementById("input-food-price")
    inputName.value = ""
    inputPrice.value = ""
    $("#add-food-container").hide()
}

async function saveFood() {
    let SESSION_KEY = localStorage.getItem("SESSION_KEY")
    let user
    if (SESSION_KEY != null) {
        try {
            user = await getData(`${BASE_URL}user/session`, {'sessionKey': SESSION_KEY})
        }
        catch(error) {
            console.log(error)
        }
    }
    if (!user) {
        localStorage.removeItem("SESSION_KEY")
        signin()
        closeModal()
        throw new Error("Sign in required.")
    }

    let inputName = document.getElementById("input-food-name")
    let inputPrice = document.getElementById("input-food-price")
    let stallName = document.getElementById("stall-modal--name").textContent

    // input validation
    let responseDiv = document.getElementById("add-food-response")
    if (inputName.value.trim().length == 0) {
        responseDiv.textContent = "Please provide an food name."
        throw new Error("Item name was not provided.")
    }

    if (inputName.value.trim().length > 25) {
        responseDiv.textContent = "Please provide a shorter food name."
        throw new Error("Item name was too long.")
    }

    if (inputPrice.value.trim().length == 0) {
        responseDiv.textContent = "Please provide a price."
        throw new Error("Price was not provided.")
    }

    if (!isNumeric(inputPrice.value.trim())) {
        responseDiv.textContent = "Invalid price."
        throw new Error("Provided price was invalid.")
    }

    let priceVal = parseFloat(inputPrice.value.trim())
    if (priceVal < 0 || !isFinite(priceVal)) {
        responseDiv.textContent = "Invalid price."
        throw new Error("Provided price was invalid.")
    }


    let itemData = {
        "sessionKey": SESSION_KEY,
        "stallName": stallName,
        "itemName": inputName.value,
        "price": priceVal
    }

    let itemRes
    try{
        itemRes = await postData(`${BASE_URL}food/new`, itemData)
        // update stall data
        allStallData = allStallData.map((stall) => {
            if (stall.stallName == stallName) {
                stall['items'].push({
                    "itemName": itemRes.itemName,
                    "price": itemRes.price
                })
            }
            return stall
        })

        // update menu
        updateStallCards()
        modalMenu = document.getElementById("menu-list")
        modalMenu.value += `${itemRes.itemName} - ???${itemRes.price}\n`

        // clear form
        let inputName = document.getElementById("input-food-name")
        let inputPrice = document.getElementById("input-food-price")
        inputName.value = ""
        inputPrice.value = ""
        responseDiv.textContent = ""
    }
    catch(error) {
        throw error
    }
    
    return itemRes
}

function init() {
    // Load stall data
    loadData()
        .then((data) => {
            createStallCards(data)  
            allStallData = data
        
            // add listener to search input
            searchInput = document.getElementById("search-input")
            searchInput.addEventListener("input", updateStallCards);
            //console.log(data)
        })
        .catch((error) => {
            console.log(error.message)
        })

    // Add food item button
    let addButton = document.getElementById("add-food-button")
    addButton.addEventListener("click", () => {
        addFoodToggle()
            .then((data) => {
                console.log(data)
                $("#add-food-container").show()
            })
            .catch((error) => console.log(error.message))
    })

    // Cancel button
    let cancelButton = document.getElementById("add-food-cancel")
    cancelButton.addEventListener("click", cancelAddFood, false)


    // Save button
    let saveButton = document.getElementById("add-food-save")
    saveButton.addEventListener("click", () => {
        saveFood()
            .then((data) => {
                console.log(data)
            })
            .catch((error) => console.log(error.message))
    })
}

init()