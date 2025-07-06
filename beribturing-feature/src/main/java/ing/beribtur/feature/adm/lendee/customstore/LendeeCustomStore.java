package ing.beribtur.feature.adm.lendee.customstore;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.entity.Lender;
import org.springframework.data.domain.Page;


public interface LendeeCustomStore {
    Page<Lendee> findLendees(String searchKeyword, String status, Offset offset);
}
