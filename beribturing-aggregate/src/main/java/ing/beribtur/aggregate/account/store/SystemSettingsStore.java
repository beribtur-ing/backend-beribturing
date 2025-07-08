package ing.beribtur.aggregate.account.store;

import ing.beribtur.aggregate.account.entity.SystemSettings;

import java.util.List;

public interface SystemSettingsStore {
    //
    void create(SystemSettings systemSettings);

    SystemSettings retrieve(String id);

    void update(SystemSettings systemSettings);

    void delete(String id);

    boolean exists(String id);

    List<SystemSettings> retrieveAll();
}

