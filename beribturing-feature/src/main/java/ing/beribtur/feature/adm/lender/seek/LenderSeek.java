package ing.beribtur.feature.adm.lender.seek;

import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.aggregate.user.logic.LenderLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LenderSeek {
    private final LenderLogic lenderLogic;

    public Page<Lender> findDisabledLendersAdmin(int page, int size, String sort) {
        return lenderLogic.findDisabledLendersAdmin(page, size, sort);
    }
}
