package ing.beribtur.facade.api.feature.rnt.user.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.rnt.user.command.ModifyProfileRntCommand;
import org.springframework.web.multipart.MultipartFile;

public interface UserRntFlowFacade {
    CommandResponse<String> modifyProfile(ModifyProfileRntCommand command, MultipartFile image) throws Exception;
} 