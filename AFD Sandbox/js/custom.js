function updateNav() {
    let SESSION_KEY = localStorage.getItem("SESSION_KEY")
    if($('#user-welcome-container').is(':visible')){
        $('#user-welcome-container').hide()
    }
    $('#login-register-container').show()
    if (SESSION_KEY == null || SESSION_KEY.trim().length == 0) return
    
    loginRegister = document.getElementById("login-register-container")
    userWelcomeContainer = document.getElementById("user-welcome-container")
    userWelcome = document.getElementById('user-welcome')

    getData(`${BASE_URL}user/session`, {'sessionKey': SESSION_KEY})
        .then((user) => {
            console.log(`Welcome, ${user.userName}`)
            userWelcome.textContent = `Hi, ${user.userName}`
            if($('#login-register-container').is(':visible')){
                $('#login-register-container').hide()
            }
            $('#user-welcome-container').show()
        })
        .catch((error) => {
            if($('#user-welcome-container').is(':visible')){
                $('#user-welcome-container').hide()
            }
            $('#login-register-container').show()
            console.log(error.message)
        })
}

async function registerUser() {
    let usernameInput = document.getElementById("register-username")
    let passwordInput = document.getElementById("register-password")
    let confirmPasswordInput = document.getElementById("register-confirm-password")
    let responseDiv = document.getElementById("register-response")

    if (usernameInput.value.trim().length == 0) {
        responseDiv.textContent = "Please provide a username."
        throw new Error("Username was not provided.")
    }

    let userData = await getData(`${BASE_URL}user/list`)
    let filteredUsers = userData.filter((user) => user.userName == usernameInput.value)
    if (filteredUsers.length) {
        responseDiv.textContent = `The user ${usernameInput.value} already exists.`
        throw new Error("Username already exists.")
    }


    if (passwordInput.value.trim().length == 0) {
        responseDiv.textContent = "Please provide a password."
        throw new Error("Password was not provided.")
    }

    if (confirmPasswordInput.value != passwordInput.value) {
        responseDiv.textContent = "Passwords do not match."
        throw new Error("Passwords did not match.")
    }

    let data = {
        "userName": usernameInput.value,
        "password": passwordInput.value
    }

    let user;
    try {
        user = await postData(`${BASE_URL}user/register`, data)
    }
    catch(error) {
        throw error
    }
    
    $('#register').modal('hide')
    $('#signin').modal('show')
    return `Successfully registered ${user.userName}`
}

async function loginUser() {
    usernameInput = document.getElementById("login-username")
    passwordInput = document.getElementById("login-password")
    responseDiv = document.getElementById("login-response")

    let data = {
        "userName": usernameInput.value,
        "password": passwordInput.value
    }

    let session;
    try {
        session = await postData(`${BASE_URL}user/login`, data)
    }
    catch(error) {
        responseDiv.textContent = error.message
        throw error
    }

    localStorage.setItem("SESSION_KEY", session.sessionKey)
    $('#signin').modal('hide')
    updateNav();

    return `Successfully logged in user '${usernameInput.value}' with session key ${session.sessionKey}.`;
}

async function logoutUser() {
    let data = {
        "sessionKey": localStorage.getItem("SESSION_KEY")
    }
    return await getData(`${BASE_URL}user/logout`, data)
}


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

// Stall Modal
function stallmodal(){
    $('#stallmodal').modal('show');
    let inputName = document.getElementById("input-food-name")
    let inputPrice = document.getElementById("input-food-price")
    inputName.value = ""
    inputPrice.value = ""
    $("#add-food-container").hide()
}

function closeModal(){
    $('#stallmodal').modal('hide');
}

function init() {
    updateNav()
    registerBtn = document.getElementById("register-button")
    registerBtn.addEventListener("click", () => {
        registerUser()
            .then((data) => {
                console.log(data)
            })
            .catch((error) => {
                console.log("Register failed.")
                console.log(error.message)
            })
    })

    loginBtn = document.getElementById("login-button")
    loginBtn.addEventListener("click", () => {
        loginUser()
            .then((data) => {
                console.log(data)
            })
            .catch((error) => {
                console.log("Login failed")
                console.log(error.message)
            })
    })

    logoutBtn = document.getElementById('logout-button')
    logoutBtn.addEventListener("click", () => {
        logoutUser()
            .then((data) => {
                console.log(data)
                localStorage.removeItem('SESSION_KEY')
                updateNav()
            })
            .catch((error) => {
                console.log("Logout failed")
                console.log(error.message)
            })
    })
}

init()