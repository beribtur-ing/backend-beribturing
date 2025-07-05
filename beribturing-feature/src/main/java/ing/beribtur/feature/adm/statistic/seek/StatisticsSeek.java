package ing.beribtur.feature.adm.statistic.seek;

import ing.beribtur.aggregate.account.logic.AccountLogic;
import ing.beribtur.aggregate.user.logic.LendeeLogic;
import ing.beribtur.aggregate.user.logic.LenderLogic;
import ing.beribtur.aggregate.item.logic.ProductLogic;
import ing.beribtur.aggregate.rental.logic.ReservationLogic;
import ing.beribtur.aggregate.payment.logic.TransactionLogic;
import ing.beribtur.aggregate.report.logic.ReportLogic;
import ing.beribtur.aggregate.rental.entity.vo.ReservationStatus;
import ing.beribtur.feature.adm.statistic.rdo.AdminOverviewRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class StatisticsSeek {
    //
    private final AccountLogic accountLogic;
    private final LendeeLogic lendeeLogic;
    private final LenderLogic lenderLogic;
    private final ProductLogic productLogic;
    private final ReservationLogic reservationLogic;
    private final TransactionLogic transactionLogic;
    private final ReportLogic reportLogic;

    public AdminOverviewRdo findOverallOverview() {
        //
        long totalLendees = lendeeLogic.countActiveUsers();
        long totalLenders = lenderLogic.countActiveUsers();
        long totalUsersCount = (totalLendees + totalLenders);

        int activeRentalsCount = (int) reservationLogic.countByStatus(ReservationStatus.Approved);

        BigDecimal totalRevenue = transactionLogic.calculateOverallRevenue();

        int pendingApprovalsCount = (int) reservationLogic.countByStatus(ReservationStatus.Pending);
        int totalPropertiesCount = (int) productLogic.countActiveProducts();
        int supportedTicketsCount = (int) reportLogic.countUnresolvedReports();

        return new AdminOverviewRdo(
                totalUsersCount,
                activeRentalsCount,
                totalRevenue,
                pendingApprovalsCount,
                totalPropertiesCount,
                supportedTicketsCount
        );
    }
}
