package ing.beribtur.aggregate.rental.logic;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.util.Entities;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.rental.entity.sdo.RentalRecordCdo;
import ing.beribtur.aggregate.rental.entity.vo.RentalStatus;
import ing.beribtur.aggregate.rental.store.RentalRecordStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RentalRecordLogic {
    //
    private final RentalRecordStore rentalRecordStore;

    public String registerRentalRecord(RentalRecordCdo rentalRecordCdo) {
        //
        RentalRecord rentalRecord = new RentalRecord(rentalRecordCdo);
        if (rentalRecordStore.exists(rentalRecord.getId())) {
            throw new IllegalArgumentException("rental record already exists. " + rentalRecord.getId());
        }
        rentalRecordStore.create(rentalRecord);
        return rentalRecord.getId();
    }

    public List<String> registerRentalRecords(List<RentalRecordCdo> rentalRecordCdos) {
        //
        return rentalRecordCdos.stream().map(this::registerRentalRecord).collect(Collectors.toList());
    }

    public RentalRecord findRentalRecord(String rentalRecordId) {
        //
        RentalRecord rentalRecord = rentalRecordStore.retrieve(rentalRecordId);
        if (rentalRecord == null) {
            throw new NoSuchElementException("RentalRecord id: " + rentalRecordId);
        }
        return rentalRecord;
    }

    public List<RentalRecord> findRentalRecords(Offset offset) {
        //
        return rentalRecordStore.retrieveList(offset);
    }

    public void modifyRentalRecord(String rentalRecordId, NameValueList nameValueList) {
        //
        RentalRecord rentalRecord = findRentalRecord(rentalRecordId);
        rentalRecord.modify(nameValueList);
        rentalRecordStore.update(rentalRecord);
    }

    public void modifyRentalRecord(RentalRecord rentalRecord) {
        //
        RentalRecord oldRentalRecord = findRentalRecord(rentalRecord.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldRentalRecord, rentalRecord);
        if (!nameValues.list().isEmpty()) {
            modifyRentalRecord(rentalRecord.getId(), nameValues);
        }
    }

    public void removeRentalRecord(String rentalRecordId) {
        //
        RentalRecord rentalRecord = findRentalRecord(rentalRecordId);
        rentalRecordStore.delete(rentalRecord);
    }

    public boolean existsRentalRecord(String rentalRecordId) {
        //
        return rentalRecordStore.exists(rentalRecordId);
    }

    public List<RentalRecord> findByLendeeId(String lendeeId) {
        //
        return rentalRecordStore.retrieveByLendeeId(lendeeId);
    }

    public List<RentalRecord> findByProductVariantId(String productVariantId) {
        //
        return rentalRecordStore.retrieveByProductVariantId(productVariantId);
    }

    public List<RentalRecord> findByStatus(RentalStatus status) {
        //
        return rentalRecordStore.retrieveByStatus(status);
    }
}
