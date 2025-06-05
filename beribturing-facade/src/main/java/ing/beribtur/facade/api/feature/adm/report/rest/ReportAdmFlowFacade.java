package ing.beribtur.facade.api.feature.adm.report.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.adm.report.command.ResolveReportAdmCommand;

public interface ReportAdmFlowFacade {
    //
    CommandResponse<String> resolveReport(ResolveReportAdmCommand command);
}
