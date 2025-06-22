package ing.beribtur.facade.api.feature.own.user.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.own.user.command.ModifyProfileOwnCommand;
import org.springframework.web.multipart.MultipartFile;

public interface UserOwnFlowFacade {
    CommandResponse<String> modifyProfile(ModifyProfileOwnCommand command, MultipartFile image) throws Exception;
}
