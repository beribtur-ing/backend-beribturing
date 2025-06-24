package ing.beribtur.aggregate.user.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.entity.vo.Profile;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LendeeCdo extends CreationDataObject {
    //
    private String name;
    private String phoneNumber;
    private boolean active;
    private Profile profile;
    private String accountId; // Reference to the Account entity

    @Override
    public String genId() {
        return Lendee.genId(accountId);
    }
}
