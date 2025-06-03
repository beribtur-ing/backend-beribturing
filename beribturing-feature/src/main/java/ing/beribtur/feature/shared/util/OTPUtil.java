package ing.beribtur.feature.shared.util;

import java.security.SecureRandom;

public class OTPUtil {
    //
    private static final SecureRandom random = new SecureRandom();

    public static Integer generateOTP() {
        // Generates a 6-digit OTP
        return 100000 + random.nextInt(900000);
    }
}
