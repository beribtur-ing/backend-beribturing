package ing.beribtur.aggregate.notification.logic;

import ing.beribtur.aggregate.notification.entity.Notification;
import ing.beribtur.aggregate.notification.entity.vo.*;
import ing.beribtur.aggregate.notification.store.NotificationStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationLogic {
    //
    private final NotificationStore notificationStore;

    public String createNotification(String receiverId, String senderId, NotificationMessage message, 
                                   NotificationType type, ChannelType channelType) {
        Notification notification = new Notification();
        notification.setId(UUID.randomUUID().toString());
        notification.setReceiverId(receiverId);
        notification.setSenderId(senderId);
        notification.setMessage(message);
        notification.setType(type);
        notification.setChannelType(channelType);
        notification.setStatus(Status.PENDING);
        notification.setSentAt(LocalDateTime.now());
        
        notificationStore.create(notification);
        return notification.getId();
    }

    public Notification findNotification(String id) {
        return notificationStore.retrieve(id);
    }

    public List<Notification> findNotifications(List<String> ids) {
        return notificationStore.retrieveAll(ids);
    }

    public void markAsSent(String notificationId) {
        Notification notification = notificationStore.retrieve(notificationId);
        if (notification != null) {
            notification.setStatus(Status.SENT);
            notification.setSentAt(LocalDateTime.now());
            notificationStore.update(notification);
        }
    }

    public void markAsRead(String notificationId) {
        Notification notification = notificationStore.retrieve(notificationId);
        if (notification != null) {
            notification.setStatus(Status.READ);
            notification.setReceivedAt(LocalDateTime.now());
            notificationStore.update(notification);
        }
    }

    public void markAsFailed(String notificationId) {
        Notification notification = notificationStore.retrieve(notificationId);
        if (notification != null) {
            notification.setStatus(Status.FAILED);
            notificationStore.update(notification);
        }
    }

    public void deleteNotification(String id) {
        notificationStore.delete(id);
    }
}
