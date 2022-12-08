let presets = 
[
    {
        "name": "Add new food item",
        "url": "food/new",
        "data": {
            "stallName": "Blue Alley",
            "itemName": "Test Sandwich",
            "price": 120
        },
        "type": "POST"
    },
    {
        "name": "List all food items",
        "url": "food/list",
        "data": {},
        "type": "GET"
    },
    {
        "name": "List food items given stall",
        "url": "food/stall",
        "data": {
            "stallName": "Blue Alley"
        },
        "type": "GET"
    },
    {
        "name": "Add new review",
        "url": "review/new",
        "data": {
            "userName": "deloconi",
            "stallName": "Blue Alley",
            "reviewBody": "Food is good.",
            "reviewDate": "December 8, 2022",
            "rating": 4
        },
        "type": "POST"
    },
    {
        "name": "List all reviews",
        "url": "review/list",
        "data": {},
        "type": "GET"
    },
    {
        "name": "List reviews given stall",
        "url": "review/stall",
        "data": {
            "stallName": "Chicks Rule"
        },
        "type": "GET"
    },
    {
        "name": "Add new location",
        "url": "location/new",
        "data": {
            "locationName": "Ebai's Cafeteria",
            "longitude": 121.0803578633369,
            "latitude": 14.63933007015154
        },
        "type": "POST"
    },
    {
        "name": "List all locations",
        "url": "location/list",
        "data": {},
        "type": "GET"
    },
    {
        "name": "Add new food stall",
        "url": "stall/new",
        "data": {
            "stallName": "Chunky Chicks",
            "description": "Sells chicken sandwiches and pasta",
            "locationName": "Gonzaga Cafeteria",
            "longitude": 121.07799954913702,
            "latitude": 14.638947290686565
        },
        "type": "POST"
    },
    {
        "name": "List all food stalls",
        "url": "stall/list",
        "data": {},
        "type": "GET"
    },
    {
        "name": "Register new user",
        "url": "user/register",
        "data": {
            "userName": "ongyiumark",
            "password": "1234"
        },
        "type": "POST"
    },
    {
        "name": "Log in user",
        "url": "user/login",
        "data": {
            "userName": "ogeranti",
            "password": "qwerty"
        },
        "type": "POST"
    },
    {
        "name": "Log out user",
        "url": "user/logout",
        "data": {
            "sessionKey": "[insert randomly generated session key]"
        },
        "type": "GET"
    },
    {
        "name": "List all users",
        "url": "user/list",
        "data": {},
        "type": "GET"
    },
    {
        "name": "Update user details",
        "url": "user/update",
        "data": {
            "oldUserName": "deloconi",
            "oldPassword": "password",
            "userName": "delaconi22",
            "password": "yes"
        },
        "type": "POST"
    },
    {
        "name": "Get user from session key",
        "url": "user/session",
        "data": {
            "sessionKey": "[insert randomly generated session key]"
        },
        "type": "GET"
    },
    {
        "name": "List all images",
        "url": "image/list",
        "data": {},
        "type": "GET"
    },
    {
        "name": "Show an image",
        "url": "image/show",
        "data": {
            "fileName": "stall--blue-alley.png"
        },
        "type": "GET"
    }
]