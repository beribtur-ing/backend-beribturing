package ing.beribtur.feature.shared.action;

import ing.beribtur.accent.context.SpaceContext;
import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthHelper {
    //
    private final AccountLogic accountLogic;

    public String currentUserId(Role role) {
        //
        String phoneNumber = SpaceContext.get().getUsername();
        Account currentAccount = accountLogic.findByPhoneNumberAndRole(phoneNumber, role.name());
        return currentAccount.getId();
    }
}
