package ing.beribtur.facade.api.feature.adm.report.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.adm.report.command.ResolveReportAdmCommand;
import ing.beribtur.feature.adm.report.flow.ReportAdmFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/admin/report")
public class ReportAdmFlowResource implements ReportAdmFlowFacade {
    //
    private final ReportAdmFlow reportAdmFlow;

    @Override
    @PostMapping("/resolve-report/command")
    public CommandResponse<String> resolveReport(@RequestBody ResolveReportAdmCommand command) {
        //
        String reportId = command.getReportId();
        String entityId = reportAdmFlow.resolve(reportId);
        return new CommandResponse<>(entityId);
    }
}

