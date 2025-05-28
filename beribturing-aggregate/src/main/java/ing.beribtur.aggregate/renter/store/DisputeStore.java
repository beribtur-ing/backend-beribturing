package ing.beribtur.aggregate.renter.store;


import ing.beribtur.aggregate.renter.entity.Dispute;

import java.util.List;

public interface DisputeStore {
    //
    Dispute create(Dispute dispute);
    Dispute retrieve(Long id);
    List<Dispute> retrieveAll(List<Long> ids);
    Dispute update(Dispute dispute);
    void delete(Long id);
}
