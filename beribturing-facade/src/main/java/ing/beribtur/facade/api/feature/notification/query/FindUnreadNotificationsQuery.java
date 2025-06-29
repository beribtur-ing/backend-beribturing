package ing.beribtur.facade.api.feature.notification.query;

import ing.beribtur.accent.message.OffsetQueryRequest;
import ing.beribtur.aggregate.notification.entity.Notification;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.List;

@Getter
@Setter
public class FindUnreadNotificationsQuery extends OffsetQueryRequest<List<Notification>> {
    //
    private String receiverId;

    public void validate() {
        //
        Assert.hasText(receiverId, "'receiverId' is required.");
    }
}
