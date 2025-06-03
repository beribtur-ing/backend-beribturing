package ing.beribtur.facade.api.auth.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.aggregate.user.entity.vo.LenderType;
import ing.beribtur.aggregate.user.entity.vo.Profile;
import ing.beribtur.auth.flow.AuthFlow;
import ing.beribtur.facade.api.auth.command.ResetPasswordCommand;
import ing.beribtur.facade.api.auth.command.SendOtpCommand;
import ing.beribtur.facade.api.auth.command.VerifyOtpAndSignUpLendeeCommand;
import ing.beribtur.facade.api.auth.command.VerifyOtpAndSignUpLenderCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthFlowResource implements AuthFlowFacade {
    private final AuthFlow authFlow;

    @Override
    @PostMapping("/send-otp/command")
    public CommandResponse<Boolean> sendOtp(@RequestBody SendOtpCommand command) {
        command.validate();
        String phoneNumber = command.getPhoneNumber();
        Boolean response = this.authFlow.sendSignUpOTP(phoneNumber);

        return new CommandResponse<>(response);
    }

    @Override
    @PostMapping("/send-reset-password-otp/command")
    public CommandResponse<Boolean> sendResetPasswordOTP(@RequestBody SendOtpCommand command) {
        command.validate();
        String phoneNumber = command.getPhoneNumber();
        Boolean response = this.authFlow.sendResetPasswordOTP(phoneNumber);

        return new CommandResponse<>(response);
    }

    @Override
    @PostMapping("/lendee/verify-otp-sign-up/command")
    public CommandResponse<Boolean> verifyOtpAndSignUpLendee(@RequestBody VerifyOtpAndSignUpLendeeCommand command) {
        command.validate();
        String phoneNumber = command.getPhoneNumber();
        String otp = command.getOtp();
        String password = command.getPassword();
        String name = command.getName();
        Profile profile = command.getProfile();
        Boolean verifyAndSignUpRdo = this.authFlow.verifyOTPAndSignUpLendee(phoneNumber, otp, password, name, profile);

        return new CommandResponse<>(verifyAndSignUpRdo);
    }

    @Override
    @PostMapping("/lender/verify-otp-sign-up/command")
    public CommandResponse<Boolean> verifyOtpAndSignUpLender(@RequestBody VerifyOtpAndSignUpLenderCommand command) {
        command.validate();
        String phoneNumber = command.getPhoneNumber();
        String otp = command.getOtp();
        String password = command.getPassword();
        String name = command.getName();
        Profile profile = command.getProfile();
        LenderType lenderType = command.getLenderType();
        Boolean verifyAndSignUpRdo = this.authFlow.verifyOTPAndSignUpLender(phoneNumber, otp, password, name, profile, lenderType);

        return new CommandResponse<>(verifyAndSignUpRdo);
    }

    @Override
    @PostMapping("/reset-password/command")
    public CommandResponse<Boolean> resetPassword(@RequestBody ResetPasswordCommand command) {
        //
        command.validate();
        String phoneNumber = command.getPhoneNumber();
        String newPassword = command.getNewPassword();
        String otp = command.getOtp();
        Boolean response = this.authFlow.resetPassword(phoneNumber, newPassword, otp);

        return new CommandResponse<>(response);
    }
}
