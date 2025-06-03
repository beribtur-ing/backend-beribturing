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
    @Builder.Default
    private boolean enabled=true;
    @Builder.Default
    private boolean accountNonExpired=true;
    @Builder.Default
    private boolean accountNonLocked=true;
    @Builder.Default
    private boolean credentialsNonExpired=true;
}
