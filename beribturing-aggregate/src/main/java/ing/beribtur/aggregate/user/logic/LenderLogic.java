package ing.beribtur.aggregate.user.logic;

import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.aggregate.user.store.LenderStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LenderLogic {
    //
    private final LenderStore lenderStore;

    public void create(Lender lender) {
        lenderStore.create(lender);
    }

    public Lender retrieve(String id) {
        return lenderStore.retrieve(id);
    }

    public void update(Lender lender) {
        lenderStore.update(lender);
    }

    public void delete(String id) {
        lenderStore.delete(id);
    }

    public List<Lender> retrieveAll(List<String> ids) {
        return lenderStore.retrieveAll(ids);
    }

}
