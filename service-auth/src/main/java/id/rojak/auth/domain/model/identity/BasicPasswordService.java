package id.rojak.auth.domain.model.identity;

import id.rojak.auth.common.AssertionConcern;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by inagi on 8/1/17.
 */
@Component
public final class BasicPasswordService extends AssertionConcern
        implements PasswordService{

    private static final String DIGITS = "0123456789";
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int STRONG_THRESHOLD = 20;
    private static final String SYMBOLS = "\"`!?$?%^&*()_-+={[}]:;@'~#|\\<,>.?/";
    private static final int VERY_STRONG_THRESHOLD = 40;

    public BasicPasswordService() {
        super();
    }

    @Override
    public String generateStrongPassword() {
        String generatedPassword = null;

        StringBuffer password = new StringBuffer();

        Random random = new Random();

        boolean isStrong = false;

        int index = 0;

        while (!isStrong) {

            int opt = random.nextInt(4);

            switch (opt) {
                case 0:
                    index = random.nextInt(LETTERS.length());
                    password.append(LETTERS.substring(index, index+1));
                    break;
                case 1:
                    index = random.nextInt(LETTERS.length());
                    password.append(LETTERS.substring(index, index+1).toLowerCase());
                    break;
                case 2:
                    index = random.nextInt(DIGITS.length());
                    password.append(DIGITS.substring(index, index+1));
                    break;
                case 3:
                    index = random.nextInt(SYMBOLS.length());
                    password.append(SYMBOLS.substring(index, index+1));
                    break;
            }

            generatedPassword = password.toString();

            if (generatedPassword.length() >= 7) {
                isStrong = this.isStrong(generatedPassword);
            }
        }

        return generatedPassword;
    }

    @Override
    public boolean isStrong(String aPlainTextPassword) {
        return this.calculatePasswordStrength(aPlainTextPassword) >= STRONG_THRESHOLD;
    }

    @Override
    public boolean isVeryStrong(String aPlainTextPassword) {
        return this.calculatePasswordStrength(aPlainTextPassword) >= VERY_STRONG_THRESHOLD;
    }

    @Override
    public boolean isWeak(String aPlainTextPassword) {
        return this.calculatePasswordStrength(aPlainTextPassword) < STRONG_THRESHOLD;
    }

    @Override
    public int calculatePasswordStrength(String aPlainTextPassword) {
        this.assertArgumentNotNull(aPlainTextPassword, "Password strength cannot be tested on null.");

        int strength = 0;

        int length = aPlainTextPassword.length();

        if (length > 7) {
            strength += 10;
            // bonus: one point each additional
            strength += (length - 7);
        }

        int digitCount = 0;
        int letterCount = 0;
        int lowerCount = 0;
        int upperCount = 0;
        int symbolCount = 0;

        for (int idx = 0; idx < length; ++idx) {

            char ch = aPlainTextPassword.charAt(idx);

            if (Character.isLetter(ch)) {
                ++letterCount;
                if (Character.isUpperCase(ch)) {
                    ++upperCount;
                } else {
                    ++lowerCount;
                }
            } else if (Character.isDigit(ch)) {
                ++digitCount;
            } else {
                ++symbolCount;
            }
        }

        strength += (upperCount + lowerCount + symbolCount);

        // bonus: letters and digits
        if (letterCount >= 2 && digitCount >= 2) {
            strength += (letterCount + digitCount);
        }

        return strength;
    }
}
