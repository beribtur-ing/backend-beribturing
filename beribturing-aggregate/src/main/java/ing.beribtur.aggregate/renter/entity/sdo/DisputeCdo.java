package ing.beribtur.aggregate.renter.entity.sdo;

import ing.beribtur.aggregate.renter.entity.Rental;
import ing.beribtur.aggregate.shared.entity.User;

import java.time.LocalDateTime;

public class DisputeCdo {
    //
    private Rental rental;
    private User raisedBy;
    private String reason;
    private String resolution;
    private boolean resolved;
    private LocalDateTime raisedAt;
}

