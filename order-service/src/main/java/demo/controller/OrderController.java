package demo.controller;

import demo.domain.Order;
import demo.service.OrderService;
import demo.service.OrderStatusUpdateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private static final String DEFAULT_ORDER_SORT_BY = "creationTime";
    private static final String DEFAULT_ORDER_SORT_DIR = "desc";

    private static final String REQUEST_TIMEOUT_RESPONSE = "Sorry, server is busy. Please try again later.";

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderInfoService) {
        this.orderService = orderInfoService;
    }

    // ------------ Get order by order id ----------------------------------------------------------

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Order findByOrderId(@PathVariable("orderId") String orderId) {
        return this.orderService.findByOrderId(orderId);
    }

    // ------------ Get all the orders placed by one user --------------------------------------------

    @RequestMapping(value = "/{userId}/orders/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Order>> findUserOrders(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(this.orderService.findByUserId(new Sort(DEFAULT_ORDER_SORT_DIR, DEFAULT_ORDER_SORT_BY), userId));
    }

    // ----------- Place order ------------------------------------------------------------------------

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Order placeOrder(@RequestBody Order order) {
        return this.orderService.saveOrder(order);
    }

    // ----------- update order status ------------------------------------------------------------------------

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.POST)
    public void updateOrderStatus(@PathVariable("orderId") String orderId,
                                  @RequestBody OrderStatusUpdateMessage orderStatusUpdateMessage) {
        this.orderService.updateOrderStatus(orderId, orderStatusUpdateMessage);
    }

}
