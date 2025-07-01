package ing.beribtur.aggregate.account.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class OwnerNotificationPreferences extends NotificationPreferences {
    
    private EmailNotifications emailNotifications;
    private SmsNotifications smsNotifications;
    
    public OwnerNotificationPreferences(EmailNotifications emailNotifications, SmsNotifications smsNotifications) {
        this.type = "OWNER";
        this.emailNotifications = emailNotifications;
        this.smsNotifications = smsNotifications;
    }
    
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EmailNotifications {
        private boolean newBookingsAndReservations;
        private boolean messagesFromCustomers;
        private boolean paymentConfirmations;
    }
    
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SmsNotifications {
        private boolean newBookingsAndReservations;
        private boolean messagesFromCustomers;
        private boolean paymentConfirmations;
    }
    
    public static OwnerNotificationPreferences createDefault() {
        return new OwnerNotificationPreferences(
            new EmailNotifications(false, false, false),
            new SmsNotifications(false, false, false)
        );
    }
}