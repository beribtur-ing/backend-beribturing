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


    public static ProductAvailability fromJson(String json) {
        //
        return JsonUtil.fromJson(json, ProductAvailability.class);
    }

    public static ProductAvailability sample() {
        //
        return ProductAvailability.builder()
                .availableFrom(LocalDateTime.now())
                .availableUntil(LocalDateTime.now().plusDays(30))
                .availableDays(List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY))
                .build();
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }

    @Override
    public String toString() {
        //
        return toJson();
    }
}
