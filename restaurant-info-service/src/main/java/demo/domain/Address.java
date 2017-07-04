package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Address {

    private String address, city, state;
    private Integer zipcode;

    public Address() {
    }

    public Address(String state, String city, String address, Integer zipcode) {
        this.state = state;
        this.city = city;
        this.address = address;
        this.zipcode = zipcode;
    }

}
