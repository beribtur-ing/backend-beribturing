package ing.beribtur.aggregate.user.store;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.user.entity.Lendee;

import java.util.List;

public interface LendeeStore {
    //
    void create(Lendee lendee);
    void createAll(List<Lendee> lendees);
    Lendee retrieve(String id);
    List<Lendee> retrieveAll(List<String> ids);
    List<Lendee> retrieveList(Offset offset);
    void update(Lendee lendee);
    void delete(Lendee lendee);
    void delete(String id);
    boolean exists(String id);

    Lendee findByPhoneNumber(String username);
}
