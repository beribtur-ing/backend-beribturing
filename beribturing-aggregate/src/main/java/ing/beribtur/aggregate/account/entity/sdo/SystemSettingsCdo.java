package ing.beribtur.aggregate.account.entity.sdo;


import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.account.entity.vo.NotificationSettings;
import ing.beribtur.aggregate.account.entity.vo.PaymentSettings;
import ing.beribtur.aggregate.account.entity.vo.SecuritySettings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SystemSettingsCdo extends CreationDataObject {
    //
    private String platformName;
    private String supportEmail;
    private String platformDescription;
    private boolean maintenanceMode;
    private PaymentSettings paymentSettings;
    private NotificationSettings notificationSettings;
    private SecuritySettings securitySettings;
}
