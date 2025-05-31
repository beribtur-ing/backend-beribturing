package ing.beribtur.aggregate.account.entity;


import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.aggregate.account.entity.vo.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account extends DomainEntity {
    //
    private String id;
    private String phoneNumber;
    private String password;
    private String email;
    private Role role;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
}
