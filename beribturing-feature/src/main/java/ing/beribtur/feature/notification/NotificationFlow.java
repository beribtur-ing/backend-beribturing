package ing.beribtur.feature.notification;

import ing.beribtur.aggregate.notification.entity.sdo.NotificationCdo;
import ing.beribtur.aggregate.notification.logic.NotificationLogic;
import ing.beribtur.proxy.nats.NatsPublisherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class NotificationFlow {
    //
    private final NotificationLogic notificationLogic;
    private final NatsPublisherService natsPublisherService;

    public String register(NotificationCdo cdo) {
        //
        String notificationId = this.notificationLogic.registerNotification(cdo);
        this.natsPublisherService.publish("beribturing.notifications", cdo.getMessage().toJson());
        return notificationId;
    }
}
