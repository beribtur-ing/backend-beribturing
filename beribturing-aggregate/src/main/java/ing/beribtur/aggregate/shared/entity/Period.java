package ing.beribtur.aggregate.shared.entity;

import ing.beribtur.accent.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Period implements ValueObject {
    //
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
