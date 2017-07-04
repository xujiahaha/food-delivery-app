package demo.service.impl;

import demo.domain.Payment;
import demo.domain.PaymentRepository;
import demo.domain.PaymentStatus;
import demo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.RestTemplate;

import static demo.service.PaymentProducerConfig.PAYMENT_EXCHANGE_NAME;
import static demo.service.PaymentProducerConfig.ROUTING_KEY_NAME;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private static final long DEFAULT_RESPONSE_RECEIVE_TIMEOUT = 6000L;

    private PaymentRepository paymentRepository;

    private AsyncRabbitTemplate asyncRabbitTemplate;

    private RestTemplate restTemplate;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              AsyncRabbitTemplate asyncRabbitTemplate,
                              RestTemplate restTemplate) {
        this.paymentRepository = paymentRepository;
        this.asyncRabbitTemplate = asyncRabbitTemplate;
        this.restTemplate = restTemplate;
    }

    @Override
    public Payment savePayment(Payment payment) {
        return this.paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(String paymentId) {
        return this.paymentRepository.findOne(paymentId);
    }

    @Override
    public Payment getPaymentByOrderId(String orderId) {
        return this.paymentRepository.findByOrderId(orderId);
    }


    @Override
    public ListenableFuture<AuthorizationResponse> makePayment(Payment payment) {
        AuthorizationRequest request = new AuthorizationRequest(payment.getCreditCard(), payment.getAmount());
        log.info("getting authorization from payment processing service...");
        asyncRabbitTemplate.setReceiveTimeout(DEFAULT_RESPONSE_RECEIVE_TIMEOUT);
        return asyncRabbitTemplate.convertSendAndReceive(PAYMENT_EXCHANGE_NAME, ROUTING_KEY_NAME, request);
    }

    @Override
    public void updateOrderStatusAfterPayment(String orderId, OrderStatusUpdateMessage orderStatusUpdateMessage) {
        String url = "http://localhost:8989/order/";
        restTemplate.postForLocation(url+orderId, orderStatusUpdateMessage);
    }

}
