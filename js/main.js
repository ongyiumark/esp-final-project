async function populateDatabase() {
    let locationData = await getData(`${BASE_URL}location/list`)
    let stallData = await getData(`${BASE_URL}stall/list`)
    let itemData = await getData(`${BASE_URL}food/list`)
    let imageData = await getData(`${BASE_URL}image/list`)
    let userData = await getData(`${BASE_URL}user/list`)
    let reviewData = await getData(`${BASE_URL}review/list`)

    // Abort if database is not empty
    if (locationData.length) return "Location table is not empty. Database population aborted."
    if (stallData.length) return "Food stall table is not empty. Database population aborted."
    if (itemData.length) return "Food item table is not empty. Database population aborted."
    if (imageData.length) return "Image table is not empty. Database population aborted."
    if (userData.length) return "User table is not empty. Database population aborted."
    if (reviewData.length) return "Food Stall table is not empty. Database population aborted."

    // Populate location
    for (let location of locationSeeder) {
        try {
            let res = await postData(`${BASE_URL}location/new`, location)
            console.log(`The location '${res.locationName}' was added.`)
        }
        catch(err) {
            return `Something went wrong while trying to add the location '${location.locationName}'. Database population aborted.`
        }
    }

    // Populate images
    // The file name must correspond to an image already in the FileProjectDemo/images folder.
    for (let stall of stallSeeder) {
        try {
            let img = await postData(`${BASE_URL}image/new`, {'fileName': stall.imageName})
            console.log(`The image '${img.fileName}' was added.`)
        }
        catch(err) {
            return `Something went wrong while trying to add the image '${stall.imageName}'. Database population aborted.`
        }
    }

    // Populate food stalls
    locationData = await getData(`${BASE_URL}location/list`)
    imageData = await getData(`${BASE_URL}image/list`)
    let proccessedStallSeeder = await Promise.all(stallSeeder.map(async (stall) => {
        // Collect location id
        for (let location of locationData) {
            if (location.locationName == stall.locationName) {
                stall['longitude'] = location.longitude;
                stall['latitude'] = location.latitude;
            }
        }

        // Collect image id
        for (let image of imageData) {
            if (image.fileName == stall.imageName) {
                stall['imageId'] = image.imageId;
            }
        }

        return stall
    }))

    for (let stall of proccessedStallSeeder) {
        try {
            stall = await postData(`${BASE_URL}stall/new`, stall)
            console.log(`The food stall '${stall.stallName}' was added.`)
        }
        catch(err) {
            return `Something went wrong while trying to add the food stall '${stall.stallName}'. Database population aborted.`
        }
    }

    // Populate food items
    stallData = await getData(`${BASE_URL}stall/list`)
    for (let stall of stallSeeder) {
        for (let item of stall.items) {
            try {
                item['stallName'] = stall.stallName;
                item = await postData(`${BASE_URL}food/new`, item)
                console.log(`The food item '${item.itemName}' from '${stall.stallName}' was added.`)
            }
            catch(err) {
                return `Something went wrong while trying to add the food item '${item.itemName}' from '${stall.stallName}'. Database population aborted.`
            }
        }
    }

    // Populate users
    for (let user of userSeeder) {
        try {
            user = await postData(`${BASE_URL}user/register`, user)
            console.log(`The user '${user.userName}' was added.`)
        }
        catch(err) {
            return `Something went wrong while trying to add the user '${user.userName}'. Database population aborted.`
        }
    }

    // Populate reviews
    for (let review of reviewSeeder) {
        try {
            let stallName = review.stallName;
            let userName = review.userName;
            review = await postData(`${BASE_URL}review/new`, review)
            console.log(`A review of '${stallName}' by '${userName}' was added.`)
        }
        catch(err) {
            console.log(err.message)
            return `Something went wrong while trying to add a review of '${review.stallName}' by '${review.userName}'. Database population aborted.`
        }
    }

    return "Database successfully populated."
}

function init() {
    // Set up populate button
    document.getElementById("populate-btn").addEventListener("click", () => {
        populateDatabase()
            .then((msg) => console.log(msg))
            .catch((error) => console.log(error.message))
    }, false);
}

init();