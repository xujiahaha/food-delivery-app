package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Data
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cardNumber;
    private String cardHolderName;
    private String expiryMonth;
    private String expiryYear;
    private String securityCode;

    public CreditCard() {
    }

    @JsonCreator
    public CreditCard(@JsonProperty("cardNumber") String cardNumber,
                      @JsonProperty("cardHolderName") String cardHolderName,
                      @JsonProperty("expiryMonth") String expiryMonth,
                      @JsonProperty("expiryYear") String expiryYear,
                      @JsonProperty("securityCode") String securityCode) {
        if(cardNumber == null || cardNumber.length() != 16 || !isValidNumber(cardNumber)) {
            throw new IllegalArgumentException("Invalid credit card number " + cardNumber);
        }
        this.cardNumber = cardNumber;
        if(! isValidName(cardHolderName)) {
            throw new IllegalArgumentException("Invalid card holder name " + cardHolderName);
        }
        this.cardHolderName = cardHolderName;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        if(securityCode == null || securityCode.length() != 3 || !isValidNumber(securityCode)) {
            throw new IllegalArgumentException("Invalid security code " + securityCode);
        }
        this.securityCode = securityCode;
    }

    private boolean isValidNumber(String number) {
        return number.matches("[0-9]+");
    }

    private boolean isValidName(String name) {
        // assume card holder name is at least 3 letters and can only have letters, spaces, and dot.
        if(name == null) return false;
        name = name.trim().toUpperCase();
        return name.length() >= 3 && name.matches("[A-Z\\.\\s]+");
    }

}
