package ing.beribtur.aggregate.notification.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactCdo extends CreationDataObject {
    //
    private String userId;
    private String email;
    private String phoneNumber;

    public String genId() {
        //
        return super.genId();
    }
}
