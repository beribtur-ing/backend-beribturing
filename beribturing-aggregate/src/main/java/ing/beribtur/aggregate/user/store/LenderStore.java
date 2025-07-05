package ing.beribtur.aggregate.user.store;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.user.entity.Lender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LenderStore {
    //
    void create(Lender lender);
    void createAll(List<Lender> lenders);
    Lender retrieve(String id);
    List<Lender> retrieveAll(List<String> ids);
    List<Lender> retrieveList(Offset offset);
    void update(Lender lender);
    void delete(Lender lender);
    void delete(String id);
    boolean exists(String id);
    Page<Lender> findDisabledLenders(Pageable pageable);

    Lender findByPhoneNumber(String username);
    
    // Count methods for statistics
    long countActiveUsers();
}
