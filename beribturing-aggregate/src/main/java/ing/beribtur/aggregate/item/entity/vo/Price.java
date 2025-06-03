package ing.beribtur.aggregate.item.entity.vo;


import ing.beribtur.accent.domain.ValueObject;
import ing.beribtur.aggregate.payment.entity.vo.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Price implements ValueObject {
    //
    private Currency currency;
    private PriceUnit unit; // HOURLY, DAILY, WEEKLY

    public static Price fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Price.class);
    }

    public static Price sample() {
        //
        return Price.builder()
                .currency(Currency.KRW)
                .unit(PriceUnit.HOURLY)
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
