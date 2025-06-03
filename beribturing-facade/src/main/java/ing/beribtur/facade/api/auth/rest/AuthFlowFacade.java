package ing.beribtur.facade.auth.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.auth.command.ResetPasswordCommand;
import ing.beribtur.facade.auth.command.SendOtpCommand;
import ing.beribtur.facade.auth.command.VerifyOtpAndSignUpLendeeCommand;
import ing.beribtur.facade.auth.command.VerifyOtpAndSignUpLenderCommand;

public interface AuthFlowFacade {
    //
    CommandResponse<Boolean> sendOtp(SendOtpCommand command);
    CommandResponse<Boolean> sendResetPasswordOTP(SendOtpCommand command);
    CommandResponse<Boolean> verifyOtpAndSignUpLendee(VerifyOtpAndSignUpLendeeCommand command);
    CommandResponse<Boolean> verifyOtpAndSignUpLender(VerifyOtpAndSignUpLenderCommand command);
    CommandResponse<Boolean> resetPassword(ResetPasswordCommand command);
}
