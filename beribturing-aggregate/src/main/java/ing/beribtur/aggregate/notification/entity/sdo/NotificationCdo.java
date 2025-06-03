package ing.beribtur.aggregate.notification.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.notification.entity.vo.NotificationType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class NotificationCdo extends CreationDataObject {
    private String recipientId;
    private String message;
    private NotificationType type;
    private boolean read;
    private LocalDateTime timestamp;
}
