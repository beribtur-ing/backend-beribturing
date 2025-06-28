package ing.beribtur.facade.api.feature.notification.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.aggregate.notification.entity.sdo.NotificationCdo;
import ing.beribtur.facade.api.feature.notification.command.RegisterNotificationCommand;
import ing.beribtur.feature.notification.NotificationFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/notification")
public class NotificationFlowResource implements NotificationFlowFacade {
    //
    private final NotificationFlow notificationFlow;

    @Override
    @PostMapping("/register-notification/command")
    public CommandResponse<String> registerNotification(@RequestBody RegisterNotificationCommand command) {
        //
        command.validate();
        NotificationCdo cdo = command.getNotificationCdo();
        String id = this.notificationFlow.register(cdo);
        return new CommandResponse<>(id);
    }
}
