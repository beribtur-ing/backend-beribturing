package ing.beribtur.storejpa.aggregate.rental;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.rental.entity.vo.RentalStatus;
import ing.beribtur.aggregate.rental.store.RentalRecordStore;
import ing.beribtur.storejpa.aggregate.rental.jpo.RentalRecordJpo;
import ing.beribtur.storejpa.aggregate.rental.repository.RentalRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

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
    public void createAll(List<RentalRecord> rentalRecords) {
        List<RentalRecordJpo> jpos = rentalRecords.stream()
                .map(RentalRecordJpo::new)
                .collect(Collectors.toList());

        List<RentalRecordJpo> savedJpos = rentalRecordRepository.saveAll(jpos);

        // Update the IDs in the domain entities
        for (int i = 0; i < rentalRecords.size(); i++) {
            rentalRecords.get(i).setId(savedJpos.get(i).getId());
        }
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
    public List<RentalRecord> retrieveList(Offset offset) {
        PageRequest pageRequest = PageRequest.of(offset.page(), offset.limit());
        return rentalRecordRepository.findAll(pageRequest).stream()
                .map(RentalRecordJpo::toDomain)
                .collect(Collectors.toList());
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
    public void delete(RentalRecord rentalRecord) {
        delete(rentalRecord.getId());
    }

    @Override
    public void delete(String id) {
        rentalRecordRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        return rentalRecordRepository.existsById(id);
    }

    @Override
    public List<RentalRecord> retrieveByLendeeId(String lendeeId) {
        return RentalRecordJpo.toDomains(rentalRecordRepository.findByLendeeId(lendeeId));
    }

    @Override
    public List<RentalRecord> retrieveByProductVariantId(String productVariantId) {
        return RentalRecordJpo.toDomains(rentalRecordRepository.findByProductVariantId(productVariantId));
    }

    @Override
    public List<RentalRecord> retrieveByStatus(RentalStatus status) {
        return RentalRecordJpo.toDomains(rentalRecordRepository.findByStatus(status.name()));
    }

    @Override
    public List<RentalRecord> retrieveAllByOwnerId(String ownerId, String status) {
        //
        return RentalRecordJpo.toDomains(rentalRecordRepository.findAllByOwnerIdAndStatusIsOrStatusIsNull(ownerId,status));
    }

    // Additional methods for specific queries
    public List<RentalRecord> findByStatusAndLendeeId(String status, String lendeeId) {
        return RentalRecordJpo.toDomains(rentalRecordRepository.findByStatusAndLendeeId(status, lendeeId));
    }
}
