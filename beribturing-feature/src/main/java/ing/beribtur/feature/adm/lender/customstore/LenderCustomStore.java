package ing.beribtur.feature.adm.lender.customstore;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.user.entity.Lender;
import org.springframework.data.domain.Page;


public interface LenderCustomStore {
    Page<Lender> findLenders(String searchKeyword, String status, Offset offset);
}
