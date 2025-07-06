package ing.beribtur.feature.adm.user.sdo;

import ing.beribtur.aggregate.user.entity.Lender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsersRdo {
    private Lender lender;
}
