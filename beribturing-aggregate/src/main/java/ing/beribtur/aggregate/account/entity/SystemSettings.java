package ing.beribtur.aggregate.account.entity;


import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.util.JsonUtil;
import ing.beribtur.aggregate.account.entity.sdo.SystemSettingsCdo;
import ing.beribtur.aggregate.account.entity.vo.NotificationSettings;
import ing.beribtur.aggregate.account.entity.vo.PaymentSettings;
import ing.beribtur.aggregate.account.entity.vo.SecuritySettings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SystemSettings extends DomainEntity {
    //
    private String platformName;
    private String supportEmail;
    private String platformDescription;
    private boolean maintenanceMode;
    private PaymentSettings paymentSettings;
    private NotificationSettings notificationSettings;
    private SecuritySettings securitySettings;

    public SystemSettings(SystemSettingsCdo systemSettingsCdo) {
        //
        super(systemSettingsCdo.genId());
        BeanUtils.copyProperties(systemSettingsCdo, this);
    }

    public static String genId() {
        //
        return "default-system-settings-id";
    }

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        //
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName().trim()) {
                case "platformName" -> this.platformName = value;
                case "supportEmail" -> this.supportEmail = value;
                case "platformDescription" -> this.platformDescription = value;
                case "maintenanceMode" -> this.maintenanceMode = Boolean.parseBoolean(value);
                case "paymentSettings" -> this.paymentSettings = JsonUtil.fromJson(value, PaymentSettings.class);
                case "notificationSettings" ->
                        this.notificationSettings = JsonUtil.fromJson(value, NotificationSettings.class);
                case "securitySettings" -> this.securitySettings = JsonUtil.fromJson(value, SecuritySettings.class);
                default -> throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }
}
