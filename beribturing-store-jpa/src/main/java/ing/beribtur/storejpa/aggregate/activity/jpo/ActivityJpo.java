package ing.beribtur.storejpa.aggregate.activity.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.activity.entity.Activity;
import ing.beribtur.aggregate.activity.entity.vo.ActivityType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ACTIVITY")
public class ActivityJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String type;

    private String description;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    private String relatedEntityId;

    public ActivityJpo(Activity activity) {
        //
        BeanUtils.copyProperties(activity, this);
        this.type = activity.getType().name();
    }

    public Activity toDomain() {
        //
        Activity activity = new Activity();
        BeanUtils.copyProperties(this, activity);
        activity.setType(ActivityType.valueOf(type));
        return activity;
    }

    public static List<Activity> toDomains(List<ActivityJpo> jpos) {
        //
        return jpos.stream().map(ActivityJpo::toDomain).toList();
    }
}