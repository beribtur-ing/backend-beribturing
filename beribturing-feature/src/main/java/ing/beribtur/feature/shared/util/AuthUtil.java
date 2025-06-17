package ing.beribtur.feature.shared.util;

import ing.beribtur.accent.context.SpaceContext;
import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import org.springframework.util.Assert;

public class AuthUtil {
    //
    public static void phoneNumberValidation(String phoneNumber) {
        // Validate phone number format
        Assert.hasText(phoneNumber, "phoneNumber must not be empty");
        Assert.isTrue(phoneNumber.matches("^998\\d{9}$"), "phoneNumber must be a valid number");
    }

    public static void emailValidation(String email) {
        // Validate email format
        Assert.hasText(email, "email must not be empty");
        Assert.isTrue(email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"), "email must be a valid email address");
    }

    public static void passwordValidation(String password) {
        // Validate password strength
        Assert.hasText(password, "password must not be empty");
        Assert.isTrue(password.length() >= 6, "password must be at least 6 characters long");
        // Contains at least one digit, one lowercase letter, one uppercase letter, and one special character
        Assert.isTrue(password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!.,]).{6,}$"),
                "password must contain at least one digit, one lowercase letter, one uppercase letter, and one special character");
    }

    public static String currentUserId(AccountLogic accountLogic) {
        //
        String phoneNumber = SpaceContext.get().getUsername();
        Account currentAccount = accountLogic.findByPhoneNumber(phoneNumber);
        String ownerId = currentAccount.getId();
        return ownerId;
    }
}
