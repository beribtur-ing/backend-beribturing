package ing.beribtur.aggregate.renter.entity;


import ing.beribtur.aggregate.shared.entity.vo.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    //
    private Long id;
    private Rental rental;
    private BigDecimal amount;
    private LocalDateTime paidAt;
    private PaymentStatus status;
    private String transactionId;
    private String paymentMethod;
}

