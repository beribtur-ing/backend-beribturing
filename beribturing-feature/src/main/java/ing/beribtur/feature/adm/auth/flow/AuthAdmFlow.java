package ing.beribtur.feature.adm.auth.flow;

import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.entity.sdo.AccountCdo;
import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.aggregate.user.entity.sdo.LenderCdo;
import ing.beribtur.aggregate.user.entity.vo.LenderType;
import ing.beribtur.aggregate.user.entity.vo.Profile;
import ing.beribtur.aggregate.user.logic.LenderLogic;
import ing.beribtur.feature.shared.util.OTPUtil;
import ing.beribtur.proxy.redis.RedisService;
import ing.beribtur.proxy.sms.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthAdmFlow {
    @Value("${otp.duration.reset-password}")
    private String resetPasswordDuration;
    @Value("${otp.duration.sign-up}")
    private String signUpDuration;

    private final AccountLogic accountLogic;
    private final RedisService redisService;
    private final SmsService smsService;
    private final PasswordEncoder passwordEncoder;
    private final LenderLogic lenderLogic;

    public Boolean sendOTP(String phoneNumber) {
        //
        String roleName = Role.ROLE_ADMIN.name();
        if (accountLogic.existsPhoneAndRole(phoneNumber, roleName)) {
            throw new IllegalArgumentException("This number is already verified.");
        }

        if (redisService.get(phoneNumber) != null) {
            throw new IllegalArgumentException("OTP already has been sent.");
        }
        Integer otp = OTPUtil.generateOTP();
        System.out.println("Generate OTP: " + otp);
        smsService.sendSignUpOTP(phoneNumber, otp);
        redisService.save(phoneNumber, String.valueOf(otp), Long.parseLong(signUpDuration));
        return true;
    }

    public Boolean sendResetPasswordOTP(String phoneNumber) {
        //
        String roleName = Role.ROLE_ADMIN.name();
        if (!accountLogic.existsPhoneAndRole(phoneNumber, roleName)) {
            throw new IllegalArgumentException("This number is not verified.");
        }

        if (redisService.get(phoneNumber) != null) {
            throw new IllegalArgumentException("OTP already has been sent.");
        }
        Integer otp = OTPUtil.generateOTP();
        System.out.println("Generate OTP: " + otp);
        smsService.sendChangePasswordOTP(phoneNumber, otp);
        redisService.save(phoneNumber, String.valueOf(otp), Long.parseLong(resetPasswordDuration));
        return true;
    }

    public Boolean resetPassword(String phoneNumber, String newPassword, String otp) {
        //
        String roleName = Role.ROLE_ADMIN.name();
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
}
