package ing.beribtur.facade.api.feature.notification.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.notification.entity.Notification;
import ing.beribtur.facade.api.feature.notification.query.FindReceivedNotificationsQuery;
import ing.beribtur.facade.api.feature.notification.query.FindUnreadNotificationsQuery;

import java.util.List;

public interface NotificationSeekFacade {
    //
    QueryResponse<List<Notification>> findUnreadNotifications(FindUnreadNotificationsQuery query);
    
    QueryResponse<List<Notification>> findReceivedNotifications(FindReceivedNotificationsQuery query);
}