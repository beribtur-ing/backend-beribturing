package ing.beribtur.storejpa.aggregate.user.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.aggregate.user.entity.vo.Gender;
import ing.beribtur.aggregate.user.entity.vo.GeoLocation;
import ing.beribtur.aggregate.user.entity.vo.LenderType;
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
@Table(name = "LENDER")
public class LenderJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String lenderType; // store enum name as String

    @Column(nullable = false)
    private boolean active;

    // Flattened Profile fields
    private String gender;    // Gender enum name
    private String email;
    private String address;
    private String avatarUrl;

    // Flattened GeoLocation fields
    private Double latitude;
    private Double longitude;

    private long productSequence;

    public LenderJpo(Lender lender) {
        super(lender);
        BeanUtils.copyProperties(lender, this);

        if (lender.getLenderType() != null) {
            this.lenderType = lender.getLenderType().name();
        }

        if (lender.getProfile() != null) {
            Profile profile = lender.getProfile();

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

    public Lender toDomain() {
        Lender lender = new Lender();
        BeanUtils.copyProperties(this, lender);

        if (this.lenderType != null) {
            lender.setLenderType(LenderType.valueOf(this.lenderType));
        }

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

        lender.setProfile(profile);

        return lender;
    }

    public static List<Lender> toDomains(List<LenderJpo> jpos) {
        return jpos.stream().map(LenderJpo::toDomain).toList();
    }
}

