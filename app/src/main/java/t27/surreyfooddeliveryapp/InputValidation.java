package t27.surreyfooddeliveryapp;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by billxue on 2017-05-09.
 */

public class InputValidation {

    public static Boolean isValidEmail(CharSequence email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    public static Boolean isSamePassword(String password1, String password2) {
        if (password1.compareTo(password2) == 0)
            return true;
        return false;
    }

    public static Boolean isValidPhoneNumber(String number) {
        boolean isValid = false;

        if (!Pattern.matches("[a-zA-Z]+", number)) {
            if (number.length() != 10)
                return isValid;
            isValid = true;
        }
        return isValid;
    }

    public static Boolean isValidName(String name) {
        if (name.length() > 0)
            return true;
        return false;
    }

    public static Boolean isValidAddress(String address) {
        if (address.length() > 0)
            return true;
        return false;
    }

    public static Boolean isValidReadyTime(String time) {
        try {
            Integer.parseInt(time);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    
}
