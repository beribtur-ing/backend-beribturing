package ing.beribtur.aggregate.activity.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.activity.entity.vo.ActivityType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityCdo extends CreationDataObject {
    private String userId;
    private ActivityType type;
    private String description;
    private LocalDateTime timestamp;
    private String relatedEntityId;
}