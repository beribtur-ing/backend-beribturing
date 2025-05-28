package ing.beribtur.aggregate.renter.entity.sdo;

import ing.beribtur.aggregate.rentalprovider.entity.RentalItem;
import ing.beribtur.aggregate.renter.entity.Rental;
import ing.beribtur.aggregate.shared.entity.User;

import java.time.LocalDateTime;


public class ReviewCdo {
    //
    private Rental rental;
    private User reviewer;
    private RentalItem item;
    private int rating; // 1–5
    private String comment;
    private LocalDateTime reviewedAt;
}
