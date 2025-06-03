package ing.beribtur.aggregate.report.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
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
    private String recordId;

    // Domain relationships
    private transient Lendee reporter;
    private transient RentalRecord record;

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName().trim()) {
                case "reporterId" -> this.reporterId = value;
                case "reason" -> this.reason = value;
                case "reportDate" -> this.reportDate = LocalDateTime.parse(value);
                case "resolved" -> this.resolved = Boolean.parseBoolean(value);
                case "recordId" -> this.recordId = value;
                default -> throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }
}
