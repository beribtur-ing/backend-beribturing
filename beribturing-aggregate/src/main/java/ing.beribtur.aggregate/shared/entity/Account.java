package ing.beribtur.aggregate.shared.entity;


import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.aggregate.shared.entity.vo.Role;

public class Account extends DomainEntity {
    //
    private String id;
    private String phoneNumber;
    private String password;
    private String email;
    private Role role;
}
