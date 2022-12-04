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
    modalRatings.textContent = stall.rating

    let menuList = ""
    for (let item of stall.items) {
        menuList += `${item.itemName} - ₱${item.price}\n`
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
        stallCardAverageRatingDiv.textContent = stall.rating
    
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
}

init()
//createStallCards(stallList)