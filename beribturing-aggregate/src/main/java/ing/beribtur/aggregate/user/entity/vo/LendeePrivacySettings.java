package ing.beribtur.aggregate.user.entity.vo;

import ing.beribtur.accent.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LendeePrivacySettings implements ValueObject {
    private ProfileVisibility profileVisibility;
    private DataAndLocation dataAndLocation;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProfileVisibility {
        private boolean showProfileToOtherUsers;
        private boolean showRentalHistory;
        private boolean showReviews;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataAndLocation {
        private boolean shareLocationWithLenders;
        private boolean allowDataCollectionForRecommendations;
    }

    public static LendeePrivacySettings getDefaultSettings() {
        return new LendeePrivacySettings(
            new ProfileVisibility(true, true, true),
            new DataAndLocation(false, true)
        );
    }
}