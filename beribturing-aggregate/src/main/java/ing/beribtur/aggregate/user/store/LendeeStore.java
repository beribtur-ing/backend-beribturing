package ing.beribtur.aggregate.user.store;


import ing.beribtur.aggregate.user.entity.Lendee;

import java.util.List;

public interface LendeeStore {
    //
    Lendee create(Lendee lendee);
    Lendee retrieve(String id);
    List<Lendee> retrieveAll(List<String> ids);
    Lendee update(Lendee lendee);
    void delete(String id);
}
