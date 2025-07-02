package ing.beribtur.beribturing;

import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.entity.sdo.AccountCdo;
import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class StartupRunnerConfig {
    private final AccountLogic accountLogic;

    @Bean
    public CommandLineRunner startupRunner(PasswordEncoder passwordEncoder) {
        return args -> {
            String number = "998935367303";
            Role role = Role.ROLE_ADMIN;
            if (!accountLogic.existsPhoneAndRole(number, role.name())) {
                accountLogic.create(new Account(AccountCdo.builder()
                        .phoneNumber(number)
                        .password(passwordEncoder.encode("Qwerty12@"))
                        .email("email")
                        .role(role)
                        .build()));
            }

            //owner
            if(!accountLogic.existsPhoneAndRole(number, Role.ROLE_OWNER.name())) {
                accountLogic.create(new Account(AccountCdo.builder()
                        .phoneNumber(number)
                        .password(passwordEncoder.encode("Qwerty12@"))
                        .email("email")
                        .role(Role.ROLE_OWNER)
                        .build()));
            }
            //renter
            if (!accountLogic.existsPhoneAndRole(number, Role.ROLE_RENTER.name())) {
                accountLogic.create(new Account(AccountCdo.builder()
                        .phoneNumber(number)
                        .password(passwordEncoder.encode("Qwerty12@"))
                        .email("email")
                        .role(Role.ROLE_RENTER)
                        .build()));
            }
        };
    }
}
