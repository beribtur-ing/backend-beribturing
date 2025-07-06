package ing.beribtur.aggregate.user.logic;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.util.Entities;
import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.aggregate.user.entity.sdo.LenderCdo;
import ing.beribtur.aggregate.user.store.LenderStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LenderLogic {
    //
    private final LenderStore lenderStore;

    public String registerLender(LenderCdo lenderCdo) {
        //
        Lender lender = new Lender(lenderCdo);
        if (lenderStore.exists(lender.getId())) {
            throw new IllegalArgumentException("lender already exists. " + lender.getId());
        }
        lenderStore.create(lender);
        return lender.getId();
    }

    public List<String> registerLenders(List<LenderCdo> lenderCdos) {
        //
        return lenderCdos.stream().map(this::registerLender).collect(Collectors.toList());
    }

    public Lender findLender(String lenderId) {
        //
        Lender lender = lenderStore.retrieve(lenderId);
        if (lender == null) {
            throw new NoSuchElementException("Lender id: " + lenderId);
        }
        return lender;
    }

    public List<Lender> findLenders(Offset offset) {
        //
        return lenderStore.retrieveList(offset);
    }

    public void modifyLender(String lenderId, NameValueList nameValues) {
        //
        Lender lender = findLender(lenderId);
        lender.modify(nameValues);
        lenderStore.update(lender);
    }

    public void modifyLender(Lender lender) {
        //
        Lender oldLender = findLender(lender.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldLender, lender);
        if (!nameValues.list().isEmpty()) {
            modifyLender(lender.getId(), nameValues);
        }
    }

    public void removeLender(String lenderId) {
        //
        Lender lender = findLender(lenderId);
        lenderStore.delete(lender);
    }

    public boolean existsLender(String lenderId) {
        //
        return lenderStore.exists(lenderId);
    }

    public List<Lender> retrieveAll(List<String> ids) {
        return lenderStore.retrieveAll(ids);
    }

    public long nextProductSequence(String lenderId) {
        //
        Lender lender = findLender(lenderId);
        long sequence = lender.getProductSequence();
        lender.setProductSequence(sequence + 1);
        modifyLender(lender);
        return sequence;
    }

    public Lender findByPhoneNumber(String username) {
        return lenderStore.findByPhoneNumber(username);
    }

    public long countActiveUsers() {
        //
        return lenderStore.countActiveUsers();
    }
}
