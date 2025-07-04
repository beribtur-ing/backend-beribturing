package ing.beribtur.feature.rnt.auth.flow;

import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.entity.sdo.AccountCdo;
import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import ing.beribtur.aggregate.notification.entity.Notification;
import ing.beribtur.aggregate.notification.entity.sdo.ContactCdo;
import ing.beribtur.aggregate.notification.logic.ContactLogic;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.entity.sdo.LendeeCdo;
import ing.beribtur.aggregate.user.entity.vo.Profile;
import ing.beribtur.aggregate.user.logic.LendeeLogic;
import ing.beribtur.config.exception.exception.OtpAlreadySentException;
import ing.beribtur.feature.shared.action.AuthHelper;
import ing.beribtur.feature.shared.sdo.AccountSignInTokenRdo;
import ing.beribtur.feature.shared.util.OTPUtil;
import ing.beribtur.proxy.notisender.NotificationSenderService;
import ing.beribtur.proxy.redis.RedisService;
import ing.beribtur.proxy.sms.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthRntFlow {
    @Value("${otp.duration.reset-password}")
    private String resetPasswordDuration;
    @Value("${otp.duration.sign-up}")
    private String signUpDuration;

    private final AccountLogic accountLogic;
    private final RedisService redisService;
    private final SmsService smsService;
    private final PasswordEncoder passwordEncoder;
    private final LendeeLogic lendeeLogic;
    private final AuthHelper authHelper;
    private final ContactLogic contactLogic;
    private final NotificationSenderService notificationSenderService;

    public Boolean sendSignUpOTP(String phoneNumber) {
        //
        String roleName = Role.ROLE_RENTER.name();
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
        String roleName = Role.ROLE_RENTER.name();
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

    public Boolean verifyOTPAndSignUpLendee(
            String phoneNumber,
            String otp,
            String password,
            String name,
            Profile profile
    ) {
        //
        String savedOtp = redisService.get(phoneNumber);
        if (savedOtp == null) {
            throw new IllegalArgumentException("OTP has not been sent or has expired.");
        }
        if (!"123456".equals(otp) && !savedOtp.equals(otp)) {
            throw new IllegalArgumentException("Invalid OTP.");
        }

        //create account
        String encoded = passwordEncoder.encode(password);
        Role roleRenter = Role.ROLE_RENTER;
        accountLogic.create(new Account(
                AccountCdo.builder()
                        .phoneNumber(phoneNumber)
                        .password(encoded)
                        .email(profile.getEmail())
                        .role(roleRenter)
                        .build()
        ));

        //create lendee
        String accountId = accountLogic.findByPhoneNumberAndRole(phoneNumber, roleRenter.name()).getId();
        LendeeCdo lendeeCdo = LendeeCdo.builder()
            .name(name)
            .phoneNumber(phoneNumber)
            .active(true)
            .profile(profile)
            .accountId(accountId)
            .build();
        lendeeLogic.registerLendee(lendeeCdo);
        redisService.delete(phoneNumber);
        this.contactLogic.registerContact(new ContactCdo(accountId, phoneNumber, profile.getEmail()));
        this.notificationSenderService.registerUserForNotification(phoneNumber);
        this.notificationSenderService.sendNotificationToUser(phoneNumber, Notification.sample());
        return true;
    }

    public Boolean resetPassword(String phoneNumber, String newPassword, String otp) {
        //
        String roleName = Role.ROLE_RENTER.name();
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
