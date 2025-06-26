package ing.beribtur.config.security;

import ing.beribtur.accent.context.SpaceContext;
import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.store.AccountStore;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceLogic implements UserDetailsService {
    //
    private final AccountStore userAccountStore;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] usernameRole = username.split("-");
        Account user = userAccountStore.findByPhoneNumberAndRole(usernameRole[0], usernameRole[1]);
        if (user == null || !user.isEnabled()) {
            throw new UsernameNotFoundException(usernameRole[0]);
        }
        SpaceContext.get().setUsername(usernameRole[0]);
        return UserDetailsLogic.build(user);
    }
}
