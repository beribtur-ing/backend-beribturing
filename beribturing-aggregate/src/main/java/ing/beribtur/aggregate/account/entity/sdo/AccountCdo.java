package ing.beribtur.aggregate.account.entity.sdo;


import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.account.entity.vo.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountCdo extends CreationDataObject {
    private String phoneNumber;
    private String password;
    private String email;
    private Role role;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
}
