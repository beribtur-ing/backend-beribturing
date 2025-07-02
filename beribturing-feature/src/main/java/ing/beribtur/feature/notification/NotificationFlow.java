package ing.beribtur.feature.notification;

import ing.beribtur.aggregate.notification.entity.Notification;
import ing.beribtur.aggregate.notification.entity.sdo.NotificationCdo;
import ing.beribtur.aggregate.notification.entity.vo.Status;
import ing.beribtur.aggregate.notification.logic.NotificationLogic;
import ing.beribtur.proxy.nats.NatsPublisherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class NotificationFlow {
    //
    @Value("${nats.subjects.notification}")
    private String notificationSubject;

    private final NotificationLogic notificationLogic;
    private final NatsPublisherService natsPublisherService;

    public NotificationFlow(NotificationLogic notificationLogic, NatsPublisherService natsPublisherService) {
        //
        this.notificationLogic = notificationLogic;
        this.natsPublisherService = natsPublisherService;
    }

    public String register(NotificationCdo cdo) {
        //
        String notificationId = this.notificationLogic.registerNotification(cdo);
        boolean isPublished = this.natsPublisherService.publish(notificationSubject, cdo.toJson());
        Notification notification = this.notificationLogic.findNotification(notificationId);
        if (isPublished) {
            notification.setStatus(Status.SENT);
        } else {
            notification.setStatus(Status.FAILED);
        }
        this.notificationLogic.modifyNotification(notification);
        return notificationId;
    }

    public int markAsRead(List<String> notificationIds) {
        //
        int updatedCount = 0;
        for (String notificationId : notificationIds) {
            try {
                Notification notification = this.notificationLogic.findNotification(notificationId);
                if (notification != null && notification.getStatus() != Status.READ) {
                    notification.setStatus(Status.READ);
                    notification.setReceivedAt(LocalDateTime.now());
                    this.notificationLogic.modifyNotification(notification);
                    updatedCount++;
                }
            } catch (Exception e) {
                System.err.println("Failed to mark notification as read: " + notificationId + ", error: " + e.getMessage());
            }
        }
        return updatedCount;
    }

}
