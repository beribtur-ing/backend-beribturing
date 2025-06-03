package ing.beribtur.storejpa.aggregate.user.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.entity.vo.Gender;
import ing.beribtur.aggregate.user.entity.vo.GeoLocation;
import ing.beribtur.aggregate.user.entity.vo.Profile;
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

    // Flattened Profile fields
    private String gender;      // Enum name (e.g., Male, Female)
    private String email;
    private String address;
    private String avatarUrl;

    // Flattened GeoLocation fields
    private Double latitude;
    private Double longitude;

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
        return lendee;
    }

    public static List<Lendee> toDomains(List<LendeeJpo> jpos) {
        return jpos.stream().map(LendeeJpo::toDomain).toList();
    }
}

