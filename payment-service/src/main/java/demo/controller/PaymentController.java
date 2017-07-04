package demo.controller;

import demo.domain.Payment;
import demo.domain.PaymentStatus;
import demo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;


@RestController
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    private static final String REQUEST_TIMEOUT_RESPONSE = "Sorry, server is busy. Please try again later.";

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // ------------ Get payment by paymentId ----------------------------------------------------------

    @RequestMapping(value = "/payment/{paymentId}", method = RequestMethod.GET)
    public Payment getPaymentInfoById(@PathVariable("paymentId") String paymentId) {
        return this.paymentService.getPaymentById(paymentId);
    }

    // ------------ Get payment by orderId ----------------------------------------------------------

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public Payment getPaymentInfoByOrderId(@RequestParam("orderId") String orderId) {
        return this.paymentService.getPaymentByOrderId(orderId);
    }

    // ------------ make a payment ----------------------------------------------------------

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public DeferredResult<ResponseEntity<?>> makePayment(@RequestBody Payment payment) {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        Payment newPayment = this.paymentService.savePayment(payment);
        ListenableFuture<AuthorizationResponse> authorizationResponse = this.paymentService.makePayment(newPayment);
        authorizationResponse.addCallback(new ListenableFutureCallback<AuthorizationResponse>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("Fail to make a payment for order {}", newPayment.getOrderId(), throwable);
                newPayment.setPaymentStatus(PaymentStatus.TIMEOUT);
                ResponseEntity<String> responseEntity =
                        new ResponseEntity<String>(REQUEST_TIMEOUT_RESPONSE, HttpStatus.REQUEST_TIMEOUT);
                deferredResult.setResult(responseEntity);
                paymentService.savePayment(newPayment);
            }

            @Override
            public void onSuccess(AuthorizationResponse authorizationResponse) {
                PaymentResponse paymentResponse = new PaymentResponse();
                paymentResponse.setPaymentId(newPayment.getId());
                paymentResponse.setOrderId(newPayment.getOrderId());
                if(authorizationResponse.isApproved()) {
                    paymentResponse.setRandomDeliveryTime(5, 60);
                    paymentResponse.setApproved(true);
                    paymentResponse.setMessage("Order has been placed successfully. Thanks you.");
                    newPayment.setPaymentStatus(PaymentStatus.APPROVED);
                    OrderStatusUpdateMessage message = new OrderStatusUpdateMessage(newPayment.getId(), OrderStatus.COMPLETED);
                    log.info("updating status of order {}", newPayment.getOrderId());
                    paymentService.updateOrderStatusAfterPayment(newPayment.getOrderId(), message);
                } else {
                    paymentResponse.setMessage("Payment is declined. Please re-check your payment method.");
                    newPayment.setPaymentStatus(PaymentStatus.DECLINED);
                }
                paymentService.savePayment(newPayment);
                ResponseEntity<PaymentResponse> responseEntity =
                        new ResponseEntity<PaymentResponse>(paymentResponse, HttpStatus.OK);
                deferredResult.setResult(responseEntity);
            }
        });
        return deferredResult;
    }

}
