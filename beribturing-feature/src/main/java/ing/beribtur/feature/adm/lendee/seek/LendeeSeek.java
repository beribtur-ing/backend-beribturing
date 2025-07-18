package ing.beribtur.feature.adm.lendee.seek;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.logic.LendeeLogic;
import ing.beribtur.feature.adm.lendee.customstore.LendeeCustomStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LendeeSeek {
    //
    private final LendeeCustomStore lendeeCustomStore;
    private final LendeeLogic lendeeLogic;

    public Page<Lendee> findLendeesAdmin(String searchKeyword, String status, Offset offset) {
        return lendeeCustomStore.findLendees(searchKeyword, status, offset);
    }

    public Lendee findLendeeAdmin(String lendeeId) {
        //
        return lendeeLogic.findLendee(lendeeId);
    }
}
