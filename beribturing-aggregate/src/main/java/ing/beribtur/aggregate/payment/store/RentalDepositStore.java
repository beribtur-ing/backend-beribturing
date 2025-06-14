package ing.beribtur.aggregate.payment.store;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.payment.entity.RentalDeposit;
import ing.beribtur.aggregate.payment.entity.vo.DepositStatus;

import java.util.List;

public interface RentalDepositStore {
    //
    void create(RentalDeposit rentalDeposit);
    RentalDeposit retrieve(String id);
    List<RentalDeposit> retrieveAll(List<String> ids);
    List<RentalDeposit> retrieveList(Offset offset);
    void update(RentalDeposit rentalDeposit);
    void delete(String id);
    boolean exists(String id);
    
    RentalDeposit retrieveByRentalRecordId(String rentalRecordId);
    List<RentalDeposit> retrieveByPayerId(String payerId);
    List<RentalDeposit> retrieveByStatus(DepositStatus status);
}
