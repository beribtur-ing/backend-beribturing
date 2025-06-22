package ing.beribtur.storejpa.aggregate.user;

import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.aggregate.user.entity.vo.LenderType;
import ing.beribtur.aggregate.user.store.LenderStore;
import ing.beribtur.storejpa.aggregate.user.jpo.LenderJpo;
import ing.beribtur.storejpa.aggregate.user.repository.LenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LenderJpaStore implements LenderStore {
    //
    private final LenderRepository lenderRepository;

    @Override
    public void create(Lender lender) {
        LenderJpo lenderJpo = new LenderJpo(lender);
        lenderRepository.save(lenderJpo);
        lender.setId(lenderJpo.getId());
    }

    @Override
    public Lender retrieve(String id) {
        LenderJpo lenderJpo = lenderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lender not found: " + id));
        return lenderJpo.toDomain();
    }

    @Override
    public List<Lender> retrieveAll(List<String> ids) {
        List<LenderJpo> lenderJpos = lenderRepository.findAllById(ids);
        return LenderJpo.toDomains(lenderJpos);
    }

    @Override
    public void update(Lender lender) {
        LenderJpo existingJpo = lenderRepository.findById(lender.getId())
                .orElseThrow(() -> new IllegalArgumentException("Lender not found: " + lender.getId()));

        LenderJpo updatedJpo = new LenderJpo(lender);
        updatedJpo.setEntityVersion(existingJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(existingJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(existingJpo.getRegisteredOn());

        lenderRepository.save(updatedJpo);
    }

    @Override
    public void delete(String id) {
        lenderRepository.deleteById(id);
    }

    // Additional query methods
    public Lender findByPhoneNumber(String phoneNumber) {
        LenderJpo lenderJpo = lenderRepository.findByPhoneNumber(phoneNumber);
        if (lenderJpo == null) {
            throw new IllegalArgumentException("Lender not found with phone number: " + phoneNumber);
        }
        return lenderJpo.toDomain();
    }

    public List<Lender> findByEmail(String email) {
        return LenderJpo.toDomains(lenderRepository.findByEmail(email));
    }

//    public List<Lender> findByIsActive(boolean isActive) {
//        return LenderJpo.toDomains(lenderRepository.findByIsActive(isActive));
//    }

    public List<Lender> findByLanderType(LenderType landerType) {
        return LenderJpo.toDomains(lenderRepository.findByLenderType(landerType.name()));
    }

    @Override
    public Page<Lender> findDisabledLenders(Pageable pageable) {
        Page<LenderJpo> lenderJpos = lenderRepository.findByActive(false, pageable);
        return new PageImpl<>(
                LenderJpo.toDomains(lenderJpos.getContent()),
                pageable,
                lenderJpos.getTotalElements()
        );
    }
}

