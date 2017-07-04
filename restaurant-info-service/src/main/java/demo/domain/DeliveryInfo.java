package demo.domain;

import lombok.Data;

@Data
public class DeliveryInfo {

    private static final double DEFAULT_DELIVERY_DISTANCE = 20; // in km

    private double charge;
    private double maxDeliveryDistance; // only delivery to location within some, say 10km, distance.
    private String averageDeliveryTime;

    public DeliveryInfo() {
    }

    public DeliveryInfo(double charge) {
        this.charge = charge;
    }

    public DeliveryInfo(double charge, double maxDeliveryDistance) {
        this.charge = charge;
        this.maxDeliveryDistance = maxDeliveryDistance;
    }

    public double getMaxDeliveryDistance() {
        return maxDeliveryDistance;
    }

    public void setMaxDeliveryDistance(double maxDeliveryDistance) {
        this.maxDeliveryDistance = maxDeliveryDistance;
    }
}
