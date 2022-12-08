async function updateUser(user) {
    let userNameInput = document.getElementById("username")
    let currentPasswordInput = document.getElementById("current-pass")
    let newPasswordInput = document.getElementById("new-pass")
    let confirmPasswordInput = document.getElementById("confirm-pass")
    let responseDiv = document.getElementById("user-update-response")

    if (userNameInput.value.trim().length == 0) {
        responseDiv.textContent = "Please provide a username."
        throw new Error("Username was not provided.")
    }

    let userData = await getData(`${BASE_URL}user/list`)
    let filteredUsers = userData.filter((user) => user.userName == userNameInput.value)
    if (filteredUsers.length) {
        responseDiv.textContent = `The user '${userNameInput.value}' already exists.`
        throw new Error("Username already exists.")
    }

    if (newPasswordInput.value.trim().length == 0) {
        responseDiv.textContent = "Please provide a password."
        throw new Error("Password was not provided.")
    }

    if (newPasswordInput.value != confirmPasswordInput.value) {
        responseDiv.textContent = "Passwords do not match."
        throw new Error("Passwords did not match.")
    }

    changeData = {
        'oldUserName': user.userName,
        'oldPassword': currentPasswordInput.value,
        'userName': userNameInput.value,
        'password': newPasswordInput.value
    }

    let userRes
    try {
        userRes = await postData(`${BASE_URL}user/update`, changeData)
    }
    catch(error) {
        responseDiv.textContent = error.message
        throw error
    }

    responseDiv.textContent = "User profile updated successfully."
    userNameInput.value = ""
    currentPasswordInput.value = ""
    newPasswordInput.value = ""
    confirmPasswordInput.value = ""
    return userRes
}

async function init() {
    // check if user is signed in
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
        throw new Error("Sign in required.")
    }

    document.getElementById("save-change-button").addEventListener("click", () => {
        updateUser(user)
            .then((data) => {
                console.log(data)
                updateNav()
            })
            .catch((error) => console.log(error.message))
    })
}

init().then().catch((error) => {console.log(error.message)})