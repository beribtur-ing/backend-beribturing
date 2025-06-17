package ing.beribtur.beribturing;

import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.entity.sdo.AccountCdo;
import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StartupRunnerConfig {
    private final AccountLogic accountLogic;

    @Bean
    public CommandLineRunner startupRunner() {
        return args -> {
            accountLogic.create(new Account(AccountCdo.builder()
                    .phoneNumber("998935367303")
                    .password("Qwerty12@")
                    .email("email")
                    .role(Role.ROLE_ADMIN)
                    .build()));
        };
    }
}
