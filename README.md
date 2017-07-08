# food-delivery-app
Spring Boot application based on microservices architecture. 

**Done**
- Search restaurant by name.
- Get restaurant info.
- Place order, get order details.
- Make payment.
- Handle payment errors, such as payment authorization timeout and invalid credit card info.
> Currently it is assumed that all users are anonymous (not registered) and shopping cart is managed at the front end.
Once we have the account-service in the future, the shopping cart service can be implemented at the back end.

**TO DO**
- [ ] Take delivery distance, restaurant hours into consideration.
- [ ] Add account-service to manage user registration and login.
- [ ] Finish Spring Cloud platform.
- [ ] Test system performance of handling high volume of requests.

### System Architecture
![alt image](system_architecture.png)
> Detailed information about each service can be found in service folder.

### Requirements
- Java JDK 8
- Apache Maven
- Docker

### Get Started
1. Run `docker-compose up -d` to start MongoDB and Rabbitmq if you don't have them on your local computer.
2. Build then start services: <br />
restaurant-info-service --> order-service --> payment-service --> payment-processing-service.
3. Upload restaurant test data in `restaurant.json`.
4. Open restaurant UI on http://localhost:8080
