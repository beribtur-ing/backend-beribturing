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

    public void validate() {
        Assert.hasText(currentPassword, "Current password is required");
        Assert.hasText(newPassword, "New password is required");

        Assert.isTrue(!currentPassword.equals(newPassword), "New password must be different from current password");
        
        AuthUtil.passwordValidation(newPassword);
    }
}