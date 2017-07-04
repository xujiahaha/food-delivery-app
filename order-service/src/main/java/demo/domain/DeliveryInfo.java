package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryInfo {

    private String userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address address;

}
