package ing.beribtur.feature.adm.lender.seek;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.aggregate.user.logic.LenderLogic;
import ing.beribtur.feature.adm.lender.customstore.LenderCustomStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LenderSeek {
    //
    private final LenderCustomStore lenderCustomStore;
    private final LenderLogic lenderLogic;

    public Page<Lender> findLendersAdmin(String searchKeyword, String status, Offset offset) {
        return lenderCustomStore.findLenders(searchKeyword, status, offset);
    }

    public Lender findLenderAdmin(String lenderId) {
        //
        return lenderLogic.findLender(lenderId);
    }
}
