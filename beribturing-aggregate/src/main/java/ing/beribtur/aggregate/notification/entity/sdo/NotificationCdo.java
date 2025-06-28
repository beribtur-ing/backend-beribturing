package ing.beribtur.aggregate.notification.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.notification.entity.vo.ChannelType;
import ing.beribtur.aggregate.notification.entity.vo.NotificationMessage;
import ing.beribtur.aggregate.notification.entity.vo.NotificationType;
import lombok.*;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCdo extends CreationDataObject {
    //
    private String senderId;
    private String receiverId;
    private NotificationMessage message;
    private NotificationType type;
    private ChannelType channelType;

    public String genId() {
        //
        return super.genId();
    }

    public static NotificationCdo sample() {
        //
        Map<String, String> additionalInfo = new HashMap<>();
        additionalInfo.put("action", "view");
        additionalInfo.put("itemId", "123");

        NotificationMessage message = new NotificationMessage(
            "Welcome to BeribTuring",
            "Thank you for joining our rental platform",
            additionalInfo
        );

        return NotificationCdo.builder()
            .senderId("system")
            .receiverId("user123")
            .message(message)
            .type(NotificationType.SYSTEM)
            .channelType(ChannelType.SERVICE)
            .build();
    }

    public static NotificationCdo sampleChatNotification() {
        //
        NotificationMessage message = new NotificationMessage(
            "New Message",
            "You have a new message from John Doe",
            null
        );

        return NotificationCdo.builder()
            .senderId("user789")
            .receiverId("user456")
            .message(message)
            .type(NotificationType.CHAT)
            .channelType(ChannelType.SERVICE)
            .build();
    }

    public static NotificationCdo sampleEmailNotification() {
        //
        Map<String, String> additionalInfo = new HashMap<>();
        additionalInfo.put("category", "rental_update");
        additionalInfo.put("priority", "high");

        NotificationMessage message = new NotificationMessage(
            "Rental Request Update",
            "Your rental request has been approved and is ready for pickup",
            additionalInfo
        );

        return NotificationCdo.builder()
            .senderId("owner202")
            .receiverId("renter101")
            .message(message)
            .type(NotificationType.REQUEST_UPDATE)
            .channelType(ChannelType.EMAIL)
            .build();
    }

    public static NotificationCdo sampleSmsAlert() {
        //
        NotificationMessage message = new NotificationMessage(
            "Payment Reminder",
            "Your rental payment is due in 24 hours",
            null
        );

        return NotificationCdo.builder()
            .senderId("system")
            .receiverId("user303")
            .message(message)
            .type(NotificationType.ALERT)
            .channelType(ChannelType.SMS)
            .build();
    }
}
