package ing.beribtur.aggregate.user.store;


import ing.beribtur.aggregate.user.entity.Lendee;

import java.util.List;

public interface LendeeStore {
    //
    void create(Lendee lendee);
    Lendee retrieve(String id);
    List<Lendee> retrieveAll(List<String> ids);
    void update(Lendee lendee);
    void delete(String id);

    Lendee findByPhoneNumber(String username);
}
