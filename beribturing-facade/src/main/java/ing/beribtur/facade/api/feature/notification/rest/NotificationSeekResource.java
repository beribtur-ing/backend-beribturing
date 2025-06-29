package ing.beribtur.facade.api.feature.notification.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.accent.util.QueryResponseUtil;
import ing.beribtur.aggregate.notification.entity.Notification;
import ing.beribtur.facade.api.feature.notification.query.FindUnreadNotificationsQuery;
import ing.beribtur.feature.notification.NotificationSeek;
import ing.beribtur.feature.notification.sdo.NotificationSearchQdo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/notification")
public class NotificationSeekResource implements NotificationSeekFacade {
    //
    private final NotificationSeek notificationSeek;

    @Override
    @PostMapping("/find-unread-notifications/query")
    public QueryResponse<List<Notification>> findUnreadNotifications(@RequestBody FindUnreadNotificationsQuery query) {
        //
        query.validate();
        String receiverId = query.getReceiverId();
        Page<Notification> responsePage = notificationSeek.findUnreadNotifications(receiverId, query.getOffset());
        return QueryResponseUtil.fromPage(responsePage);
    }
}
