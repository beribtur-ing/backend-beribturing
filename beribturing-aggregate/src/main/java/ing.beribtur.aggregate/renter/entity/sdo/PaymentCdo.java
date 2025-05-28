package ing.beribtur.aggregate.renter.entity.sdo;

import ing.beribtur.aggregate.renter.entity.Rental;
import ing.beribtur.aggregate.shared.entity.vo.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentCdo {
    //
    private Rental rental;
    private BigDecimal amount;
    private LocalDateTime paidAt;
    private PaymentStatus status;
    private String transactionId;
    private String paymentMethod;
}

