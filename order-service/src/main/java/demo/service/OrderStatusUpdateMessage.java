package demo.service;

import demo.domain.OrderStatus;

public class OrderStatusUpdateMessage {
    private String paymentId;
    private OrderStatus orderStatus;

    public OrderStatusUpdateMessage() {
    }

    public OrderStatusUpdateMessage(String paymentId, OrderStatus orderStatus) {
        this.paymentId = paymentId;
        this.orderStatus = orderStatus;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderStatusUpdateMessage{" +
                "paymentId='" + paymentId + '\'' +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
