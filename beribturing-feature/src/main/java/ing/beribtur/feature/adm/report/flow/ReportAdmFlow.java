package ing.beribtur.feature.adm.report.flow;

import ing.beribtur.aggregate.report.logic.ReportLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportAdmFlow {
    //
    private final ReportLogic reportLogic;

    public String resolve(String reportId) {
        //
        return reportLogic.resolve(reportId);
    }
}

