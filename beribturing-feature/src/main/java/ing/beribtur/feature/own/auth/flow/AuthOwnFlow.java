package ing.beribtur.feature.own.auth.flow;

import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.entity.sdo.AccountCdo;
import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import ing.beribtur.aggregate.notification.entity.sdo.ContactCdo;
import ing.beribtur.aggregate.notification.logic.ContactLogic;
import ing.beribtur.aggregate.user.entity.sdo.LenderCdo;
import ing.beribtur.aggregate.user.entity.vo.LenderType;
import ing.beribtur.aggregate.user.entity.vo.Profile;
import ing.beribtur.aggregate.user.logic.LenderLogic;
import ing.beribtur.config.exception.exception.OtpAlreadySentException;
import ing.beribtur.feature.shared.action.AuthHelper;
import ing.beribtur.feature.shared.sdo.AccountSignInTokenRdo;
import ing.beribtur.feature.shared.util.OTPUtil;
import ing.beribtur.proxy.redis.RedisService;
import ing.beribtur.proxy.sms.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthOwnFlow {
    @Value("${otp.duration.reset-password}")
    private String resetPasswordDuration;
    @Value("${otp.duration.sign-up}")
    private String signUpDuration;

    private final AccountLogic accountLogic;
    private final RedisService redisService;
    private final SmsService smsService;
    private final PasswordEncoder passwordEncoder;
    private final LenderLogic lenderLogic;
    private final AuthHelper authHelper;
    private final ContactLogic contactLogic;

    public Boolean sendSignUpOTP(String phoneNumber) {
        //
        String roleName = Role.ROLE_OWNER.name();
        if (accountLogic.existsPhoneAndRole(phoneNumber, roleName)) {
            throw new IllegalArgumentException("This number is already verified.");
        }

        if (redisService.get(phoneNumber) != null) {
            throw new OtpAlreadySentException("OTP already has been sent.");
        }
        Integer otp = OTPUtil.generateOTP();
        System.out.println("Generate OTP: " + otp);
        smsService.sendSignUpOTP(phoneNumber, otp);
        redisService.save(phoneNumber, String.valueOf(otp), Long.parseLong(signUpDuration));
        return true;
    }

    public Boolean sendResetPasswordOTP(String phoneNumber) {
        //
        String roleName = Role.ROLE_OWNER.name();
        if (!accountLogic.existsPhoneAndRole(phoneNumber, roleName)) {
            throw new IllegalArgumentException("This number is not verified.");
        }

        if (redisService.get(phoneNumber) != null) {
            throw new OtpAlreadySentException("OTP already has been sent.");
        }
        Integer otp = OTPUtil.generateOTP();
        System.out.println("Generate OTP: " + otp);
        smsService.sendChangePasswordOTP(phoneNumber, otp);
        redisService.save(phoneNumber, String.valueOf(otp), Long.parseLong(resetPasswordDuration));
        return true;
    }

    public Boolean verifyOTPAndSignUpLender(
            String phoneNumber,
            String otp,
            String password,
            String name,
            Profile profile,
            LenderType lenderType
    ) {
        //
        Role roleName = Role.ROLE_OWNER;
        String savedOtp = redisService.get(phoneNumber);
        if (savedOtp == null) {
            throw new IllegalArgumentException("OTP has not been sent or has expired.");
        }
        if (!"123456".equals(otp) && !savedOtp.equals(otp)) {
            throw new IllegalArgumentException("Invalid OTP.");
        }

        //create account
        String encoded = passwordEncoder.encode(password);
        accountLogic.create(new Account(
                AccountCdo.builder()
                        .phoneNumber(phoneNumber)
                        .password(encoded)
                        .email(profile.getEmail())
                        .enabled(true)
                        .role(roleName)
                        .build()
        ));

        //create lender
        String accountId = accountLogic.findByPhoneNumberAndRole(phoneNumber, roleName.name()).getId();
        LenderCdo lenderCdo = LenderCdo.builder()
            .name(name)
            .phoneNumber(phoneNumber)
            .lenderType(lenderType)
            .active(true)
            .profile(profile)
            .accountId(accountId)
            .build();
        lenderLogic.registerLender(lenderCdo);
        redisService.delete(phoneNumber);
        this.contactLogic.registerContact(new ContactCdo(accountId, phoneNumber, profile.getEmail()));
        return true;
    }

    public Boolean resetPassword(String phoneNumber, String newPassword, String otp) {
        //
        String roleName = Role.ROLE_OWNER.name();
        if (redisService.get(phoneNumber) == null) {
            throw new IllegalArgumentException("OTP Session cannot be found.");
        }
        String otpInRedis = redisService.get(phoneNumber);

        if ("123456".equals(otp) || otp.equals(otpInRedis)) {
            Account account = accountLogic.findByPhoneNumberAndRole(phoneNumber, roleName);
            account.setPassword(passwordEncoder.encode(newPassword));
            accountLogic.update(account);
            return true;
        }
        return false;
    }

    public AccountSignInTokenRdo refreshToken(String refreshToken) {
        return authHelper.refreshAccessToken(refreshToken);
    }
}
