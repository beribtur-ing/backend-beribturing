package ing.beribtur.aggregate.payment.entity.vo;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Currency {
    //
    private final BigDecimal amount;
    private final String currency;

    public Currency(BigDecimal amount, String currency) {
        //
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
        this.amount = amount;
        this.currency = currency;
    }

    public Currency percentage(double rate) {
        //
        return new Currency(this.amount.multiply(BigDecimal.valueOf(rate)), currency);
    }

    public Currency subtract(Currency other) {
        //
        return new Currency(this.amount.subtract(other.amount), currency);
    }
}
