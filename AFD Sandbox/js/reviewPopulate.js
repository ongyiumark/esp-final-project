let allReviewData
const reviewContainer = document.getElementById("review-container")

async function loadData() {
    // load user data
    let userData = await getData(`${BASE_URL}user/list`)

    // load stall data
    let stallData = await getData(`${BASE_URL}stall/list`)

    // load review data
    let reviewData = await getData(`${BASE_URL}review/list`)

    // load location data
    let locationData = await getData(`${BASE_URL}location/list`)
    
    // atach stall name and user name to each review object
    let reviewList = await Promise.all(reviewData.map(async (review) => {
        // get stall name
        for (let stall of stallData) {
            if (review.stallId == stall.stallId) {
                review['stallName'] = stall.stallName
                review['locationId'] = stall.locationId
            }
        }

        // get location name
        for (let loc of locationData) {
            if (review.locationId == loc.locationId) {
                review['locationName'] = loc.locationName;
            }
        }
        
        // get user name
        for (let user of userData) {
            if (review.userId == user.userId) {
                review['userName'] = user.userName
            }
        }
        
        // hardcode date for now
        review['date'] = "November 21, 2022"
        
        return review
    }))

    return reviewList
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
        reviewDate.textContent = review.date
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

function updateReviews() {
    reviewContainer.innerHTML = "";

    searchSelect = document.getElementById("search-select")
    searchInput = document.getElementById("search-input")

    // output all stalls if search input is empty or if no search type is selected
    if (searchInput.value == "" || searchSelect.value == "none") {
        createReviews(allReviewData)
        return;
    } 

    // regex the search
    let filteredReviewList = allReviewData.filter((review) => {
        const re = new RegExp(searchInput.value.toLowerCase())
        if (searchSelect.value == "stall") {
            return re.test(review.stallName.toLowerCase())
        }
        else if (searchSelect.value == "location") {
            return re.test(review.locationName.toLowerCase())
        }
        else {
            console.log("This shouldn't be happening. Something is wrong")
            // In the off chance this happens, we just show all stalls
            return true
        }
    })

    createReviews(filteredReviewList)
}

function init() {
    loadData()
        .then((data) => {
            // console.log(data)
            createReviews(data)  
            allReviewData = data
        
            // add listener to search input
            searchInput = document.getElementById("search-input")
            searchInput.addEventListener("input", updateReviews);
            //console.log(data)
        })
        .catch((error) => {
            console.log(error)
        })
}

init()

