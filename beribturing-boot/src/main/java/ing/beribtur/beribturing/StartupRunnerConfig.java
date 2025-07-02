package ing.beribtur.beribturing;

import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.entity.sdo.AccountCdo;
import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.aggregate.user.entity.sdo.LendeeCdo;
import ing.beribtur.aggregate.user.entity.sdo.LenderCdo;
import ing.beribtur.aggregate.user.entity.vo.LenderType;
import ing.beribtur.aggregate.user.logic.LendeeLogic;
import ing.beribtur.aggregate.user.logic.LenderLogic;
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
    public CommandLineRunner startupRunner(PasswordEncoder passwordEncoder, LenderLogic lenderLogic, LendeeLogic lendeeLogic) {
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
            if (!accountLogic.existsPhoneAndRole(number, Role.ROLE_OWNER.name())) {
                accountLogic.create(new Account(AccountCdo.builder()
                        .phoneNumber(number)
                        .password(passwordEncoder.encode("Qwerty12@"))
                        .email("email")
                        .role(Role.ROLE_OWNER)
                        .build()));

                lenderLogic.create(new Lender(
                        LenderCdo.builder()
                                .phoneNumber(number)
                                .name("Lender Name")
                                .lenderType(LenderType.Business)
                                .active(true)
                                .accountId(accountLogic.findByPhoneNumberAndRole(number, Role.ROLE_OWNER.name()).getId())
                                .build()
                ));
            }
            //renter
            if (!accountLogic.existsPhoneAndRole(number, Role.ROLE_RENTER.name())) {
                accountLogic.create(new Account(AccountCdo.builder()
                        .phoneNumber(number)
                        .password(passwordEncoder.encode("Qwerty12@"))
                        .email("email")
                        .role(Role.ROLE_RENTER)
                        .build()));

                lendeeLogic.create(new Lendee(
                        LendeeCdo.builder()
                                .name("Lendee Name")
                                .phoneNumber(number)
                                .active(true)
                                .accountId(accountLogic.findByPhoneNumberAndRole(number, Role.ROLE_RENTER.name()).getId())
                                .build()
                ));
            }
        };
    }
}
