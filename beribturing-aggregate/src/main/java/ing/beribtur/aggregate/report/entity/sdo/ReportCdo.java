package ing.beribtur.aggregate.report.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportCdo extends CreationDataObject {
    //
    private String reporterId;
    private String reason;
    private String recordId;

    public String genId() {
        //
        return super.genId();
    }
}
