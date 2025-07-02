package ing.beribtur.proxy.notisender;

import ing.beribtur.aggregate.notification.entity.Notification;
import ing.beribtur.config.nats.NatsSubjectManagerService;
import ing.beribtur.proxy.nats.NatsPublisherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationSenderService {
    //
    private final String subjectsNotification;
    private final NatsPublisherService natsPublisherService;
    private final NatsSubjectManagerService natsSubjectManagerService;

    public NotificationSenderService(@Value("${nats.subjects.notification}") String subjectsNotification, NatsPublisherService natsPublisherService, NatsSubjectManagerService natsSubjectManagerService) {
        //
        this.subjectsNotification = subjectsNotification;
        this.natsPublisherService = natsPublisherService;
        this.natsSubjectManagerService = natsSubjectManagerService;
    }

    public void registerUserForNotification(String user) {
        //
        String streamName = String.format("%s-%s", user, "notification");
        this.natsSubjectManagerService.addUserSubjectToStream(streamName, user);
    }

    public void sendNotificationToUser(String user, Notification notification) {
        //
        String subject = String.format("%s.%s", subjectsNotification, user);
        this.natsPublisherService.publish(subject, notification.toJson());
    }
}
