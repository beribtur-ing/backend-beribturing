package ing.beribtur.aggregate.user.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.aggregate.user.entity.vo.LenderType;
import ing.beribtur.aggregate.user.entity.vo.Profile;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LenderCdo extends CreationDataObject {
    //
    private String name;
    private String phoneNumber;    //can be used as username
    private LenderType lenderType;
    private boolean active;
    private Profile profile;
    private String accountId; // Reference to the Account entity

    @Override
    public String genId() {
        return Lender.genId(accountId);
    }
}
