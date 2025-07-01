package ing.beribtur.facade.api.feature.own.user.command;

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
public class UpdateNotificationPreferencesOwnCommand extends CommandRequest<String> {
    
    private EmailNotifications emailNotifications;
    private SmsNotifications smsNotifications;
    
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
    
    public void validate() {
        //
        Assert.notNull(emailNotifications, "Email notifications configuration is required");
        Assert.notNull(smsNotifications, "SMS notifications configuration is required");
    }
}