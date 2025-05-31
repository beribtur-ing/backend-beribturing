package ing.beribtur.aggregate.user.store;


import ing.beribtur.aggregate.user.entity.Lender;

import java.util.List;

public interface LenderStore {
    //
    void create(Lender lender);
    Lender retrieve(String id);
    List<Lender> retrieveAll(List<String> ids);
    void update(Lender lender);
    void delete(String id);
}
