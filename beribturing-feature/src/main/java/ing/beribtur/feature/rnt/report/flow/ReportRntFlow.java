package ing.beribtur.feature.rnt.report.flow;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.report.entity.sdo.ReportCdo;
import ing.beribtur.aggregate.report.logic.ReportLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportRntFlow {
    //
    private final ReportLogic reportLogic;

    public String registerReport(ReportCdo reportCdo) {
        //
        return reportLogic.registerReport(reportCdo);
    }

    public String modifyReport(String reportId, NameValueList nameValueList) {
        //
        reportLogic.modifyReport(reportId, nameValueList);
        return reportId;
    }

    public String removeReport(String reportId) {
        //
        reportLogic.removeReport(reportId);
        return reportId;
    }
}

