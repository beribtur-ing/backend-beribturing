package ing.beribtur.aggregate.notification.store;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.notification.entity.Notification;
import ing.beribtur.aggregate.notification.entity.vo.ChannelType;
import ing.beribtur.aggregate.notification.entity.vo.NotificationType;
import ing.beribtur.aggregate.notification.entity.vo.Status;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NotificationStore {
    //
    void create(Notification notification);
    void createAll(List<Notification> notifications);
    Notification retrieve(String id);
    List<Notification> retrieveAll(List<String> notificationIds);
    List<Notification> retrieveList(Offset offset);
    void update(Notification notification);
    void delete(Notification notification);
    void delete(String id);
    boolean exists(String id);

    List<Notification> retrieveByReceiverId(String receiverId);
    List<Notification> retrieveBySenderId(String senderId);
    List<Notification> retrieveByStatus(Status status);
    List<Notification> retrieveByChannelType(ChannelType channelType);
    List<Notification> retrieveByReceiverIdAndStatus(String receiverId, Status status);
    long getNextSequence(String senderId);
    Page<Notification> retrieveUnreadNotifications(String receiverId, Offset offset);
    Page<Notification> retrieveReceivedNotifications(String receiverId, Status status, NotificationType type, ChannelType channelType, Offset offset);
}
