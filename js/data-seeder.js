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
    {
        "stallName": "Glory's Ilocos Empanada",
        "description": "A taste of Ilocos",
        "locationName": "Gonzaga Cafeteria",
        "imageName": "stall--glorys.jpg",
        "items": [
            {
                "itemName": "Buy 1 Take 1",
                "price": 100
            },
            {
                "itemName": "Special Empanada",
                "price": 60
            },
            {
                "itemName": "Beef Tapa Empanada",
                "price": 60
            },
            {
                "itemName": "Chicken Inasal Empanada",
                "price": 60
            },
            {
                "itemName": "Bagnet Empanada",
                "price": 60
            },
            {
                "itemName": "New Spicy Empanada",
                "price": 60
            }
        ]
    },
    {
        "stallName": "Ebai's Pitstop",
        "description": "Ebai's Cafe and Pastry is a restaurant specializing in Filipino foods",
        "locationName": "Ateneo College Covered Courts",
        "imageName": "stall--ebais.jpg",
        "items": [
            {
                "itemName": "Rice w/ Vegetables",
                "price": 68
            },
            {
                "itemName": "Rice w/ Chicken",
                "price": 93
            },
            {
                "itemName": "Rice w/ Pork",
                "price": 93
            },
            {
                "itemName": "Rice w/ Fish",
                "price": 93
            },
            {
                "itemName": "Rice w/ Beef",
                "price": 93
            },
            {
                "itemName": "One serve Vegetable",
                "price": 55
            },
            {
                "itemName": "One serve chicken",
                "price": 80
            },
            {
                "itemName": "One serve pork",
                "price": 80
            },
            {
                "itemName": "One serve fish",
                "price": 80
            },
            {
                "itemName": "One serve beef",
                "price": 85
            },
            {
                "itemName": "One serve shrimp",
                "price": 85
            },
            {
                "itemName": "One serve inihaw",
                "price": 80
            },
            {
                "itemName": "One serve liempo",
                "price": 80
            },
            {
                "itemName": "Chicken Barbeque",
                "price": 128
            },
            {
                "itemName": "Chicken Inasal",
                "price": 128
            }
        ]
    },
    {
        "stallName": "Itadakimasu",
        "description": "Serving your Japanese favorites!",
        "locationName": "Gonzaga Cafeteria",
        "imageName": "stall--itadakimasu.jpg",
        "items": [
            {
                "itemName": "Chicken Karaage",
                "price": 120
            },
            {
                "itemName": "Ebi Tempura",
                "price": 150
            },
            {
                "itemName": "Gyudon",
                "price": 130
            },
            {
                "itemName": "Charsiu Ramen",
                "price": 160
            },
            {
                "itemName": "Tonkatsu",
                "price": 120
            },
            {
                "itemName": "Katsudon",
                "price": 145
            },
            {
                "itemName": "Katsu Curry",
                "price": 150
            },
            {
                "itemName": "Tamago",
                "price": 40
            }
        ]
    },
    {
        "stallName": "Hunger Buster",
        "description": "Burgers and Sandwiches",
        "locationName": "Gonzaga Cafeteria",
        "imageName": "stall--hunger-buster.jpg",
        "items": [
            {
                "itemName": "[Burger] Dying",
                "price": 120
            },
            {
                "itemName": "[Burger] Starving",
                "price": 100
            },
            {
                "itemName": "[Burger] Burning",
                "price": 120
            },
            {
                "itemName": "[Burger] Flaming",
                "price": 120
            },
            {
                "itemName": "[Burger] Craving",
                "price": 90
            },
            {
                "itemName": "[Burger] Craving 2.0",
                "price": 100
            },
            {
                "itemName": "[Fries] Solo",
                "price": 60
            },
            {
                "itemName": "[Fries] Sharing",
                "price": 100
            },
            {
                "itemName": "[Wings] 1/3 Pound",
                "price": 100
            },
            {
                "itemName": "Texan BBQ Burger",
                "price": 120
            },
            {
                "itemName": "Bacon Two Cheese",
                "price": 130
            },
            {
                "itemName": "BLT Burger",
                "price": 120
            },
            {
                "itemName": "Rising Burger",
                "price": 110
            },
            {
                "itemName": "Extra Patty",
                "price": 80
            }
        ]
    },
    {
        "stallName": "Ate Rica's Bacsilog",
        "description": "The 1st and original BACSILOG",
        "locationName": "Gonzaga Cafeteria",
        "imageName": "stall--AR-bacsilog.jpg",
        "items": [
            {
                "itemName": "Bacsilog",
                "price": 89
            },
            {
                "itemName": "Hotsilog",
                "price": 89
            },
            {
                "itemName": "Footsilog",
                "price": 89
            },
            {
                "itemName": "Tapsilog",
                "price": 109
            },
            {
                "itemName": "Tocilog",
                "price": 109
            },
            {
                "itemName": "Nuggetsilog",
                "price": 109
            }
        ]
    }
    // Follow the format above and make sure to put the image in FinalProjectDemo/images
];

const userSeeder = [
    {
        "userName": "admin",
        "password": "admin"
    },
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