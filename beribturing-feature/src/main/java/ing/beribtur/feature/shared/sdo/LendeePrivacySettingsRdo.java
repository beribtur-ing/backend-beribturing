package ing.beribtur.feature.shared.sdo;

import ing.beribtur.aggregate.user.entity.vo.LendeePrivacySettings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LendeePrivacySettingsRdo {
    private ProfileVisibilityRdo profileVisibility;
    private DataAndLocationRdo dataAndLocation;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProfileVisibilityRdo {
        private boolean showProfileToOtherUsers;
        private boolean showRentalHistory;
        private boolean showReviews;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataAndLocationRdo {
        private boolean shareLocationWithLenders;
        private boolean allowDataCollectionForRecommendations;
    }

    public static LendeePrivacySettingsRdo from(LendeePrivacySettings privacySettings) {
        if (privacySettings == null) {
            return null;
        }
        
        return new LendeePrivacySettingsRdo(
            new ProfileVisibilityRdo(
                privacySettings.getProfileVisibility().isShowProfileToOtherUsers(),
                privacySettings.getProfileVisibility().isShowRentalHistory(),
                privacySettings.getProfileVisibility().isShowReviews()
            ),
            new DataAndLocationRdo(
                privacySettings.getDataAndLocation().isShareLocationWithLenders(),
                privacySettings.getDataAndLocation().isAllowDataCollectionForRecommendations()
            )
        );
    }
}