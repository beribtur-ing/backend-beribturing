package ing.beribtur.aggregate.payment.logic;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.util.Entities;
import ing.beribtur.aggregate.payment.entity.RentalDeposit;
import ing.beribtur.aggregate.payment.entity.sdo.RentalDepositCdo;
import ing.beribtur.aggregate.payment.entity.vo.DepositStatus;
import ing.beribtur.aggregate.payment.store.RentalDepositStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RentalDepositLogic {
    //
    private final RentalDepositStore rentalDepositStore;

    public String registerRentalDeposit(RentalDepositCdo rentalDepositCdo) {
        //
        RentalDeposit rentalDeposit = new RentalDeposit(rentalDepositCdo);
        if (rentalDepositStore.exists(rentalDeposit.getId())) {
            throw new IllegalArgumentException("Rental deposit already exists. " + rentalDeposit.getId());
        }
        rentalDepositStore.create(rentalDeposit);
        return rentalDeposit.getId();
    }

    public List<String> registerRentalDeposits(List<RentalDepositCdo> rentalDepositCdos) {
        //
        return rentalDepositCdos.stream().map(this::registerRentalDeposit).collect(Collectors.toList());
    }

    public RentalDeposit findRentalDeposit(String rentalDepositId) {
        //
        RentalDeposit rentalDeposit = rentalDepositStore.retrieve(rentalDepositId);
        if (rentalDeposit == null) {
            throw new NoSuchElementException("Rental deposit id: " + rentalDepositId);
        }
        return rentalDeposit;
    }

    public List<RentalDeposit> findRentalDeposits(List<String> rentalDepositIds) {
        //
        return rentalDepositStore.retrieveAll(rentalDepositIds);
    }

    public List<RentalDeposit> findRentalDeposits(Offset offset) {
        //
        return rentalDepositStore.retrieveList(offset);
    }

    public void modifyRentalDeposit(String rentalDepositId, NameValueList nameValues) {
        //
        RentalDeposit rentalDeposit = findRentalDeposit(rentalDepositId);
        rentalDeposit.modify(nameValues);
        rentalDepositStore.update(rentalDeposit);
    }

    public void modifyRentalDeposit(RentalDeposit rentalDeposit) {
        //
        RentalDeposit oldRentalDeposit = findRentalDeposit(rentalDeposit.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldRentalDeposit, rentalDeposit);
        if (!nameValues.list().isEmpty()) {
            modifyRentalDeposit(rentalDeposit.getId(), nameValues);
        }
    }

    public void removeRentalDeposit(String rentalDepositId) {
        //
        RentalDeposit rentalDeposit = findRentalDeposit(rentalDepositId);
        rentalDepositStore.delete(rentalDeposit.getId());
    }

    public boolean existsRentalDeposit(String rentalDepositId) {
        //
        return rentalDepositStore.exists(rentalDepositId);
    }

    public RentalDeposit findByRentalRecordId(String rentalRecordId) {
        //
        return rentalDepositStore.retrieveByRentalRecordId(rentalRecordId);
    }

    public List<RentalDeposit> findByPayerId(String payerId) {
        //
        return rentalDepositStore.retrieveByPayerId(payerId);
    }

    public List<RentalDeposit> findByStatus(DepositStatus status) {
        //
        return rentalDepositStore.retrieveByStatus(status);
    }

    public void markAsRefundable(String rentalDepositId) {
        //
        RentalDeposit rentalDeposit = findRentalDeposit(rentalDepositId);
        rentalDeposit.setStatus(DepositStatus.REFUNDABLE);
        rentalDepositStore.update(rentalDeposit);
    }

    public void markAsRefunded(String rentalDepositId) {
        //
        RentalDeposit rentalDeposit = findRentalDeposit(rentalDepositId);
        rentalDeposit.setStatus(DepositStatus.REFUNDED);
        rentalDeposit.setResolvedAt(LocalDateTime.now());
        rentalDepositStore.update(rentalDeposit);
    }

    public void markForReview(String rentalDepositId, String notes) {
        //
        RentalDeposit rentalDeposit = findRentalDeposit(rentalDepositId);
        rentalDeposit.setStatus(DepositStatus.REVIEW_NEEDED);
        rentalDeposit.setNotes(notes);
        rentalDepositStore.update(rentalDeposit);
    }

    public void withhold(String rentalDepositId, String reason) {
        //
        RentalDeposit rentalDeposit = findRentalDeposit(rentalDepositId);
        rentalDeposit.setStatus(DepositStatus.WITHHELD);
        rentalDeposit.setNotes(reason);
        rentalDeposit.setResolvedAt(LocalDateTime.now());
        rentalDepositStore.update(rentalDeposit);
    }

    public List<RentalDeposit> findHeldDeposits() {
        //
        return rentalDepositStore.retrieveByStatus(DepositStatus.HELD);
    }

    public List<RentalDeposit> findDepositsNeedingReview() {
        //
        return rentalDepositStore.retrieveByStatus(DepositStatus.REVIEW_NEEDED);
    }

    public List<RentalDeposit> findRefundableDeposits() {
        //
        return rentalDepositStore.retrieveByStatus(DepositStatus.REFUNDABLE);
    }
}
