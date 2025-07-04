package ing.beribtur.facade.api.feature.adm.auth.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.facade.api.feature.adm.auth.command.RefreshTokenAdmCommand;
import ing.beribtur.facade.api.feature.adm.auth.command.ResetPasswordAdmCommand;
import ing.beribtur.facade.api.feature.adm.auth.command.SendOtpAdmCommand;
import ing.beribtur.feature.shared.sdo.AccountSignInTokenRdo;

public interface AuthAdmFlowFacade {
    CommandResponse<Boolean> sendOtp(SendOtpAdmCommand command);
    CommandResponse<Boolean> sendResetPasswordOTP(SendOtpAdmCommand command);
    CommandResponse<Boolean> resetPassword(ResetPasswordAdmCommand command);
    CommandResponse<AccountSignInTokenRdo> refreshToken(RefreshTokenAdmCommand command);
}
