package ing.beribtur.aggregate.shared.store;



import ing.beribtur.aggregate.shared.entity.User;

import java.util.List;

public interface UserStore {
    //
    User create(User user);
    User retrieve(Long id);
    List<User> retrieveAll(List<Long> ids);
    User update(User user);
    void delete(Long id);
}
