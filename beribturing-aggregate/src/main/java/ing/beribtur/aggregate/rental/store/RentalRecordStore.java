package ing.beribtur.aggregate.rental.store;


import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.rental.entity.vo.RentalStatus;

import java.util.List;

public interface RentalRecordStore {
    //
    void create(RentalRecord rentalRecord);
    void createAll(List<RentalRecord> rentalRecords);
    RentalRecord retrieve(String id);
    List<RentalRecord> retrieveAll(List<String> ids);
    List<RentalRecord> retrieveList(Offset offset);
    void update(RentalRecord rentalRecord);
    void delete(RentalRecord rentalRecord);
    void delete(String id);
    boolean exists(String id);

    List<RentalRecord> retrieveByLendeeId(String lendeeId);
    List<RentalRecord> retrieveByProductVariantId(String productVariantId);
    List<RentalRecord> retrieveByStatus(RentalStatus status);
    List<RentalRecord> retrieveAllByOwnerId(String ownerId,String status);
}
