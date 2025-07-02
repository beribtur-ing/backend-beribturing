package ing.beribtur.aggregate.notification.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.notification.entity.sdo.NotificationCdo;
import ing.beribtur.aggregate.notification.entity.vo.*;
import ing.beribtur.aggregate.user.entity.vo.Notifiable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends DomainEntity {
    //
    private String receiverId;
    private String senderId;
    private NotificationMessage message;
    private NotificationType type;
    private ChannelType channelType;
    private Status status;
    private LocalDateTime sentAt;
    private LocalDateTime receivedAt;
    // Domain relationships
    private transient Notifiable recipient;


    public Notification(NotificationCdo notificationCdo) {
        //
        super(notificationCdo.genId());
        BeanUtils.copyProperties(notificationCdo, this);
        this.sentAt = LocalDateTime.now();
        this.status = Status.PENDING;
    }

    public static String genId(String senderId, long sequence) {
        //
        return String.format("%s-%d", senderId, sequence);
    }

    public static Notification sample() {
        //
        Map<String, String> additionalInfo = new HashMap<>();
        additionalInfo.put("action", "view");
        additionalInfo.put("itemId", "123");

        NotificationMessage message = new NotificationMessage(
            "Welcome to BeribTuring",
            "Thank you for joining our rental platform.",
            additionalInfo
        );

        Notification notification = new Notification();
        notification.setReceiverId("receiver123");
        notification.setSenderId("system");
        notification.setMessage(message);
        notification.setType(NotificationType.SYSTEM);
        notification.setChannelType(ChannelType.SERVICE);
        notification.setStatus(Status.PENDING);
        notification.setSentAt(LocalDateTime.now());
        notification.setReceivedAt(null);
        return notification;
    }

    public static Notification sampleChatNotification() {
        //
        NotificationMessage message = new NotificationMessage(
            "New Message",
            "You have a new message from John Doe",
            null
        );

        Notification notification = new Notification();
        notification.setReceiverId("user456");
        notification.setSenderId("user789");
        notification.setMessage(message);
        notification.setType(NotificationType.CHAT);
        notification.setChannelType(ChannelType.SERVICE);
        notification.setStatus(Status.SENT);
        notification.setSentAt(LocalDateTime.now().minusMinutes(5));
        notification.setReceivedAt(LocalDateTime.now().minusMinutes(3));
        return notification;
    }

    public static Notification sampleEmailNotification() {
        //
        Map<String, String> additionalInfo = new HashMap<>();
        additionalInfo.put("category", "rental_update");
        additionalInfo.put("priority", "high");

        NotificationMessage message = new NotificationMessage(
            "Rental Request Update",
            "Your rental request has been approved and is ready for pickup",
            additionalInfo
        );

        Notification notification = new Notification();
        notification.setReceiverId("renter101");
        notification.setSenderId("owner202");
        notification.setMessage(message);
        notification.setType(NotificationType.REQUEST_UPDATE);
        notification.setChannelType(ChannelType.EMAIL);
        notification.setStatus(Status.SENT);
        notification.setSentAt(LocalDateTime.now().minusHours(2));
        notification.setReceivedAt(null);
        return notification;
    }

    public static Notification sampleSmsAlert() {
        //
        NotificationMessage message = new NotificationMessage(
            "Payment Reminder",
            "Your rental payment is due in 24 hours",
            null
        );

        Notification notification = new Notification();
        notification.setReceiverId("user303");
        notification.setSenderId("system");
        notification.setMessage(message);
        notification.setType(NotificationType.ALERT);
        notification.setChannelType(ChannelType.SMS);
        notification.setStatus(Status.PENDING);
        notification.setSentAt(null);
        notification.setReceivedAt(null);
        return notification;
    }

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        //
        throw new IllegalArgumentException("Update not allowed. ");
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}
