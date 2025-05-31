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
        Account user = userAccountStore.findByPhoneNumber(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        SpaceContext.get().setUsername(username);
        return UserDetailsLogic.build(user);
    }
}
