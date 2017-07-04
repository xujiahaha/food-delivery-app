package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.*;

@Data
@Document(collection = "orders")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    @Id
    private String id;

    @Indexed
    private String userId;
    private String paymentId;
    private OrderStatus orderStatus;
    private List<Item> items = new ArrayList<>();
    private DeliveryInfo deliveryInfo;
    private double totalCost;
    private String note;
    @CreatedDate
    private Date creationTime;
    @LastModifiedDate
    private Date lastModifyTime;

    private Map<OrderStatus, Date> updateHistory = new HashMap<>();

    public Order() {
    }

    @JsonCreator
    public Order(@JsonProperty("items") List<Item> items,
                 @JsonProperty("deliveryInfo") DeliveryInfo deliveryInfo,
                 @JsonProperty("note") String note) {
        this.id = id;
        this.items = items;
        this.deliveryInfo = deliveryInfo;
        this.note = note;
        this.creationTime = new Date();
        this.totalCost = calculateTotalCost(this.items);
        this.orderStatus = OrderStatus.PENDING;
        this.updateHistory.put(this.orderStatus, this.creationTime);
    }

    private double calculateTotalCost(List<Item> items) {
        double cost = 0L;
        for(Item item : items) {
            cost += item.getPrice()*100;
        }
        return cost/100.0;
    }

}
