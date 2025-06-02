package ing.beribtur.storejpa.aggregate.payment.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.payment.entity.Discount;
import ing.beribtur.aggregate.payment.entity.vo.DiscountScope;
import ing.beribtur.aggregate.payment.entity.vo.DiscountType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "DISCOUNT")
public class DiscountJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String type;
    
    @Column(nullable = false)
    private BigDecimal amount;
    
    @Column(nullable = false)
    private String scope;
    
    private String targetId;
    
    private LocalDateTime startDate;
    
    private LocalDateTime endDate;
    
    private boolean active;

    public DiscountJpo(Discount discount) {
        //
        super(discount);
        BeanUtils.copyProperties(discount, this);
        
        // Handle enum conversions
        if (discount.getType() != null) {
            this.type = discount.getType().name();
        }
        
        if (discount.getScope() != null) {
            this.scope = discount.getScope().name();
        }
    }

    public Discount toDomain() {
        //
        Discount discount = new Discount();
        BeanUtils.copyProperties(this, discount);
        
        // Convert strings back to enums
        if (this.type != null) {
            discount.setType(DiscountType.valueOf(this.type));
        }
        
        if (this.scope != null) {
            discount.setScope(DiscountScope.valueOf(this.scope));
        }
        
        return discount;
    }

    public static List<Discount> toDomains(List<DiscountJpo> jpos) {
        //
        return jpos.stream().map(DiscountJpo::toDomain).toList();
    }
}