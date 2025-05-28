package ing.beribtur.storejpa.aggregate.beribturing.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.admin.entity.SubscriptionPlan;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SUBSCRIPTION_PLAN")
public class SubscriptionPlanJpo extends DomainEntityJpo {
    //
    private String name;
    private String description;
    private BigDecimal monthlyFee;
    private int maxListings;
    private int maxConcurrentRentals;
    private boolean active;

    public SubscriptionPlanJpo(SubscriptionPlan subscriptionPlan) {
        //
        BeanUtils.copyProperties(subscriptionPlan, this);
    }

    public SubscriptionPlan toDomain() {
        //
        SubscriptionPlan subscriptionPlan = new SubscriptionPlan();
        BeanUtils.copyProperties(this, subscriptionPlan);
        return subscriptionPlan;
    }

    public static List<SubscriptionPlan> toDomains(List<SubscriptionPlanJpo> jpos) {
        //
        return jpos.stream().map(SubscriptionPlanJpo::toDomain).toList();
    }
}
