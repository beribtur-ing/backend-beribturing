package ing.beribtur.aggregate.payment.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Currency {
    //
    private BigDecimal amount;
    private String currency;

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
