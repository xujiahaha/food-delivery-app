package demo.service.impl;

import demo.domain.Order;
import demo.domain.OrderRepository;
import demo.domain.OrderStatus;
import demo.service.OrderService;
import demo.service.OrderStatusUpdateMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findByOrderId(String orderId) {
        return this.orderRepository.findById(orderId);
    }

    @Override
    public List<Order> findByUserId(Sort sort, String userId) {
        return this.orderRepository.findByUserId(sort, userId);
    }

    @Override
    public Order saveOrder(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public void updateOrderStatus(String orderId, OrderStatusUpdateMessage orderStatusUpdateMessage) {
        Order order = this.orderRepository.findOne(orderId);
        OrderStatus newStatus = orderStatusUpdateMessage.getOrderStatus();
        order.setOrderStatus(newStatus);
        order.setPaymentId(orderStatusUpdateMessage.getPaymentId());
        Date lastModifiedAt = new Date();
        order.setLastModifyTime(lastModifiedAt);
        order.getUpdateHistory().put(newStatus, lastModifiedAt);
        this.orderRepository.save(order);
        logger.info("status of order {} has been updated successfully", orderId);
    }

}
