package ing.beribtur.aggregate.renter.entity;


import ing.beribtur.aggregate.shared.entity.User;

import java.time.LocalDateTime;

public class Dispute {
    //
    private Long id;
    private Rental rental;
    private User raisedBy;
    private String reason;
    private String resolution;
    private boolean resolved;
    private LocalDateTime raisedAt;
}

