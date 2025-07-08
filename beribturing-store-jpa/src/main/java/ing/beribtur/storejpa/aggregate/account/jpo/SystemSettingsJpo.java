package ing.beribtur.storejpa.aggregate.account.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.accent.util.JsonUtil;
import ing.beribtur.aggregate.account.entity.SystemSettings;
import ing.beribtur.aggregate.account.entity.vo.NotificationSettings;
import ing.beribtur.aggregate.account.entity.vo.PaymentSettings;
import ing.beribtur.aggregate.account.entity.vo.SecuritySettings;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SYSTEM_SETTINGS")
public class SystemSettingsJpo extends DomainEntityJpo {
    //
    private String platformName;
    private String supportEmail;
    private String platformDescription;
    private boolean maintenanceMode;

    @Column(nullable = false)
    private String paymentSettingsJson;

    @Column(nullable = false)
    private String notificationSettingsJson;

    @Column(nullable = false)
    private String securitySettingsJson;

    public SystemSettingsJpo(SystemSettings systemSettings) {
        //
        super(systemSettings);
        BeanUtils.copyProperties(systemSettings, this, "paymentSettings", "notificationSettings", "securitySettings");

        this.paymentSettingsJson = JsonUtil.toJson(systemSettings.getPaymentSettings());
        this.notificationSettingsJson = JsonUtil.toJson(systemSettings.getNotificationSettings());
        this.securitySettingsJson = JsonUtil.toJson(systemSettings.getSecuritySettings());
    }

    public SystemSettings toDomain() {
        //
        SystemSettings domain = new SystemSettings();
        BeanUtils.copyProperties(this, domain, "paymentSettingsJson", "notificationSettingsJson", "securitySettingsJson");

        domain.setPaymentSettings(JsonUtil.fromJson(this.paymentSettingsJson, PaymentSettings.class));
        domain.setNotificationSettings(JsonUtil.fromJson(this.notificationSettingsJson, NotificationSettings.class));
        domain.setSecuritySettings(JsonUtil.fromJson(this.securitySettingsJson, SecuritySettings.class));

        return domain;
    }
}
