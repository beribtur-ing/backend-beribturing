package ing.beribtur.storejpa.aggregate.account;

import ing.beribtur.aggregate.account.entity.SystemSettings;
import ing.beribtur.aggregate.account.store.SystemSettingsStore;
import ing.beribtur.storejpa.aggregate.account.jpo.SystemSettingsJpo;
import ing.beribtur.storejpa.aggregate.account.repository.SystemSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SystemSettingsJpaStore implements SystemSettingsStore {
    //
    private final SystemSettingsRepository systemSettingsRepository;

    @Override
    public void create(SystemSettings systemSettings) {
        //
        SystemSettingsJpo jpo = new SystemSettingsJpo(systemSettings);
        systemSettingsRepository.save(jpo);
        systemSettings.setId(jpo.getId());
    }

    @Override
    public SystemSettings retrieve(String id) {
        //
        SystemSettingsJpo jpo = systemSettingsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SystemSettings not found: " + id));
        return jpo.toDomain();
    }

    @Override
    public void update(SystemSettings systemSettings) {
        //
        SystemSettingsJpo existing = systemSettingsRepository.findById(systemSettings.getId())
                .orElseThrow(() -> new IllegalArgumentException("SystemSettings not found: " + systemSettings.getId()));

        SystemSettingsJpo updated = new SystemSettingsJpo(systemSettings);
        updated.setEntityVersion(existing.getEntityVersion());
        updated.setRegisteredBy(existing.getRegisteredBy());
        updated.setRegisteredOn(existing.getRegisteredOn());

        systemSettingsRepository.save(updated);
    }

    @Override
    public void delete(String id) {
        //
        systemSettingsRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        //
        return systemSettingsRepository.existsById(id);
    }

    @Override
    public List<SystemSettings> retrieveAll() {
        //
        return systemSettingsRepository.findAll()
                .stream()
                .map(SystemSettingsJpo::toDomain)
                .toList();
    }
}
