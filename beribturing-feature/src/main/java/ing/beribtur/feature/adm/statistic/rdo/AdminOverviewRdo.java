package ing.beribtur.feature.adm.statistic.rdo;

import java.math.BigDecimal;

public record AdminOverviewRdo(
        long totalUsersCount,
        long activeRentalsCount,
        BigDecimal totalRevenue,
        int pendingApprovalsCount,
        int totalPropertiesCount,
        int supportedTicketsCount
) {
}
