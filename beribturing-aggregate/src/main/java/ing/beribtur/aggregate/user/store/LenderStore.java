package ing.beribtur.aggregate.user.store;


import ing.beribtur.aggregate.user.entity.Lender;

import java.util.List;

public interface LenderStore {
    //
    Lender create(Lender lender);
    Lender retrieve(String id);
    List<Lender> retrieveAll(List<String> ids);
    Lender update(Lender lender);
    void delete(String id);
}
