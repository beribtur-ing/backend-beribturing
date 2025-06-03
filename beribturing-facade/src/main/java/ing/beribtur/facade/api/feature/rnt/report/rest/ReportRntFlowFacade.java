package ing.beribtur.facade.api.feature.rnt.report.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.rnt.report.command.ModifyReportRntCommand;
import ing.beribtur.facade.api.feature.rnt.report.command.RegisterReportRntCommand;
import ing.beribtur.facade.api.feature.rnt.report.command.RemoveReportRntCommand;

public interface ReportRntFlowFacade {
    //
    CommandResponse<String> registerReport(RegisterReportRntCommand command);
    CommandResponse<String> modifyReport(ModifyReportRntCommand command);
    CommandResponse<String> removeReport(RemoveReportRntCommand command);
}
