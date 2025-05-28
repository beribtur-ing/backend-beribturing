package ing.beribtur.aggregate.rentalprovider.entity;

import ing.beribtur.aggregate.renter.entity.Rental;
import ing.beribtur.aggregate.shared.entity.vo.Address;

import java.time.LocalDateTime;

public class DeliveryInfo {
    private Long id;

    private Rental rental;

    private boolean requiresDelivery;
    private String deliveryNotes;

    private Address pickupAddress;

    private Address dropoffAddress;

    private LocalDateTime scheduledPickup;
    private LocalDateTime scheduledDropOff;
}

