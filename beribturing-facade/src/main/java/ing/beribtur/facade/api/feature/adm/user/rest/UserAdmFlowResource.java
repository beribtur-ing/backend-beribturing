package ing.beribtur.facade.api.feature.adm.user.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.adm.user.command.ChangePasswordAdmCommand;
import ing.beribtur.feature.adm.user.flow.UserAdmFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature/admin/user")
@RequiredArgsConstructor
public class UserAdmFlowResource implements UserAdmFlowFacade {
    //
    private final UserAdmFlow userAdmFlow;

    @Override
    @PostMapping("/change-password/command")
    public CommandResponse<String> changePassword(@RequestBody ChangePasswordAdmCommand command) throws Exception {
        command.validate();
        
        String entityId = userAdmFlow.changePassword(
            command.getCurrentPassword(),
            command.getNewPassword()
        );
        return new CommandResponse<>(entityId);
    }
}