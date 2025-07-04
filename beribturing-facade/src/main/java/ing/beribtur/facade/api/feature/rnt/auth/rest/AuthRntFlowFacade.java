package ing.beribtur.facade.api.feature.rnt.auth.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.rnt.auth.command.RefreshTokenRntCommand;
import ing.beribtur.facade.api.feature.rnt.auth.command.ResetPasswordRntCommand;
import ing.beribtur.facade.api.feature.rnt.auth.command.SendOtpRntCommand;
import ing.beribtur.facade.api.feature.rnt.auth.command.VerifyOtpAndSignUpRntCommand;
import ing.beribtur.feature.shared.sdo.AccountSignInTokenRdo;

public interface AuthRntFlowFacade {
    CommandResponse<Boolean> sendOtp(SendOtpRntCommand command);
    CommandResponse<Boolean> verifyOtpAndSignUp(VerifyOtpAndSignUpRntCommand command);
    CommandResponse<Boolean> sendResetPasswordOTP(SendOtpRntCommand command);
    CommandResponse<Boolean> resetPassword(ResetPasswordRntCommand command);
    CommandResponse<AccountSignInTokenRdo> refreshToken(RefreshTokenRntCommand command);
}
