package ing.beribtur.aggregate.account.entity;


import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
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
        //
        super(accountCdo.genId());
        BeanUtils.copyProperties(accountCdo, this);
    }

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        //
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName().trim()) {
                case "phoneNumber" -> this.phoneNumber = value;
                case "password" -> this.password = value;
                case "email" -> this.email = value;
                case "role" -> this.role = Role.valueOf(value);
                case "enabled" -> this.enabled = Boolean.parseBoolean(value);
                case "accountNonExpired" -> this.accountNonExpired = Boolean.parseBoolean(value);
                case "accountNonLocked" -> this.accountNonLocked = Boolean.parseBoolean(value);
                case "credentialsNonExpired" -> this.credentialsNonExpired = Boolean.parseBoolean(value);
                default -> throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }
}
