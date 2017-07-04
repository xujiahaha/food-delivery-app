package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {

    enum AddressType {
        DEILIVERING,
        BILLING
    }

    private String address, city, state;
    private Integer zipcode;
    private AddressType addressType;

    public Address() {
    }

    public Address(String address, String city, String state, Integer zipcode) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

}
