package ing.beribtur.aggregate.user.logic;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.util.Entities;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.entity.sdo.LendeeCdo;
import ing.beribtur.aggregate.user.store.LendeeStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LendeeLogic {
    //
    private final LendeeStore lendeeStore;

    public String registerLendee(LendeeCdo lendeeCdo) {
        //
        Lendee lendee = new Lendee(lendeeCdo);
        if (lendeeStore.exists(lendee.getId())) {
            throw new IllegalArgumentException("lendee already exists. " + lendee.getId());
        }
        lendeeStore.create(lendee);
        return lendee.getId();
    }

    public List<String> registerLendees(List<LendeeCdo> lendeeCdos) {
        //
        return lendeeCdos.stream().map(this::registerLendee).collect(Collectors.toList());
    }

    public Lendee findLendee(String lendeeId) {
        //
        Lendee lendee = lendeeStore.retrieve(lendeeId);
        if (lendee == null) {
            throw new NoSuchElementException("Lendee id: " + lendeeId);
        }
        return lendee;
    }

    public List<Lendee> findLendees(Offset offset) {
        //
        return lendeeStore.retrieveList(offset);
    }

    public List<Lendee> retrieveAll(List<String> ids){
        return lendeeStore.retrieveAll(ids);
    }

    public void modifyLendee(String lendeeId, NameValueList nameValues) {
        //
        Lendee lendee = findLendee(lendeeId);
        lendee.modify(nameValues);
        lendeeStore.update(lendee);
    }

    public void modifyLendee(Lendee lendee) {
        //
        Lendee oldLendee = findLendee(lendee.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldLendee, lendee);
        if (!nameValues.list().isEmpty()) {
            modifyLendee(lendee.getId(), nameValues);
        }
    }

    public void removeLendee(String lendeeId) {
        //
        Lendee lendee = findLendee(lendeeId);
        lendeeStore.delete(lendee);
    }

    public boolean existsLendee(String lendeeId) {
        //
        return lendeeStore.exists(lendeeId);
    }

    public Lendee findByPhoneNumber(String username) {
        return lendeeStore.findByPhoneNumber(username);
    }
}
