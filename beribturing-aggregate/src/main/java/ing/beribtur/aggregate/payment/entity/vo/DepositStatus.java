package ing.beribtur.aggregate.payment.entity.vo;

public enum DepositStatus {
    //
    HELD,             // Platform is holding deposit
    REFUNDABLE,       // Approved for refund
    REFUNDED,         // Sent back to lendee
    REVIEW_NEEDED,    // Flagged for manual review (e.g., damage)
    WITHHELD,          // Retained by lender or platform
}
