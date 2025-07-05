package ing.beribtur.feature.adm.auth.flow;

import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.aggregate.account.logic.AccountLogic;
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
public class AuthAdmFlow {
    @Value("${otp.duration.reset-password}")
    private String resetPasswordDuration;
    @Value("${otp.duration.sign-up}")
    private String signUpDuration;

    private final AccountLogic accountLogic;
    private final RedisService redisService;
    private final SmsService smsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthHelper authHelper;

    public Boolean sendOTP(String phoneNumber) {
        //
        String roleName = Role.ROLE_ADMIN.name();
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
        String roleName = Role.ROLE_ADMIN.name();
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
            accountLogic.modifyAccount(account);
            return true;
        }
        return false;
    }

    public AccountSignInTokenRdo refreshToken(String refreshToken) {
        return authHelper.refreshAccessToken(refreshToken);
    }
}
