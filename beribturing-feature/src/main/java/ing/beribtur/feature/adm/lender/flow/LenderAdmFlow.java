package ing.beribtur.feature.adm.lender.flow;


import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.aggregate.user.logic.LenderLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LenderAdmFlow {
    //
    private final LenderLogic lenderLogic;
    private final AccountLogic accountLogic;

    public String modifyLenderStatus(String lenderId) {
        Lender lender = lenderLogic.findLender(lenderId);
        lender.setActive(!lender.isActive());
        lenderLogic.modifyLender(lender);

        Account account = accountLogic.findAccount(lenderId);
        account.setEnabled(!account.isEnabled());
        accountLogic.modifyAccount(account);
        return lenderId;
    }
}
