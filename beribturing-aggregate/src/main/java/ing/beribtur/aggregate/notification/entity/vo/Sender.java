package ing.beribtur.aggregate.notification.entity.vo;

import ing.beribtur.accent.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Sender implements ValueObject, Serializable {
    //
    private String id;
    private SenderType type;
}
