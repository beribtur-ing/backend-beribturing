package ing.beribtur.aggregate.user.logic;

import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.aggregate.user.store.LenderStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Lender> findDisabledLendersAdmin(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, sort != null ? Sort.by(sort) : Sort.unsorted());
        return lenderStore.findDisabledLenders(pageable);
    }

    public long nextProductSequence(String lenderId) {
        //
        Lender lender = retrieve(lenderId);
        long sequence = lender.getProductSequence();
        lender.setProductSequence(sequence + 1);
        update(lender);
        return sequence;
    }
}
