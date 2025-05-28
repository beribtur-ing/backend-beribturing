package ing.beribtur.aggregate.renter.entity;

import ing.beribtur.aggregate.rentalprovider.entity.RentalItem;
import ing.beribtur.aggregate.shared.entity.User;

import java.time.LocalDateTime;

public class Review {
    //
    private Long id;
    private Rental rental;
    private User reviewer;
    private RentalItem item;
    private int rating; // 1–5
    private String comment;
    private LocalDateTime reviewedAt;
}
