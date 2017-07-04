package demo.service.impl;

import demo.model.CreditCard;
import demo.service.AuthorizationRequest;
import demo.service.AuthorizationResponse;
import demo.service.PaymentConsumerConfig;
import demo.service.PaymentProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentProcessingServiceImpl implements PaymentProcessingService {

    private static final Logger log = LoggerFactory.getLogger(PaymentProcessingServiceImpl.class);


    @Override
    @RabbitListener(queues = PaymentConsumerConfig.REQUEST_QUEUE_NAME)
    public AuthorizationResponse processPayment(AuthorizationRequest request) throws InterruptedException {
        AuthorizationResponse response = new AuthorizationResponse();
        boolean isApproved = validateCreditCard(request.getCreditCard(), request.getAmount());
        response.setApproved(isApproved);
        log.info("{}",response.isApproved());
        return response;
    }

    /*
    * This method is designed to simulate the process of getting authorization from card issuer.
    * In real world, this process may take 2~3 seconds.
    * The case of response timeout is considered also.
    * */
    private static boolean validateCreditCard(CreditCard creditCard, double amount) throws InterruptedException {
        log.info("Validating credit card information on thread " + Thread.currentThread().getName());
        long validatingTime = getRandomPaymentProcessTime(1000, 2000);
        Thread.sleep(validatingTime);
        return getRandomApproval();
    }

    /*
    * generate random sleeping time. [min, max]
    * */
    private static int getRandomPaymentProcessTime(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    /*
    * This method is used to generate random payment result.
    * 90% of payment request will get approved, 10% will get declined.
    * */
    private static boolean getRandomApproval() {
        Random rand = new Random();
        if(rand.nextInt(100) < 95) {
            return true;
        }
        return false;
    }
}
