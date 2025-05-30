package ing.beribtur.config.security;

import ing.beribtur.accent.context.SpaceContext;
import ing.beribtur.aggregate.shared.entity.Account;
import ing.beribtur.aggregate.shared.store.AccountStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceLogic implements UserDetailsService {
    //
    @Autowired
    AccountStore userAccountStore;

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
