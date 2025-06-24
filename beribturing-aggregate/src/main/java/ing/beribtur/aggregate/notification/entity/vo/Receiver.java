package ing.beribtur.aggregate.notification.entity.vo;

import ing.beribtur.accent.domain.ValueObject;
import ing.beribtur.aggregate.notification.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Receiver implements ValueObject, Serializable {
    //
    private String userId;
    private Contact contact;
}
