package ing.beribtur.storejpa.aggregate.notification.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.notification.entity.Notification;
import ing.beribtur.aggregate.notification.entity.vo.*;
import ing.beribtur.accent.util.JsonUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "NOTIFICATION")
public class NotificationJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String receiverId;

    @Column(nullable = false)
    private String senderId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ChannelType channelType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime sentAt;

    private LocalDateTime receivedAt;

    public NotificationJpo(Notification notification) {
        //
        super(notification);
        BeanUtils.copyProperties(notification, this);

        // Convert NotificationMessage to JSON
        if (notification.getMessage() != null) {
            this.message = JsonUtil.toJson(notification.getMessage());
        }
    }

    public Notification toDomain() {
        //
        Notification notification = new Notification();
        BeanUtils.copyProperties(this, notification);

        // Convert JSON back to NotificationMessage
        if (this.message != null) {
            notification.setMessage(JsonUtil.fromJson(this.message, NotificationMessage.class));
        }

        return notification;
    }

    public static List<Notification> toDomains(List<NotificationJpo> jpos) {
        //
        return jpos.stream().map(NotificationJpo::toDomain).toList();
    }
}
