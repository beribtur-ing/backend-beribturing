package ing.beribtur.facade.api.feature.own.user.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.own.user.command.ModifyProfileOwnCommand;
import ing.beribtur.facade.api.feature.own.user.command.UpdateNotificationPreferencesOwnCommand;
import ing.beribtur.facade.api.feature.own.user.command.ChangePasswordOwnCommand;
import org.springframework.web.multipart.MultipartFile;

public interface UserOwnFlowFacade {
    CommandResponse<String> modifyProfile(ModifyProfileOwnCommand command, MultipartFile image) throws Exception;
    CommandResponse<String> updateNotificationPreferences(UpdateNotificationPreferencesOwnCommand command) throws Exception;
    CommandResponse<String> changePassword(ChangePasswordOwnCommand command) throws Exception;
}
