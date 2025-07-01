package ing.beribtur.facade.api.feature.rnt.user.command;

import ing.beribtur.accent.message.CommandRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNotificationPreferencesRntCommand extends CommandRequest<String> {
    
    private EmailNotifications emailNotifications;
    private PushNotifications pushNotifications;
    private SmsNotifications smsNotifications;
    private MarketingNotifications marketingNotifications;
    
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EmailNotifications {
        private boolean rentalReminders;
        private boolean newMessages;
    }
    
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PushNotifications {
        private boolean rentalReminders;
        private boolean newMessages;
        private boolean promotionsAndDeals;
    }
    
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SmsNotifications {
        private boolean rentalReminders;
        private boolean newMessages;
    }
    
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MarketingNotifications {
        private boolean promotionsAndDeals;
        private boolean marketingEmails;
    }
    
    public void validate() {
        //
        Assert.notNull(emailNotifications, "Email notifications configuration is required");
        Assert.notNull(pushNotifications, "Push notifications configuration is required");
        Assert.notNull(smsNotifications, "SMS notifications configuration is required");
        Assert.notNull(marketingNotifications, "Marketing notifications configuration is required");
    }
}