package ing.beribtur.aggregate.activity.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.activity.entity.sdo.ActivityCdo;
import ing.beribtur.aggregate.activity.entity.vo.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Activity extends DomainEntity {
    //
    private String userId;
    private ActivityType type;
    private String description;
    private LocalDateTime timestamp;
    private String relatedEntityId;

    public Activity(ActivityCdo activityCdo) {
        //
        super(activityCdo.genId());
        BeanUtils.copyProperties(activityCdo, this);
    }

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        //
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName().trim()) {
                case "userId" -> this.userId = value;
                case "type" -> this.type = ActivityType.valueOf(value);
                case "description" -> this.description = value;
                case "timestamp" -> this.timestamp = LocalDateTime.parse(value);
                case "relatedEntityId" -> this.relatedEntityId = value;
                default -> throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }
}
