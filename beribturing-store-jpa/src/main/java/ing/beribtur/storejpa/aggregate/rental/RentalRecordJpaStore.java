package ing.beribtur.storejpa.aggregate.rental;

import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.rental.store.RentalRecordStore;
import ing.beribtur.storejpa.aggregate.rental.jpo.RentalRecordJpo;
import ing.beribtur.storejpa.aggregate.rental.repository.RentalRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RentalRecordJpaStore implements RentalRecordStore {

    private final RentalRecordRepository rentalRecordRepository;

    @Override
    public void create(RentalRecord rentalRecord) {
        RentalRecordJpo rentalRecordJpo = new RentalRecordJpo(rentalRecord);
        rentalRecordRepository.save(rentalRecordJpo);
        rentalRecord.setId(rentalRecordJpo.getId());
    }

    @Override
    public RentalRecord retrieve(String id) {
        RentalRecordJpo rentalRecordJpo = rentalRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("RentalRecord not found: " + id));
        return rentalRecordJpo.toDomain();
    }

    @Override
    public List<RentalRecord> retrieveAll(List<String> ids) {
        List<RentalRecordJpo> jpos = rentalRecordRepository.findAllById(ids);
        return jpos.stream().map(RentalRecordJpo::toDomain).toList();
    }

    @Override
    public void update(RentalRecord rentalRecord) {
        RentalRecordJpo existingJpo = rentalRecordRepository.findById(rentalRecord.getId())
                .orElseThrow(() -> new IllegalArgumentException("RentalRecord not found: " + rentalRecord.getId()));

        RentalRecordJpo updatedJpo = new RentalRecordJpo(rentalRecord);
        updatedJpo.setEntityVersion(existingJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(existingJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(existingJpo.getRegisteredOn());

        rentalRecordRepository.save(updatedJpo);
    }

    @Override
    public void delete(String id) {
        rentalRecordRepository.deleteById(id);
    }

    // Additional methods for specific queries
    List<RentalRecord> findByProductVariantId(String productVariantId) {
        return RentalRecordJpo.toDomains(rentalRecordRepository.findByProductVariantId(productVariantId));
    }
    List<RentalRecord> findByLendeeId(String lendeeId) {
        return RentalRecordJpo.toDomains(rentalRecordRepository.findByLendeeId(lendeeId));
    }
    List<RentalRecord> findByStatus(String status) {
        return RentalRecordJpo.toDomains(rentalRecordRepository.findByStatus(status));
    }
    List<RentalRecord> findByStatusAndLendeeId(String status, String lendeeId){
        return RentalRecordJpo.toDomains(rentalRecordRepository.findByStatusAndLendeeId(status, lendeeId));
    }
}