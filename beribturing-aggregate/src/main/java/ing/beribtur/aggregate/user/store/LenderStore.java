package ing.beribtur.aggregate.user.store;


import ing.beribtur.aggregate.user.entity.Lender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LenderStore {
    //
    void create(Lender lender);
    Lender retrieve(String id);
    List<Lender> retrieveAll(List<String> ids);
    void update(Lender lender);
    void delete(String id);
    Page<Lender> findDisabledLenders(Pageable pageable);
}
