package ing.beribtur.aggregate.account.entity.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminNotificationPreferences extends NotificationPreferences {


    public AdminNotificationPreferences() {
        this.type = "ADMIN";
    }

    public static AdminNotificationPreferences createDefault() {
        return new AdminNotificationPreferences();
    }
}