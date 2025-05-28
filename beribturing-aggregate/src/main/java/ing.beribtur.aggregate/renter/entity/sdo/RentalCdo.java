package ing.beribtur.aggregate.renter.entity.sdo;

import ing.beribtur.aggregate.rentalprovider.entity.DeliveryInfo;
import ing.beribtur.aggregate.rentalprovider.entity.RentalItem;
import ing.beribtur.aggregate.shared.entity.User;
import ing.beribtur.aggregate.shared.entity.vo.RentalStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RentalCdo {
    //
    private User customer;
    private RentalItem item;
    private LocalDateTime rentalStart;
    private LocalDateTime rentalEnd;
    private BigDecimal totalPrice;
    private RentalStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private DeliveryInfo deliveryInfo;
}

