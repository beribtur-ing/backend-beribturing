package ing.beribtur.aggregate.user.logic;

import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.store.LendeeStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LendeeLogic {
    //
    private final LendeeStore lendeeStore;

    public void create(Lendee lendee){
        lendeeStore.create(lendee);
    }

    public Lendee retrieve(String id){
        return lendeeStore.retrieve(id);
    }

    public List<Lendee> retrieveAll(List<String> ids){
        return lendeeStore.retrieveAll(ids);
    }

    public void update(Lendee lendee){
        lendeeStore.update(lendee);
    }

    public void delete(String id){
        lendeeStore.delete(id);
    }

}
