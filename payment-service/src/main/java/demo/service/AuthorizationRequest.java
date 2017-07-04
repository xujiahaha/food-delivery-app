package demo.service;

import demo.domain.CreditCard;


public class AuthorizationRequest {

    private CreditCard creditCard;
    private double amount;

    public AuthorizationRequest() {
    }


    public AuthorizationRequest(CreditCard creditCard, double amount) {
        this.creditCard = creditCard;
        this.amount = amount;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
