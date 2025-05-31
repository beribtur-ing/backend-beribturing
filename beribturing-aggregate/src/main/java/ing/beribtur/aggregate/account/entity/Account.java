package ing.beribtur.aggregate.account.entity;


import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.account.entity.sdo.AccountCdo;
import ing.beribtur.aggregate.account.entity.vo.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account extends DomainEntity {
    //
    private String phoneNumber;
    private String password;
    private String email;
    private Role role;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    public Account(AccountCdo accountCdo) {
        super(accountCdo.genId());
        BeanUtils.copyProperties(accountCdo, this);
    }

    @Override
    protected void modifyAttributes(NameValueList var1) {

    }
}
