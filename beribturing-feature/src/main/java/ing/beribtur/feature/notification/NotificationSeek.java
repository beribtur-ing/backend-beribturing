package ing.beribtur.feature.notification;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.notification.entity.Notification;
import ing.beribtur.aggregate.notification.entity.vo.ChannelType;
import ing.beribtur.aggregate.notification.entity.vo.NotificationType;
import ing.beribtur.aggregate.notification.entity.vo.Status;
import ing.beribtur.aggregate.notification.logic.NotificationLogic;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class NotificationSeek {
    //
    private final NotificationLogic notificationLogic;

    public NotificationSeek(NotificationLogic notificationLogic) {
        //
        this.notificationLogic = notificationLogic;
    }

    public Page<Notification> findUnreadNotifications(String receiverId, Offset offset) {
        //
        return this.notificationLogic.findUnreadNotifications(receiverId, offset);
    }

    public Page<Notification> findReceivedNotifications(String receiverId, Status status, NotificationType type, ChannelType channelType, Offset offset) {
        //
        return this.notificationLogic.findReceivedNotifications(receiverId, status, type, channelType, offset);
    }
}
