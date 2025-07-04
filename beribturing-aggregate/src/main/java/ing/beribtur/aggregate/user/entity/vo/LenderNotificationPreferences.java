package ing.beribtur.aggregate.user.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LenderNotificationPreferences {
    
    private EmailNotifications emailNotifications;
    private SmsNotifications smsNotifications;
    
    public LenderNotificationPreferences(EmailNotifications emailNotifications, SmsNotifications smsNotifications) {
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
    
    public static LenderNotificationPreferences createDefault() {
        return new LenderNotificationPreferences(
            new EmailNotifications(false, false, false),
            new SmsNotifications(false, false, false)
        );
    }
}