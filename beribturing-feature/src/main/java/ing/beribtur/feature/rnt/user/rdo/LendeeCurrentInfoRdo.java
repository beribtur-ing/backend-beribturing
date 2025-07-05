package ing.beribtur.feature.rnt.user.rdo;

public record LendeeCurrentInfoRdo(
        int totalPropertiesCount,
        int activeBookingsCount,
        double monthRevenue,
        int unReadMessageCount
) {

}
