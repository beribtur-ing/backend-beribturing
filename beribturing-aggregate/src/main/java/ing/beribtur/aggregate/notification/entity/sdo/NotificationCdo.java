package ing.beribtur.aggregate.notification.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.notification.entity.vo.NotificationType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCdo extends CreationDataObject {
    private String recipientId;
    private String message;
    private NotificationType type;
    private boolean read;
    private LocalDateTime timestamp;
}
