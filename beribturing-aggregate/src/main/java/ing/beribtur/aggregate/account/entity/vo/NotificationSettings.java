package ing.beribtur.aggregate.account.entity.vo;

import ing.beribtur.accent.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationSettings implements ValueObject {
    //
    private boolean emailNotifications;
    private boolean smsNotifications;
    private boolean pushNotifications;
    private String notificationEmailTemplate;
}
