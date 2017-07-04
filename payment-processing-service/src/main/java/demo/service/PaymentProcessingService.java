package demo.service;


public interface PaymentProcessingService {

    AuthorizationResponse processPayment(AuthorizationRequest request) throws InterruptedException;
}
