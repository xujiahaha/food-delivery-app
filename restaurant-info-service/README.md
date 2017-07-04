# restaurant-info-service

**restaurant-info-service** is responsible for retrieving menus and other information related to a restaurant. 

**MongoDB** is used for storing restaurant data, avoiding so many join operations when using a relational database. The other reason of using MongoDB here is that it offers geospatial query, which will make it easier to implement the feature of finding restaurants nearby.

----------

API Overview
-------------
Method   | Path  | Description 
--------| -----  | --------------
GET     | /restaurants | get all restaurants
GET     | /restaurant?name={restaurantName} |get restaurants by given name (return all restaurants whose name containing given name)
GET     | /restaurant/{restaurantId} |get restaurant by restaurant id
GET     | /restaurant/{restaurantId}/items | get all the menu items in a restaurant
POST     | /restaurant | upload a restaurant
POST     | /restaurants | upload a list of restaurants
> *Clarification: The two write operations here are designed for uploading data to MongoDB for testing. In real application, the master-slave model will be used. Master database is write-only while slave ones are read-only.*

----------

API References
-------------
#### 1. Upload restaurants

&nbsp;&nbsp;&nbsp;&nbsp;URL: `/restaurants`

&nbsp;&nbsp;&nbsp;&nbsp;HTTP Method: **POST**

&nbsp;&nbsp;&nbsp;&nbsp;Success Response: 201 (Created) 

Data Example:
```
[
  {
    "restaurantName": "The Cheese Cake Factory",
    "items": [
      {
        "itemId": "595184df77c826cc6f223f9d",
        "itemName": "CAESAR SALAD",
        "price": 7.99,
        "category": "salads",
        "active": true,
        "favorites": false
      },
      {
        "itemId": "595184df77c826cc6f223f9e",
        "itemName": "LUNCH CHICKEN SPECIALS",
        "price": 11.99,
        "category": "lunch specials",
        "active": true,
        "favorites": false
      }
    ]
  },
  {
    "restaurantName": "California Pizza Kitchen",
    "items": [
      {
        "itemId": "595184df77c826cc6f223f9f",
        "itemName": "ASPARAGUS + ARUGULA SALAD",
        "price": 7.99,
        "category": "small plates",
        "active": true,
        "favorites": false
      },
      {
        "itemId": "595184df77c826cc6f223fa0",
        "itemName": "LETTUCE WRAPS",
        "price": 11.99,
        "category": "appetizers",
        "active": true,
        "favorites": false
      },
      {
        "itemId": "595184df77c826cc6f223fa1",
        "itemName": "CRISPY FISH TACOS",
        "price": 13.99,
        "category": "main plates",
        "active": true,
        "favorites": false
      }
    ]
  }
]
```

#### 2. Get restaurants by name

&nbsp;&nbsp;&nbsp;&nbsp;URL: `/restaurant?name={restaurantName}`

&nbsp;&nbsp;&nbsp;&nbsp;HTTP Method: **GET**

&nbsp;&nbsp;&nbsp;&nbsp;Success Response: 200 (Ok) 

Example: 

Request: `/restaurant?name=cake`

Response:
```
{
  "restaurantName": "The Cheese Cake Factory",
  "items": [
    {
      "itemId": "595184df77c826cc6f223f9d",
      "itemName": "CAESAR SALAD",
      "price": 799,
      "category": "salads",
      "active": true,
      "favorites": false
    },
    {
      "itemId": "595184df77c826cc6f223f9e",
      "itemName": "LUNCH CHICKEN SPECIALS",
      "price": 1199,
      "category": "lunch specials",
      "active": true,
      "favorites": false
    }
  ],
  "id": "595b38287998d015f8724c27",
  "links": [
    {
      "rel": "self",
      "href": "http://localhost:8080/restaurant/595b38287998d015f8724c27"
    }
  ]
}
```

----------