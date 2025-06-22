package ing.beribtur.facade.api.feature.adm.payment.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.adm.payment.command.*;
import ing.beribtur.feature.adm.payment.flow.DiscountAdmFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feature/admin/payment/discount")
@RequiredArgsConstructor
public class DiscountAdmFlowResource implements DiscountAdmFlowFacade {
    private final DiscountAdmFlow discountAdmFlow;

    @Override
    @PostMapping("/register/command")
    public CommandResponse<String> registerDiscount(@RequestBody RegisterDiscountAdmCommand command) {
        command.validate();
        String discountId = discountAdmFlow.registerDiscount(command.getDiscountCdo());
        return new CommandResponse<>(discountId);
    }

    @Override
    @PostMapping("/register-multiple/command")
    public CommandResponse<List<String>> registerDiscounts(@RequestBody List<RegisterDiscountAdmCommand> commands) {
        commands.forEach(RegisterDiscountAdmCommand::validate);
        List<String> discountIds = discountAdmFlow.registerDiscounts(
            commands.stream()
                .map(RegisterDiscountAdmCommand::getDiscountCdo)
                .collect(Collectors.toList())
        );
        return new CommandResponse<>(discountIds);
    }

    @Override
    @PostMapping("/modify/command")
    public CommandResponse<Void> modifyDiscount(@RequestBody ModifyDiscountAdmCommand command) {
        command.validate();
        discountAdmFlow.modifyDiscount(command.getDiscountId(), command.getNameValues());
        return new CommandResponse<>();
    }

    @Override
    @PostMapping("/activate/command")
    public CommandResponse<Void> activateDiscount(@RequestBody ActivateDiscountAdmCommand command) {
        command.validate();
        discountAdmFlow.activateDiscount(command.getDiscountId());
        return new CommandResponse<>();
    }

    @Override
    @PostMapping("/deactivate/command")
    public CommandResponse<Void> deactivateDiscount(@RequestBody DeactivateDiscountAdmCommand command) {
        command.validate();
        discountAdmFlow.deactivateDiscount(command.getDiscountId());
        return new CommandResponse<>();
    }

    @Override
    @PostMapping("/remove/command")
    public CommandResponse<Void> removeDiscount(@RequestBody RemoveDiscountAdmCommand command) {
        command.validate();
        discountAdmFlow.removeDiscount(command.getDiscountId());
        return new CommandResponse<>();
    }
} 