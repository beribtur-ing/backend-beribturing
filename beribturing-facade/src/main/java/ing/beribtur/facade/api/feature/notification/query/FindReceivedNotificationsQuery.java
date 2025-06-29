package ing.beribtur.facade.api.feature.notification.query;

import ing.beribtur.accent.message.OffsetQueryRequest;
import ing.beribtur.aggregate.notification.entity.Notification;
import ing.beribtur.aggregate.notification.entity.vo.ChannelType;
import ing.beribtur.aggregate.notification.entity.vo.NotificationType;
import ing.beribtur.aggregate.notification.entity.vo.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class FindReceivedNotificationsQuery extends OffsetQueryRequest<List<Notification>> {
    //
    private String receiverId;
    private Status status;
    private NotificationType type;
    private ChannelType channelType;

    public void validate() {
        //
        Assert.hasText(receiverId, "'receiverId' is required.");
        
        if (status != null) {
            Assert.isTrue(Arrays.stream(Status.values()).anyMatch(s -> s.equals(status)), "'status' is invalid");
        }
        
        if (type != null) {
            Assert.isTrue(Arrays.stream(NotificationType.values()).anyMatch(t -> t.equals(type)), "'type' is invalid");
        }
        
        if (channelType != null) {
            Assert.isTrue(Arrays.stream(ChannelType.values()).anyMatch(c -> c.equals(channelType)), "'channelType' is invalid");
        }
    }
}