package ing.beribtur.aggregate.report.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportCdo extends CreationDataObject {
    //
    private String reporterId;
    private String reason;
    private LocalDateTime reportDate;
    private boolean resolved;
    private String recordId;

    public String genId() {
        //
        return super.genId();
    }
}
