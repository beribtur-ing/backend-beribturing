package ing.beribtur.facade.api.feature.rnt.report.rest;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.aggregate.report.entity.sdo.ReportCdo;
import ing.beribtur.facade.api.feature.rnt.report.command.ModifyReportRntCommand;
import ing.beribtur.facade.api.feature.rnt.report.command.RegisterReportRntCommand;
import ing.beribtur.facade.api.feature.rnt.report.command.RemoveReportRntCommand;
import ing.beribtur.feature.rnt.report.flow.ReportRntFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/renter/report")
public class ReportRntFlowResource implements ReportRntFlowFacade {
    //
    private final ReportRntFlow reportRntFlow;

    @Override
    @PostMapping("/register-report/command")
    public CommandResponse<String> registerReport(@RequestBody RegisterReportRntCommand command) {
        //
        command.validate();
        ReportCdo reportCdo = command.getReportCdo();
        String entityId = reportRntFlow.registerReport(reportCdo);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/modify-report/command")
    public CommandResponse<String> modifyReport(@RequestBody ModifyReportRntCommand command) {
        //
        command.validate();
        String reportId = command.getReportId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = reportRntFlow.modifyReport(reportId, nameValueList);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/remove-report/command")
    public CommandResponse<String> removeReport(@RequestBody RemoveReportRntCommand command) {
        //
        command.validate();
        String reportId = command.getReportId();
        String entityId = reportRntFlow.removeReport(reportId);
        return new CommandResponse<>(entityId);
    }
}

