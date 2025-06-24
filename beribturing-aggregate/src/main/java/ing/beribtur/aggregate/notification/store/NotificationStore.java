package ing.beribtur.aggregate.notification.store;

import ing.beribtur.aggregate.notification.entity.Contact;
import ing.beribtur.aggregate.notification.entity.Notification;
import ing.beribtur.aggregate.notification.entity.vo.ChannelType;
import ing.beribtur.aggregate.notification.entity.vo.Status;

import java.util.List;

public interface NotificationStore {
    //
    void create(Notification notification);
    Notification retrieve(String id);
    List<Notification> retrieveAll(List<String> ids);
    List<Notification> retrieveByReceiverId(String receiverId);
    List<Notification> retrieveByStatus(Status status);
    List<Notification> retrieveByChannelType(ChannelType channelType);
    List<Notification> retrieveByReceiverIdAndStatus(String receiverId, Status status);
    void update(Notification notification);
    void delete(String id);
    
    void createContact(Contact contact);
    Contact retrieveContact(String userId);
    void updateContact(Contact contact);
    void deleteContact(String userId);
}
