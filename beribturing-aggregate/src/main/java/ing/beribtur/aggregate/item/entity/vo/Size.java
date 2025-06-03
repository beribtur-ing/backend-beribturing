package ing.beribtur.aggregate.item.entity.vo;

import ing.beribtur.accent.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Size implements ValueObject {
    //
    private String label;        // Optional: S, M, L, XL, etc.
    private Double width;        // in cm or inches
    private Double height;
    private Double depth;
    private Double weight;       // in kg
    private String measureUnit;  // metric or imperial

    public static Size fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Size.class);
    }

    public static Size sample() {
        //
        return Size.builder()
                .label("My Sample Size")
                .width(100.0)
                .height(100.0)
                .depth(100.0)
                .weight(100.0)
                .measureUnit("metric")
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
