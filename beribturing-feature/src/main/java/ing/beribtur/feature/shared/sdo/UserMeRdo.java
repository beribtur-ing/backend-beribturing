package ing.beribtur.feature.shared.sdo;

import ing.beribtur.aggregate.account.entity.vo.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserMeRdo {
    private String avatarUrl;
    private String phoneNumber;
    private String email;
    private Role role;
    private String name;
}
