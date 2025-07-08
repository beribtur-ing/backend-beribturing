package ing.beribtur.facade.api.feature.adm.lender.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.adm.lender.command.ModifyLenderStatusCommand;
import ing.beribtur.feature.adm.lender.flow.LenderAdmFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature/admin/lender")
@RequiredArgsConstructor
public class LenderAdmFlowResource implements LenderAdmFlowFacade {
    //
    private final LenderAdmFlow lenderAdmFlow;

    @Override
    @PostMapping("/modify-lender-status/command")
    public CommandResponse<String> modifyLenderStatus(@RequestBody ModifyLenderStatusCommand command) {
        //
        command.validate();
        String lenderId = lenderAdmFlow.modifyLenderStatus(command.getLenderId());
        return new CommandResponse<>(lenderId);
    }
}
