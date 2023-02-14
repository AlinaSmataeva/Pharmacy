package org.smataeva.finalproject.validation;

public class Validator {
    private Validator(){
    }
    public static boolean validateUsername(String username) {
        return (username != null && username.matches(ValidationReg.REG_USERNAME));
    }

    public static boolean validateAmount(String amount) {
        return (amount != null && amount.matches(ValidationReg.REG_AMOUNT));
    }

    public static boolean validatePhone(String phone) {
        return (phone != null && phone.matches(ValidationReg.REG_PHONE));
    }

    public static boolean validatePassword(String password) {
        return (password != null && password.matches(ValidationReg.REG_PASSWORD));
    }

    public static boolean validateEmail(String email) {
        return (email != null && email.matches(ValidationReg.REG_EMAIL));
    }
}
