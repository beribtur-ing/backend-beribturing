package ing.beribtur.facade.api.feature.rnt.user.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.feature.shared.util.AuthUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class ChangePasswordRntCommand extends CommandRequest<String> {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;

    public void validate() {
        Assert.hasText(currentPassword, "Current password is required");
        Assert.hasText(newPassword, "New password is required");
        Assert.hasText(confirmPassword, "Confirm password is required");
        
        Assert.isTrue(newPassword.equals(confirmPassword), "New password and confirm password must match");
        Assert.isTrue(!currentPassword.equals(newPassword), "New password must be different from current password");
        
        AuthUtil.passwordValidation(newPassword);
    }
}