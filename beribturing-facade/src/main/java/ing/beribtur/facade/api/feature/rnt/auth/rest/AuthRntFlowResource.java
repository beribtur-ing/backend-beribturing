package ing.beribtur.facade.api.feature.rnt.auth.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.user.entity.vo.Profile;
import ing.beribtur.facade.api.feature.rnt.auth.command.RefreshTokenRntCommand;
import ing.beribtur.facade.api.feature.rnt.auth.command.ResetPasswordRntCommand;
import ing.beribtur.facade.api.feature.rnt.auth.command.SendOtpRntCommand;
import ing.beribtur.facade.api.feature.rnt.auth.command.VerifyOtpAndSignUpRntCommand;
import ing.beribtur.feature.rnt.auth.flow.AuthRntFlow;
import ing.beribtur.feature.shared.sdo.AccountSignInTokenRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature/renter/auth")
@RequiredArgsConstructor
public class AuthRntFlowResource implements AuthRntFlowFacade {
    private final AuthRntFlow authRntFlow;

    @Override
    @PostMapping("/send-otp/command")
    public CommandResponse<Boolean> sendOtp(@RequestBody SendOtpRntCommand command) {
        command.validate();
        String phoneNumber = command.getPhoneNumber();
        Boolean response = this.authRntFlow.sendSignUpOTP(phoneNumber);

        return new CommandResponse<>(response);
    }

    @Override
    @PostMapping("/verify-otp-sign-up/command")
    public CommandResponse<Boolean> verifyOtpAndSignUp(@RequestBody VerifyOtpAndSignUpRntCommand command) {
        command.validate();
        String phoneNumber = command.getPhoneNumber();
        String otp = command.getOtp();
        String password = command.getPassword();
        String name = command.getName();
        Profile profile = command.getProfile();
        Boolean verifyAndSignUpRdo = this.authRntFlow.verifyOTPAndSignUpLendee(phoneNumber, otp, password, name, profile);

        return new CommandResponse<>(verifyAndSignUpRdo);
    }

    @Override
    @PostMapping("/send-reset-password-otp/command")
    public CommandResponse<Boolean> sendResetPasswordOTP(@RequestBody SendOtpRntCommand command) {
        command.validate();
        String phoneNumber = command.getPhoneNumber();
        Boolean response = this.authRntFlow.sendResetPasswordOTP(phoneNumber);

        return new CommandResponse<>(response);
    }

    @Override
    @PostMapping("/reset-password/command")
    public CommandResponse<Boolean> resetPassword(@RequestBody ResetPasswordRntCommand command) {
        //
        command.validate();
        String phoneNumber = command.getPhoneNumber();
        String newPassword = command.getNewPassword();
        String otp = command.getOtp();
        Boolean response = this.authRntFlow.resetPassword(phoneNumber, newPassword, otp);

        return new CommandResponse<>(response);
    }

    @Override
    @PostMapping("/refresh-token/command")
    public CommandResponse<AccountSignInTokenRdo> refreshToken(@RequestBody RefreshTokenRntCommand command) {
        //
        command.validate();
        AccountSignInTokenRdo rdo = this.authRntFlow.refreshToken(command.getRefreshToken());
        return new CommandResponse<>(rdo);
    }
}
