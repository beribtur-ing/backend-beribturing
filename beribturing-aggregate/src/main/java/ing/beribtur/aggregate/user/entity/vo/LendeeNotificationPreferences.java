package ing.beribtur.aggregate.user.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LendeeNotificationPreferences extends NotificationPreferences{
    
    private EmailNotifications emailNotifications;
    private PushNotifications pushNotifications;
    private SmsNotifications smsNotifications;
    private MarketingNotifications marketingNotifications;
    
    public LendeeNotificationPreferences(EmailNotifications emailNotifications, PushNotifications pushNotifications, 
                                       SmsNotifications smsNotifications, MarketingNotifications marketingNotifications) {
        this.emailNotifications = emailNotifications;
        this.pushNotifications = pushNotifications;
        this.smsNotifications = smsNotifications;
        this.marketingNotifications = marketingNotifications;
    }
    
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
    
    public static LendeeNotificationPreferences createDefault() {
        return new LendeeNotificationPreferences(
            new EmailNotifications(false, false),
            new PushNotifications(false, false, false),
            new SmsNotifications(false, false),
            new MarketingNotifications(false, false)
        );
    }
}