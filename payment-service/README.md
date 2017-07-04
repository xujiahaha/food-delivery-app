# payment-service

**payment-service** is responsible for making a payment for an order.
Relational database is used for storing payment information.

When a request of making payment is received, **payment-service** sends a message via Rabbitmq to **payment-processing-service** to get payment authorization. Once the payment got approved, it also sends a Http request to **order-service** to update order status.

----------

API Overview
-------------
Method   | Path  | Description 
--------| -----  | --------------
POST    | /payment | make payment for an order
GET     | /payment/{paymentId} | get payment by payment id.
GET     | /payment?orderId={orderId}|get payment by order id.


----------

API References
-------------
#### 1. Make payment

&nbsp;&nbsp;&nbsp;&nbsp;URL: `/payment`

&nbsp;&nbsp;&nbsp;&nbsp;HTTP Method: **POST**

&nbsp;&nbsp;&nbsp;&nbsp;Success Response: 200 (Ok) 

&nbsp;&nbsp;&nbsp;&nbsp;Error Response: 400 (Bad Request) 

#### 2. Get payment by payment id

&nbsp;&nbsp;&nbsp;&nbsp;URL: `/payment/{paymentId}`

&nbsp;&nbsp;&nbsp;&nbsp;HTTP Method: **GET**

&nbsp;&nbsp;&nbsp;&nbsp;Success Response: 200 (Ok) 

#### 3. Get payment by order id

&nbsp;&nbsp;&nbsp;&nbsp;URL: `/payment?orderId={orderId}`

&nbsp;&nbsp;&nbsp;&nbsp;HTTP Method: **GET**

&nbsp;&nbsp;&nbsp;&nbsp;Success Response: 200 (Ok) 

----------