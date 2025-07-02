package ing.beribtur.storejpa.aggregate.notification.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "FCM_TOKEN")
public class FcmTokenJpo extends DomainEntityJpo {
    //
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private ContactJpo contact;
}
