package ing.beribtur.facade.api.feature.notification.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.aggregate.notification.entity.sdo.NotificationCdo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RegisterNotificationCommand extends CommandRequest<String> {
    //
    private NotificationCdo notificationCdo;

    public void validate() {
        //
        Assert.notNull(notificationCdo, "'notificationCdo' is required.");
    }
}
