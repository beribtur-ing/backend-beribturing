package ing.beribtur.storejpa.aggregate.account.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SYSTEM_SETTINGS")
public class SystemSettingsJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String platformName;

    @Column(nullable = false)
    private String supportEmail;

    @Column(length = 1000)
    private String platformDescription;

    private boolean maintenanceMode;

    // PaymentSettings
    private int commissionRate;
    private String defaultCurrency;
    private String publicKey;
    private boolean enableAutomaticPayouts;

    // NotificationSettings
    private boolean emailNotifications;
    private boolean smsNotifications;
    private boolean pushNotifications;
    private String notificationEmailTemplate;

    // SecuritySettings
    private int sessionTimoutMinutes;
    private int maxLoginAttempts;
    private boolean requireTwoFactorAuthentication;
    private boolean enforcePasswordComplexity;
    private boolean enableAuditLogging;

    public SystemSettingsJpo(SystemSettings systemSettings) {
        //
        super(systemSettings);
        BeanUtils.copyProperties(systemSettings, this);

        // PaymentSettings
        if (systemSettings.getPaymentSettings() != null) {
            PaymentSettings payment = systemSettings.getPaymentSettings();
            this.commissionRate = payment.getCommissionRate();
            this.defaultCurrency = payment.getDefaultCurrency();
            this.publicKey = payment.getPublicKey();
            this.enableAutomaticPayouts = payment.isEnableAutomaticPayouts();
        }

        // NotificationSettings
        if (systemSettings.getNotificationSettings() != null) {
            NotificationSettings notification = systemSettings.getNotificationSettings();
            this.emailNotifications = notification.isEmailNotifications();
            this.smsNotifications = notification.isSmsNotifications();
            this.pushNotifications = notification.isPushNotifications();
            this.notificationEmailTemplate = notification.getNotificationEmailTemplate();
        }

        // SecuritySettings
        if (systemSettings.getSecuritySettings() != null) {
            SecuritySettings security = systemSettings.getSecuritySettings();
            this.sessionTimoutMinutes = security.getSessionTimoutMinutes();
            this.maxLoginAttempts = security.getMaxLoginAttempts();
            this.requireTwoFactorAuthentication = security.isRequireTwoFactorAuthentication();
            this.enforcePasswordComplexity = security.isEnforcePasswordComplexity();
            this.enableAuditLogging = security.isEnableAuditLogging();
        }
    }

    public static List<SystemSettings> toDomains(List<SystemSettingsJpo> jpos) {
        //
        return jpos.stream().map(SystemSettingsJpo::toDomain).toList();
    }

    public static Page<SystemSettings> toDomains(Page<SystemSettingsJpo> jpoPage) {
        //
        List<SystemSettingsJpo> content = jpoPage.getContent();
        List<SystemSettings> domains = toDomains(content);
        return new PageImpl<>(domains, jpoPage.getPageable(), jpoPage.getTotalElements());
    }

    public SystemSettings toDomain() {
        //
        SystemSettings systemSettings = new SystemSettings();
        BeanUtils.copyProperties(this, systemSettings);

        // PaymentSettings
        systemSettings.setPaymentSettings(new PaymentSettings(
                this.commissionRate,
                this.defaultCurrency,
                this.publicKey,
                this.enableAutomaticPayouts
        ));

        // NotificationSettings
        systemSettings.setNotificationSettings(new NotificationSettings(
                this.emailNotifications,
                this.smsNotifications,
                this.pushNotifications,
                this.notificationEmailTemplate
        ));

        // SecuritySettings
        systemSettings.setSecuritySettings(new SecuritySettings(
                this.sessionTimoutMinutes,
                this.maxLoginAttempts,
                this.requireTwoFactorAuthentication,
                this.enforcePasswordComplexity,
                this.enableAuditLogging
        ));

        return systemSettings;
    }
}
