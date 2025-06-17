package ing.beribtur.facade.api.feature.adm.auth.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.adm.auth.command.ResetPasswordAdmCommand;
import ing.beribtur.facade.api.feature.adm.auth.command.SendOtpAdmCommand;
import ing.beribtur.feature.adm.auth.flow.AuthAdmFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature/admin/auth")
@RequiredArgsConstructor
public class AuthAdmFlowResource implements AuthAdmFlowFacade {
    private final AuthAdmFlow authAdmFlow;

    @Override
    @PostMapping("/send-otp/command")
    public CommandResponse<Boolean> sendOtp(@RequestBody SendOtpAdmCommand command) {
        command.validate();
        String phoneNumber = command.getPhoneNumber();
        Boolean response = this.authAdmFlow.sendOTP(phoneNumber);

        return new CommandResponse<>(response);
    }

    @Override
    @PostMapping("/send-reset-password-otp/command")
    public CommandResponse<Boolean> sendResetPasswordOTP(@RequestBody SendOtpAdmCommand command) {
        command.validate();
        String phoneNumber = command.getPhoneNumber();
        Boolean response = this.authAdmFlow.sendResetPasswordOTP(phoneNumber);

        return new CommandResponse<>(response);
    }

    @Override
    @PostMapping("/reset-password/command")
    public CommandResponse<Boolean> resetPassword(@RequestBody ResetPasswordAdmCommand command) {
        //
        command.validate();
        String phoneNumber = command.getPhoneNumber();
        String newPassword = command.getNewPassword();
        String otp = command.getOtp();
        Boolean response = this.authAdmFlow.resetPassword(phoneNumber, newPassword, otp);

        return new CommandResponse<>(response);
    }
}
