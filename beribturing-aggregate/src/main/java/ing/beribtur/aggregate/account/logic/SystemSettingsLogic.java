package ing.beribtur.aggregate.account.logic;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.util.Entities;
import ing.beribtur.aggregate.account.entity.SystemSettings;
import ing.beribtur.aggregate.account.entity.sdo.SystemSettingsCdo;
import ing.beribtur.aggregate.account.store.SystemSettingsStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class SystemSettingsLogic {
    //
    private final SystemSettingsStore systemSettingsStore;

    public String registerSystemSettings(SystemSettingsCdo systemSettingsCdo) {
        SystemSettings systemSettings = new SystemSettings(systemSettingsCdo);
        if (systemSettingsStore.exists(systemSettings.getId())) {
            throw new IllegalArgumentException("SystemSettings already exist. ID: " + systemSettings.getId());
        }
        systemSettingsStore.create(systemSettings);
        return systemSettings.getId();
    }

    public SystemSettings findSystemSettings(String systemSettingsId) {
        SystemSettings systemSettings = systemSettingsStore.retrieve(systemSettingsId);
        if (systemSettings == null) {
            throw new NoSuchElementException("SystemSettings not found. ID: " + systemSettingsId);
        }
        return systemSettings;
    }

    public void modifySystemSettings(String systemSettingsId, NameValueList nameValues) {
        SystemSettings systemSettings = findSystemSettings(systemSettingsId);
        systemSettings.modify(nameValues);
        systemSettingsStore.update(systemSettings);
    }

    public void modifySystemSettings(SystemSettings systemSettings) {
        SystemSettings oldSettings = findSystemSettings(systemSettings.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldSettings, systemSettings);
        if (!nameValues.list().isEmpty()) {
            modifySystemSettings(systemSettings.getId(), nameValues);
        }
    }

    public void removeSystemSettings(String systemSettingsId) {
        systemSettingsStore.delete(systemSettingsId);
    }

    public boolean existsSystemSettings(String systemSettingsId) {
        return systemSettingsStore.exists(systemSettingsId);
    }

    public List<SystemSettings> findAllSystemSettings() {
        return systemSettingsStore.retrieveAll();
    }
}

