package ing.beribtur.feature.adm.user.flow;

import ing.beribtur.accent.context.SpaceContext;
import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAdmFlow {
    //
    private final AccountLogic accountLogic;
    private final PasswordEncoder passwordEncoder;

    public String changePassword(String currentPassword, String newPassword) {
        String username = SpaceContext.get().getUsername();
        Account account = accountLogic.findByPhoneNumberAndRole(username, Role.ROLE_ADMIN.name());
        
        Assert.isTrue(passwordEncoder.matches(currentPassword, account.getPassword()), 
            "Current password is incorrect");
        
        account.setPassword(passwordEncoder.encode(newPassword));
        accountLogic.modifyAccount(account);
        
        return account.getId();
    }
}