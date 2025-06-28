package ing.beribtur.aggregate.notification.logic;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.util.Entities;
import ing.beribtur.aggregate.notification.entity.Notification;
import ing.beribtur.aggregate.notification.entity.sdo.NotificationCdo;
import ing.beribtur.aggregate.notification.entity.vo.ChannelType;
import ing.beribtur.aggregate.notification.entity.vo.Status;
import ing.beribtur.aggregate.notification.store.NotificationStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationLogic {
    //
    private final NotificationStore notificationStore;

    public String registerNotification(NotificationCdo notificationCdo) {
        //
        Notification notification = new Notification(notificationCdo);
        if (notificationStore.exists(notification.getId())) {
            throw new IllegalArgumentException("notification already exists. " + notification.getId());
        }
        notificationStore.create(notification);
        return notification.getId();
    }

    public List<String> registerNotifications(List<NotificationCdo> notificationCdos) {
        //
        return notificationCdos.stream().map(this::registerNotification).collect(Collectors.toList());
    }

    public Notification findNotification(String notificationId) {
        //
        Notification notification = notificationStore.retrieve(notificationId);
        if (notification == null) {
            throw new NoSuchElementException("Notification id: " + notificationId);
        }
        return notification;
    }

    public List<Notification> findNotifications(Offset offset) {
        //
        return notificationStore.retrieveList(offset);
    }

    public List<Notification> findNotificationsByReceiver(String receiverId) {
        //
        return notificationStore.retrieveByReceiverId(receiverId);
    }

    public List<Notification> findNotificationsBySender(String senderId) {
        //
        return notificationStore.retrieveBySenderId(senderId);
    }

    public List<Notification> findNotificationsByStatus(Status status) {
        //
        return notificationStore.retrieveByStatus(status);
    }

    public List<Notification> findNotificationsByChannelType(ChannelType channelType) {
        //
        return notificationStore.retrieveByChannelType(channelType);
    }

    public void modifyNotification(String notificationId, NameValueList nameValues) {
        //
        Notification notification = findNotification(notificationId);
        notification.modify(nameValues);
        notificationStore.update(notification);
    }

    public void modifyNotification(Notification notification) {
        //
        Notification oldNotification = findNotification(notification.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldNotification, notification);
        if (!nameValues.list().isEmpty()) {
            modifyNotification(notification.getId(), nameValues);
        }
    }

    public void removeNotification(String notificationId) {
        //
        Notification notification = findNotification(notificationId);
        notificationStore.delete(notification);
    }

    public boolean existsNotification(String notificationId) {
        //
        return notificationStore.exists(notificationId);
    }

    public long nextNotificationSequence(String senderId) {
        //
        return notificationStore.getNextSequence(senderId);
    }
}