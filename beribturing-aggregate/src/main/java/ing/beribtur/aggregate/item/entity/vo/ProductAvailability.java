package ing.beribtur.aggregate.item.entity.vo;

import ing.beribtur.accent.domain.ValueObject;
import ing.beribtur.accent.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductAvailability implements ValueObject {
    //
    private LocalDateTime availableFrom;
    private LocalDateTime availableUntil;
    private List<DayOfWeek> availableDays;
}
