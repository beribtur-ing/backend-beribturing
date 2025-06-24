package ing.beribtur.aggregate.notification.entity.vo;

import ing.beribtur.accent.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationMessage implements ValueObject, Serializable {
    //
    private String title;
    private String body;
    private Map<String, String> additionalInfo;
}
