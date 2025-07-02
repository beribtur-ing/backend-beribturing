package ing.beribtur.aggregate.account.entity.vo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OwnerNotificationPreferences.class, name = "OWNER"),
        @JsonSubTypes.Type(value = RenterNotificationPreferences.class, name = "RENTER")
})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class NotificationPreferences {

    protected String type;

    public static NotificationPreferences createDefault(Role role) {
        return switch (role) {
            case ROLE_OWNER -> OwnerNotificationPreferences.createDefault();
            case ROLE_RENTER -> RenterNotificationPreferences.createDefault();
            case ROLE_ADMIN -> AdminNotificationPreferences.createDefault();
            default -> throw new IllegalArgumentException("Unsupported role: " + role);
        };
    }
}