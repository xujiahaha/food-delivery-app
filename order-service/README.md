# order-service

**order-service** is responsible for creating, updating and searching order.

**MongoDB** is used to store order information. 

----------

API Overview
-------------
Method   | Path  | Description 
--------| -----  | --------------
POST    | /order | create order
POST    | /order/{orderId} | update order status
GET     | /order/{orderId} | get order by order id.
GET     | /{userId}/orders|get all the orders placed by one user.


----------

API References
-------------
 
#### 1. Create order

&nbsp;&nbsp;&nbsp;&nbsp;URL: `/order`

&nbsp;&nbsp;&nbsp;&nbsp;HTTP Method: **POST**

&nbsp;&nbsp;&nbsp;&nbsp;Success Response: 201 (CREATE) 

Data Example:
```
{
  "items":[
    {
      "itemName":"pizza",
      "itemId":"1111-1111",
      "quantity":2,
      "price": 19.98
    },
    {
      "itemName":"hot dog",
      "itemId":"2222-2222",
      "quantity":1,
      "price": 1.99
    }
  ],
  "deliveryInfo" :{
    "userId":"user_1",
    "firstName": "Emma",
    "lastName":"Wang",
    "phoneNumber":"888-8888",
    "address": {
    }
  },
  "note": "no mustard on hot dog"
}
```

#### 2. Update order status 

&nbsp;&nbsp;&nbsp;&nbsp;URL: `/order/{orderId}`

&nbsp;&nbsp;&nbsp;&nbsp;HTTP Method: **POST** 

&nbsp;&nbsp;&nbsp;&nbsp;Success Response: 200 (OK) 


#### 3. Get order by order id

&nbsp;&nbsp;&nbsp;&nbsp;URL: `/order/{orderId}` 

&nbsp;&nbsp;&nbsp;&nbsp;HTTP Method: **GET** 

&nbsp;&nbsp;&nbsp;&nbsp;Success Response: 200 (OK) 

Example: 
```
/order/595b16f4cd7be40e78c6c10a
```

#### 4. Get orders placed by one user

&nbsp;&nbsp;&nbsp;&nbsp;URL: `/{userId}/orders`

&nbsp;&nbsp;&nbsp;&nbsp;HTTP Method: **GET** 

&nbsp;&nbsp;&nbsp;&nbsp;Success Response: 200 (OK)

--------