package ing.beribtur.storejpa.aggregate.account.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.entity.vo.NotificationPreferences;
import ing.beribtur.aggregate.account.entity.vo.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
        name = "ACCOUNT",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"phoneNumber", "role"})
        }
)
public class AccountJpo extends DomainEntityJpo implements UserDetails {

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    private String email;

    @Column(nullable = false)
    private String role;

    private boolean enabled = true;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    
    @Column(columnDefinition = "TEXT")
    private String notificationPreferences;


    public AccountJpo(Account account) {
        //
        BeanUtils.copyProperties(account, this);
        this.role = account.getRole().name();
        this.setNotificationPreferencesFromDomain(account.getNotificationPreferences());
    }

    public Account toDomain() {
        //
        Account account = new Account();
        BeanUtils.copyProperties(this, account);
        account.setRole(Role.valueOf(role));
        account.setNotificationPreferences(getNotificationPreferencesAsDomain());
        return account;
    }

    public static List<Account> toDomains(List<AccountJpo> jpos) {
        //
        return jpos.stream().map(AccountJpo::toDomain).toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
    private void setNotificationPreferencesFromDomain(NotificationPreferences preferences) {
        if (preferences != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                this.notificationPreferences = mapper.writeValueAsString(preferences);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to serialize notification preferences", e);
            }
        }
    }
    
    private NotificationPreferences getNotificationPreferencesAsDomain() {
        if (notificationPreferences == null || notificationPreferences.isEmpty()) {
            return NotificationPreferences.createDefault(Role.valueOf(role));
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(notificationPreferences, NotificationPreferences.class);
        } catch (JsonProcessingException e) {
            return NotificationPreferences.createDefault(Role.valueOf(role));
        }
    }
}