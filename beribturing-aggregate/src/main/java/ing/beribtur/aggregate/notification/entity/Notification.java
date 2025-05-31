package ing.beribtur.aggregate.notification.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.aggregate.notification.entity.vo.NotificationType;
import ing.beribtur.aggregate.user.entity.vo.Notifiable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends DomainEntity {
    //
    private UUID recipientId;
    private String message;
    private NotificationType type;
    private boolean isRead;
    private LocalDateTime timestamp;

    // Domain relationships
    private transient Notifiable recipient;
}
