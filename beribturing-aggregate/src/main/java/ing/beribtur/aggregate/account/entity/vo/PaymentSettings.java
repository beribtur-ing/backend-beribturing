package ing.beribtur.aggregate.account.entity.vo;

import ing.beribtur.accent.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSettings implements ValueObject {
    //
    private int commissionRate;
    private String defaultCurrency;
    private String publicKey;
    private boolean enableAutomaticPayouts;
}
