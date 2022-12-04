// Sign in and Register Modals
function signin(){
    if($('#register').is(':visible')){
        $('#register').modal('hide')
    }
    $('#signin').modal('show')
}
function register(){
    if($('#signin').is(':visible')){
        $('#signin').modal('hide')
    }
    $('#register').modal('show')
}

function writeReview() {
    $('#write-review').modal('show')
    for (let i=1; i<=5; i++) {
        $('#write-review-star-'+i).addClass("inactive-star")
        $('#write-review-star-'+i).removeClass("active-star")
    }
}

var starsSelected

function clickStar(n) {
    for (let i=1; i<=n; i++) {
        $('#write-review-star-'+i).addClass("active-star")
        $('#write-review-star-'+i).removeClass("inactive-star")
    }
    for (let j=n+1; j<=5; j++) {
        $('#write-review-star-'+j).addClass("inactive-star")
        $('#write-review-star-'+j).removeClass("active-star")
    }
    starsSelected = n
}

function submitReview() {
    if (!starsSelected) {
        console.log("invalid")
        return
    }
    console.log(starsSelected)
}