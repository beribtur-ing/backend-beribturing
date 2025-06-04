package ing.beribtur.facade.api.feature.rnt.auth.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.rnt.auth.command.ResetPasswordRntCommand;
import ing.beribtur.facade.api.feature.rnt.auth.command.SendOtpRntCommand;
import ing.beribtur.facade.api.feature.rnt.auth.command.VerifyOtpAndSignUpRntCommand;

public interface AuthRntFlowFacade {
    CommandResponse<Boolean> sendOtp(SendOtpRntCommand command);
    CommandResponse<Boolean> verifyOtpAndSignUpLender(VerifyOtpAndSignUpRntCommand command);
    CommandResponse<Boolean> sendResetPasswordOTP(SendOtpRntCommand command);
    CommandResponse<Boolean> resetPassword(ResetPasswordRntCommand command);
}
