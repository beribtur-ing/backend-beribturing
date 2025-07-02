package ing.beribtur.facade.api.feature.notification.command;

import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.List;

@Getter
@Setter
public class MarkNotificationsAsReadCommand extends CommandRequest<Integer> {
    //
    private List<String> notificationIds;

    public void validate() {
        //
        Assert.notNull(notificationIds, "'notificationIds' is required.");
        Assert.notEmpty(notificationIds, "'notificationIds' cannot be empty.");
    }
}