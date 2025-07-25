package ing.beribtur.storejpa.aggregate.user.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.entity.vo.LendeeNotificationPreferences;
import ing.beribtur.aggregate.user.entity.vo.LendeePrivacySettings;
import ing.beribtur.aggregate.user.entity.vo.LendeeSecuritySettings;
import ing.beribtur.aggregate.user.entity.vo.LendeeAppearanceSettings;
import ing.beribtur.aggregate.user.entity.vo.Gender;
import ing.beribtur.aggregate.user.entity.vo.GeoLocation;
import ing.beribtur.aggregate.user.entity.vo.Profile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "LENDEE")
public class LendeeJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private long reservationSequence;

    // Flattened Profile fields
    private String gender;      // Enum name (e.g., Male, Female)
    private String email;
    private String address;
    @Column(columnDefinition = "varchar(500)")
    private String avatarUrl;

    // Flattened GeoLocation fields
    private Double latitude;
    private Double longitude;

    @Column(columnDefinition = "TEXT")
    private String notificationPreferences;

    @Column(columnDefinition = "TEXT")
    private String privacySettings;

    @Column(columnDefinition = "TEXT")
    private String securitySettings;

    @Column(columnDefinition = "TEXT")
    private String appearanceSettings;

    public LendeeJpo(Lendee lendee) {
        super(lendee);
        BeanUtils.copyProperties(lendee, this);

        if (lendee.getProfile() != null) {
            Profile profile = lendee.getProfile();

            if (profile.getGender() != null) {
                this.gender = profile.getGender().name();
            }

            this.email = profile.getEmail();
            this.address = profile.getAddress();
            this.avatarUrl = profile.getAvatarUrl();

            if (profile.getLocation() != null) {
                this.latitude = profile.getLocation().getLatitude();
                this.longitude = profile.getLocation().getLongitude();
            }
        }

        this.setNotificationPreferencesFromDomain(lendee.getNotificationPreferences());
        this.setPrivacySettingsFromDomain(lendee.getPrivacySettings());
        this.setSecuritySettingsFromDomain(lendee.getSecuritySettings());
        this.setAppearanceSettingsFromDomain(lendee.getAppearanceSettings());
    }

    public Lendee toDomain() {
        Lendee lendee = new Lendee();
        BeanUtils.copyProperties(this, lendee);

        Profile profile = new Profile();

        if (this.gender != null) {
            profile.setGender(Gender.valueOf(this.gender));
        }

        profile.setEmail(this.email);
        profile.setAddress(this.address);
        profile.setAvatarUrl(this.avatarUrl);

        if (this.latitude != null && this.longitude != null) {
            GeoLocation location = new GeoLocation();
            location.setLatitude(this.latitude);
            location.setLongitude(this.longitude);
            profile.setLocation(location);
        }

        lendee.setProfile(profile);
        lendee.setNotificationPreferences(getNotificationPreferencesAsDomain());
        lendee.setPrivacySettings(getPrivacySettingsAsDomain());
        lendee.setSecuritySettings(getSecuritySettingsAsDomain());
        lendee.setAppearanceSettings(getAppearanceSettingsAsDomain());
        return lendee;
    }

    public static List<Lendee> toDomains(List<LendeeJpo> jpos) {
        return jpos.stream().map(LendeeJpo::toDomain).toList();
    }

    private void setNotificationPreferencesFromDomain(LendeeNotificationPreferences preferences) {
        if (preferences != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                this.notificationPreferences = mapper.writeValueAsString(preferences);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to serialize notification preferences", e);
            }
        }
    }

    private LendeeNotificationPreferences getNotificationPreferencesAsDomain() {
        if (notificationPreferences == null || notificationPreferences.isEmpty()) {
            return LendeeNotificationPreferences.createDefault();
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(notificationPreferences, LendeeNotificationPreferences.class);
        } catch (JsonProcessingException e) {
            return LendeeNotificationPreferences.createDefault();
        }
    }

    private void setPrivacySettingsFromDomain(LendeePrivacySettings settings) {
        if (settings != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                this.privacySettings = mapper.writeValueAsString(settings);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to serialize privacy settings", e);
            }
        }
    }

    private LendeePrivacySettings getPrivacySettingsAsDomain() {
        if (privacySettings == null || privacySettings.isEmpty()) {
            return LendeePrivacySettings.getDefaultSettings();
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(privacySettings, LendeePrivacySettings.class);
        } catch (JsonProcessingException e) {
            return LendeePrivacySettings.getDefaultSettings();
        }
    }

    private void setSecuritySettingsFromDomain(LendeeSecuritySettings settings) {
        if (settings != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                this.securitySettings = mapper.writeValueAsString(settings);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to serialize security settings", e);
            }
        }
    }

    private LendeeSecuritySettings getSecuritySettingsAsDomain() {
        if (securitySettings == null || securitySettings.isEmpty()) {
            return LendeeSecuritySettings.getDefaultSettings();
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(securitySettings, LendeeSecuritySettings.class);
        } catch (JsonProcessingException e) {
            return LendeeSecuritySettings.getDefaultSettings();
        }
    }

    private void setAppearanceSettingsFromDomain(LendeeAppearanceSettings settings) {
        if (settings != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                this.appearanceSettings = mapper.writeValueAsString(settings);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to serialize appearance settings", e);
            }
        }
    }

    private LendeeAppearanceSettings getAppearanceSettingsAsDomain() {
        if (appearanceSettings == null || appearanceSettings.isEmpty()) {
            return LendeeAppearanceSettings.getDefaultSettings();
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(appearanceSettings, LendeeAppearanceSettings.class);
        } catch (JsonProcessingException e) {
            return LendeeAppearanceSettings.getDefaultSettings();
        }
    }
}

