package ing.beribtur.facade.api.feature.own.auth.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.aggregate.user.entity.vo.LenderType;
import ing.beribtur.aggregate.user.entity.vo.Profile;
import ing.beribtur.facade.api.feature.own.auth.command.ResetPasswordOwnCommand;
import ing.beribtur.facade.api.feature.own.auth.command.SendOtpOwnCommand;
import ing.beribtur.facade.api.feature.own.auth.command.VerifyOtpAndSignUpOwnCommand;
import ing.beribtur.feature.own.auth.flow.AuthOwnFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature/owner/auth")
@RequiredArgsConstructor
public class AuthOwnFlowResource implements AuthOwnFlowFacade {
    private final AuthOwnFlow authOwnFlow;

    @Override
    @PostMapping("/send-otp/command")
    public CommandResponse<Boolean> sendOtp(@RequestBody SendOtpOwnCommand command) {
        command.validate();
        String phoneNumber = command.getPhoneNumber();
        Boolean response = this.authOwnFlow.sendSignUpOTP(phoneNumber);

        return new CommandResponse<>(response);
    }

    @Override
    @PostMapping("/verify-otp-sign-up/command")
    public CommandResponse<Boolean> verifyOtpAndSignUp(@RequestBody VerifyOtpAndSignUpOwnCommand command) {
        command.validate();
        String phoneNumber = command.getPhoneNumber();
        String otp = command.getOtp();
        String password = command.getPassword();
        String name = command.getName();
        Profile profile = command.getProfile();
        LenderType lenderType = command.getLenderType();
        Boolean verifyAndSignUpRdo = this.authOwnFlow.verifyOTPAndSignUpLender(phoneNumber, otp, password, name, profile, lenderType);

        return new CommandResponse<>(verifyAndSignUpRdo);
    }

    @Override
    @PostMapping("/send-reset-password-otp/command")
    public CommandResponse<Boolean> sendResetPasswordOTP(@RequestBody SendOtpOwnCommand command) {
        command.validate();
        String phoneNumber = command.getPhoneNumber();
        Boolean response = this.authOwnFlow.sendResetPasswordOTP(phoneNumber);

        return new CommandResponse<>(response);
    }

    @Override
    @PostMapping("/reset-password/command")
    public CommandResponse<Boolean> resetPassword(@RequestBody ResetPasswordOwnCommand command) {
        //
        command.validate();
        String phoneNumber = command.getPhoneNumber();
        String newPassword = command.getNewPassword();
        String otp = command.getOtp();
        Boolean response = this.authOwnFlow.resetPassword(phoneNumber, newPassword, otp);

        return new CommandResponse<>(response);
    }
}
