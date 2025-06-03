package ing.beribtur.aggregate.notification.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.notification.entity.vo.NotificationType;
import ing.beribtur.aggregate.user.entity.vo.Notifiable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends DomainEntity {
    //
    private String recipientId;
    private String message;
    private NotificationType type;
    private boolean read;
    private LocalDateTime timestamp;

    // Domain relationships
    private transient Notifiable recipient;

    @Override
    protected void modifyAttributes(NameValueList var1) {

    }
}
