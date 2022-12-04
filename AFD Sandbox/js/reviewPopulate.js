
let allReviews;

const reviewContainer = document.getElementById("review-container")

async function loadData() {
    let stallData = await getData(`${BASE_URL}stall/list`)
    let userData = await getData(`${BASE_URL}user/list`)
    let reviewData = await getData(`${BASE_URL}review/list`)

    let reviewList = await Promise.all(reviewData.map(async (review) => {
        for (let stall of stallData) {
            if (stall.stallId == review.stallId) {
                review['stallName'] = stall.stallName;
            }
        }

        for (let user of userData) {
            if (user.userId == review.userId) {
                review['userName'] = user.userName;
            }
        }

        return review
    }))

    return reviewList;
}

async function updateReviews() {
    // remove all children of container
    reviewContainer.innerHTML = "";
    let stallHeader = document.getElementById('specific-stall')
    if (stallHeader) stallHeader.setAttribute("hidden", "")

    searchSelect = document.getElementById("search-select")
    searchInput = document.getElementById("search-input")

    // output all reviews if search input is empty or if no search type is selected
    if (searchInput.value == "" || searchSelect.value == "none") {
        return allReviews
    } 

    // handle this later
    if (searchSelect.value == 'user') {
        return allReviews
    }

    // Attach image and location
    let imageData = await getData(`${BASE_URL}image/list`)
    let locationData = await getData(`${BASE_URL}location/list`)
    let stallData = await getData(`${BASE_URL}stall/list`)

    let currStall;
    for (let stall of stallData) {
        if (stall.stallName != searchInput.value) continue
        currStall = {}
        let reviewData = await getData(`${BASE_URL}review/stall`, {stallName: stall.stallName})

        // average ratings
        let totalRating = 0;
        let numRatings = reviewData.length
        for (let review of reviewData) {
            totalRating += review.rating
        }
        let avgRating = (numRatings == 0 ? 'N/A': totalRating/numRatings)
        currStall['rating'] = avgRating

        // get location name
        for (let loc of locationData) {
            if (stall.locationId == loc.locationId) {
                currStall['locationName'] = loc.locationName;
            }
        }

        currStall['imagePath'] = 'images/stallpic.png'
        // get image path
        for (let img of imageData) {
            if (stall.imageId == img.imageId) {
                currStall['imagePath'] = `../FinalProjectDemo/images/${img.fileName}`;
            }
        }

        currStall['stallName'] = stall.stallName;
        currStall['description'] = stall.description;
    }

    if (currStall) {
        stallHeader.removeAttribute("hidden");
        document.getElementById('specific-stall-img').src = currStall.imagePath
        document.getElementById("specific-stall-name").textContent = currStall.stallName
        document.getElementById("rating-double").textContent = (currStall.rating == 'N/A' ? currStall.rating : `${currStall.rating.toFixed(1)} / 5.0`)
        document.getElementById("stall--location").textContent = currStall.locationName
        document.getElementById("stall--description").textContent = currStall.description
    
        let filteredReviews = allReviews.filter((review) => (review.stallName == searchInput.value))
        return filteredReviews
    }

    

    return allReviews
}

function createReviews(revList) {
    for (review of revList) {
        var newCard = document.createElement("div")
        newCard.classList.add("review-card")
        newCard.setAttribute("id", "reviewCard#"+review.reviewId)
        reviewContainer.appendChild(newCard)

        currentCard = document.getElementById("reviewCard#"+review.reviewId)
        var reviewDetails = document.createElement("div")
        reviewDetails.classList.add("review-details")
        newCard.appendChild(reviewDetails)
        var reviewStars = document.createElement("div")
        reviewStars.classList.add("review-stars")
        newCard.appendChild(reviewStars)
        var reviewBodyText = document.createElement("div")
        reviewBodyText.classList.add("review-body-text")
        newCard.appendChild(reviewBodyText)

        // review details
        var reviewerName = document.createElement("div")
        reviewerName.classList.add("reviewer-name")
        reviewerName.textContent = review.userName
        reviewDetails.appendChild(reviewerName)
        var reviewDate = document.createElement("div")
        reviewDate.classList.add("review-date")
        reviewDate.textContent = review.reviewDate
        reviewDetails.appendChild(reviewDate)
        var reviewStallName = document.createElement("div")
        reviewStallName.classList.add("review-stall-name")
        reviewStallName.textContent = review.stallName
        reviewDetails.appendChild(reviewStallName)
        
        // review stars
        for (let i=0; i<review.rating; i++) {
            var star = document.createElement("img")
            star.setAttribute("src", "images/star-solid.svg")
            star.classList.add("active-star")
            reviewStars.appendChild(star)
        }
        
        for (let i=0; i<5-review.rating; i++) {
            var star = document.createElement("img")
            star.setAttribute("src", "images/star-solid.svg")
            star.classList.add("inactive-star")
            reviewStars.appendChild(star)
        }


        // review body text
        reviewBodyText.textContent = review.reviewBody
    }
}

function init() {
    const stall = document.getElementById("specific-stall")
    stall.setAttribute("hidden", "");

    // Load review data
    loadData()
        .then((data) => {
            createReviews(data)  
            allReviews = data
        
            // add listener to search input
            // searchInput = document.getElementById("search-input")
            // searchInput.addEventListener("input", updateStallCards);
            //console.log(data)
        })
        .catch((error) => {
            console.log(error.message)
        })

    // Set up search button
    document.getElementById("search-btn").addEventListener('click', () => {
        updateReviews()
            .then((data) => {
                createReviews(data)
            })
            .catch((error) => {
                console.log(error)
            })
    });
}

init()
