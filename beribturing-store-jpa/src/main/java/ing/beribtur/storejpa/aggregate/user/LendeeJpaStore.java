package ing.beribtur.storejpa.aggregate.user;

import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.store.LendeeStore;
import ing.beribtur.storejpa.aggregate.user.jpo.LendeeJpo;
import ing.beribtur.storejpa.aggregate.user.repository.LendeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LendeeJpaStore implements LendeeStore {
    //
    private final LendeeRepository lendeeRepository;

    @Override
    public void create(Lendee lendee) {
        LendeeJpo lendeeJpo = new LendeeJpo(lendee);
        lendeeRepository.save(lendeeJpo);
        lendee.setId(lendeeJpo.getId());
    }

    @Override
    public Lendee retrieve(String id) {
        LendeeJpo lendeeJpo = lendeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lendee not found: " + id));
        return lendeeJpo.toDomain();
    }

    @Override
    public List<Lendee> retrieveAll(List<String> ids) {
        List<LendeeJpo> lendeeJpos = lendeeRepository.findAllById(ids);
        return LendeeJpo.toDomains(lendeeJpos);
    }

    @Override
    public void update(Lendee lendee) {
        LendeeJpo existingJpo = lendeeRepository.findById(lendee.getId())
                .orElseThrow(() -> new IllegalArgumentException("Lendee not found: " + lendee.getId()));

        LendeeJpo updatedJpo = new LendeeJpo(lendee);
        updatedJpo.setEntityVersion(existingJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(existingJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(existingJpo.getRegisteredOn());

        lendeeRepository.save(updatedJpo);
    }

    @Override
    public void delete(String id) {
        lendeeRepository.deleteById(id);
    }

    // Additional query methods
    public List<Lendee> findByPhoneNumber(String phoneNumber) {
        return LendeeJpo.toDomains(lendeeRepository.findByPhoneNumber(phoneNumber));
    }

    public List<Lendee> findByEmail(String email) {
        return LendeeJpo.toDomains(lendeeRepository.findByEmail(email));
    }

    public List<Lendee> findByIsActive(boolean isActive) {
        return LendeeJpo.toDomains(lendeeRepository.findByActive(isActive));
    }
}

