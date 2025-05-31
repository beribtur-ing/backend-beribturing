package ing.beribtur.aggregate.notification.store;


import ing.beribtur.aggregate.notification.entity.Notification;

import java.util.List;

public interface NotificationStore {
    //
    void create(Notification notification);
    Notification retrieve(String id);
    List<Notification> retrieveAll(List<String> ids);
    void update(Notification notification);
    void delete(String id);
}
