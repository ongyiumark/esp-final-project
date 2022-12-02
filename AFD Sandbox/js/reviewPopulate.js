var firstRev = {
    name: "Naik Oladive",
    date: "June 35, 2021",
    stallName: "Random Stall Name",
    rating: 4,
    reviewBody: "This is my review body hello world",
    reviewId: 1
}

var secondRev = {
    name: "Naik Oladive 1",
    date: "January 13, 2131",
    stallName: "Random Stall Name 1",
    rating: 5,
    reviewBody: "This is my review body hello world",
    reviewId: 2
}

var thirdRev = {
    name: "Naik Oladive 2",
    date: "March 16, 2001",
    stallName: "Random Stall Name 1",
    rating: 1,
    reviewBody: "This is my review body hello world",
    reviewId: 3
}

reviewList = [firstRev, secondRev, thirdRev]
const reviewContainer = document.getElementById("review-container")

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
        reviewerName.textContent = review.name
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

createReviews(reviewList)

sampleStall = {
    name: "some name here",
    rating: 4.7,
    location: "Gonzaga Hall",
    description: "some description here"
}

function supplySpecificStallDetails(stall) {
    document.getElementById("specific-stall-name").textContent = stall.name
    document.getElementById("rating-double").textContent = stall.rating + " / 5.0"
    document.getElementById("specific-stall-location").textContent += stall.location
    document.getElementById("specific-stall-description").textContent += stall.description
}

supplySpecificStallDetails(sampleStall)
