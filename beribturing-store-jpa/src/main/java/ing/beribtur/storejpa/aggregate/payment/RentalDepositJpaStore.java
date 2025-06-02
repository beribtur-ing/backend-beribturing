package ing.beribtur.storejpa.aggregate.payment;

import ing.beribtur.aggregate.payment.entity.RentalDeposit;
import ing.beribtur.aggregate.payment.entity.vo.DepositStatus;
import ing.beribtur.aggregate.payment.store.RentalDepositStore;
import ing.beribtur.storejpa.aggregate.payment.jpo.RentalDepositJpo;
import ing.beribtur.storejpa.aggregate.payment.repository.RentalDepositRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RentalDepositJpaStore implements RentalDepositStore {
    //
    private final RentalDepositRepository rentalDepositRepository;

    @Override
    public void create(RentalDeposit rentalDeposit) {
        RentalDepositJpo rentalDepositJpo = new RentalDepositJpo(rentalDeposit);

        rentalDepositRepository.save(rentalDepositJpo);
        rentalDeposit.setId(rentalDepositJpo.getId());
    }

    @Override
    public RentalDeposit retrieve(String id) {
        RentalDepositJpo rentalDepositJpo = rentalDepositRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("RentalDeposit not found: " + id));
        return rentalDepositJpo.toDomain();
    }

    @Override
    public List<RentalDeposit> retrieveAll(List<String> ids) {
        List<RentalDepositJpo> rentalDepositJpos = rentalDepositRepository.findAllById(ids);
        return rentalDepositJpos.stream()
                .map(RentalDepositJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(RentalDeposit rentalDeposit) {
        RentalDepositJpo rentalDepositJpo = rentalDepositRepository.findById(rentalDeposit.getId())
                .orElseThrow(() -> new IllegalArgumentException("RentalDeposit not found: " + rentalDeposit.getId()));
        
        // Update the JPO with the domain entity's values
        RentalDepositJpo updatedJpo = new RentalDepositJpo(rentalDeposit);
        updatedJpo.setEntityVersion(rentalDepositJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(rentalDepositJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(rentalDepositJpo.getRegisteredOn());
        
        rentalDepositRepository.save(updatedJpo);
    }

    @Override
    public void delete(String id) {
        rentalDepositRepository.deleteById(id);
    }
    
    // Additional methods for specific queries
    public List<RentalDeposit> findByRentalRecordId(String rentalRecordId) {
        return RentalDepositJpo.toDomains(rentalDepositRepository.findByRentalRecordId(rentalRecordId));
    }
    
    public List<RentalDeposit> findByPayerId(String payerId) {
        return RentalDepositJpo.toDomains(rentalDepositRepository.findByPayerId(payerId));
    }
    
    public List<RentalDeposit> findByStatus(DepositStatus status) {
        return RentalDepositJpo.toDomains(rentalDepositRepository.findByStatus(status.name()));
    }
    
    public List<RentalDeposit> findByRentalRecordIdAndStatus(String rentalRecordId, DepositStatus status) {
        return RentalDepositJpo.toDomains(
            rentalDepositRepository.findByRentalRecordIdAndStatus(rentalRecordId, status.name())
        );
    }
}