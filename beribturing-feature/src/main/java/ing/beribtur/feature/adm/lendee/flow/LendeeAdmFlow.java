package ing.beribtur.feature.adm.lendee.flow;


import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.logic.LendeeLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LendeeAdmFlow {
    //
    private final LendeeLogic lendeeLogic;
    private final AccountLogic accountLogic;

    public String modifyLendeeStatus(String lendeeId) {
        Lendee lendee = lendeeLogic.findLendee(lendeeId);
        lendee.setActive(!lendee.isActive());
        lendeeLogic.modifyLendee(lendee);

        Account account = accountLogic.findAccount(lendeeId);
        account.setEnabled(!account.isEnabled());
        accountLogic.modifyAccount(account);
        return lendeeId;
    }
}
