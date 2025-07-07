package ing.beribtur.facade.api.feature.adm.lendee.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.adm.lendee.command.ModifyLendeeStatusCommand;
import ing.beribtur.feature.adm.lendee.flow.LendeeAdmFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature/admin/lendee")
@RequiredArgsConstructor
public class LendeeAdmFlowResource implements LendeeAdmFlowFacade {
    //
    private final LendeeAdmFlow lendeeAdmFlow;

    @Override
    @PostMapping("/modify-lendee-status/command")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommandResponse<String> modifyLendeeStatus(ModifyLendeeStatusCommand command) {
        //
        command.validate();
        String lendeeId = lendeeAdmFlow.modifyLendeeStatus(command.getLendeeId());
        return new CommandResponse<>(lendeeId);
    }
}
