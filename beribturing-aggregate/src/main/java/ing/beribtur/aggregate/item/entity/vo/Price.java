package ing.beribtur.aggregate.item.entity.vo;


import ing.beribtur.accent.domain.ValueObject;
import ing.beribtur.aggregate.payment.entity.vo.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Price implements ValueObject {
    //
    private BigDecimal amount;
    private Currency currency;
    private PriceUnit unit; // HOURLY, DAILY, WEEKLY
}
