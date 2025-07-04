package ing.beribtur.facade.api.feature.rnt.user.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.rnt.user.command.ModifyProfileRntCommand;
import ing.beribtur.facade.api.feature.rnt.user.command.UpdateNotificationPreferencesRntCommand;
import ing.beribtur.facade.api.feature.rnt.user.command.UpdateSecuritySettingsRntCommand;
import ing.beribtur.facade.api.feature.rnt.user.command.UpdateAppearanceSettingsRntCommand;
import ing.beribtur.facade.api.feature.rnt.user.command.ChangePasswordRntCommand;
import ing.beribtur.facade.api.feature.own.user.command.UpdatePrivacySettingsRntCommand;
import org.springframework.web.multipart.MultipartFile;

public interface UserRntFlowFacade {
    CommandResponse<String> modifyProfile(ModifyProfileRntCommand command, MultipartFile image) throws Exception;
    CommandResponse<String> updateNotificationPreferences(UpdateNotificationPreferencesRntCommand command) throws Exception;
    CommandResponse<String> updatePrivacySettings(UpdatePrivacySettingsRntCommand command) throws Exception;
    CommandResponse<String> updateSecuritySettings(UpdateSecuritySettingsRntCommand command) throws Exception;
    CommandResponse<String> updateAppearanceSettings(UpdateAppearanceSettingsRntCommand command) throws Exception;
    CommandResponse<String> changePassword(ChangePasswordRntCommand command) throws Exception;
} 