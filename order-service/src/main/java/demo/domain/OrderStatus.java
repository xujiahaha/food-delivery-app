package demo.domain;

public enum OrderStatus {

    PENDING,              // has been created and is waiting for payment
    COMPLETED,            // paid successfully
    CANCELLED,            // order has been cancelled
    DELIVERYING,         // is on the way for delivery
    DELIVERED           // items have been delivered.

}
