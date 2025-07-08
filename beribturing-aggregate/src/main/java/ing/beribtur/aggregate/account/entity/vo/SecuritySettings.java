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
public class SecuritySettings implements ValueObject {
    //
    private int sessionTimoutMinutes;
    private int maxLoginAttempts;
    private boolean requireTwoFactorAuthentication;
    private boolean enforcePasswordComplexity;
    private boolean enableAuditLogging;
}
