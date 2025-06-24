package ing.beribtur.aggregate.notification.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.notification.entity.vo.*;
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

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        //
        throw new IllegalArgumentException("Update not allowed. ");
    }
}
