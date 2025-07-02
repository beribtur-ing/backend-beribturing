package ing.beribtur.facade.api.feature.notification.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.notification.command.MarkNotificationsAsReadCommand;
import ing.beribtur.facade.api.feature.notification.command.RegisterNotificationCommand;

public interface NotificationFlowFacade {
    //
    CommandResponse<String> registerNotification(RegisterNotificationCommand command);
    
    CommandResponse<Integer> markNotificationsAsRead(MarkNotificationsAsReadCommand command);
}
