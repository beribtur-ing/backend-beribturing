package ing.beribtur.feature.notification.sdo;

import ing.beribtur.aggregate.notification.entity.vo.ChannelType;
import ing.beribtur.aggregate.notification.entity.vo.NotificationType;
import ing.beribtur.aggregate.notification.entity.vo.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationSearchQdo {
    //
    private String receiverId;
    private String senderId;
    private NotificationType type;
    private ChannelType channelType;
    private Status status;
}