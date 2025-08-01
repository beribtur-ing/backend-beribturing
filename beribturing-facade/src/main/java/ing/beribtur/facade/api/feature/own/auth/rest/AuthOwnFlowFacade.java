package ing.beribtur.facade.api.feature.own.auth.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.facade.api.feature.own.auth.command.RefreshTokenOwnCommand;
import ing.beribtur.facade.api.feature.own.auth.command.ResetPasswordOwnCommand;
import ing.beribtur.facade.api.feature.own.auth.command.SendOtpOwnCommand;
import ing.beribtur.facade.api.feature.own.auth.command.VerifyOtpAndSignUpOwnCommand;
import ing.beribtur.feature.shared.sdo.AccountSignInTokenRdo;

public interface AuthOwnFlowFacade {
    CommandResponse<Boolean> sendOtp(SendOtpOwnCommand command);
    CommandResponse<Boolean> sendResetPasswordOTP(SendOtpOwnCommand command);
    CommandResponse<Boolean> verifyOtpAndSignUp(VerifyOtpAndSignUpOwnCommand command);
    CommandResponse<Boolean> resetPassword(ResetPasswordOwnCommand command);
    CommandResponse<AccountSignInTokenRdo> refreshToken(RefreshTokenOwnCommand command);
}
