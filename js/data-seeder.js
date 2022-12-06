const IMG_DIR = "images"

const locationSeeder = [
    {
        "locationName": "Moro Lorenzo Sports Center",
        "longitude": 121.08037705485796,
        "latitude": 14.645535271224302
    },
    {
        "locationName": "Gonzaga Cafeteria",
        "longitude": 121.07791226649906,
        "latitude": 14.639167861813434
    },
    {
        "locationName": "Ateneo College Covered Courts",
        "longitude": 121.07882533951445,
        "latitude": 14.637015076887934
    }
];

const stallSeeder = [
    {
        "stallName": "Blue Alley",
        "description": "Blue Alley is a restaurant located in the Moro Lorenzo Sports Center that serves all day breakfast and comfort food.",
        "locationName": "Moro Lorenzo Sports Center",
        "imageName": "stall--blue-alley.png",
        "items": [
            {
                "itemName": "Pasta Bolognese",
                "price": 110,
            },
            {
                "itemName": "Waffle and Bacon",
                "price": 100,
            },
            {
                "itemName": "Baked Mac",
                "price": 110,
            },
            {
                "itemName": "Special Halo-halo",
                "price": 65,
            },
            {
                "itemName": "Fresh Fruit Shakes",
                "price": 60,
            },
            {
                "itemName": "Bihon Guisado",
                "price": 75,
            },
            {
                "itemName": "Pancit Canton",
                "price": 75,
            },
            {
                "itemName": "Lumpiang Tongue",
                "price": 50,
            },
            {
                "itemName": "Cheese Sticks",
                "price": 75,
            },
            {
                "itemName": "Kropek",
                "price": 30,
            },
            {
                "itemName": "Lumpia Shanghai",
                "price": 75,
            },
            {
                "itemName": "French Fries (Plain)",
                "price": 50,
            },
            {
                "itemName": "French Fries (Cheese)",
                "price": 75,
            },
            {
                "itemName": "French Fries (Barbeque)",
                "price": 75,
            },
            {
                "itemName": "Nachos w/ Cheese Dip",
                "price": 75,
            },
            {
                "itemName": "Chilli Wings",
                "price": 100,
            },
            {
                "itemName": "Chicken Lolipop",
                "price": 100,
            },
        ]
    },
    {
        "stallName": "Tender Juicy",
        "description": "Kids noon at ngayon can tell it's Purefoods Tender Juicy Hotdog!",
        "locationName": "Gonzaga Cafeteria",
        "imageName": "stall--tender-juicy.jpg",
        "items": [
            {
                "itemName": "Asado Siopao",
                "price": 35
            },
            {
                "itemName": "Bola-bola Siopao",
                "price": 35
            },
            {
                "itemName": "Pork Siomai",
                "price": 39
            },
            {
                "itemName": "Hotdog Kingsize",
                "price": 45
            },
            {
                "itemName": "Hotdog Kingsize w/ Bun",
                "price": 50
            },
            {
                "itemName": "Hotdog Giant",
                "price": 55
            },
            {
                "itemName": "Hotdog Giant w/ Bun",
                "price": 60
            },
            {
                "itemName": "Pan de Corned Beef",
                "price": 45
            },
            {
                "itemName": "Bread Stick",
                "price": 25
            },
        ]
    },
    // Follow the format above and make sure to put the image in FinalProjectDemo/images
];

const userSeeder = [
    {
        "userName": "deloconi",
        "password": "password"
    },
    {
        "userName": "ogeranti",
        "password": "qwerty"
    },
    {
        "userName": "rolducho",
        "password": "123456"
    },
];

const reviewSeeder = [
    {
        "userName": "deloconi",
        "stallName": "Blue Alley",
        "rating": 4,
        "reviewDate": "December 3, 2022",
        "reviewBody": "Visited Blue Alley yesterday. The ambience was nice and the service was just ok, but the value for money was great. Overall, pretty good."
    },
    {
        "userName": "ogeranti",
        "stallName": "Tender Juicy",
        "rating": 3,
        "reviewDate": "December 2, 2022",
        "reviewBody": "Tender juicy is tender juicy."
    }
];