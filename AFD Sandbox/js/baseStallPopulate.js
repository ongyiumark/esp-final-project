var stallOne = {
    stallName: "Sutra",
    stallLocationName: "Location Name",
    imagePath: "images/stallpic.png",
    stallId: 1,
    rating: 3.6
}

var stallTwo = {
    stallName: "Bacsilog",
    stallLocationName: "Location Name",
    imagePath: "images/stallpic.png",
    stallId: 2,
    rating: 5
}

var stallThree = {
    stallName: "three",
    stallLocationName: "Location Name",
    imagePath: "images/stallpic.png",
    stallId: 3,
    rating: 3.3
}

var stallFour = {
    stallName: "four",
    stallLocationName: "Location Name",
    imagePath: "images/stallpic.png",
    stallId: 4,
    rating: 4.4
}

var stallFive = {
    stallName: "five",
    stallLocationName: "Location Name",
    imagePath: "images/stallpic.png",
    stallId: 5,
    rating: 1.9
}

var stallList = [stallOne, stallTwo, stallThree, stallFour, stallFive]
const stallContainer = document.getElementById("stall-container")

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
        stallCardLocationNameDiv.textContent = stall.stallLocationName
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
}

createStallCards(stallList)