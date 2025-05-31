package ing.beribtur.aggregate.report.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.user.entity.Lendee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Report extends DomainEntity {
    //
    private String reporterId;
    private String reason;
    private LocalDateTime reportDate;
    private boolean resolved;

    // Domain relationships
    private transient Lendee reporter;
    private transient RentalRecord record;
}
